import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import wolfpro.MyRobot;
public class ThreeForm {

	private JFrame frame;
	private JTextField textField;
	
	private boolean visSF=false;//SetForm;
	private boolean visHF=false;//HelpForm;
	private boolean visPF=false;//PlayForm;
	//private

	private class Handler implements ActionListener{
		int mod=-1;
		
		Handler(int i){
			mod = i;
			
		}
		private void crGUI(int mod){
			if(mod == 0){
				MyRobot mr  = new MyRobot();
			}			
			
		}
		public void actionPerformed(ActionEvent e){
			String mess= "hi "+String.valueOf(mod);//textField.getText();
			crGUI(mod);
			JOptionPane.showMessageDialog(frame, mess, "что то", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void addComponentsToPane(Container pane) {
		GridLayout gl = new GridLayout();
		gl.setColumns(3);
		pane.setLayout(gl);

		JButton but[]=new JButton[3];//Three buttons;
		for(int i=0; i<3; ++i){
			String[] nc={"set", "play", "help"};
			but[i]=new JButton(nc[i]);
			but[i].addActionListener(new Handler(i));
			pane.add(but[i]);
		}
		
	}

	 void CreateAndShowGUI() throws Exception {
		frame = new JFrame("ScanApp v0.9");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(frame.getContentPane());
		frame.setPreferredSize(new Dimension(200, 70));
		frame.setSize(new Dimension(200, 70));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.println(frame.getSize());
		frame.setBounds((screenSize.width-frame.getWidth()),
				(screenSize.height - frame.getHeight())/3, frame.getWidth(),
				frame.getHeight());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}
	
}
