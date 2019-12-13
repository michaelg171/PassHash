package application.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Main;
import application.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController {

	@FXML
	Button BACK;
	@FXML
	Button HashIt;
	@FXML
	TextField ACCT1;
	@FXML
	TextField PW1;
	@FXML
	Label warning;
	@FXML
	Button Done;
	@FXML
	Button Continue;
	
	private int count =0;
	
	public void HashIt(ActionEvent event){
		String AcctName = ACCT1.getText();
		String PWord = PW1.getText();
		
		///////////////////////////////////////////////////////////////
		
		for(int i =0; i<WelcomeController.Users.size();i++){
			String temp = AcctName;
			temp+=",";
			temp+=PWord;
			User use = (User) WelcomeController.Users.get(i);
			
			if(use.getName().equals(WelcomeController.name)){
				use.accounts.add(temp);
			}
		}
		////////////////////////////////////////////////////////
		
		if(count!=10){
			
		File file = new File("A:/PassHash"+"/"+WelcomeController.name);
			if(file.isDirectory()){
				file = new File("A:/PassHash"+"/"+WelcomeController.name+"/"+"Passwords.txt");
				if(file.isFile()){
					try {
						FileWriter writer = new FileWriter(file, true);
						writer.write(String.format("%s, %s\n",ACCT1.getText(), PW1.getText()));
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
					
						file.createNewFile();
						FileWriter writer = new FileWriter(file);
						writer.write(String.format("%s, %s\n", ACCT1.getText(), PW1.getText()));
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//file write
				}
			}else{
				new File("A:/PassHash"+"/"+WelcomeController.name).mkdir();
				file = new File("A:/PassHash"+"/"+WelcomeController.name+"/"+"Passwords.txt");
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					FileWriter writer = new FileWriter(file);
					writer.write(String.format("%s, %s\n",ACCT1.getText(), PW1.getText()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		ACCT1.clear();
		PW1.clear();
		count+=1;
		
		}else{
			warning.setText("Max Amount Reached Redirecting to account");
			try {
				
				Stage primaryStage = Main.stage;
				Parent root = FXMLLoader.load(getClass().getResource("/UserMenu.fxml"));
				Scene scene = new Scene(root,600,600);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void Quit(ActionEvent event){

		File[] children;	
		File Dir = new File(WelcomeController.dir+"/"+WelcomeController.name);
		
		 if (Dir.isDirectory()){
			 if(Dir.list().length>0){
			 children = Dir.listFiles();
			 for(int i =0; i<children.length;i++){
				children[i].delete();
				 System.out.println(children[i]);
			 	}
			 }
			 Dir.delete();
		 }
		 WelcomeController.Users.remove(WelcomeController.Users.size()-1);
		
		
		try {
			Stage primaryStage=Main.stage;
			Parent root = FXMLLoader.load(getClass().getResource("/MMenu.fxml"));
			Scene scene = new Scene(root,600,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void Continue(ActionEvent event){
		
		try {
			
			Stage primaryStage = Main.stage;
			Parent root = FXMLLoader.load(getClass().getResource("/UserMenu.fxml"));
			Scene scene = new Scene(root,600,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

