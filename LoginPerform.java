import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginPerform extends JFrame implements ActionListener {
	static final int TRUE=1;
	static final int FALSE=0;

	Container c = getContentPane();
	private JTextField bankAccount = new JTextField(18);
	private JTextField password = new JTextField(17);
	private JButton button = new JButton("submit");
	public LoginPerform() {
		setTitle("Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("Account "));
		c.add(bankAccount);
		c.add(new JLabel("Password "));
		c.add(password);
		
		button.addActionListener(this);
		c.add(button);

		setSize(300,200);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e1) {
		String inputAccount = bankAccount.getText();
		String inputPassword = password.getText();
		String bankAccounts;
		String fileAddress="C:\\Temp\\bankAccount.txt";
		String[] userSession=null;
		
		// input validation
		TraverseFile traverseFile = new TraverseFile(fileAddress,inputAccount,inputPassword);
		userSession=traverseFile.userAccount();
		if (userSession!=null) {
			new ChoosePerform(userSession);
		}
		else {
			c.add(new JLabel("ERROR"));
			setVisible(true);
		}
	}
}

