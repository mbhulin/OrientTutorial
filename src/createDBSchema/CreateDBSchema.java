package createDBSchema;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;

public class CreateDBSchema {

	public static void main(String[] args) {
		// Create new database
		OrientGraphFactory factory = new OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld1");
		OrientGraphNoTx db = factory.getNoTx();

		OrientVertexType position2D = db.createVertexType("Position2D");
		position2D.createProperty ("x", OType.INTEGER).setMandatory (true).setNotNull (true);
		position2D.createProperty("y", OType.INTEGER).setMandatory (true).setNotNull (true);
		
		OrientVertexType namedVertex = db.createVertexType("NamedVertex");
		namedVertex.createProperty("Name", OType.STRING).setMandatory(true).setNotNull(true);
		namedVertex.createProperty("Description", OType.STRING);
		
		OrientVertexType locationConcept = db.createVertexType("LocationConcept", "NamedVertex");
		OrientVertexType location = db.createVertexType("Location", "NamedVertex");
		location.createProperty("Shape", OType.LINKLIST, position2D);
		
		OrientEdgeType is_a = db.createEdgeType("IS_A");
		OrientEdgeType is_part_of = db.createEdgeType("IS_PART_OF");
		
		db.command(new OCommandSQL ("create class Size3D")).execute();
		db.command(new OCommandSQL ("create Property Size3D.Length INTEGER")).execute(); // Length in cm
		db.command(new OCommandSQL ("alter property Size3D.Length MANDATORY true")).execute();
		db.command(new OCommandSQL ("alter property Size3D.Length NOTNULL true")).execute();
		db.command(new OCommandSQL ("create Property Size3D.Width INTEGER")).execute(); // Width in cm
		db.command(new OCommandSQL ("alter property Size3D.Width MANDATORY true")).execute();
		db.command(new OCommandSQL ("alter property Size3D.Width NOTNULL true")).execute();
		db.command(new OCommandSQL ("create Property Size3D.Hight INTEGER")).execute(); // Hight in cm
		db.command(new OCommandSQL ("alter property Size3D.Hight MANDATORY true")).execute();
		db.command(new OCommandSQL ("alter property Size3D.Hight NOTNULL true")).execute();

		OrientVertexType objectConcept = db.createVertexType("ObjectConcept", namedVertex); // Abstract Objects
		OrientVertexType object = db.createVertexType("Object", namedVertex); // Real Objects
		object.createProperty("Path_to_Image", OType.STRING); // Path to image file
		OClass size3D = db.getRawGraph ().getMetadata().getSchema().getClass("Size3D");
		object.createProperty("Size", OType.EMBEDDED, size3D);
		
		OrientEdgeType prob_is_at = db.createEdgeType("PROB_IS_AT");
		prob_is_at.createProperty("Score", OType.INTEGER).setMin("0").setMandatory(true).setNotNull(true);
		prob_is_at.createProperty("Hight", OType.INTEGER); // Hight above floor for robot's linear drive
		
		db.shutdown();
		factory.close();
	}
}
