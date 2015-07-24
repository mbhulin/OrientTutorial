package gui;

import java.util.EventObject;

import com.tinkerpop.blueprints.Vertex;

/*
 * Event reports selected position a user has chosen in drawingPane
 */
public class DrawingPaneChangedEvent extends EventObject {
	private Vertex pos; //position clicked

	public DrawingPaneChangedEvent(DrawingPane source, Vertex pos) {
		super(source);
		this.pos = pos;
	}

	public Vertex getPos () {
		return this.pos;
	}
}
