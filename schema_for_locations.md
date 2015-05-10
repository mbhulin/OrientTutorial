# Develop the Schema for Locations
![Location Hierarchy](LocationHierarchy.JPG)

Locations relevant for the robot are rooms, floors, houses, even outdoor locations like a yard or a street. Hence we have to define a vertex class **Location** as subclass of V.

Each *location* needs a **name**. The persons attended by the service robot use the name to refer to the location. The name need not be unique because a location can be identified by the containing location. A command to the robot could e.g. be: "Bring this bottle of juice to the *kitchen of Mr. Millers apartement*".

To move inside of a room a robot must get the information about the **shape** of the room. A *shape* can be stored as a list of **positions** where each *position* is a pair of x- and y-coordinates.

To store a *shape* we have several options in OrientDB:
* We can connect a *location vertex* with some position vertices by an edge type "has_corner". ![Location connected to corners](LocationPositionGraph1.png) We need an ordered list of edges for a location vertex because the sequence of the positions is relevant for the shape. Otherwise the same set of corners could define different shapes: ![two shapes](twoShapes.png)
* We can connect a *location vertex* with one *position vertex* by a "first_corner" edge. This position vertex is then connected to another position vertex by a "next-position" edge. ![Location connected to corners](LocationPositionGraph2.png)
* OrientDB is not only a graph database but also obeyes to the object oriented paradigm. Therefore a third alternative is possible using aggregation: Each location has a *shape* property which is of type *LINKLIST* or *EMBEDDEDLIST* of positions. Use *EMBEDDEDLIST* if the positions can only exist as a part of a location, use *LINKLIST* if the positions can be used for other purposes.

Let's briefly discuss the different alternatives. In the first and second alternative positions and locations are different, stand alone entities connected by a relationship. If the location is deleted the positions remain in the database. They could be used for other puposes e.g. because objects are located at these positions. The second alternative has the disadvantage that after deletion of a location the corresponding positions are still connected by the *next_corner* relationship. The application which deletes a location has to delete the corresponding *next_corner*-edges.

The third alternative connects locations and positions more tightly. Locations are the main entities and the positions forming the shape of the location are a part of the location. However the positions can exist without a location; they get an independant object id.

The last alternative is similar to the third one but the positions are embedded in the location object. If a location is deleted they are automatically deleted, too.

In this tutorial we will use the third alternative because *LINKLIST* is an interesting special feature of OrientDB. Since positions can also be used to indicate the position of *objects* we don't embed them into locations.

## Create the Vertex Class "Location" using the Java API
Remember our small Java program with the Java class *CreateDBSchema*. Up to now it consists only of two instructions which create the database "RobotWorld". We now add instructions which create a new vertex class "Location". This is straight forward using the createVertexType() method. Then we use the createProperty() method to create the properties "Name" and "Description" of datatype String.

```java
OrientVertexType location = db.createVertexType("Location");
location.createProperty("Name", OType.STRING).setMandatory(true).setNotNull(true);
location.createProperty("Description", OType.STRING);
```
The result of ``createProperty()`` is of type ``OrientVertexProperty``. Therefore the method ``.setMandatory(true)`` can be applied. Again the result is a property object. So the ``.setNotNull(true)`` method can be applied in the same line. This means that each location object must have a Name property and this Name property must not be null.