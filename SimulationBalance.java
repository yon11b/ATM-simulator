import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class SimulationBalance extends JFrame {
	String account;
	String[] split=null;
	String nowBalance=null;
	String nowAccount=null;
	
	public SimulationBalance(String[] userInfo) {
	account=userInfo[0];
	// 현재 잔고 구하기
	try {
		BufferedReader reader = new BufferedReader( new FileReader("C:\\Temp\\transactions.txt"));
		String str;
		String userTransaction=null;
		
		// Traverse the transactions.txt
		while((str=reader.readLine())!=null){
			split=str.split(" ");
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
	}
	public String[] getTransaction() {
		split= new String[]{nowAccount,nowBalance,"balance"};
		return split;
	}
}