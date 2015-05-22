package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

/**
 * Multi purpose dialog to define Objects, Object Concepts, Locations and Location Concepts.
 * The caller indicates the actual task by the first parameter of the constructor.
 * 
 * @author hulin
 *
 */
public class ValueDialog  extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextArea description;
	private OrientGraph db;
	private JTextField x;
	private JTextField y;
	private JTextField z;
	private JList<String> conceptList;
	
	/**
	 * Create the dialog.
	 */
	public ValueDialog(String task, OrientGraph db) {
		this.db = db;
		Vector<Vertex>vertexVectorConcepts = new Vector<Vertex> ();
		setBounds(100, 100, 482, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{62, 66, 86, 58, 84, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblName = new JLabel(task + " Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.WEST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 0;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			name = new JTextField();
			name.setText("My Name");
			GridBagConstraints gbc_ObjectName = new GridBagConstraints();
			gbc_ObjectName.fill = GridBagConstraints.HORIZONTAL;
			gbc_ObjectName.insets = new Insets(0, 0, 5, 5);
			gbc_ObjectName.gridx = 1;
			gbc_ObjectName.gridy = 0;
			contentPanel.add(name, gbc_ObjectName);
			name.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.anchor = GridBagConstraints.WEST;
			gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescription.gridx = 0;
			gbc_lblDescription.gridy = 1;
			contentPanel.add(lblDescription, gbc_lblDescription);
		}
		{
			description = new JTextArea();
			description.setRows(3);
			description.setLineWrap(true);
			description.setColumns(10);
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.gridwidth = 3;
			gbc_textArea.insets = new Insets(0, 0, 5, 5);
			gbc_textArea.anchor = GridBagConstraints.NORTHWEST;
			gbc_textArea.gridx = 1;
			gbc_textArea.gridy = 1;
			contentPanel.add(description, gbc_textArea);
		}
		{
			JLabel lblx = new JLabel();
			lblx.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblx = new GridBagConstraints();
			gbc_lblx.anchor = GridBagConstraints.EAST;
			gbc_lblx.insets = new Insets(0, 0, 5, 5);
			gbc_lblx.gridx = 0;
			gbc_lblx.gridy = 2;
			contentPanel.add(lblx, gbc_lblx);
		}
		{
			JLabel lbly = new JLabel();
			lbly.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lbly = new GridBagConstraints();
			gbc_lbly.anchor = GridBagConstraints.EAST;
			gbc_lbly.insets = new Insets(0, 0, 5, 5);
			gbc_lbly.gridx = 1;
			gbc_lbly.gridy = 2;
			contentPanel.add(lbly, gbc_lbly);
		}
		{
			JLabel lblz = new JLabel();
			GridBagConstraints gbc_lblz = new GridBagConstraints();
			gbc_lblz.insets = new Insets(0, 0, 5, 5);
			gbc_lblz.gridx = 2;
			gbc_lblz.gridy = 2;
			contentPanel.add(lblz, gbc_lblz);
		}
		{
			x = new JTextField();
			GridBagConstraints gbc_X = new GridBagConstraints();
			gbc_X.insets = new Insets(0, 0, 5, 5);
			gbc_X.fill = GridBagConstraints.HORIZONTAL;
			gbc_X.gridx = 0;
			gbc_X.gridy = 3;
			contentPanel.add(x, gbc_X);
			x.setColumns(10);
		}
		{
			y = new JTextField();
			GridBagConstraints gbc_Y = new GridBagConstraints();
			gbc_Y.insets = new Insets(0, 0, 5, 5);
			gbc_Y.fill = GridBagConstraints.HORIZONTAL;
			gbc_Y.gridx = 1;
			gbc_Y.gridy = 3;
			contentPanel.add(y, gbc_Y);
			y.setColumns(10);
		}
		{
			z = new JTextField();
			GridBagConstraints gbc_Z = new GridBagConstraints();
			gbc_Z.insets = new Insets(0, 0, 5, 5);
			gbc_Z.fill = GridBagConstraints.HORIZONTAL;
			gbc_Z.gridx = 2;
			gbc_Z.gridy = 3;
			contentPanel.add(z, gbc_Z);
			z.setColumns(10);
		}
		{
			JLabel lblConcepts = new JLabel(task + " Concept");
			GridBagConstraints gbc_lblConcepts = new GridBagConstraints();
			gbc_lblConcepts.insets = new Insets(0, 0, 0, 5);
			gbc_lblConcepts.gridx = 0;
			gbc_lblConcepts.gridy = 4;
			contentPanel.add(lblConcepts, gbc_lblConcepts);
		}
		if (task.equals("Object")||task.equals("Location")||task.equals("Location Concept")) {
			conceptList = new JList<String>();
			Vector<String> nameVectorConcepts = new Vector<String> ();
			String classNameToFetch = task;
			if (task.equals("Location Concept")) classNameToFetch = "LocationConcept";
			else if (task.equals("Object Concept")) classNameToFetch = "ObjectConcept";
			for (Vertex v: db.getVerticesOfClass(classNameToFetch)) {
				nameVectorConcepts.add(v.getProperty("Name"));
				vertexVectorConcepts.add(v);
			};
			conceptList.setListData(nameVectorConcepts);
			GridBagConstraints gbc_conceptList = new GridBagConstraints();
			gbc_conceptList.insets = new Insets(0, 0, 0, 5);
			gbc_conceptList.fill = GridBagConstraints.BOTH;
			gbc_conceptList.gridx = 1;
			gbc_conceptList.gridy = 4;
			contentPanel.add(conceptList, gbc_conceptList);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent click) {
						if (name.getText().equals("")) {
							name.setText("Name is mandatory");
							name.setForeground(Color.RED);
						}
						else {
							Vertex newObject = db.addVertex("class:ObjectInstance", "Name", name.getText(), "Description", description.getText());
//							ODatabaseDocumentTx dbDocument = db.getRawGraph();
							if (!name.getText().equals("")) {
								try {
									Float l = Float.parseFloat(x.getText());
									Float w = Float.parseFloat(y.getText());
									Float h = Float.parseFloat(z.getText());
									ODocument sizeDoc = new ODocument ("Size3D");
									sizeDoc.field("Length", l);
									sizeDoc.field("Width", w);
									sizeDoc.field("Hight", h);
									newObject.setProperty("Size", sizeDoc);
								} catch (Exception e) {
									e.printStackTrace();
									x.setText("Float Nr");
									x.setForeground(Color.RED);
									y.setText("Float Nr");
									y.setForeground(Color.RED);
									z.setText("Float Nr");
									z.setForeground(Color.RED);
								}
							}
							if (!conceptList.isSelectionEmpty()) {
								int indexList [] = conceptList.getSelectedIndices();
								for (int i : indexList) {
									db.addEdge(null, newObject, vertexVectorConcepts.get(i), "IS_A");
									
								}
							}
//							String storeSize = "Insert into ObjectInstance (Name, Description, Size) values (?,?,";
//							storeSize = storeSize + "{\"@type\":\"d\",\"@version\":0,\"@class\":\"Size3D\",\"Length\":";
//							storeSize = storeSize + "?,\"Width\":?,\"Hight\":?,\"@fieldTypes\":\"Length=f,Width=f,Hight=f\"})";
//							System.out.println(ObjectName.getText() + Description.getText() + Length.getText());
//							System.out.println(storeSize);
//							db.command(new OCommandSQL (storeSize)).execute(ObjectName.getText(),Description.getText(),Length.getText(),Width.getText(),Hight.getText());
							db.commit();
							setVisible(false);
						}
					}
					
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						name.setText("");
						description.setText("");
					}
				});

				cancelButton.setActionCommand("Cancel");

				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
