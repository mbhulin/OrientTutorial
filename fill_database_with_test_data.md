# Fill the Database with Test Data
To keep this tutorial simple we do not develop a sophisticated user interface to draw rooms, walls, doors, windows etc. We  fill the database with static data of a sample apartment instead.

## Execute the Following Steps

* Download the file [FillDB.java](./src/startApplications/FillDB.java)
* Import this file into the package *startApplications*.
* Adapt the path to the database folder ``OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld", "admin", "admin")``
* If you like you can edit FillDB.java and add some additional locations, objects or positions.
* Finally run FillDB.

## Take a Brief Look at the Program


```java
public class FillDB {

	public static void main(String[] args) {
		OrientGraphFactory factory = new OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld", "admin", "admin");
		OrientGraph db = factory.getTx();

		// Clear the database: delete all edges and vertices
		for (Edge e: db.getEdges()) db.removeEdge(e);
		for (Vertex v: db.getVertices()) db.removeVertex(v);
		db.commit();

		//Create some vertices of LocationConcept with connecting edges
		Vertex livingRoom = db.addVertex("class:LocationConcept", "Name", "living room", "Description", "Room to talk to other people, to read, to watch TV, ...");
		Vertex diningRoom = db.addVertex("class:LocationConcept", "Name", "dining room", "Description", "Room to eat typically with a table and chairs");
		Vertex kitchen = db.addVertex("class:LocationConcept", "Name", "kitchen", "Description", "Room for cooking and to prepare meals");
		...
		db.addEdge("class:IS_A", door, connector, "IS_A");
		db.addEdge("class:IS_A", livingRoom, room, "IS_A");
		db.addEdge("class:IS_A", diningRoom, room, "IS_A");
		...
    }
}
```

After establishing the connection to the database - again in plocal mode - first all existing data are deleted. Then some vertices and edges are created.

## Add Vertices to Database
There are several possibilities to add new vertices to the database using the ``addVertex()`` method [(see OrientDB Documentation for details)](http://orientdb.com/docs/last/Graph-Database-Tinkerpop.html).

### Create an empty Vertex first, then set property values
You can add a new empty vertex first and then set its properties:

```java
Vertex myLocation = db.addVertex("class:Location");
myObject.setProperty("Name", "Sophia's room");
myObject.setProperty("Description", "Bedroom of Sophia");
db.commit()
```

### Create a Vertex together with its property values
Or you can create a vertex with all its properties in one step. This option is used in FillDB:

```java
db.addVertex("class:Location", "Name", "Sophia's room", "Description", "Bedroom of Sophia");
db.commit();
```

The first parameter specifies the subclass of V. The following parameters are pairs of property-name and value.

### Collect all property values in a Map
The last possibility is to gather all information inside of a property-value map and use this map as second parameter of addVertex:

```java
Map<String,Object> props = new HashMap<String,Object>();
props.put("Name", "Sophia's room");
props.put("Description", "Bedroom of Sophia");
Vertex myObject = db.addVertex("class:Object", props);
db.commit();
```

### Create Vertex using SQL
Instead of Blueprint's addVertex method you could use SQL to create a new vertex:

```java
db.command(new OCommandSQL ("INSERT INTO Location (Name, Description) VALUES ('Sophia's room','Bedroom of Sophia')")).execute();
```

### Add an Embedded Document
In our database for service robots mobile objects have a size which consists of three dimensions: x, y and z. Since the property **Size3D** is not a linked vertex but embedded inside of the object vertex it has to be created as a **document** first, using the [Document API](http://orientdb.com/docs/last/Document-Database.html):

```java
ODocument sizeTable = new ODocument ("Size3D");
sizeTable.field("x", 100);
sizeTable.field("y", 100);
sizeTable.field("z", 85);
Vertex tableI = db.addVertex("class:Object", "Name", "dining table", "Description", "My circular dining table", "Size", sizeTable);
```

### Add a Linked List
Locations have a Shape property which consists of a list of positions. To store a location with its shape you have to store the positions first, then create an ArrayList of positions, and finally use this list as parameter in the ``addVertex()`` method:

```java
Vertex p1 = db.addVertex("class:Position", "x", 400, "y", 200, "z", 0);
Vertex p2 = db.addVertex("class:Position", "x", 600, "y", 200, "z", 0);
Vertex p3 = db.addVertex("class:Position", "x", 800, "y", 200, "z", 0);
Vertex p4 = db.addVertex("class:Position", "x", 1050, "y", 200, "z", 0);
ArrayList <Vertex> shapeSleepingRoom = new ArrayList <Vertex> ();
shapeSleepingRoom.add(p1);
shapeSleepingRoom.add(p2);
shapeSleepingRoom.add(p3);
shapeSleepingRoom.add(p4);
Vertex mySleepingRoom = db.addVertex("class:Location", "Name", "sleeping room", "Description", "Sleeping room of Mr. Miller", "Shape", shapeSleepingRoom);
```

### Add an Edge
To create an edge use the ``addEdge()`` method and provide the vertices to connect as parameters. If you do not use light weight edges you can add properties to edges.

```java
Edge passTime1 = db.addEdge(null, posTable2, doorSleepM, "IS_CONNECTED_TO");
passTime1.setProperty("PassTimeSec", 10);
```

Notice that the first parameter is not necessary in OrientDB but you can provide the subclass of E as first parameter instead of null:

```java
Edge passTime1 = db.addEdge("class:IS_CONNECTED_TO", posTable2, doorSleepM, "IS_CONNECTED_TO");
```
