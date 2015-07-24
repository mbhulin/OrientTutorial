package operations;

import java.util.ArrayList;
import java.util.Iterator;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class Operations {
	private OrientGraph db; // Connection to database

	// Array of search paths of robot. Each partial path represents the robot's path from one position to another position, where the object could be.
	private ArrayList <ArrayList <Vertex>> searchPath;
	
	public Operations(OrientGraph db) {
		this.db = db;
		searchPath = new ArrayList <ArrayList <Vertex>> ();
	}
	
	/*
	 * Simulation of the search process of a service robot for an object. The resulting search path is stored in the property searchPath.
	 * start: current position of robot
	 * dest: current position of search object
	 */
	public String searchForObject (Vertex start, Vertex dest, Vertex searchObject) {
		
		ArrayList <PositionScore> posList = createPosList (searchObject); //Create list of positions where object could be together with scores and store it in posList
		if (posList.isEmpty()) return "No positions exist where object " + searchObject.getProperty("Name") + " could be";
		String startRid = start.getId().toString();
		
		// Loop: Calculate paths to all positions in posList together with time estimation to score ratio and choose best search position
		while (!posList.isEmpty()) {
			PositionScore bestPos = null; // Store position with best pasTime/Score ratio found so far in bestPos
			Vertex pathToBestPos = null; // Store path (result of myDijkstra function) in pathToBestPos
			float bestCost = Float.MAX_VALUE; // bestCost = passTime/Score ratio of best bestPos
			for (PositionScore ps: posList) {
				// let OrientDB calculate the best path to position ps.pos using the dijkstra algorithm
				String destRid = ps.pos.getId().toString(); // id of Vertex dest
				String dijkstraQueryString = "SELECT myDijkstra(" + startRid + ", " + destRid + ", 'PassTimeSec', 'IS_CONNECTED_TO', 'BOTH')";
				OSQLSynchQuery dijkstraQuery = new OSQLSynchQuery(dijkstraQueryString);
				Iterator <Vertex> pathVertexIterator = ((Iterable<Vertex>) db.command(dijkstraQuery).execute()).iterator(); // The query should return an Iterable with only one Vertex containing the path to ps
				//Analyze result
				if (pathVertexIterator.hasNext()) {
					Vertex p = pathVertexIterator.next().getProperty("myDijkstra");
					if (p != null) {
						float timePerScore = (int) p.getProperty("cost") / ps.score;
						if (timePerScore < bestCost) {
							bestPos = ps;
							pathToBestPos = p;
							bestCost = timePerScore;
						}
					}
				}
			}

			if (pathToBestPos != null) {
				ArrayList <Vertex> currentPath = new ArrayList <Vertex> ();
				for (Vertex v: (Iterable <Vertex>) pathToBestPos.getProperty("pathVList")) {
					currentPath.add(v);
				}
				searchPath.add(currentPath);
				if (bestPos.pos.getId().toString().equals(dest.getId().toString())) {
					System.out.println("Object " + searchObject.getProperty("Name") + " found at position " + bestPos.pos.getId().toString());
					return "Object " + searchObject.getProperty("Name") + " found at position " + bestPos.pos.getId().toString();
				}
				startRid = bestPos.pos.getId().toString(); // Update current position of robot which is new start
			} else {
				// pathToBestPos == null means that there is no path to any possible position of the object.
				System.out.println("Robot cannot reach the object " + searchObject.getProperty("Name"));
				return "Robot cannot reach the object " + searchObject.getProperty("Name");
			}
			posList.remove(bestPos);
			if (posList.isEmpty()) {
				System.out.println ("Robot could not find Object " + searchObject.getProperty("Name"));
				return "Robot could not find Object " + searchObject.getProperty("Name");
			}
		}
		return "?";

	}
	

	public ArrayList<ArrayList<Vertex>> getSearchPath() {
		return searchPath;
	}

	public void clearSearchPath() {
		this.searchPath.clear();
	}

	/*
	 * Create a list of Positions where the object with id "objRid" could be,
	 * either directly connected positions or positions of other objects where the object could lie.
	 */
	public ArrayList<PositionScore> createPosList(Vertex obj) {
		ArrayList <PositionScore> posList = new ArrayList <PositionScore> (); //Result list
		
		//Create list of positions where object is directly connected to a position
		OSQLSynchQuery query1 = new OSQLSynchQuery ("select in as pos, Score as combiScore from PROB_IS_AT where out = ? and in.@class = 'Position'");
		Iterable<Vertex> posIter1 = null;
		try {
			posIter1 = db.command(query1).execute(obj);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Create list of positions where object is connected to other objects
		OSQLSynchQuery query2 = new OSQLSynchQuery ("select e2.in as pos, e1.Score as s1, e2.Score as s2, eval('s1 * s2 / 10') as combiScore from (select @rid as e1, in.outE('PROB_IS_AT') as e2 from PROB_IS_AT where out = ? and in.@class = 'Object' unwind e2) order by combiScore desc");
		Iterable<Vertex> posIter2 = null;
		try {
			posIter2 = db.command(query2).execute(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Vertex pos: posIter1) {
			posList.add(new PositionScore (pos.getProperty("pos"), (int) pos.getProperty("combiScore")));
		}
		
		for (Vertex pos: posIter2) {
			posList.add(new PositionScore (pos.getProperty("pos"), (int) pos.getProperty("combiScore")));
		}
		
		return posList;
	}

	public void printPath(Vertex path) {
		if (path == null) {
			System.out.println("No path found by myDijkstra function.");
			return;
		}
		if ((path.getProperty("pathEList") == null)||(path.getProperty("pathVList") == null)) {
			System.out.println("Wrong vertex structure; no path");
			return;			
		}
		for (Vertex v: (Iterable <Vertex>) path.getProperty("pathVList")) {
			System.out.print(v);
		}
		System.out.println();
		for (Edge e: (Iterable <Edge>) path.getProperty("pathEList")) {
			System.out.print(e);
		}
		System.out.println();
	}
}
