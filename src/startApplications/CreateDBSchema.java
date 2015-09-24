package startApplications;

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
		OrientGraphFactory factory = new OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld");
		OrientGraphNoTx db = factory.getNoTx();

		/* 3 dimensional vector as abstract class
		 * used as position and as size for objects.
		 */
		db.command(new OCommandSQL ("create class Coordinate extends V ABSTRACT")).execute(); 
		db.command(new OCommandSQL ("create Property Coordinate.x FLOAT")).execute();
		db.command(new OCommandSQL ("alter property Coordinate.x MANDATORY true")).execute();
		db.command(new OCommandSQL ("alter property Coordinate.x NOTNULL true")).execute();
		db.command(new OCommandSQL ("create Property Coordinate.y FLOAT")).execute();
		db.command(new OCommandSQL ("alter property Coordinate.y MANDATORY true")).execute();
		db.command(new OCommandSQL ("alter property Coordinate.y NOTNULL true")).execute();
		db.command(new OCommandSQL ("create Property Coordinate.z FLOAT")).execute();
		db.command(new OCommandSQL ("alter property Coordinate.z MANDATORY true")).execute();
		db.command(new OCommandSQL ("alter property Coordinate.z NOTNULL true")).execute();
		OClass coordinate = db.getRawGraph().getMetadata().getSchema().getClass("Coordinate");
	
		OrientVertexType namedVertex = db.createVertexType("NamedVertex");
		namedVertex.createProperty("Name", OType.STRING).setMandatory(true).setNotNull(true);
		namedVertex.createProperty("Description", OType.STRING);
		
		OrientVertexType locationConcept = db.createVertexType("LocationConcept", "NamedVertex");
		OrientVertexType location = db.createVertexType("Location", "NamedVertex");
		OrientVertexType position = db.createVertexType("Position", coordinate); // Coordinate used as position
		position.createProperty("inLocation", OType.LINK, location); 
		location.createProperty("Shape", OType.LINKLIST, position); // Shape of the location as polygon stored as a list of positions
		
		/* Location instance IS_A Location concept e.g. kitchen of Mr. Smith IS_A kitchen,
		 * Location concept IS_A other Location concept e.g. kitchen IS_A room,
		 * Object instance IS_A Object concept e.g. red cup with the little crack IS_A cup
		 * Object concept IS_A other Object concept e.g. cup IS_A tableware
		 */
		OrientEdgeType is_a = db.createEdgeType("IS_A");
		
		/* Location instance IS_PART_OF other Location instance e.g. kitchen of Mr. Smith IS_PART_OF flat of Mr. Smith
		 * Location concept IS_PART_OF other Location concept e.g. room IS_PART_OF flat
		 * Object instance IS_PART_OF other Object instance e.g. left top drawer IS_PART_OF big cupboard next to door
		 * Object concept IS_PART_OF other Object concept e.g. drawer IS_PART_OF kitchen cabinent
		 */
		OrientEdgeType is_part_of = db.createEdgeType("IS_PART_OF");
		
		/* Location instance IS_CONNECTED_TO other Location instance which IS_A Connector
		 * One Position IS_CONNECTED_TO another position; this means that the robot stores an estimated time to get from position 1 to position 2.
		 * Principally the robot can navigate autonomously from any position to another position in the same room.
		 */
		OrientEdgeType is_connected_to = db.createEdgeType("IS_CONNECTED_TO");
		
		OrientVertexType objectConcept = db.createVertexType("ObjectConcept", namedVertex); // Object types like cup, plate, table, ...
	OrientVertexType mobileObject = db.createVertexType("MobileObject", namedVertex);
    // Real mobile objects like the red cup with the little crack
    mobileObject.createProperty("Path_to_Image", OType.STRING); // Path to image file
    OrientVertexType size3D = db.createVertexType("Size3D", coordinate);
    // The abstract class coordinate is used to store sizes of objects
    mobileObject.createProperty("Size", OType.EMBEDDED, size3D); // Size of a mobile Object
    // with x > y and z = height in the object's default position
    
    /* Sort of probability (score) that a mobile object is at a certain position
     * or at another object. The Score value is between 0 and 10. However the sum of all
     * scores is not fixed as it would be with probabilities. Scores are only compared.
     */
    OrientEdgeType prob_is_at = db.createEdgeType("PROB_IS_AT");
    prob_is_at.createProperty("Score", OType.FLOAT).setMin("0").setMandatory(true).setNotNull(true);
    prob_is_at.createProperty("Hight", OType.FLOAT); // Height above floor for robot's linear drive

		
		db.shutdown();
		factory.close();
	}
}
