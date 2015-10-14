package operations;

import com.tinkerpop.blueprints.Vertex;

/*
 * Data structure to store a Position vertex together with a calculated score
 */
public class PositionScore {
	public Vertex pos;
	public Float score;
	
	public PositionScore(Vertex pos, Float score) {
		this.pos = pos;
		this.score = score;
	}
}
