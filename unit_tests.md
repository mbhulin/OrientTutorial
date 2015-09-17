# Unit Tests
After establishing the schema you can try to store some data in your database. The schema helps to avoid storing wrong or inconsistent data. You can test this behaviour with *unit tests*.

In your Eclipse project *RobotWorldModel* execute the following steps:
* Add a new package with name *tests*.
* Add the library *JUnit4* to your project. To do this go to  
*Project > Properties > Java Build Path*  
and there to the *Libraries* tab. Click on *Add Library*, choose *JUnit* and then *JUnit4*.
* In the tests package add a new Java class with name *LocationTests*.
* Add a static method ``setupBeforeClass()``. This method is executed before all tests and hence is used to prepare a proper environment for the tests. The connection to the database is established and all data in the class *Location* are deleted. Notice: This time a *remote connection* to the database is used. Hence the server must be running when you execute the tests.

```java
public class LocationTests {
	private static OrientGraph db;
	private static OrientGraphFactory factory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new OrientGraphFactory("remote:localhost/RobotWorld1", "admin", "admin"); // The OrientDB server must be running
		db = factory.getTx(); // Connect to the database
		
		db.command(new OCommandSQL ("delete vertex Location")).execute(); // Delete all Location vertices in the database
	}
```

* Add a static method ``teardownAfterClass()``. This method is executed after all tests and is used to cleanup the environment. The database connection is shut down and the factory is closed.

```java
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		db.shutdown();
		factory.close();
	}

```

* Add a test where you try to store a *Position2D* without the x-coordinate. This should fail because x is mandatory.

```java
	@Test
	public void testPositionWithoutX() {
		String errorMessage = "";
		Vertex pos = db.addVertex("class:Position");
		pos.setProperty("y", 100);
		pos.setProperty("z", 0);
		try {
			db.commit();
		} catch (Exception e) {
			db.rollback();
			errorMessage = e.getMessage();
		}
		Assert.assertTrue(errorMessage.contains("x' is mandatory"));
	}

```

* Add a test where you try to store a *Location* with *Name* set to NULL. In this test method SQL is used. Again OrientDB should not store this location.

```java
	@Test
	public void testLocationWithoutName () {
		String errorMessage = "";
		try {
			db.command(new OCommandSQL ("insert into Location (Name, Description) values (NULL, 'A location without name')")).execute();
			db.commit();
		} catch (Exception e) {
			db.rollback();
			errorMessage = e.getMessage();
		}
		Assert.assertTrue(errorMessage.contains("Name' cannot be null"));
	}
```

* Add a test where you try to store a correctly constructed *Location* together with its shape. This Location should be stored in the database.

```java
	@Test
	public void testLocationOK () {
		long nrLocationsBefore = db.countVertices("Location");
		String errorMessage = "No exception";
		Vertex pos1 = db.addVertex("class:Position", "x", 0, "y", 0, "z", 0);
		Vertex pos2 = db.addVertex("class:Position", "x", 1000, "y", 0, "z", 0);
		Vertex pos3 = db.addVertex("class:Position", "x", 1000, "y", 500, "z", 0);
		Vertex pos4 = db.addVertex("class:Position", "x", 0, "y", 500, "z", 0);
		
		ArrayList <Vertex> shape = new ArrayList <Vertex> ();
		shape.add(pos1);
		shape.add(pos2);
		shape.add(pos3);
		shape.add(pos4);
		
		Vertex newLocation = db.addVertex("class:Location");
		newLocation.setProperty("Name", "Extra Room");
		newLocation.setProperty("Description", "This room is not needed. It is an extra room.");
		newLocation.setProperty("shape", shape);

		try {
			db.commit();
		} catch (Exception e) {
			db.rollback();
			errorMessage = e.getMessage();
		}
		long nrLocationsAfter = db.countVertices("Location");
		Assert.assertEquals("No exception", errorMessage);
		Assert.assertEquals(nrLocationsBefore + 1, nrLocationsAfter);
		db.removeVertex(pos1);
		db.removeVertex(pos2);
		db.removeVertex(pos3);
		db.removeVertex(pos4);
		db.removeVertex(newLocation);
		db.commit();
	}
```

* Start the [OrientDB server](http://orientdb.com/docs/last/Tutorial-Run-the-server.html). Then run your test class. To do this right click on *LocationTests.java* in the Eclipse package explorer. In the pop up menu choose *Run As* > *JUnit Test*. All tests should succeed.

You can download both Java files, *CreateDBSchema.java* and *LocationTests.java*, as [ZIP-Archive here](RobotWorldModel_V1.zip).
