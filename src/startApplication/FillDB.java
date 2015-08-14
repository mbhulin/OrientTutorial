package startApplications;

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
//		OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/RobotWorld", "admin", "admin");
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
		Vertex bathroom = db.addVertex("class:LocationConcept", "Name", "bathroom", "Description", "Room to wash typically with a basin a bath tube or a shower.");
		Vertex sleepingRoom = db.addVertex("class:LocationConcept", "Name", "sleeping room", "Description", "Room to sleep typically with a bed and a wardrobe");		
		Vertex corridor = db.addVertex("class:LocationConcept", "Name", "corridor", "Description", "Room to connect other rooms or entrance hall of a building");
		Vertex floor = db.addVertex("class:LocationConcept", "Name", "floor", "Description", "All rooms on one level build a floor");
		Vertex room = db.addVertex("class:LocationConcept", "Name", "room", "Description", "Any room inside of a building");
		Vertex house = db.addVertex("class:LocationConcept", "Name", "house", "Description", "Special building where people live");
		Vertex apartment = db.addVertex("class:LocationConcept", "Name", "apartment", "Description", "Apartment in a house");
		Vertex building = db.addVertex("class:LocationConcept", "Name", "building", "Description", "A house or hall or church, something with a roof on it");
		Vertex door = db.addVertex("class:LocationConcept", "Name", "door", "Description", "A door connects rooms. It can be opened and shut.");
		Vertex connector = db.addVertex("class:LocationConcept", "Name", "connector", "Description", "A location that connects rooms e.g. a door or an elevator.");
		db.addEdge("class:IS_A", door, connector, "IS_A");
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
		
		//Create some vertices Position which are used for locations
		Vertex p1 = db.addVertex("class:Position", "x", 400, "y", 200, "z", 0);
		Vertex p2 = db.addVertex("class:Position", "x", 600, "y", 200, "z", 0);
		Vertex p3 = db.addVertex("class:Position", "x", 800, "y", 200, "z", 0);
		Vertex p4 = db.addVertex("class:Position", "x", 1050, "y", 200, "z", 0);
		Vertex p5 = db.addVertex("class:Position", "x", 0, "y", 450, "z", 0);
		Vertex p6 = db.addVertex("class:Position", "x", 400, "y", 450, "z", 0);
		Vertex p7 = db.addVertex("class:Position", "x", 600, "y", 450, "z", 0);
		Vertex p8 = db.addVertex("class:Position", "x", 800, "y", 450, "z", 0);
		Vertex p9 = db.addVertex("class:Position", "x", 1050, "y", 450, "z", 0);
		Vertex p10 = db.addVertex("class:Position", "x", 0, "y", 800, "z", 0);
		Vertex p11 = db.addVertex("class:Position", "x", 400, "y", 800, "z", 0);
		Vertex p12 = db.addVertex("class:Position", "x", 1200, "y", 800, "z", 0);
		Vertex o1 = db.addVertex("class:Position", "x", 0, "y", 0, "z", 0);
		Vertex o2 = db.addVertex("class:Position", "x", 1200, "y", 0, "z", 0);
		Vertex o3 = db.addVertex("class:Position", "x", 1200, "y", 200, "z", 0);
