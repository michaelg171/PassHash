package application.Controller;

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

public class ChgPwController implements Initializable {
	@FXML
	Button BACK;
	@FXML
	Button HashIt;
	@FXML

	
	ArrayList labels = new ArrayList<Label>();
	public void HashIt(ActionEvent event){
	
	}
	public void Back(ActionEvent event){
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		}
	}