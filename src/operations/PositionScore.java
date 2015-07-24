package operations;

import com.tinkerpop.blueprints.Vertex;

/*
 * Data structure to store a Position vertex together with a calculated score
 */
public class PositionScore {
	public Vertex pos;
	public int score;
	
	public PositionScore(Vertex pos, int score) {
		this.pos = pos;
		this.score = score;
	}
}
