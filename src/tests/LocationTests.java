package tests;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class LocationTests {
	private static OrientGraph db;
	private static OrientGraphFactory factory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new OrientGraphFactory("remote:localhost/RobotWorld", "admin", "admin"); // The OrientDB server must be running
		db = factory.getTx(); // Connect to the database
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		db.shutdown();
		factory.close();
	}

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
}
