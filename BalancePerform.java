import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BalancePerform extends JFrame {
	String account;
	Container c = getContentPane();
	private JLabel money = new JLabel();
	public BalancePerform(String[] userSession) {
		this.account=userSession[0];
		setTitle("Get balance");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c.setLayout(new FlowLayout());
		
		money.setText("My balance is...");
		c.add(money);
		c.add(new JLabel(getBalance()));
		
		JButton exit=new JButton("EXIT");
		exit.addActionListener(new ExitActionListener());
		c.add(exit);
		setSize(300,200);
		setVisible(true);
	}
	public String getBalance() {
		String nowBalance=null;
		String nowAccount=null;
		// 현재 잔고 구하기
		try {
			BufferedReader reader = new BufferedReader( new FileReader("C:\\Temp\\transactions.txt"));
			String str;
			String userTransaction=null;
			
			// Traverse the transactions.txt
			while((str=reader.readLine())!=null){
				String[] split=str.split(" ");
				nowAccount=split[0];
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
				// index 1 is now_balance
				String[] split=userTransaction.split(" ");
				nowBalance=split[1];	
			}
			
			reader.close();
		}catch (IOException e) {
			System.out.println("I/O Error!!");
		}
		return nowBalance;
		
	}
	private class ExitActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}