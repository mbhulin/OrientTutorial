package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class DrawingPane extends JPanel implements MouseListener {
	private static final long serialVersionUID = 2L;
	private static final float Massstab = (float) 0.75;
	private static final float OffsetX = (float) 50;
	private static final float OffsetY = (float) 700;
	private OrientGraph db;
	private int task;
	private int pathPart;
	private Vertex pos;
	private MainFrameRWM caller;

	ArrayList<DrawingPaneListener> drawingPaneListeners = new ArrayList<DrawingPaneListener>();

	public void addDrawingPaneListener (DrawingPaneListener dpl) {
		drawingPaneListeners.add(dpl);
	}
	
	public DrawingPane(OrientGraph db, MainFrameRWM mfrwm) {
		super();
		this.db = db;
		this.task = 0;
//		this.pathPart = 0;
		this.addMouseListener(this);
		this.caller = mfrwm;
	}

	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}
	
	public int getPathPart() {
		return pathPart;
	}

	public void setPathPart(int pp) {
		this.pathPart = pp;
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		drawDB(g);
		if (this.task == 3||this.task == 4) drawPath(g);
	}

	private void drawPath(Graphics g) {
		int i = 0;
		g.setColor(Color.BLUE);
		Vertex vOld = null;
		for (ArrayList <Vertex> partOfPath: this.caller.oper.getSearchPath()) {
			for (Vertex v: partOfPath) {
				if (vOld != null) g.drawLine(ScaleToPlotX(vOld.getProperty("x")), ScaleToPlotY(vOld.getProperty("y")),ScaleToPlotX(v.getProperty("x")), ScaleToPlotY(v.getProperty("y")));
				vOld = v;
			}
			i++;
			if (i > this.pathPart && task == 4) break;
		}
		if (vOld != null) g.fillOval(ScaleToPlotX(vOld.getProperty("x")), ScaleToPlotY(vOld.getProperty("y")), 10, 10);
	}

	private void drawDB(Graphics g) {
		// draw outline of each location
		for (Vertex v:db.getVerticesOfClass("Location")) {
			// if the location is a door draw it in RED
			Iterable <Vertex> vIt = v.getVertices(Direction.OUT, "IS_A");
			for (Vertex vv: vIt) {
				if (vv.getProperty("@class").equals("LocationConcept"))
					if (vv.getProperty("Name").equals("door")) {
						g.setColor(Color.RED);
						((Graphics2D) g).setStroke(new BasicStroke (4f));
						break;
					}
			}
			
			Iterable <Vertex> corners = v.getProperty("Shape");
			Polygon locationShape = new Polygon();
			int x = 0, y = 0, i = 0, xSum = 0, ySum = 0;
			for (Vertex corner: corners) {
				x = ScaleToPlotX(corner.getProperty("x"));
				y = ScaleToPlotY(corner.getProperty("y"));
				locationShape.addPoint(x, y);
				xSum = xSum + x;
				ySum = ySum + y;
				i++;
			}
			g.drawPolygon(locationShape);
			g.drawString((String) v.getProperty("Name"), xSum/i - 10, ySum/i + 10); // draw the name in the middle of the room
			g.setColor(Color.BLACK); // switch back to BLACK color for the next room
			((Graphics2D) g).setStroke(new BasicStroke (1f));
		}
		
		// draw all positions inside of a location as little RED circles
		g.setColor(Color.RED);
		for (Edge e: db.getEdgesOfClass("IS_CONNECTED_TO")) {
			Vertex p1 = e.getVertex(Direction.IN);
			Vertex p2 = e.getVertex(Direction.OUT);
			String p1Class = p1.getProperty("@class");
			String p2Class = p1.getProperty("@class");	

			if (p1Class.equals("Position") && p2Class.equals("Position")) {
				g.fillOval(ScaleToPlotX(p1.getProperty("x")), ScaleToPlotY(p1.getProperty("y")),10,10);
				g.fillOval(ScaleToPlotX(p2.getProperty("x")), ScaleToPlotY(p2.getProperty("y")),10,10);
				
			}
		}
		g.setColor(Color.BLUE);
		if (pos != null) {
			g.fillOval(ScaleToPlotX(pos.getProperty("x")), ScaleToPlotY(pos.getProperty("y")),10,10);
		}
	}
	private int ScaleToPlotY(float y) {
		// TODO Auto-generated method stub
		return (int) (OffsetY - y * Massstab);
	}

	private int ScaleToPlotX(float x) {
		// TODO Auto-generated method stub
		return (int) (x * Massstab + OffsetX);
	}

	@Override
	public void mouseClicked(MouseEvent e)	{
		if (task == 0 || task == 3) return; // do nothing if Status is 0 or 3
		
		if (task == 4) {
			this.pathPart++;
			if (pathPart == caller.oper.getSearchPath().size() - 1) {
				caller.info1.setText("Info: ");
				caller.info.setText("Path complete");
				this.repaint();
			} else if (pathPart == caller.oper.getSearchPath().size()) {
				this.task = 0;
				this.pathPart = 0;
				caller.info1.setText("User action: ");
				caller.info.setText("Click on 'Start another search'!");
				return;
			} else {
				this.repaint();
				return;
			}
		} else if (task == 1 || task == 2) {
			int x = Math.round ((e.getX() - Math.round(OffsetX))/Massstab);
			int y = Math.round ((Math.round(OffsetY) -e.getY())/Massstab);
			String queryPositionString = "SELECT * FROM Position WHERE x < ? and x > ? and y < ? and y > ?";
			OSQLSynchQuery queryPosition = new OSQLSynchQuery (queryPositionString);
			Iterator <Vertex> positionsIterator = ((Iterable<Vertex>) db.command(queryPosition).execute(x+10,x-10,y+10,y-10)).iterator();
			if (positionsIterator.hasNext()) {
				pos = positionsIterator.next();
				this.repaint();
			} else pos = null;
			
			for (DrawingPaneListener dpl: drawingPaneListeners) {
				dpl.DrawingPaneChanged(new DrawingPaneChangedEvent(this, pos));
			}
		}
		return;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


}
