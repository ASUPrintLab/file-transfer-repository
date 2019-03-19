package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import application_v2.TransferTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/** Transfer Time controller when adding a new transfer time window
 * @author Mitchell Roberts
 * @version 2.0
*/
public class TimeController implements Initializable{
	/*
	 * Variables for Location Window
	 */
	@FXML
    private Button confirmTime; //okay button in dialog
	@FXML
    private Button cancelTime; //cancel button in dialog
	@FXML
    private Button addTime; //cancel button in dialog
	@FXML
    private Button addTransferTime; //add button in dialog
	@FXML
    private ComboBox<String> timeFrom; //From drop down
	@FXML
    private ComboBox<String> timeTo; //To drop down
	@FXML
	private TableView<TransferTime> timeTable;
	@FXML
	private TableColumn<TransferTime, String> timeColFromTemp;
	@FXML
	private TableColumn<TransferTime, String> timeColToTemp;
	
	private boolean save;
	
	//Observable List for the drop down times - MR
	final ObservableList<String> options = FXCollections.observableArrayList(
			"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","8:15 AM","8:30 AM", "8:35 AM","8:45 AM","9:00 AM",
			"9:15 AM","9:30 AM","9:45 AM","10:00 AM","10:15 AM","10:30 AM","10:45 AM","11:00 AM","11:15 AM","11:30 AM","11:45 AM","12:00 PM",
			"12:15 PM","12:30 PM","12:45 PM","12:59 PM","1:00 PM","1:15 PM","1:30 PM","1:45 PM","2:00 PM","2:15 PM","2:30 PM","2:45 PM","3:00 PM",
			"3:15 PM","3:30 PM","3:45 PM","4:00 PM","4:15 PM","4:30 PM","4:45 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM",
			"11:00 PM"
		);
	/**
	 * Initializes the components actions on launch - first method called
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Bind the event handler
		confirmTime.setOnAction(this::handleconfirm); 
		cancelTime.setOnAction(this::handlecancel);
		addTime.setOnAction(this::handleAddTime);
		timeFrom.setItems(options); //Add options
		timeTo.setItems(options);
		
		this.save = false;
		
		//Applies the objects to the actual cells in the table
		timeColFromTemp.setCellValueFactory(new PropertyValueFactory<TransferTime, String>("startTime"));
		timeColToTemp.setCellValueFactory(new PropertyValueFactory<TransferTime, String>("stopTime"));
		
		
	}
	
	/**
	 * Adds new transfer time to the Table View.
	 * @param event
	 */
	@FXML
	public void handleAddTime(ActionEvent event) {
		String from = timeFrom.getValue();
		String to = timeTo.getValue();
		if (from != null || to != null) {
			TransferTime newTransferTime = new TransferTime(from,to); 
			//Add times to table
			timeTable.getItems().add(newTransferTime); 	
		}
	}
	/**
	 * Method that handles the action if the user presses "Save" - Close the window
	 * @param event - ActionEvent
	 */
	@FXML
	private void handleconfirm(ActionEvent event) {
		this.save = true;
		Stage stage = (Stage) confirmTime.getScene().getWindow();
	    stage.close(); //Close current window
	}
	/**
	 * Method that handles the action if the user presses "Cancel" 
	 * @param event - ActionEvent
	 */
	@FXML
	private void handlecancel(ActionEvent event) {
		this.save = false;
	    Stage stage = (Stage) cancelTime.getScene().getWindow();
	    stage.close(); //Close current window
	}
	
	public ObservableList<TransferTime> getTimes() {
		return timeTable.getItems();
	}
	
	public boolean ifSaved() {
		return this.save;
	}

}
