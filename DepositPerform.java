import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DepositPerform extends JFrame implements ActionListener{
	JTextField tf=new JTextField(10);
	String money;
	JButton button;
	String account;

	Container c= getContentPane();
	// init window
	public DepositPerform(String[] userSession) {
		this.account=userSession[0];
		setTitle("Deposit window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(new FlowLayout());
		c.add(new JLabel("Please Input money."));	
		c.add(tf);
		
		button = new JButton("Deposit");
		button.addActionListener(this);
		c.add(button);

		JButton exit=new JButton("EXIT");
		exit.addActionListener(new ExitActionListener());
		c.add(exit);
		setSize(300,200);
		setVisible(true);
	}
	
	public void reTransactions() {
		money=tf.getText();
		FileWriter fileWrite=null;
		InputStreamReader in= null;
		FileInputStream fin=null;
		String nowBalance="";
		String nowAccount;
		
		// Get now balance
		try {
			BufferedReader reader = new BufferedReader( new FileReader("C:\\Temp\\transactions.txt"));
			String str;
			String userTransaction=null;

			// Traverse the transactions.txt
			while((str=reader.readLine())!=null){
				String[] split=str.split(" ");
				nowAccount=split[0];
				//System.out.println(nowAccount);
				if (nowAccount.equals(account)) {
					userTransaction=new String(str);
				}
			}
			// If transactions don't exit...
			if (userTransaction==null) {
				nowBalance="0";
			}
			// If transactions exit...
			else {
				// index 1 is now balance
				String[] split=userTransaction.split(" ");
				nowBalance=split[1];	
			}
			//System.out.println("이전 현재 잔액: "+Integer.parseInt(nowBalance));
			//System.out.println("입금할 금액: "+Integer.parseInt(money));
			
			nowBalance=String.valueOf(Integer.parseInt(nowBalance)+Integer.parseInt(money));
			//System.out.println("갱신한 현재 잔액: "+Integer.parseInt(nowBalance));
			reader.close();
		}catch (IOException e) {
			System.out.println("I/O Error!!");
		}
		
		// update the transactions.txt
		try {
			fileWrite=new FileWriter("C:\\Temp\\transactions.txt",true);	

			// account
			fileWrite.write("\n");
			fileWrite.write(account);

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
			fileWrite.write("deposit");

			// money				
			fileWrite.write(" ");	
			fileWrite.write(money);
			
			System.out.println("SUCCESS!");
			
			fileWrite.close();			
		}catch (IOException e) {
			System.out.println("I/O Error!!");
		}
		c.add(new JLabel("Deposit Success and now balance is "));			
		c.add(new JLabel(nowBalance+" Won"));					
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			reTransactions();
		}
	}
	
	private class ExitActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
	
	


