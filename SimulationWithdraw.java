import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

public class SimulationWithdraw extends JFrame{
	String money;
	String account;
	String[] split;
	public SimulationWithdraw(String[] userInfo) {
		this.account=userInfo[0];
		this.money=userInfo[3];
	
		String withdrawMoney = money;	
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
				if (nowAccount.equals(account)) {
					userTransaction=new String(str);
				}
			}			
			reader.close();
		}catch (IOException ex1) {
			ex1.printStackTrace();
		}
		if (userTransaction==null) {
			nowBalance="0";
		}
		else {
			// If transactions exit...		
			// index 1 is now balance
			split=userTransaction.split(" ");
			nowBalance=split[1];
			//System.out.println("NowBalance"+nowBalance);
			//System.out.println("WithDrawMoney"+withdrawMoney);
		
			//System.out.println("이전 현재 잔액: "+Integer.parseInt(nowBalance));
			//System.out.println("출금할 금액: "+Integer.parseInt(withdrawMoney));
			
			nowBalance=String.valueOf(Integer.parseInt(nowBalance)-Integer.parseInt(withdrawMoney));
			//System.out.println("갱신한 현재 잔액: "+Integer.parseInt(nowBalance));
			
			if (Integer.parseInt(nowBalance)<Integer.parseInt(withdrawMoney)) {
				str="[ERROR] balance is insufficient!!";
				split=str.split(" ");
				return;
			}
		}
		// update the transactions.txt
		try {
			FileWriter fileWrite=new FileWriter("C:\\Temp\\transactions.txt",true);	
						
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
			fileWrite.write("withdraw");
			
			// money			
			fileWrite.write(" ");	
			fileWrite.write(withdrawMoney);
			
			//System.out.println("SUCCESS!");
			
			fileWrite.close();	
		}catch (IOException e2) {
			System.out.println("I/O Error!!");
		}
	}
	public String[] getTransaction() {
		return split;
	}
}
		
