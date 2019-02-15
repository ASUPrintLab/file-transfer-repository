package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application_v2.PressManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class dialogController implements Initializable {
	
	@FXML
    private Button confirmPress; //okay button in dialog
	@FXML
    private Button cancelPress; //cancel button in dialog
	@FXML
    private TextField pressName; //name of new press in dialog
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		confirmPress.setOnAction(this::handleconfirm); 
		//Invokes the action
		cancelPress.setOnAction(this::handlecancel);
		
	}
	
	@FXML
	private void handleconfirm(ActionEvent event) {
		if (pressName.getText().trim().isEmpty()) { //Check if the textfield is empty
			PressManager.setRecentAdded(false);	
		}
		else {
			PressManager.addPress(pressName.getText(), "0");
			PressManager.setRecentAdded(true);	
		}
		Stage stage = (Stage) confirmPress.getScene().getWindow();
		stage.close(); //Close current window
	}
	
	@FXML
	private void handlecancel(ActionEvent event) {
		PressManager.setRecentAdded(false);	
		// get a handle to the stage
	    Stage stage = (Stage) cancelPress.getScene().getWindow();
	    stage.close(); //Close current window
	}


}
