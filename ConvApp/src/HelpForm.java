import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class HelpForm {
	private static JFrame frame;

	private static void addComponentsToPane(Container pane) throws IOException {
		// GridLayout gl = new GridLayout();
		// gl.setColumns(1);
		// pane.setLayout(gl);
		//GridLayout gl = new GridLayout();
		//gl.setColumns(1);
		
		
		// JTextField ta = new JTextField("adssad");
		File f = new File("help");
		BufferedReader reader = new BufferedReader(new FileReader(f));

		Vector<String> st= new Vector<String>();
		String s;
		while ((s= reader.readLine())!=null) {
			st.add(s);
			System.out.println(s);
		}
		JList<String> jl = new JList<String>(st);
		jl.setLayoutOrientation(JList.VERTICAL);
		pane.add(new Label("вавsad"));
		pane.add(new Label("s"));
		pane.add(jl);
		reader.close();
	}

	public static void CreateAndShowGUI() throws Exception {
		frame = new JFrame("Help ScanApp");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(frame.getContentPane());
		frame.setPreferredSize(new Dimension(400, 500));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((screenSize.width - frame.getWidth()),
				(screenSize.height - frame.getHeight()), frame.getWidth(),
				frame.getHeight());
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		CreateAndShowGUI();
	}

}