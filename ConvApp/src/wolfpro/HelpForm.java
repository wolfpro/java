package wolfpro;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

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

        Vector<String> st = new Vector<String>();
        String s;
        while ((s = reader.readLine()) != null) {
            st.add(s);
            // System.out.println(s);
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
        int _x = screenSize.width - frame.getWidth();
        int _y = screenSize.height - frame.getHeight();
        frame.setBounds(_x,
                _y, frame.getWidth(),
                frame.getHeight());
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }
/*
    public static void main(String[] args) throws Exception {
		CreateAndShowGUI();
	}*/

}