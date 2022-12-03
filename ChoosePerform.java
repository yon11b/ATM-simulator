import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ChoosePerform extends JFrame {
	private JButton balanceBtn = new JButton("Balance");
	private JButton withdrawBtn = new JButton("Withdraw");
	private JButton depositBtn = new JButton("Deposit");
	String[] userSession;
	
	public ChoosePerform(String[] userSession) {
		this.userSession=userSession;
		
		setTitle("What do you want to do?");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER,20,70));
		
		balanceBtn.addActionListener(new BalanceBtnActionListener());
		withdrawBtn.addActionListener(new WithdrawBtnActionListener());
		depositBtn.addActionListener(new DepositBtnActionListener());
		
		c.add(balanceBtn);
		c.add(withdrawBtn);
		c.add(depositBtn);
		
		setSize(350,250);
		setVisible(true);
	}
	private class BalanceBtnActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {			
			new BalancePerform(userSession);
		}
	}
	private class WithdrawBtnActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new WithdrawPasswordCheck(userSession);
			setVisible(false);
		}
	}
	private class DepositBtnActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new DepositPerform(userSession);
			setVisible(false);
		}
	}
}
