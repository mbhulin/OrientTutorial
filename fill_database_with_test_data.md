# Fill the Database with Test Data
To keep this tutorial simple we do not develop a sophisticated user interface to draw rooms, walls, doors, windows etc. We  fill the database with static data of a sample apartment instead.

Execute the following steps:
* Inside of your Eclipse project *RobotWorldModel* create a new package *startApplications*.
* Download the file FillDB.java
* Import this file into the package *startApplications*.
* Take a look at the program

```java
public class FillDB {

	public static void main(String[] args) {
		OrientGraphFactory factory = new OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld", "admin", "admin");
		OrientGraph db = factory.getTx();

		//Create some vertices of LocationConcept with connecting edges
		Vertex livingRoom = db.addVertex("class:LocationConcept", "Name", "living room", "Description", "Room to talk to other people, to read, to watch TV, ...");
		Vertex diningRoom = db.addVertex("class:LocationConcept", "Name", "dining room", "Description", "Room to eat typically with a table and chairs");
		Vertex kitchen = db.addVertex("class:LocationConcept", "Name", "kitchen", "Description", "Room for cooking and to prepare meals");
		...
		db.addEdge("class:IS_A", door, connector, "IS_A");
		db.addEdge("class:IS_A", livingRoom, room, "IS_A");
		db.addEdge("class:IS_A", diningRoom, room, "IS_A");
```

After establishing the connection to the database - again in plocal mode - some vertices of the subclass
* If you like you can edit FillDB.java and add some additional locations, objects or positions.
* Start the OrientDB Server on your computer. This is explained in the OrientDB documentation in section [Run the Server](http://orientdb.com/docs/last/orientdb.wiki/Tutorial-Run-the-server.html)
* Run FillDB
