package startApplication;

import java.awt.EventQueue;

import gui.MainFrameRWM;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class StartGUI {

	public static void main(String[] args) {
		OrientGraphFactory factory = new OrientGraphFactory("remote:localhost/orientdb/databases/RobotWorld", "admin", "admin");
		final OrientGraph db = factory.getTx();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrameRWM frame = new MainFrameRWM(db);
					frame.setBounds(0, 0, 1250, 800);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
