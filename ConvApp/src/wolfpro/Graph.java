package wolfpro;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Graph {

    static Vector<String> lcol;
    static Vector<String> rcol;
    private static DefaultListModel listModelLeft = new DefaultListModel<String>();
    private static DefaultListModel listModelRight = new DefaultListModel<String>();
    private JFrame frame;
    private JTextField textField;

    public void addComponentsToPane(Container pane) {
        GridLayout gl = new GridLayout();
        gl.setColumns(2);
        ;
        pane.setLayout(gl);

        textField = new JTextField();
        JList listLeft = new JList(listModelLeft);
        JList listRight = new JList(listModelRight);

        JScrollPane scpaneL = new JScrollPane(listLeft);
        JScrollPane scpaneR = new JScrollPane(listRight);

        pane.add(scpaneL);
        pane.add(scpaneR);
    }

    void addlist(String s, int indificator) {
        if (indificator == 0) {
            listModelLeft.addElement(s);
        } else
            listModelRight.addElement(s);
    }

    private void createAndShowGUI() throws Exception {
        frame = new JFrame("ConvApp v0.9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.setPreferredSize(new Dimension(200, 400));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - frame.getWidth()) / 2,
                (screenSize.height - frame.getWidth()) / 2, frame.getWidth(),
                frame.getHeight());
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public void add(String s, int mode) {
        if (mode == 0) {
            listModelLeft.addElement(s);
            lcol.add(s);
        }
        if (mode == 1) {
            listModelRight.addElement(s);
            rcol.add(s);
        }
    }

    // Аргументы у этой функции были убраны.
    public void run() {

        lcol = new Vector<String>();
        rcol = new Vector<String>();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {// Рисование
            // формы;
            public void run() {
                try {
                    new Graph().createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

		/*
         * for(int i=0; i<100; i++)//Заполнение левого и правого списка;
		 * add(String.valueOf(i), i%2);
		 */
    }

}
