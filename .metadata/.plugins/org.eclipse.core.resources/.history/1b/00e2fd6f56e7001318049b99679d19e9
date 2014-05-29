package wolfpro;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HelpForm {
    private JFrame frame;

    private void addComponentsToPane(Container pane) throws IOException {
        GridLayout gl = new GridLayout();
        gl.setColumns(1);
        pane.setLayout(gl);

        JTextArea ta = new JTextArea();
        File f = new File("help");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String s;
        while ((s = reader.readLine()) != null) {
            ta.add(new Label(s));
        }
        pane.add(ta);
    }

    public void CreateAndShowGUI() throws Exception {
        frame = new JFrame("Help ScanApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setPreferredSize(new Dimension(400, 500));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - frame.getWidth()), (screenSize.height - frame.getHeight()), frame.getWidth(), frame.getHeight());
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public void main(String[] args) throws Exception {
        CreateAndShowGUI();
    }

}
