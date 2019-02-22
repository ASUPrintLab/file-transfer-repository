package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LocationController implements Initializable {
	
	/*
	 * Variables for Location Window
	 */
	@FXML
    private Button confirmLoc; //okay button in dialog
	@FXML
    private Button cancelLoc; //cancel button in dialog
	@FXML
    private Button browseFrom; //browse from button in dialog
	@FXML
    private Button browseTo; //browse to button in dialog
	@FXML
    private TextField connectionName; //name of connection in dialog
	@FXML
    private TextField toLocation; 
	@FXML
    private TextField fromLocation; 
	@FXML
	final DirectoryChooser fc = new DirectoryChooser(); //Directory to choose location of folder
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Bind the event handler
		confirmLoc.setOnAction(this::handleconfirm); 
		cancelLoc.setOnAction(this::handlecancel);
		browseFrom.setOnAction(this::handleBrowse); 
		browseTo.setOnAction(this::handleBrowse);
		
	}
	
	@FXML
	private void handleconfirm(ActionEvent event) {

		if (connectionName.getText().trim().isEmpty() || fromLocation.getText().trim().isEmpty() || toLocation.getText().trim().isEmpty()) {
			Alert errorAlert = new Alert(
	                Alert.AlertType.ERROR,
	                "There are field(s) that are empty. Please make sure all fields are filled before saving."
	        );
			Button confirm = (Button) errorAlert.getDialogPane().lookupButton(ButtonType.OK);
			confirm.setText("Okay");
			errorAlert.setHeaderText("Detected empty field.");
			errorAlert.showAndWait();
		}
		else {
			Stage stage = (Stage) confirmLoc.getScene().getWindow();
			stage.close(); //Close current window	
		}
	}
	
	@FXML
	private void handlecancel(ActionEvent event) {
	    Stage stage = (Stage) cancelLoc.getScene().getWindow();
	    stage.close(); //Close current window
	}
	/*
	 * Handles the browse button action for finding folder locations 
	 */
	@FXML
	private void handleBrowse(ActionEvent event) {
		Node source = (Node) event.getSource();
		// Gets the current javafx scene
		Window theStage = source.getScene().getWindow();
		fc.setTitle("Choose Target Location");
		File returnVal = fc.showDialog(theStage);
		if (event.getSource() ==  browseFrom) {
			 if(returnVal != null){
				 String val = returnVal.toString();
				 fromLocation.setText(val); //Set the text field text with the folder path
			 }
		 }
		else if (event.getSource() ==  browseTo) {
			 if(returnVal != null){
				 String val = returnVal.toString();
				 toLocation.setText(val); 
			 }
		 }
	}

	public String getName() {
		return connectionName.getText();
	}

	public String getSourceLoc() {
		return fromLocation.getText();
	}
	
	public String getTargetLoc() {
		return toLocation.getText();
	}
	
	


}
