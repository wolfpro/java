package wolfpro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import java.io.*;

import javax.swing.*;

class myFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Robot robot;
	int W = 100, H = 100;
	static JPanel panel = new JPanel();
	static Container pane;
	// now;
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	static JList<String> list;
	static JPanel btnPanel = new JPanel();
	static JPanel txt = new JPanel();
	static String x = "", y = "";
	static ImageFrame frame;
	static BufferedImage image;

	String[] str(DefaultListModel<String> listModel) {
		String[] s = new String[listModel.size()];

		for (int i = 0; i < listModel.size(); i++) {
			s[i] = listModel.elementAt(i) + " " + String.valueOf(W) + " "
					+ String.valueOf(H);
			// System.out.println(s[i]);
		}
		return s;
	}

	public void save(DefaultListModel<String> listModel) throws Exception {
		OutputStream f = new FileOutputStream("data");
		String[] s = new String[listModel.size()];
		PrintWriter pr = new PrintWriter(f);
		for (int i = 0; i < listModel.size(); i++) {
			s[i] = listModel.elementAt(i) + " " + String.valueOf(W) + " "
					+ String.valueOf(H);
			pr.println(s[i]);
		}
		pr.close();
		f.close();
	}

	public myFrame() {
		setTitle("Irobot v0.9");

		list = new JList<String>(listModel);
		panel.setLayout(new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		list.setSelectedIndex(0);
		list.setFocusable(false);
		panel.add(new JScrollPane(list), BorderLayout.CENTER);

		btnPanel.setLayout(new GridLayout(1, 2, 5, 0));
		panel.add(btnPanel, BorderLayout.NORTH);
		// pane = getContentPane();

		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice screen = env.getDefaultScreenDevice();
		try {
			robot = new Robot(screen);
		} catch (AWTException ex) {
		}

		setSize(200, 200);
		Dimension bts = new Dimension(30, 20);
		JButton bt1 = new JButton("Screen");
		final JButton bt2 = new JButton("+");
		final JButton bt3 = new JButton("-");
		final JButton ok = new JButton("OK");

		bt1.setSize(bts);
		bt2.setSize(bts);
		bt3.setSize(bts);

		bt1.setFocusable(false);
		// pane.add(bt1);

		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();

		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = robot.createScreenCapture(new Rectangle(0, 0,
						screenSize.width, screenSize.height));

				frame = new ImageFrame(image, listModel);
				frame.show();
				btnPanel.removeAll();
				btnPanel.add(bt2);
				btnPanel.add(bt3);
				pack();

				bt2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						listModel.addElement(frame.element);
						list.setSelectedIndex(listModel.size() - 1);
						list.ensureIndexIsVisible(listModel.size() - 1);
						frame.repic(str(listModel));
						frame.on = true;
						frame.repaint();
						panel.add(ok, BorderLayout.SOUTH);
						pack();
						ok.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								try {
									save(listModel);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
				});
				bt3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						listModel.remove(list.getSelectedIndex());
						setVisible(false);
						frame.setVisible(false);
						// return;
						//System.exit(0);
					}
				});

			}
		});
		btnPanel.add(bt1);

		getContentPane().add(panel);
		setPreferredSize(new Dimension(200, 220));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

class ImageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String element = "you not clicked";
	// static Graphics gr;
	ImageIcon image;
	BufferedImage img;
	Container pane;
	boolean on = false;
	String[] pic;

	public void paint(Graphics gr) {
		gr.setColor(new Color(255, 0, 0));
		gr.drawImage(img, 0, 0, null);
		// System.out.println("go drow");
		String s[];
		if (on) {
			for (int i = 0; i < pic.length; i++) {
				s = pic[i].split(" ");
				// System.out.println(i+ " "+ pic[i]);
				if (!s[0].equals("you")) {
					int x = Integer.valueOf(s[0]), y = Integer.valueOf(s[1]), W = Integer
							.valueOf(s[2]), H = Integer.valueOf(s[3]);
					// gr.drawRect(10, 10, 100, 100);
					// System.out.println("I drow " + pic[i]);
					gr.drawRect(x, y, W, H);
				}
			}
		}
	}

	void repic(String[] pic) {
		this.pic = pic;
	}

	public ImageFrame(BufferedImage image, DefaultListModel listModel) {
		img = image;
		this.image = new ImageIcon(image);
		// gr = image.getGraphics();
		setTitle("ScanApp-ScreenMod");
		pane = getContentPane();
		JLabel label = new JLabel(new ImageIcon(image));
		pane.add(label);
		pack();

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String x = String.valueOf(e.getX());
				String y = String.valueOf(e.getY());
				element = x + " " + y;

				System.out.println("click!");
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
}

public class MyRobot {
	public MyRobot() {
		myFrame frame = new myFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}
