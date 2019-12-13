package application.Controller;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileNotFoundException;
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

public class UserController implements Initializable{
	private ArrayList lables= new ArrayList<Label>();
	@FXML
	Button BACK;
	@FXML
	Button EDIT;
	@FXML
	Label ACCT1;
	@FXML
	Label ACCT2;
	@FXML
	Label ACCT3;
	@FXML
	Label ACCT4;
	@FXML
	Label ACCT5;
	@FXML
	Label ACCT6;
	@FXML
	Label ACCT7;
	@FXML
	Label ACCT8;
	@FXML
	Label ACCT9;
	@FXML
	Label ACCT10;

	private User use;

	
	public void Back(ActionEvent event){
		try {
			Stage primaryStage = Main.stage;
			Parent root = FXMLLoader.load(getClass().getResource("/MMenu.fxml"));
			Scene scene = new Scene(root,600,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void Edit(ActionEvent event){
		try {
			Stage primaryStage = Main.stage;
			Parent root = FXMLLoader.load(getClass().getResource("/PWchangeMNU.fxml"));
			Scene scene = new Scene(root,600,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList values = new ArrayList<String>();
		lables.add(ACCT1);
		lables.add(ACCT2);
		lables.add(ACCT3);
		lables.add(ACCT4);
		lables.add(ACCT5);
		lables.add(ACCT6);
		lables.add(ACCT7);
		lables.add(ACCT8);
		lables.add(ACCT9);
		lables.add(ACCT10);
	
		for(int i=0; i<WelcomeController.Users.size(); i++){
			this.use = (User) WelcomeController.Users.get(i);
				if(this.use.getName().equals(WelcomeController.name)){
					for(int j1=0; j1<this.use.accounts.size();j1++){
						System.out.println("are you in here2?");
						Label temp = (Label) lables.get(j1);
						temp.setText(String.format("%s", this.use.accounts.get(j1).toString()));
					}
				}
			}
		}
	}
				
			
		

	


