package operations;

import java.util.ArrayList;
import java.util.List;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

public class Operations {
	public static final String  filePathToDB = "plocal:C:/orientdb/databases/RobotWorld";
	public static final String urlToDB = "remote:localhost/RobotWorld";
	
	private OrientGraph db; // Connection to database

	// Array of search paths of robot. Each partial path represents the robot's path from one position to another position, where the object could be.
	private ArrayList <ArrayList <Vertex>> searchPath;
	
	public Operations(OrientGraph db) {
		this.db = db;
		searchPath = new ArrayList <ArrayList <Vertex>> ();
	}
	
	public ArrayList<ArrayList<Vertex>> getSearchPath() {
		return searchPath;
	}

	public void clearSearchPath() {
		this.searchPath.clear();
	}

	/*
	 * Simulation of the search process of a service robot for an object. The resulting search path is stored in the property searchPath.
	 * start: current position of robot
	 * dest: current position of search object
	 */
	public String searchForObject (Vertex start, Vertex dest, Vertex searchObject) {
		
		List <PositionScore> posList = createPosList (searchObject); //Create list of positions where object could be together with scores and store it in posList
		if (posList.isEmpty()) return "No positions exist where object " + searchObject.getProperty("Name") + " could be";
		String startRid = start.getId().toString();
		
		// Loop: Calculate paths to all positions in posList together with time estimation to score ratio and choose best search position
		while (!posList.isEmpty()) {
			PositionScore bestPos = null; // Store position with best pasTime/Score ratio found so far in bestPos
			float bestCost = Float.MAX_VALUE; // bestCost = passTime/Score ratio of best bestPos
			Iterable<OrientVertex> pathToBestPos = null;
			for (PositionScore ps: posList) {
				// let OrientDB calculate the best path to position ps.pos using the dijkstra algorithm
				String destRid = ps.pos.getId().toString(); // id of current Vertex in posList: ps.pos
				String dijkstraQueryString = "SELECT dijkstra2(" + startRid + ", " + destRid + ", 'IS_CONNECTED_TO', 'PassTimeSec', 'BOTH')";
				OSQLSynchQuery dijkstraQuery = new OSQLSynchQuery(dijkstraQueryString);
				Iterable <Vertex> result = db.command(dijkstraQuery).execute();
				Iterable <OrientVertex> pathIterable = null;
				for (Vertex v: result) { pathIterable = v.getProperty("dijkstra2"); break; }
				if (pathIterable != null) {
					float timePerScore = calculatePathPassTime(pathIterable) / ps.score;
					if (timePerScore < bestCost) { // is the new position better than the best position so far regarding timePerScore?
						bestPos = ps;
						pathToBestPos = pathIterable;
						bestCost = timePerScore;
					}
				}
			}

			if (pathToBestPos != null) {
				ArrayList <Vertex> currentPath = new ArrayList <Vertex> ();
				for (OrientVertex v: pathToBestPos) {
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

	/*
	 * Since mydijkstra2 only returns the best path as list of vertices but not the path's cost 
	 * this value has to be recalculated here
	 */
	private float calculatePathPassTime(Iterable<OrientVertex> pathIterable) {
		float totalPassTime = 0;
		OrientVertex lastPos = null;
		for (OrientVertex p: (Iterable <OrientVertex>) pathIterable) {
			if (lastPos != null) {
				Iterable <Edge> edges = lastPos.getEdges(p, Direction.BOTH, "IS_CONNECTED_TO");
				Float minPassTimeEdge = Float.MAX_VALUE; // if more than one edge exist take that one with the minimal cost
				for (Edge e: edges) {
					Float passTimeEdge = ((Float) e.getProperty("PassTimeSec")).floatValue();
					if (passTimeEdge != null) {
						if (passTimeEdge < minPassTimeEdge) minPassTimeEdge = passTimeEdge;
					}
				}
				totalPassTime = totalPassTime + minPassTimeEdge.floatValue();
			}
			lastPos = p;
		}
		return totalPassTime;
	}

	/*
	 * Create a list of Positions where the object obj could be,
	 * either directly connected positions or positions of other objects where the object could lie.
	 */
	public List<PositionScore> createPosList(Vertex obj) {
		List <PositionScore> posList = new ArrayList <PositionScore> (); //Result list
		
		//Create list of positions where object is directly connected to a position
		OSQLSynchQuery query1 = new OSQLSynchQuery ("select in as pos, Score as combiScore from PROB_IS_AT where out = ? and in.@class = 'Position'");
		for (Vertex pos: (Iterable<Vertex>) db.command(query1).execute(obj)) {
			posList.add(new PositionScore ((Vertex) pos.getProperty("pos"), (Float) pos.getProperty("combiScore")));
		}
		
		//Create list of positions where object is connected to other objects
		OSQLSynchQuery query2 = new OSQLSynchQuery ("select e2.in as pos, e1.Score as s1, e2.Score as s2, eval('s1 * s2 / 10') as combiScore from (select @rid as e1, in.outE('PROB_IS_AT') as e2 from PROB_IS_AT where out = ? and in.@class = 'MobileObject' unwind e2)");		
		for (Vertex pos: (Iterable<Vertex>) db.command(query2).execute(obj)) {
			posList.add(new PositionScore ((Vertex) pos.getProperty("pos"), (Float) pos.getProperty("combiScore")));
		}
		
		return posList;
	}
}
