import java.awt.AWTException;
import handler.*;
import wolfpro.*;


public class Main {	
	static ThreeForm tf;
	static Analyser analys;
	public static void main(String[] args) throws AWTException {
		analys = new Analyser();		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					 tf = new ThreeForm();
					 tf.CreateAndShowGUI();
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

}
