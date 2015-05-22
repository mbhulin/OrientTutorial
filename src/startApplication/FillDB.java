package startApplication;

import java.util.ArrayList;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

/**
 * Fills the database RobotWorld with some test data
 * @author hulin
 *
 */
public class FillDB {

	public static void main(String[] args) {
		OrientGraphFactory factory = new OrientGraphFactory("plocal:C:/orientdb/databases/RobotWorld", "admin", "admin");
		OrientGraph db = factory.getTx();

		//Create some vertices of LocationConcept with connecting edges
		Vertex livingRoom = db.addVertex("class:LocationConcept", "Name", "living room", "Description", "Room to talk to other people, to read, to watch TV, ...");
		Vertex diningRoom = db.addVertex("class:LocationConcept", "Name", "dining room", "Description", "Room to eat typically with a table and chairs");
		Vertex kitchen = db.addVertex("class:LocationConcept", "Name", "kitchen", "Description", "Room for cooking and to prepare meals");
		Vertex bathroom = db.addVertex("class:LocationConcept", "Name", "bathroom", "Description", "Room to wash typically with a basin a bath tube or a shower.");
		Vertex sleepingRoom = db.addVertex("class:LocationConcept", "Name", "sleeping room", "Description", "Room to sleep typically with a bed and a wardrobe");		
		Vertex corridor = db.addVertex("class:LocationConcept", "Name", "corridor", "Description", "Room to connect other rooms or entrance hall of a building");
		Vertex floor = db.addVertex("class:LocationConcept", "Name", "floor", "Description", "All rooms on one level build a floor");
		Vertex room = db.addVertex("class:LocationConcept", "Name", "room", "Description", "Any room inside of a building");
		Vertex house = db.addVertex("class:LocationConcept", "Name", "house", "Description", "Special building where people live");
		Vertex apartment = db.addVertex("class:LocationConcept", "Name", "apartment", "Description", "Apartment in a house");
		Vertex building = db.addVertex("class:LocationConcept", "Name", "building", "Description", "A house or hall or church, something with a roof on it");
		db.addEdge("class:IS_A", livingRoom, room, "IS_A");
		db.addEdge("class:IS_A", diningRoom, room, "IS_A");
		db.addEdge("class:IS_A", kitchen, room, "IS_A");
		db.addEdge("class:IS_A", bathroom, room, "IS_A");
		db.addEdge("class:IS_A", sleepingRoom, room, "IS_A");
		db.addEdge("class:IS_A", corridor, room, "IS_A");
		db.addEdge(null, house, building, "IS_A");
		db.addEdge(null, room, floor, "IS_PART_OF");
		db.addEdge(null, floor, apartment, "IS_PART_OF");
		db.addEdge(null, apartment, house, "IS_PART_OF");
		db.commit();
		
		//Create some vertices Position2D which are used for locations
		Vertex p1 = db.addVertex("class:Position2D", "x", 400, "y", 0);
		Vertex p2 = db.addVertex("class:Position2D", "x", 600, "y", 0);
		Vertex p3 = db.addVertex("class:Position2D", "x", 800, "y", 0);
		Vertex p4 = db.addVertex("class:Position2D", "x", 1050, "y", 0);
		Vertex p5 = db.addVertex("class:Position2D", "x", 0, "y", 250);
		Vertex p6 = db.addVertex("class:Position2D", "x", 400, "y", 250);
		Vertex p7 = db.addVertex("class:Position2D", "x", 600, "y", 250);
		Vertex p8 = db.addVertex("class:Position2D", "x", 800, "y", 250);
		Vertex p9 = db.addVertex("class:Position2D", "x", 1050, "y", 250);
		Vertex p10 = db.addVertex("class:Position2D", "x", 0, "y", 600);
		Vertex p11 = db.addVertex("class:Position2D", "x", 400, "y", 600);
		Vertex p12 = db.addVertex("class:Position2D", "x", 1200, "y", 600);
		Vertex o1 = db.addVertex("class:Position2D", "x", 0, "y", 0);
		Vertex o2 = db.addVertex("class:Position2D", "x", 1200, "y", 0);
		Vertex o3 = db.addVertex("class:Position2D", "x", 1200, "y", 200);
		Vertex o4 = db.addVertex("class:Position2D", "x", 400, "y", 200);
		Vertex o5 = db.addVertex("class:Position2D", "x", 0, "y", 450);
		Vertex o6 = db.addVertex("class:Position2D", "x", 400, "y", 450);
		Vertex doorBath = db.addVertex("class:Position2D", "x", 600, "y", 200);
		Vertex doorKitchen = db.addVertex("class:Position2D", "x", 1000, "y", 250);
		Vertex doorSleep = db.addVertex("class:Position2D", "x", 400, "y", 300);
		Vertex doorEntrance = db.addVertex("class:Position2D", "x", 4500, "y", 0);
		Vertex doorEntranceOut = db.addVertex("class:Position2D", "x", 4500, "y", 200);
		
		//Define the polygons of the locations
		ArrayList <Vertex> shapeSleepingRoom = new ArrayList <Vertex> ();
		shapeSleepingRoom.add(p5);
		shapeSleepingRoom.add(p6);
		shapeSleepingRoom.add(p11);
		shapeSleepingRoom.add(p10);
		ArrayList <Vertex> shapeMainRoom = new ArrayList <Vertex> ();
		shapeMainRoom.add(p1);
		shapeMainRoom.add(p2);
		shapeMainRoom.add(p7);
		shapeMainRoom.add(p9);
		shapeMainRoom.add(p12);
		shapeMainRoom.add(p11);
		ArrayList <Vertex> shapeBathroom = new ArrayList <Vertex> ();
		shapeBathroom.add(p2);
		shapeBathroom.add(p3);
		shapeBathroom.add(p8);
		shapeBathroom.add(p7);
		ArrayList <Vertex> shapeKitchen = new ArrayList <Vertex> ();
		shapeKitchen.add(p3);
		shapeKitchen.add(p4);
		shapeKitchen.add(p9);
		shapeKitchen.add(p8);
		ArrayList <Vertex> shapeApartment = new ArrayList <Vertex> ();
		shapeApartment.add(p1);
		shapeApartment.add(p4);
		shapeApartment.add(p9);
		shapeApartment.add(p12);
		shapeApartment.add(p10);
		shapeApartment.add(p5);
		shapeApartment.add(p6);
		ArrayList <Vertex> shapeOutside = new ArrayList <Vertex> ();
		shapeOutside.add(o1);
		shapeOutside.add(o2);
		shapeOutside.add(o3);
		shapeOutside.add(o4);
		shapeOutside.add(o6);
		shapeOutside.add(o5);
		
		//Create the rooms of an apartment
		Vertex mainRoom = db.addVertex("class:Location", "Name", "main room", "Description", "Living and dining room of Mr. Miller", "Shape", shapeMainRoom);
		Vertex myKitchen = db.addVertex("class:Location", "Name", "kitchen", "Description", "Kitchen of Mr. Miller", "Shape", shapeKitchen);
		Vertex myBathroom = db.addVertex("class:Location", "Name", "bathroom", "Description", "Bathroom of Mr. Miller with toilet and shouer", "Shape", shapeBathroom);
		Vertex mySleepingRoom = db.addVertex("class:Location", "Name", "sleeping room", "Description", "Sleeping room of Mr. Miller", "Shape", shapeSleepingRoom);		
		Vertex myApartment = db.addVertex("class:Location", "Name", "apartment Miller", "Description", "Apartment of Mr. Miller", "Shape", shapeApartment);
		Vertex outside = db.addVertex("class:Location", "Name", "common corridor", "Description", "Corridor in building leads to different appartments", "Shape", shapeOutside);
		db.addEdge(null, mainRoom, myApartment, "IS_PART_OF");
		db.addEdge(null, myKitchen, myApartment, "IS_PART_OF");
		db.addEdge(null, myBathroom, myApartment, "IS_PART_OF");
		db.addEdge(null, mySleepingRoom, myApartment, "IS_PART_OF");
		db.addEdge(null, mainRoom, livingRoom, "IS_A");
		db.addEdge(null, mainRoom, diningRoom, "IS_A");
		db.addEdge(null, myKitchen, kitchen, "IS_A");
		db.addEdge(null, myBathroom, bathroom, "IS_A");
		db.addEdge(null, mySleepingRoom, sleepingRoom, "IS_A");
		
		//Create some doors between the rooms
		Edge MainToSleep = db.addEdge(null, mainRoom, sleepingRoom, "IS_CONNECTED_TO");
		MainToSleep.setProperty("PositionIn", doorSleep);
		MainToSleep.setProperty("PositionOut", doorSleep);
		MainToSleep.setProperty("Width", 80);
		MainToSleep.setProperty("PassTimeSec", 20);

		Edge MainToBath = db.addEdge(null, mainRoom, myBathroom, "IS_CONNECTED_TO");
		MainToBath.setProperty("PositionIn", doorBath);
		MainToBath.setProperty("PositionOut", doorBath);
		MainToBath.setProperty("Width", 80);
		MainToBath.setProperty("PassTimeSec", 20);

		Edge MainToKitchen = db.addEdge(null, mainRoom, myKitchen, "IS_CONNECTED_TO");
		MainToKitchen.setProperty("PositionIn", doorKitchen);
		MainToKitchen.setProperty("PositionOut", doorKitchen);
		MainToKitchen.setProperty("Width", 80);
		MainToKitchen.setProperty("PassTimeSec", 20);
		
		Edge entrance = db.addEdge(null, mainRoom, outside, "IS_CONNECTED_TO");
		entrance.setProperty("PositionIn", doorEntranceOut);
		entrance.setProperty("PositionOut", doorEntrance);
		entrance.setProperty("Width", 80);
		entrance.setProperty("PassTimeSec", 20);

		db.commit();
		
		//Create some vertices of ObjectConcept
		Vertex fridge = db.addVertex("class:ObjectConcept", "Name", "refridgerator", "Description", "A device to keep food cool");
		Vertex table = db.addVertex("class:ObjectConcept", "Name", "table", "Description", "A plate to put things on it");
		Vertex juice = db.addVertex("class:ObjectConcept", "Name", "juice", "Description", "Fluid of pressed fruit");
		Vertex appleJuice = db.addVertex("class:ObjectConcept", "Name", "apple juice", "Description", "Fluid of pressed apples");
		Vertex fluid = db.addVertex("class:ObjectConcept", "Name", "fluid", "Description", "solid - fluid - gas");
		db.addEdge(null, juice, fluid, "IS_A");		
		db.addEdge(null, appleJuice, juice, "IS_A");		

		db.commit();
		
		//Create some vertices of type Object
		ODocument sizeFridge = new ODocument ("Size3D");
		sizeFridge.field("Length", 60);
		sizeFridge.field("Width", 60);
		sizeFridge.field("Hight", 90);
		Vertex fridgeI = db.addVertex("class:Object", "Name", "Bosch Cool2SX", "Description", "My refridgerator", "Size", sizeFridge);
		db.addEdge(null, fridgeI, fridge, "IS_A");
		
		ODocument sizeTable = new ODocument ("Size3D");
		sizeTable.field("Length", 100);
		sizeTable.field("Width", 100);
		sizeTable.field("Hight", 85);
		Vertex tableI = db.addVertex("class:Object", "Name", "dining table", "Description", "My circular dining table", "Size", sizeTable);
		db.addEdge(null, tableI, table, "IS_A");

		ODocument sizeJuice = new ODocument ("Size3D");
		sizeJuice.field("Length", 7);
		sizeJuice.field("Width", 7);
		sizeJuice.field("Hight", 25);
		Vertex juiceI = db.addVertex("class:Object", "Name", "Alba Apple Juice 1L", "Description", "Apple Juice of Alba", "Size", sizeJuice);
		db.addEdge(null, juiceI, appleJuice, "IS_A");
		
		db.commit();
		
		//Create some positions where the objects are
		Vertex posFridge = db.addVertex("class:Position2D", "x", 860, "y", 30);
		Edge fridgeAtposFridge = db.addEdge(null, fridgeI, posFridge, "PROB_IS_AT");
		fridgeAtposFridge.setProperty("Score", 10);
		fridgeAtposFridge.setProperty("Hight", 80);

		Vertex posTable1 = db.addVertex("class:Position2D", "x", 800, "y", 400);
		Edge tableAtposTable1 = db.addEdge(null, tableI, posTable1, "PROB_IS_AT");
		tableAtposTable1.setProperty("Score", 5);
		tableAtposTable1.setProperty("Hight", 0);

		Vertex posTable2 = db.addVertex("class:Position2D", "x", 460, "y", 450);
		Edge tableAtposTable2 = db.addEdge(null, tableI, posTable2, "PROB_IS_AT");
		tableAtposTable2.setProperty("Score", 5);
		tableAtposTable2.setProperty("Hight", 0);

		Edge juiceAtTable = db.addEdge(null, juiceI, tableI, "PROB_IS_AT");
		juiceAtTable.setProperty("Score", 2);
		juiceAtTable.setProperty("Hight", 0);

		Edge juiceAtFridge = db.addEdge(null, juiceI, fridgeI, "PROB_IS_AT");
		juiceAtFridge.setProperty("Score", 5);
		juiceAtFridge.setProperty("Hight", 0);

		Vertex posJuice1 = db.addVertex("class:Position2D", "x", 900, "y", 100);
		Edge juiceAtposJuice1 = db.addEdge(null, juiceI, posJuice1, "PROB_IS_AT");
		juiceAtposJuice1.setProperty("Score", 1);
		juiceAtposJuice1.setProperty("Hight", 0);

		Vertex posJuice2 = db.addVertex("class:Position2D", "x", 700, "y", 300);
		Edge juiceAtposJuice2 = db.addEdge(null, juiceI, posJuice2, "PROB_IS_AT");
		juiceAtposJuice2.setProperty("Score", 1);
		juiceAtposJuice2.setProperty("Hight", 0);
		
		Vertex posJuice3 = db.addVertex("class:Position2D", "x", 200, "y", 400);
		Edge juiceAtposJuice3 = db.addEdge(null, juiceI, posJuice3, "PROB_IS_AT");
		juiceAtposJuice3.setProperty("Score", 1);
		juiceAtposJuice3.setProperty("Hight", 0);
		
		db.shutdown();
		factory.close();
	}

}
