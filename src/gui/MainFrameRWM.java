package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import gui.ValueDialog;
//import defWorlModelGUI.NewLocationEvent;
//import defWorlModelGUI.NewLocationListener;

public class MainFrameRWM extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrameRWM(OrientGraph db) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.out.println("Shutdown database connection before closing main window!");
				db.shutdown();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonPanel = new JPanel(); // Panel for buttons
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		// Action buttons for new Locations, Location Concepts, Objects and Object Concepts
		JButton btnNewObjectConcept = new JButton("New Object Concept");
		btnNewObjectConcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ValueDialog valDialog = new ValueDialog("New object concept", db);
				valDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				valDialog.setVisible(true);
			}
		});
		buttonPanel.add(btnNewObjectConcept);
		
		JButton btnNewObject = new JButton("New Object Instance");
		btnNewObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ValueDialog valDialog = new ValueDialog("Object", db);
				valDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				valDialog.setVisible(true);
			}
		});
		buttonPanel.add(btnNewObject);
		
/*		
		locW = new LocationWidget (Color.BLACK, db);
		contentPane.add(locW, BorderLayout.CENTER);
		locW.drawDB ();
		locW.addNewLocationListener(new NewLocationListener () {

			@Override
			public void newLocationCreated(NewLocationEvent e) {
				locDialog.setVisible(true);
				locDialog.setRectangle(e.getMyRectangle());
			}
			
		});
		
		locDialog = new LocationDialog (db);
		locDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		locDialog.setVisible(false);
		
		
		JButton btnNewLocation = new JButton("New Location");
		btnNewLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				locW.drawNewLocation();
			}
		});
		buttonPanel.add(btnNewLocation);
		
		JButton btnNewPosition = new JButton("New Posistion");
		btnNewPosition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnNewPosition.getText().equals("Stop")) {
					btnNewPosition.setText("New Position");
					locW.doNothing ();
				} else {
					btnNewPosition.setText("Stop");
					locW.defNewPosition();
				}
				
			}
			
		});
		buttonPanel.add(btnNewPosition);
		
		JButton btnNewPathConnection = new JButton("New Path Connection");
		buttonPanel.add(btnNewPathConnection);
		
		objDialog = new ObjectDialog(db);
		objDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		objDialog.setVisible(false);
		*/
	}
}

