import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SimulationDeposit{	
	String money;
	String account;
	String[] split=null;
	// 초기 화면
	public SimulationDeposit(String[] userInfo) {
		this.account=userInfo[0];
		this.money=userInfo[3];
	
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
				split=userTransaction.split(" ");
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
	}
	public String[] getTransaction() {
		return split;
	}
}
	
	