//		Vertex o4 = p1;
		Vertex o5 = db.addVertex("class:Position", "x", 400, "y", 250, "z", 0);
		Vertex o6 = db.addVertex("class:Position", "x", 0, "y", 250, "z", 0);
		Vertex doorBath1 = db.addVertex("class:Position", "x", 600, "y", 350, "z", 0);
		Vertex doorBathM = db.addVertex("class:Position", "x", 600, "y", 390, "z", 0);
		Vertex doorBath2 = db.addVertex("class:Position", "x", 600, "y", 430, "z", 0);
		Vertex doorKitchen1 = db.addVertex("class:Position", "x", 1000, "y", 450, "z", 0);
		Vertex doorKitchenM = db.addVertex("class:Position", "x", 960, "y", 450, "z", 0);
		Vertex doorKitchen2 = db.addVertex("class:Position", "x", 920, "y", 450, "z", 0);
		Vertex doorSleep1 = db.addVertex("class:Position", "x", 400, "y", 500, "z", 0);
		Vertex doorSleepM = db.addVertex("class:Position", "x", 400, "y", 540, "z", 0);
		Vertex doorSleep2 = db.addVertex("class:Position", "x", 400, "y", 580, "z", 0);
		Vertex doorEntrance1 = db.addVertex("class:Position", "x", 450, "y", 200, "z", 0);
		Vertex doorEntranceM = db.addVertex("class:Position", "x", 500, "y", 200, "z", 0);
		Vertex doorEntrance2 = db.addVertex("class:Position", "x", 550, "y", 200, "z", 0);
		db.commit();
		
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
		shapeOutside.add(p1);
		shapeOutside.add(o5);
		shapeOutside.add(o6);
		ArrayList <Vertex> shapeDoorSleep = new ArrayList <Vertex> ();
		shapeDoorSleep.add(doorSleep1);
		shapeDoorSleep.add(doorSleep2);
		ArrayList <Vertex> shapeDoorBath = new ArrayList <Vertex> ();
		shapeDoorBath.add(doorBath1);
		shapeDoorBath.add(doorBath2);
		ArrayList <Vertex> shapeDoorKitchen = new ArrayList <Vertex> ();
		shapeDoorKitchen.add(doorKitchen1);
		shapeDoorKitchen.add(doorKitchen2);
		ArrayList <Vertex> shapeDoorEntrance = new ArrayList <Vertex> ();
		shapeDoorEntrance.add(doorEntrance1);
		shapeDoorEntrance.add(doorEntrance2);
				
		//Create the rooms of an apartment
		Vertex mainRoom = db.addVertex("class:Location", "Name", "main room", "Description", "Living and dining room of Mr. Miller", "Shape", shapeMainRoom);
		Vertex myKitchen = db.addVertex("class:Location", "Name", "kitchen", "Description", "Kitchen of Mr. Miller", "Shape", shapeKitchen);
		Vertex myBathroom = db.addVertex("class:Location", "Name", "bathroom", "Description", "Bathroom of Mr. Miller with toilet and shouer", "Shape", shapeBathroom);
		Vertex mySleepingRoom = db.addVertex("class:Location", "Name", "sleeping room", "Description", "Sleeping room of Mr. Miller", "Shape", shapeSleepingRoom);		
		Vertex myApartment = db.addVertex("class:Location", "Name", "apartment Miller", "Description", "Apartment of Mr. Miller", "Shape", shapeApartment);
		Vertex outside = db.addVertex("class:Location", "Name", "common corridor", "Description", "Corridor in building leads to different appartments", "Shape", shapeOutside);
		db.commit();
		
		db.addEdge(null, mainRoom, myApartment, "IS_PART_OF");
		db.addEdge(null, myKitchen, myApartment, "IS_PART_OF");
		db.addEdge(null, myBathroom, myApartment, "IS_PART_OF");
		db.addEdge(null, mySleepingRoom, myApartment, "IS_PART_OF");
		db.addEdge(null, mainRoom, livingRoom, "IS_A");
		db.addEdge(null, mainRoom, diningRoom, "IS_A");
		db.addEdge(null, myKitchen, kitchen, "IS_A");
		db.addEdge(null, myBathroom, bathroom, "IS_A");
		db.addEdge(null, mySleepingRoom, sleepingRoom, "IS_A");
		db.commit();
		
		//Create some doors between the rooms
		Vertex doorSleep = db.addVertex("class:Location", "Name", "DoorMainSleep", "Description", "Door between main room and sleeping room", "Shape", shapeDoorSleep, "PassTimeSec", 20);
		db.addEdge("class:IS_A", doorSleep, door, "IS_A");
		Vertex doorBath = db.addVertex("class:Location", "Name", "DoorMainBath", "Description", "Door between main room and sleeping room", "Shape", shapeDoorBath, "PassTimeSec", 20);
		db.addEdge("class:IS_A", doorBath, door, "IS_A");
		Vertex doorKitchen = db.addVertex("class:Location", "Name", "DoorMainKitchen", "Description", "Door between main room and kitchen", "Shape", shapeDoorKitchen, "PassTimeSec", 20);
		db.addEdge("class:IS_A", doorKitchen, door, "IS_A");
		Vertex doorEntrance = db.addVertex("class:Location", "Name", "DoorEntrance", "Description", "Entrance door to flat", "Shape", shapeDoorEntrance, "PassTimeSec", 20);
		db.addEdge("class:IS_A", doorEntrance, door, "IS_A");
		db.commit();
		
		doorSleep1.setProperty("inLocation", doorSleep);
		doorBath1.setProperty("inLocation", doorBath);
		doorKitchen1.setProperty("inLocation", doorKitchen);
		doorEntrance1.setProperty("inLocation", doorEntrance);
		doorSleepM.setProperty("inLocation", doorSleep);
		doorBathM.setProperty("inLocation", doorBath);
		doorKitchenM.setProperty("inLocation", doorKitchen);
		doorEntranceM.setProperty("inLocation", doorEntrance);
		db.commit();

		db.addEdge(null, mainRoom, doorSleep, "IS_CONNECTED_TO");
		db.addEdge(null, mySleepingRoom, doorSleep, "IS_CONNECTED_TO");

		db.addEdge(null, mainRoom, doorBath, "IS_CONNECTED_TO");
		db.addEdge(null, myBathroom, doorBath, "IS_CONNECTED_TO");

		db.addEdge(null, mainRoom, doorKitchen, "IS_CONNECTED_TO");
		db.addEdge(null, myKitchen, doorKitchen, "IS_CONNECTED_TO");
		
		db.addEdge(null, mainRoom, doorEntrance, "IS_CONNECTED_TO");
		db.addEdge(null, outside, doorEntrance, "IS_CONNECTED_TO");
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
		sizeFridge.field("x", 60);
		sizeFridge.field("y", 60);
		sizeFridge.field("z", 90);
		Vertex fridgeI = db.addVertex("class:Object", "Name", "Bosch Cool2SX", "Description", "My refridgerator", "Size", sizeFridge);
		db.addEdge(null, fridgeI, fridge, "IS_A");
		Vertex fridge2I = db.addVertex("class:Object", "Name", "AEG Cool & Fresh", "Description", "spare fridge", "Size", sizeFridge);
		db.addEdge(null, fridge2I, fridge, "IS_A");
		
		ODocument sizeTable = new ODocument ("Size3D");
		sizeTable.field("x", 100);
		sizeTable.field("y", 100);
		sizeTable.field("z", 85);
		Vertex tableI = db.addVertex("class:Object", "Name", "dining table", "Description", "My circular dining table", "Size", sizeTable);
		db.addEdge(null, tableI, table, "IS_A");

		ODocument sizeJuice = new ODocument ("Size3D");
		sizeJuice.field("x", 7);
		sizeJuice.field("y", 7);
		sizeJuice.field("z", 25);
		Vertex juiceI = db.addVertex("class:Object", "Name", "Orient Apple Juice 1L", "Description", "Apple Juice of Orient; sweet", "Size", sizeJuice);
		db.addEdge(null, juiceI, appleJuice, "IS_A");
		Vertex juice2I = db.addVertex("class:Object", "Name", "Oracle Apple Juice 1L", "Description", "Greec Apple Juice; sweet", "Size", sizeJuice);
		db.addEdge(null, juice2I, appleJuice, "IS_A");
		db.commit();
		
		//Create some positions where the objects are
		Vertex posFridge = db.addVertex("class:Position", "x", 860, "y", 230, "z", 0, "inLocation", myKitchen);
		Edge fridgeAtposFridge = db.addEdge(null, fridgeI, posFridge, "PROB_IS_AT");
		fridgeAtposFridge.setProperty("Score", 10);
		fridgeAtposFridge.setProperty("Hight", 80);

		Vertex posTable1 = db.addVertex("class:Position", "x", 800, "y", 600, "z", 0);
		posTable1.setProperty("inLocation", mainRoom);
		Edge tableAtposTable1 = db.addEdge(null, tableI, posTable1, "PROB_IS_AT");
		tableAtposTable1.setProperty("Score", 5);
		tableAtposTable1.setProperty("Hight", 0);

		Vertex posTable2 = db.addVertex("class:Position", "x", 460, "y", 650, "z", 0);
		posTable2.setProperty("inLocation", mainRoom);
		Edge tableAtposTable2 = db.addEdge(null, tableI, posTable2, "PROB_IS_AT");
		tableAtposTable2.setProperty("Score", 5);
		tableAtposTable2.setProperty("Hight", 0);

		Edge juiceAtTable = db.addEdge(null, juiceI, tableI, "PROB_IS_AT");
		juiceAtTable.setProperty("Score", 2);
		juiceAtTable.setProperty("Hight", 0);

		Edge juiceAtFridge = db.addEdge(null, juiceI, fridgeI, "PROB_IS_AT");
		juiceAtFridge.setProperty("Score", 5);
		juiceAtFridge.setProperty("Hight", 0);

		Vertex posJuice1 = db.addVertex("class:Position", "x", 900, "y", 300, "z", 0);
		posJuice1.setProperty("inLocation", myKitchen);
		Edge juiceAtposJuice1 = db.addEdge(null, juiceI, posJuice1, "PROB_IS_AT");
		juiceAtposJuice1.setProperty("Score", 1);
		juiceAtposJuice1.setProperty("Hight", 0);

		Vertex posJuice2 = db.addVertex("class:Position", "x", 700, "y", 500, "z", 0);
		posJuice2.setProperty("inLocation", mainRoom);
		Edge juiceAtposJuice2 = db.addEdge(null, juiceI, posJuice2, "PROB_IS_AT");
		juiceAtposJuice2.setProperty("Score", 1);
		juiceAtposJuice2.setProperty("Hight", 0);
		
		Vertex posJuice3 = db.addVertex("class:Position", "x", 200, "y", 600, "z", 0);
		posJuice3.setProperty("inLocation", mySleepingRoom);
		Edge juiceAtposJuice3 = db.addEdge(null, juiceI, posJuice3, "PROB_IS_AT");
		juiceAtposJuice3.setProperty("Score", 1);
		juiceAtposJuice3.setProperty("Hight", 0);
		
		Vertex posJuice4 = db.addVertex("class:Position", "x", 100, "y", 700, "z", 0);
		posJuice4.setProperty("inLocation", mySleepingRoom);
		Edge juiceAtposJuice4 = db.addEdge(null, juice2I, posJuice4, "PROB_IS_AT");
		juiceAtposJuice4.setProperty("Score", 1);
		juiceAtposJuice4.setProperty("Hight", 0);
		Edge juiceAtposJuice5 = db.addEdge(null, juiceI, posJuice4, "PROB_IS_AT");
		juiceAtposJuice5.setProperty("Score", 2);
		juiceAtposJuice5.setProperty("Hight", 0);

		db.commit();
		
		//Create some information about passing times between positions
		Edge passTime1 = db.addEdge(null, posTable2, doorSleepM, "IS_CONNECTED_TO");
		passTime1.setProperty("PassTimeSec", 10);
		Edge passTime2 = db.addEdge(null, posTable2, doorBathM, "IS_CONNECTED_TO");
		passTime2.setProperty("PassTimeSec", 15);
		Edge passTime3 = db.addEdge(null, posTable2, doorKitchenM, "IS_CONNECTED_TO");
		passTime3.setProperty("PassTimeSec", 30);
		Edge passTime4 = db.addEdge(null, posTable2, doorEntranceM, "IS_CONNECTED_TO");
		passTime4.setProperty("PassTimeSec", 20);

		Edge passTime5 = db.addEdge(null, posTable1, doorSleepM, "IS_CONNECTED_TO");
		passTime5.setProperty("PassTimeSec", 25);
		Edge passTime6 = db.addEdge(null, posTable1, doorBathM, "IS_CONNECTED_TO");
		passTime6.setProperty("PassTimeSec", 25);
		Edge passTime7 = db.addEdge(null, posTable1, doorKitchenM, "IS_CONNECTED_TO");
		passTime7.setProperty("PassTimeSec", 10);
		Edge passTime8 = db.addEdge(null, posTable1, doorEntranceM, "IS_CONNECTED_TO");
		passTime8.setProperty("PassTimeSec", 30);
		
		Edge passTime9 = db.addEdge(null, posFridge, doorKitchenM, "IS_CONNECTED_TO");
		passTime9.setProperty("PassTimeSec", 5);

		Edge passTime10 = db.addEdge(null, posJuice1, doorKitchenM, "IS_CONNECTED_TO");
		passTime10.setProperty("PassTimeSec", 5);

		Edge passTime11 = db.addEdge(null, posJuice2, doorKitchenM, "IS_CONNECTED_TO");
		passTime11.setProperty("PassTimeSec", 10);
		Edge passTime12 = db.addEdge(null, posJuice2, doorSleepM, "IS_CONNECTED_TO");
		passTime12.setProperty("PassTimeSec", 30);
		
		Edge passTime13 = db.addEdge(null, posJuice3, doorSleepM, "IS_CONNECTED_TO");
		passTime13.setProperty("PassTimeSec", 12);
		
		Edge passTime14 = db.addEdge(null, doorKitchenM, doorSleepM, "IS_CONNECTED_TO");
		passTime14.setProperty("PassTimeSec", 29);
		
		Edge passTime15 = db.addEdge(null, posTable1, posTable2, "IS_CONNECTED_TO");
		passTime15.setProperty("PassTimeSec", 10);
		
		Edge passTime16 = db.addEdge(null, posTable1, posJuice2, "IS_CONNECTED_TO");
		passTime16.setProperty("PassTimeSec", 15);

		Edge passTime17 = db.addEdge(null, posTable2, posJuice2, "IS_CONNECTED_TO");
		passTime17.setProperty("PassTimeSec", 15);
		
		Edge passTime18 = db.addEdge(null, posJuice4, doorSleepM, "IS_CONNECTED_TO");
		passTime18.setProperty("PassTimeSec", 13);

		Edge passTime19 = db.addEdge(null, posJuice4, posJuice3, "IS_CONNECTED_TO");
		passTime19.setProperty("PassTimeSec", 8);

		db.commit();
		
		db.shutdown();
		factory.close();
	}

}
