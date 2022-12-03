import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TraverseFile {
	static final int TRUE=1;
	static final int FALSE=0;
	
	FileReader filereader;
	String account;
	String password;
	String bankAccounts;
	
	public TraverseFile(String fileAddress, String account, String password) {
		try {
			this.filereader=new FileReader(fileAddress);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.account=account;
		this.password=password;
	}
	// account, password check
	public String[] userAccount() {
		String[] accountSplit;
		try {
			BufferedReader reader = new BufferedReader(filereader);
			
			while((bankAccounts=reader.readLine())!=null){
				accountSplit=bankAccounts.split(" ");		
				String bankAccount=accountSplit[0];
				String bankPassword=accountSplit[1];
				
				// input == bankaccount
				if (bankAccount.equals(account) && bankPassword.equals(password)) {
					return accountSplit;
				}
			}
		}catch (IOException e2) {
			System.out.println("I/O Error!!");
		}
		return null;
	}
}
