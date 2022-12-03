
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame {
	private JButton loginBtn = new JButton("Login");
	private JButton simulationBtn = new JButton("Simulation");
	public Main() {
		setTitle("ATM calculator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,35,75));
		
		loginBtn.addActionListener(new LoginBtnActionListener());
		simulationBtn.addActionListener(new SimulationBtnActionListener());
		
		c.add(loginBtn);
		c.add(simulationBtn);
		
		setSize(300,200);
		setVisible(true);
		
	}
	private class LoginBtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new LoginPerform();
			setVisible(false);
		}
	}
	private class SimulationBtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new Simulation();
		}
	}

}
