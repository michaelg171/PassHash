package application.Model;

public class Account {
	String name;
	String Password;
	
	public Account(String name, String Password){
		this.name = name;
		this.Password= Password;
	}
	public String toString(){
		return this.name+" "+this.Password;
	}
}
