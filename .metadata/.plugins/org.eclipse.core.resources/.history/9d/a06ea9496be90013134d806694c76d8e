import handler.Analyser;
import wolfpro.Graph;
import wolfpro.HelpForm;
import wolfpro.MyRobot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ThreeForm {

	private JFrame frame;
	private JTextField textField;
	private MainThread mainThread;

	private boolean visSF = false;// SetForm;
	private boolean visHF = false;// HelpForm;
	private boolean visPF = false;// PlayForm;
	private FileManager fl;
	private Analyser a;
	private int W, H;
    private Vector<Rectangle> imgCor;
    private Graph graph;

    public ThreeForm() throws Exception {
        mainThread = new MainThread(this);
	}

	private void init() throws Exception {
		// a.screen(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
		// Toolkit.getDefaultToolkit().getScreenSize().height);
		imgCor = fl.getCor();
	}

	private void work() {
		graph = new Graph();
		graph.run();
		mainThread.setRunning(true);
		mainThread.start();
	}

	public void fun() {
        boolean somethingGoesWrong = false;
        String[] names = new String[imgCor.size()];
        int[] pos = new int[imgCor.size()];
        int i = 0;
        for (Rectangle area : imgCor) {
            a.screen(area);
            a.make();
            String name = a.getName();
            pos[i] = (area.y < Toolkit.getDefaultToolkit().getScreenSize().height / 2) ? 0 : 1;
            names[i] = name;
            if (name == null) {
                somethingGoesWrong = true;
            }
        }
        if (somethingGoesWrong) {
            mainThread.somethingWrong();
        } else {
            for (int j = 0; j < names.length; j++) {
                graph.add(names[j], pos[j]);
            }
        }
    }

	public void addComponentsToPane(Container pane) {
		GridLayout gl = new GridLayout();
		gl.setColumns(3);
		pane.setLayout(gl);

		JButton but[] = new JButton[3];
		String[] nc = { "set", "play", "help" };
		for (int i = 0; i < 3; ++i) {
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

    // Применяется в MainThread для доступа к координатам для скриншотов
    public Vector<Rectangle> getScreenAreas() {
        return imgCor;
    }

	private class Handler implements ActionListener {
		int mod = -1;

		Handler(int i) {
			mod = i;
		}

		private void crGUI(int mod) throws Exception {
			if (mod == 0) {
				MyRobot mr = new MyRobot(W, H);
			}
			if (mod == 1) {
				init();
				work();
			}
			if (mod == 2) {
				HelpForm hp = new HelpForm();
				hp.CreateAndShowGUI();
			}
		}

		public void actionPerformed(ActionEvent e) {
			String mess = "hi " + String.valueOf(mod);// textField.getText();
			try {
				crGUI(mod);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*JOptionPane.showMessageDialog(frame, mess, "что то",
					JOptionPane.INFORMATION_MESSAGE);*/
		}
	}

}
