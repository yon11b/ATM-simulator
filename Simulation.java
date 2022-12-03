import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class DoSimulation extends JFrame implements Runnable {
	private String account;
	private String password;
	private String balance;
	
	@Override
	public void run() {
		new UserInfoCheck();
	}
}
public class Simulation extends JFrame {
	private String[] userInfo;
	public Simulation() {
		
		// simulation execute
		Thread th = new Thread(new DoSimulation());
		th.start();
	}
}
class ShowExcecute extends JFrame {
	Container c = getContentPane();
    private JTextArea textArea=new JTextArea(12,50);
    
	public ShowExcecute() {
		setTitle("Excecuting..");
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		c.setLayout(new FlowLayout());		
		c.add(textArea);
		c.add(new JScrollPane(textArea));
		setSize(900,400);
		setVisible(true);
	}
	public void add(String[] userInfo) {
		textArea.append("\n<RESULT>\n");
		// Not found
		if (userInfo[0].equals("[ERROR]")) {
			if (userInfo[1].equals("Invalid")) {
				textArea.append("[Error] Invalid account!!");
			}
			else if (userInfo[1].equals("balance")) {			
				textArea.append("[Error] balance is insufficient!!");
			}
		}
		// balance
		else if (userInfo[2].equals("balance")) {
			textArea.append("["+userInfo[2]+"]"+" Account: "+userInfo[0]+" Balance is: "+userInfo[1]+".");
		}
		// deposit and withdraw
		else{	
			textArea.append("["+userInfo[3]+"]"+" Account: "+userInfo[0]+" "+userInfo[4]+" won"+" Total money is: "+userInfo[1]+".");			
		}	
		textArea.append("\n===========================\n");
		setVisible(true);
	}
	public void beforeExecute(String[] simulationSplit) {
		textArea.append("<INPUT>"+"\n");
		textArea.setFont(new Font("Serif",Font.BOLD,20));
		
		for (int i=0;i<simulationSplit.length;i++) {
			textArea.append(simulationSplit[i]+" ");
		}
	}
}
class UserInfoCheck{
	ShowExcecute showExcecute= new ShowExcecute();
	
	public UserInfoCheck() {
		String account;
		String password;
		String[] userInfo=null;
		TraverseFile traversefile;
		
		try {
			BufferedReader reader = new BufferedReader( new FileReader("C:\\Temp\\simulation.txt"));
			String str;
			
			// 맨 마지막 줄에 도달한다.
			while((str=reader.readLine())!=null){
				String[] split=str.split(" ");
				
				showExcecute.beforeExecute(split);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				account=split[0];
				password=split[1];
				traversefile=new TraverseFile("C:\\Temp\\bankaccount.txt",account,password);			
				
				
				userInfo=traversefile.userAccount();				
				
				// user 정보가 존재하지 않을 경우
				if (userInfo==null) {
					str="[ERROR] Invalid account!!";
					split=str.split(" ");
					showExcecute.add(split);
					continue;
				}
				
				else if (userInfo!=null) {
					account=userInfo[0];
					password=userInfo[1];
					if (split[2].equals("withdraw")) {
						//해당 계좌의 latest 내역을 불러와서 simulations.txt의 내용을 토대로 transactions.txt을 갱신함.
						SimulationWithdraw simulationWithdraw=new SimulationWithdraw(split);						
						showExcecute.add(simulationWithdraw.getTransaction());
					}
					else if(split[2].equals("deposit")) {
						SimulationDeposit simulationDeposit = new SimulationDeposit(split);
						showExcecute.add(simulationDeposit.getTransaction());
					}
					else if (split[2].equals("balance")) {
						SimulationBalance simulationBalance = new SimulationBalance(split);
						showExcecute.add(simulationBalance.getTransaction());
					}
				}
			}			
			reader.close();
		}catch (IOException e) {
			System.out.println("I/O Error!!");
		}	
	}
}

		
	

