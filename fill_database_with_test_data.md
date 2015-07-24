# Fill the Database with Test Data
To keep this tutorial simple we do not develop a sophisticated user interface to draw rooms, walls, doors, windows etc. We  fill the database with static data of a sample apartment instead.

Execute the following steps:
* Inside of your Eclipse project *RobotWorldModel* create a new package *startApplications*.
* Download the file FillDB.java
* Import this file into the package *startApplications*.
* Adapt the path to the database folder in ```OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld", "admin", "admin")```.
* If you like you can edit FillDB.java and add some additional locations, objects or positions.
* Finally run FillDB.

Let's take a brief look at the program:

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

There are several possibilities to add new vertices to the database using the **addVertex method**. You can add a new empty vertex first and then set its properties:

```java
Vertex myLocation = db.addVertex("class:Location");
myObject.setProperty("Name", "Sophia's room");
myObject.setProperty("Description", "Bedroom of Sophia");
db.commit()
```

Or you can create a vertex with all its properties in one step. This option is used in FillDB:

```java
db.addVertex("class:Location", "Name", "Sophia's room", "Description", "Bedroom of Sophia");
db.commit();
```

The first parameter specifies the subclass of V. The following parameters are pairs of property-name and value.

The last possibility is to gather all information inside of a property-value map and use this map as second parameter of addVertex:

```java
Map<String,Object> props = new HashMap<String,Object>();
props.put("Name", "Sophia's room");
props.put("Description", "Bedroom of Sophia");
Vertex myObject = db.addVertex("class:Object", props);
db.commit();
```

Instead of Blueprint's addVertex method you could also use SQL to create a new vertex:

```java
db.command(new OCommandSQL ("INSERT INTO Location (Name, Description) VALUES ('Sophia's room','Bedroom of Sophia')")).execute();
```



