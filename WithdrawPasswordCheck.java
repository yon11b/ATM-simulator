import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

public class WithdrawPasswordCheck extends JFrame implements ActionListener{
	private JTextField inputPasswordField = new JTextField(10);
	private JButton button = new JButton("확인");
	FileReader filereader;

	static final int TRUE=1;
	static final int FALSE=0;
	String[] userSession;
	String account;
	String password;
	String bankAccounts;
	Container c = getContentPane();
	
	public WithdrawPasswordCheck(String[] userSession) {
		this.userSession=userSession;
		setTitle("Before withdraw");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.setLayout(new FlowLayout());

		c.add(new JLabel("password "));
		c.add(inputPasswordField);
		
		button.addActionListener(this);
		c.add(button);
		
		setSize(300,250);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String inputPassword = inputPasswordField.getText();
		String bankAccounts;
		account=userSession[0];
		
		// AfterLoginPage에 저장된 비번과 일치하면 아래 코드 실행
		TraverseFile traverseFile = new TraverseFile("C:\\Temp\\bankaccount.txt",account,inputPassword);
		String[] userInfo=traverseFile.userAccount();
		
		if (userInfo!=null) {
			new WithdrawPerform(userInfo);
			setVisible(false);
		}	
		else{ // invalid password 
			c.add(new JLabel("Invalid password!!"));
			setVisible(true);
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
