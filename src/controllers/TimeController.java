package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.TransferTimeFrom;
import application.TransferTimeTo;
import application_v2.TransferTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class TimeController implements Initializable{
	/*
	 * Variables for Location Window
	 */
	@FXML
    private Button confirmLoc; //okay button in dialog
	@FXML
    private Button cancelLoc; //cancel button in dialog
	@FXML
    private Button addTransferTime; //add button in dialog
	@FXML
    private ComboBox<String> timeFrom; //From drop down
	@FXML
    private ComboBox<String> timeTo; //To drop down
	@FXML
	private TableView<TransferTimeFrom> timeTable;
	@FXML
	private TableColumn<TransferTime, String> timeColFrom;
	@FXML
	private TableColumn<TransferTime, String> timeColTo;
	
	//Observable List for the drop down times - MR
	final ObservableList<String> options = FXCollections.observableArrayList(
			"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","8:15 AM","8:30 AM", "8:35 AM","8:45 AM","9:00 AM",
			"9:15 AM","9:30 AM","9:45 AM","10:00 AM","10:15 AM","10:30 AM","10:45 AM","11:00 AM","11:15 AM","11:30 AM","11:45 AM","12:00 PM",
			"12:15 PM","12:30 PM","12:45 PM","12:59 PM","1:00 PM","1:15 PM","1:30 PM","1:45 PM","2:00 PM","2:15 PM","2:30 PM","2:45 PM","3:00 PM",
			"3:15 PM","3:30 PM","3:45 PM","4:00 PM","4:15 PM","4:30 PM","4:45 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM",
			"11:00 PM"
		);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Bind the event handler
		confirmLoc.setOnAction(this::handleconfirm); 
		cancelLoc.setOnAction(this::handlecancel);
		timeFrom.setItems(options); //Add options
		timeTo.setItems(options);
		
		
	}
	
	@FXML
	private void handleconfirm(ActionEvent event) {
		Stage stage = (Stage) cancelLoc.getScene().getWindow();
	    stage.close(); //Close current window
	}
	
	@FXML
	private void handlecancel(ActionEvent event) {
	    Stage stage = (Stage) cancelLoc.getScene().getWindow();
	    stage.close(); //Close current window
	}

}
