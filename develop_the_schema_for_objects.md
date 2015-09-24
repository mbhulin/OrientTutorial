# Develop the Schema for Objects
If you prefer to watch the next steps as screencast video click on the video start page.

<a href="EclipseRobotWorldModel3.mp4
" target="_blank"><img src="StartScreencastVideo.jpg"
alt="Eclipse Video" width="200" height="30" border="10" /></a>

While the *location* class stores all immobile objects we need an **object class** to store mobile objects. Of course the border between immobile locations and mobile objects is fluent. A chair will often be moved to other positions, a table may be moved a little bit after cleaning the floor and a heavy wardrobe normally isn't moved at all. If you want to read again about the details of mobile objects go back to the chapter [Motivation](motivation.md#Objects-and-Object-Concepts).

Let's define the classes for mobile objects and object concepts. Add to the main method in our CreateDBSchema class:

```java
OrientVertexType objectConcept = db.createVertexType("ObjectConcept", namedVertex); // Object types like cup, plate, table, ...
OrientVertexType mobileObject = db.createVertexType("MobileObject", namedVertex); // Real Objects like the red cup with the little crack
mobileObject.createProperty("Path_to_Image", OType.STRING); // Path to image file
```

For each mobile object we want to store its size as surrounding cuboid with length, width and height. To connect these three dimensions we can reuse the *Coordinates* class. Since *Coordinates* is an abstract class first a concrete subclass has to be created: *Size3D*. Each mobile object then gets an embedded *Size* property of type *Size3D*.

```java
OrientVertexType size3D = db.createVertexType("Size3D", coordinate); // The abstract class coordinate is used to store sizes of objects
mobileObject.createProperty("Size", OType.EMBEDDED, size3D); // Size of an object with x > y and z = height in the object's default position

```

At last we define the edge classes. Since we already have defined the IS_A and IS_PART_OF edge classes for locations we only need to add the edge class **PROB_IS_AT** which stores the probability of an object being at a location, position or in/on/at another object. Therefore this edge class has some sort of **probability** as a property. In fact not a probability but a **score** is stored. If we used a probability we would have to guarantee that the sum of all probabilities for a mobile object is 1 when one probability is changed. This could be a time consuming operation. In contrast score values can be changed independently. Additionally a **height** may be stored as information at which level an object can be found e. g. in a cupboard or on a shelf.

```java
OrientEdgeType prob_is_at = db.createEdgeType("PROB_IS_AT");
prob_is_at.createProperty("Score", OType.FLOAT).setMin("0").setMandatory(true).setNotNull(true);
prob_is_at.createProperty("Height", OType.FLOAT); // Height above floor for robot's linear drive
```

Finally the database connection is closed.

```java
db.shutdown();
factory.close();
```

Now you can execute your program. Afterwords you can control the created schema using [Studio](http://orientdb.com/docs/last/Home-page.html) or [Console](http://orientdb.com/docs/last/Console-Commands.html).

