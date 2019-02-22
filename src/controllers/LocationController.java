package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LocationController implements Initializable {
	
	/*
	 * Variables for Location Window
	 */
	@FXML
    private Button confirmLoc; //okay button in dialog
	@FXML
    private Button cancelLoc; //cancel button in dialog
	@FXML
    private TextField connectionName; //name of connection in dialog
	@FXML
    private TextField toLocation; 
	@FXML
    private TextField fromLocation; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Bind the event handler
		confirmLoc.setOnAction(this::handleconfirm); 
		cancelLoc.setOnAction(this::handlecancel);
		
	}
	
	@FXML
	private void handleconfirm(ActionEvent event) {
		Stage stage = (Stage) confirmLoc.getScene().getWindow();
		stage.close(); //Close current window
	}
	
	@FXML
	private void handlecancel(ActionEvent event) {
	    Stage stage = (Stage) cancelLoc.getScene().getWindow();
	    stage.close(); //Close current window
	}


}
