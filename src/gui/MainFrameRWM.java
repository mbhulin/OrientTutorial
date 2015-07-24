package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import operations.Operations;
//import defWorlModelGUI.NewLocationEvent;
//import defWorlModelGUI.NewLocationListener;

public class MainFrameRWM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JLabel info; // Information for the user what to do next
	protected JLabel info1; // "User Action: " or "Info: "
	private OrientGraph db; // Connection to the database
	private Vertex start; // Current position of robot
	private Vertex dest; // Current position of search object
	private Vertex searchObject;
	protected Operations oper;

	/**
	 * Create the frame.
	 */
	public MainFrameRWM(OrientGraph graph){

		db = graph;
		oper = new Operations (db);
		JButton btnStartSearch = new JButton("Start Search"); // Button to start the search for an object
		JButton btnShowNextPathPart = new JButton("Show Path in Steps");
		JLabel objectListLabel = new JLabel ("List of Objects"); // Label for objectList
		JList <String> objectList = new JList <String> (); // List with names of objects; user should select one
		JPanel buttonPanel = new JPanel(); // Panel for buttons
		JPanel infoPanel = new JPanel(); // Panel for info-Text
		info1 = new JLabel ("User Action:");
		DrawingPane drawingPane = new DrawingPane (db, this); // Panel to draw locations and positions
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Shutdown database connection before closing main window!");
				db.shutdown();
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1250, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		buttonPanel.setLayout(new BoxLayout (buttonPanel, BoxLayout.X_AXIS)); // Panel for action buttons
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		contentPane.add(drawingPane, BorderLayout.CENTER);
		drawingPane.setVisible(true);
		drawingPane.repaint();
		drawingPane.addDrawingPaneListener(new DrawingPaneListener() {
			@Override
			public void DrawingPaneChanged(DrawingPaneChangedEvent e)
			{
				if (drawingPane.getTask() == 1) { // if task == get current position of search object
					dest = e.getPos();
					if (dest != null) {
						info.setText("Click on current position of robot!");
						drawingPane.setTask(2); // task = get current position of robot
					} else info.setText("No valid position; try again!");
				} else if (drawingPane.getTask() == 2) { // if task == get current position of robot
					start = e.getPos();
					if (start != null) {
						info.setText(oper.searchForObject(start, dest, searchObject)); // Start the search simulation
						drawingPane.setTask(3); // task = show path
						btnStartSearch.setText("Start another search");
						btnStartSearch.setEnabled(true); // Enable Start button for the next search
						info1.setText("Info: ");
						if (oper.getSearchPath().size() > 1) btnShowNextPathPart.setVisible(true);
						drawingPane.repaint();
					} else info.setText("No valid position; try again!");
				}
			}
		});

		JPanel listPanel = new JPanel(); // Panel for Object List
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		Vector<Vertex>vertexVectorObjects = new Vector<Vertex> (); // Vector of object vertices in database
		Vector<String> nameVectorObjects = new Vector<String> (); // Vector of names of objects
		for (Vertex v: db.getVerticesOfClass("Object")) {
			nameVectorObjects.add(v.getProperty("Name"));
			vertexVectorObjects.add(v);
		};
		objectList.setListData(nameVectorObjects);
		objectList.addListSelectionListener(new ListSelectionListener () {
			@Override
			public void valueChanged(ListSelectionEvent lsEvent) {
				if (!objectList.isSelectionEmpty()){
					searchObject = vertexVectorObjects.get(objectList.getSelectedIndex());
					drawingPane.setTask(1); // task = get current position of search object
					info.setText("Choose current position of search object by clicking on a position.");
					objectList.setEnabled(false);
				}
			}
		});
		
		objectListLabel.setAlignmentX(LEFT_ALIGNMENT);
		objectListLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		objectList.setAlignmentX(LEFT_ALIGNMENT);
		listPanel.add(Box.createRigidArea(new Dimension(5,10)));
		listPanel.add(objectListLabel);
		listPanel.add(Box.createRigidArea(new Dimension(5,10)));
		listPanel.add(objectList);
		contentPane.add(listPanel, BorderLayout.EAST);
		objectListLabel.setVisible(false);
		objectList.setVisible(false);

		info = new JLabel ();
		
		btnStartSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info1.setText("User action: ");
				info.setText("Choose search object out of list");
				info.setVisible(true);
				drawingPane.setTask(0);
				oper.clearSearchPath();
				objectListLabel.setVisible(true);
				objectList.setVisible(true);
				objectList.clearSelection();
				objectList.setEnabled(true);
				btnStartSearch.setEnabled(false);
				return;
			}
		});
		buttonPanel.add(btnStartSearch);
		
		btnShowNextPathPart.setVisible(false);
		btnShowNextPathPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnShowNextPathPart.setVisible(false);
				drawingPane.setTask(4); // task = show path in parts, step for step
				drawingPane.setPathPart(0);
				info1.setText("User action: ");
				info.setText("Click anywhere on drawing to show the next search step!");
				repaint();
			}
		});
		buttonPanel.add(btnShowNextPathPart);
		
		info.setText("Click on 'Start Search' to search for an object!");
		info.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		info.setVisible(true);
		buttonPanel.add(Box.createRigidArea(new Dimension (400,1)));
		infoPanel.setBackground(Color.YELLOW);
		infoPanel.setSize(new Dimension (800, 50));
		infoPanel.setOpaque(true);
		infoPanel.add(info1);
		infoPanel.add(info);
		buttonPanel.add(infoPanel);
		this.repaint();
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

}

