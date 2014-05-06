import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainForm {

	private JFrame frame;
	private JTextField textField;
	
	private class Handler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String mess = textField.getText();
			JOptionPane.showMessageDialog(frame, mess, "Результат", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void addComponentsToPane(Container pane) {	
		GridLayout gl = new GridLayout();
		gl.setRows(4);
		//gl.setRows(3);
		pane.setLayout(gl);
		
		JLabel textLabel = new JLabel("Выражение:");
		pane.add(textLabel);
		textField = new JTextField();
		pane.add(textField);
		JButton button = new JButton("Вычислить");
		button.addActionListener(new Handler());
		pane.add(button);
		DefaultListModel listModel = new DefaultListModel<String>();
		JList list = new JList(listModel);
		//listModel.addElement("first");
		//listModel.addElement("second");
		JScrollPane scpane = new JScrollPane(list);
		pane.add(scpane);
	}

	
	private void createAndShowGUI() throws Exception {
		frame = new JFrame("Калькулятор v0.9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setPreferredSize(new Dimension(200, 100));
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		//SwingUtilities.updateComponentTreeUI(frame);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2, frame.getWidth(), frame.getHeight());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainForm().createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}