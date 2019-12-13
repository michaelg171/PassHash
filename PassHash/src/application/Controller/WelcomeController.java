package application.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Main;
import application.Model.*;
public class WelcomeController implements Initializable {
	@FXML
	Button Enter;
	@FXML
	Button NewAcc;
	@FXML
	TextField USE;
	@FXML
	TextField Pword;
	@FXML
	Label NonUser1;
	@FXML
	Button Exit;
	
	public static String User="";
	public static File dir = new File("A:/PassHash");
	public static ArrayList<User> Users = new ArrayList<User>();
	public static String name;
	private int count=0;
	
	public void Enter(ActionEvent event){
		name = USE.getText();
	File Tdir = new File(this.dir+"/"+USE.getText());
			if(Tdir.isDirectory()){
				this.verify();
				this.GetUser();
			
				}else{
					NonUser1.setText("User not found\nPerhaps create and account?");
					count+=1;
			
					if(count>3){
						System.exit(0);
					}
				}
			}
	public void NewUser(ActionEvent event){
		name = USE.getText();
		User use = new User(USE.getText());
		
		use.CreatePassword(Pword.getText());
		Users.add(use);
		
		new File (dir+"/"+USE.getText()).mkdirs();
		File file = new File(dir+"/"+USE.getText()+"/"+"PW.txt");
		
		
		try {
			FileWriter writer = new FileWriter(file);	
			writer.write(Pword.getText());
			writer.close();
		} catch (IOException e) {
			System.out.println("this is the error");
			e.printStackTrace();
		}
		try {
			
			Stage primaryStage = Main.stage;
			Parent root = FXMLLoader.load(getClass().getResource("/CreationMenu.fxml"));
			Scene scene = new Scene(root,600,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public  void GetUser(){
		String password="";
		String temp = USE.getText();
	
		File[]listOfFiles = dir.listFiles();
		
		for (int i =0; i<listOfFiles.length;i++){
				User use = new User(listOfFiles[i].getName());
				File file = new File(dir+"/"+use.getName()+"/"+"PW.txt");
		
				try {
					Scanner scan = new Scanner(file);
					
					while(scan.hasNext()){
						password=scan.nextLine();
						use.setPassword(password);
						break;
					}
					scan.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Users.add(use);
			}
		for(int i =0; i< Users.size();i++){
			User temp2 = (User) Users.get(i);
			System.out.println(temp2+"here");
			temp2.FillAcct();
		}
	
	}
	
	public void verify(){
		for(int i=0; i<Users.size(); i++){
			User us = (User) Users.get(i);
			if(us.getName().equals(USE.getText())){
				if(us.getPassword().equals(Pword.getText())){
					try {
						
						Stage primaryStage = Main.stage;
						Parent root = FXMLLoader.load(getClass().getResource("/UserMenu.fxml"));
						Scene scene = new Scene(root,600,600);
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}else{
					NonUser1.setText("Access Denied Invalid Password\n");
					count+=1;

					if(count>3){
						System.exit(0);
					}
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetUser();
		
		
	}
	
	public void Exit(ActionEvent event){
		System.exit(0);
	}
}

