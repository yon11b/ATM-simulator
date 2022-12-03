import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WithdrawPerform extends JFrame implements ActionListener{
	
	private JTextField money = new JTextField(10);
	private JButton enterButton = new JButton("확인");
	String[] userSession;
	Container c = getContentPane();
	
	public WithdrawPerform(String[] userSession) {
		this.userSession=userSession;
		setTitle("Withdraw window");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("How much do you want to withdraw?"));
		c.add(money);
		
		enterButton.addActionListener(this);
		c.add(enterButton);
		
		setSize(350,250);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String withdrawMoney = money.getText();	
		String userTransaction=null;
		String str;
		String nowBalance=null;
		// Get now balance
		try {
			BufferedReader reader = new BufferedReader( new FileReader("C:\\Temp\\transactions.txt"));
			
			// Traverse the transactions.txt
			while((str=reader.readLine())!=null){
				String[] split=str.split(" ");
				String nowAccount=split[0];
				if (nowAccount.equals(userSession[0])) {
					userTransaction=new String(str);
				}
			}
			reader.close();
		}catch (IOException ex1) {
			ex1.printStackTrace();
		}

		// If transactions don't exit...
		if (userTransaction==null) {
			c.add(new JLabel("balance is insufficient"));
			
		}		
		// If transactions exit...
		else {
			// index 1 is now balance
			String[] split=userTransaction.split(" ");
			nowBalance=split[1];
			if (Integer.parseInt(nowBalance)<Integer.parseInt(withdrawMoney)) {
				c.add(new JLabel("balance is insufficient"));
				setVisible(true);
				return;
			}
		}
		//System.out.println("이전 현재 잔액: "+Integer.parseInt(nowBalance));
		//System.out.println("출금할 금액: "+Integer.parseInt(withdrawMoney));
		
		nowBalance=String.valueOf(Integer.parseInt(nowBalance)-Integer.parseInt(withdrawMoney));
		//System.out.println("갱신한 현재 잔액: "+Integer.parseInt(nowBalance));
		
		// update the transactions.txt
		try {
			FileWriter fileWrite=new FileWriter("C:\\Temp\\transactions.txt",true);	
						
			// account
			fileWrite.write("\n");
			fileWrite.write(userSession[0]);
			
			// now balance
			fileWrite.write(" ");
			fileWrite.write(nowBalance);
	
			// date
			fileWrite.write(" ");
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formatedNow = date.format(formatter);
			fileWrite.write(formatedNow);
			
			// transaction
			fileWrite.write(" ");
			fileWrite.write("withdraw");
			
			// money			
			fileWrite.write(" ");	
			fileWrite.write(withdrawMoney);
			
			//System.out.println("SUCCESS!");
				
			fileWrite.close();			
		}catch (IOException e2) {
			System.out.println("I/O Error!!");
		}
		c.add(new JLabel("Withdraw Success and now balance is "));			
		c.add(new JLabel(nowBalance+" Won"));			
		
		JButton exit=new JButton("EXIT");
		exit.addActionListener(new ExitActionListener());
		c.add(exit);
		setVisible(true);
	}
	private class ExitActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
