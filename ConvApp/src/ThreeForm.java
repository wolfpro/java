import java.awt.*;
import wolfpro.*;
import handler.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

public class ThreeForm {

	private JFrame frame;
	private JTextField textField;

	private boolean visSF = false;// SetForm;
	private boolean visHF = false;// HelpForm;
	private boolean visPF = false;// PlayForm;
	private FileManager fl;
	private Analyser a;
	private int W, H;
	private Vector<Dimension> imgCor;

	private class Handler implements ActionListener {
		int mod = -1;

		Handler(int i) {
			mod = i;
		}

		private void crGUI(int mod) throws IOException, AWTException {
			if (mod == 0) {
				MyRobot mr = new MyRobot(W, H);
			}
			if (mod == 1) {
				init();
				work();
			}
		}

		public void actionPerformed(ActionEvent e) {
			String mess = "hi " + String.valueOf(mod);// textField.getText();
			try {
				crGUI(mod);
			} catch (IOException | AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame, mess, "что то",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void init() throws IOException, AWTException {
		a.screen(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		imgCor = fl.getCor();
	}

	private void work() {
		Graph graph = new Graph();
		int pos;
		for (int i = 0; i < imgCor.size(); ++i) {
			a.screen(imgCor.get(i).width, imgCor.get(i).height, W, H);
			a.make();
			if (imgCor.get(i).height < Toolkit.getDefaultToolkit()
					.getScreenSize().height / 2)
				pos = 1;
			else
				pos = 0;
			graph.add(a.getName(), pos);
		}
	}

	public void addComponentsToPane(Container pane) {
		GridLayout gl = new GridLayout();
		gl.setColumns(3);
		pane.setLayout(gl);

		JButton but[] = new JButton[3];
		for (int i = 0; i < 3; ++i) {
			String[] nc = { "set", "play", "help" };
			but[i] = new JButton(nc[i]);
			but[i].addActionListener(new Handler(i));
			pane.add(but[i]);
		}

	}

	public void CreateAndShowGUI() throws Exception {
		a = new Analyser();
		fl = new FileManager();
		a.init(fl.getImages());
		Dimension d = a.getSize();
		W = d.width + 20;
		H = d.height + 20;

		frame = new JFrame("ScanApp v0.9");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(frame.getContentPane());
		frame.setPreferredSize(new Dimension(200, 70));
		frame.setSize(new Dimension(200, 70));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// System.out.println(frame.getSize());
		frame.setBounds((screenSize.width - frame.getWidth()),
				(screenSize.height - frame.getHeight()) / 3, frame.getWidth(),
				frame.getHeight());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

}