package application.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	private String name;
	public ArrayList accounts;
	private String password;
	
	public User(String Name){
		this.name= Name;
		this.accounts= new ArrayList<Account>();
	}
	public String getName(){
		return this.name;
	}
	public String getPassword(){
		return this.password;
	}
	public void CreatePassword(String P){
		this.password =P;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String toString(){
		return this.name;
	}
	public void FillAcct(){
		File file = new File("A:/PassHash"+"/"+this.name+"/"+"Passwords.txt");
		try {
			Scanner Scan = new Scanner(file);
			while(Scan.hasNextLine()){
				String temp = Scan.nextLine();
				String []token = temp.split(",");
				Account acct = new Account(token[0], token[1]);
				this.accounts.add(acct);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
