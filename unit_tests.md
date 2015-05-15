# Unit Tests
After establishing the schema you can try to store some data in your database. The schema helps to avoid storing wrong or inconsistent data. You can test this behaviour with unit tests.

In your Eclipse project RobotWorldModel execute the following steps:
* Add a new package with name *tests*.
* Add the library *JUnit4* to your project. To do this go to  
*Project > Properties > Java Build Path*  
and there to the Libraries tab. Click on *Add Library*, choose *JUnit* and then *JUnit4*.
* In the tests' package add a new Java class with name *LocationTests*.
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
		Vertex pos = db.addVertex("class:Position2D");
		pos.setProperty("y", 100);
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


