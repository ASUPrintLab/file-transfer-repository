package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.joda.time.LocalTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


public class TableViewController implements Initializable {
	
	/*
	 * Initializing all objects being used
	 * Authors: Mitchell & Victor & Thomass
	 */
	
	CheckConnectivity prog;
	
	CheckConnectivity prog1;
	
	CheckConnectivity prog2;
	
	CheckConnectivity prog3;
	
	CheckConnectivity prog4;
	
	CheckConnectivity prog5;
	
	CheckConnectivity prog6;
	
	CheckConnectivity prog7;
	
	CheckConnectivity prog8;
	
	CheckConnectivity prog9;
	
	CheckConnectivity prog10;
	
	CheckConnectivity prog11;
	
	private Alert alert = new Alert(AlertType.ERROR);
	
	//private MainApp runnable;
	
	@FXML
	final DirectoryChooser fc = new DirectoryChooser();
	
	@FXML
	private TextField source1; 
	@FXML
	private TextField source2;
	@FXML
	private TextField source3;
	@FXML
	private TextField source4; 
	@FXML
	private TextField source5; 
	@FXML
	private TextField source6;
	@FXML
	private TextField source7;
	@FXML
	private TextField source8; 
	@FXML
	private TextField source9; 
	@FXML
	private TextField source10;
	@FXML
	private TextField source11;
	@FXML
	private TextField source12;
	
	@FXML
	private TextField target1; 
	@FXML
	private TextField target2;
	@FXML
	private TextField target3;
	@FXML
	private TextField target4; 
	@FXML
	private TextField target5; 
	@FXML
	private TextField target6;
	@FXML
	private TextField target7;
	@FXML
	private TextField target8;
	@FXML
	private TextField target9; 
	@FXML
	private TextField target10;
	@FXML
	private TextField target11;
	@FXML
	private TextField target12;
	@FXML 
	public TextArea emailText;
	
	
	@FXML
	private Button BrowseBut1, BrowseBut2, BrowseBut3, BrowseBut4, BrowseBut5, BrowseBut6, BrowseBut7, BrowseBut8, BrowseBut9, BrowseBut10, BrowseBut11, BrowseBut12,
		BrowseBut13, BrowseBut14, BrowseBut15, BrowseBut16, BrowseBut17, BrowseBut18, BrowseBut19, BrowseBut20, BrowseBut21, BrowseBut22, BrowseBut23, BrowseBut24;
	
	//Defining table
	@FXML
	private TableView<TransferTimeFrom> tableID;
	
	@FXML
	private TableView<TransferTimeFrom> tableID2;
	
	@FXML
	private TableView<TransferTimeFrom> tableID3;
	
	@FXML
	private TableView<NewEmailTransferTime> emailTableID;
	
	@FXML
	private TableColumn<TransferTimeFrom, String> TransferTimeFrom1;
	
	@FXML
	private TableColumn<TransferTimeFrom, String> TransferTimeFrom2;
	
	@FXML
	private TableColumn<TransferTimeFrom, String> TransferTimeFrom3;
	
	@FXML
	private TableColumn<TransferTimeTo, String> TransferTimeTo1;
	
	@FXML
	private TableColumn<TransferTimeTo, String> TransferTimeTo2;
	
	@FXML
	private TableColumn<TransferTimeTo, String> TransferTimeTo3;
	
	@FXML
	private TableColumn<NewEmailTransferTime, String> EmailTransferTime;
	
	@FXML
    private Button startButton; // value will be injected by the FXMLLoader
	
	@FXML
    private Button stopButton;
	
	@FXML
    private Button submitButton;
	@FXML
    private Button submitButton2;
	@FXML
    private Button submitButton3;
	@FXML
    private Button emailstopButton;
	@FXML
    private Button emailstartButton;
	@FXML
    private Button emailSubmit;
    @FXML
    private Button emailDelete;
	
	@FXML
    private Button delete;
	
	@FXML
    private Button delete1;
	
	@FXML
    private Button delete2;
	
	@FXML
    private Circle light1;
	
	@FXML
    private Circle light2;
	
	@FXML
    private Circle light3;
	
	@FXML
    private Circle light4;
	
	@FXML
    private Circle light5;
	
	@FXML
    private Circle light6;
	
	
	@FXML
	private ComboBox NewTransferTimeAM;
	
	@FXML
	private ComboBox NewTransferTimePM;
	
	@FXML
	private ComboBox NewTransferTimeAM2;
	
	@FXML
	private ComboBox NewTransferTimePM2;
	
	@FXML
	private ComboBox NewTransferTimeAM3;
	
	@FXML
	private ComboBox NewTransferTimePM3;
	
	@FXML
	private ComboBox NewEmailTime;
	
	File file = new File("C:/FTU");
	File json = new File("C:/FTU/FTU.json");
	DateFormat df = new SimpleDateFormat("MM_dd_yy HH;mm");
	Date dateobj = new Date();
	
	//index for delete item
	private IntegerProperty index = new SimpleIntegerProperty();
	

	/*
	 * Creating all the table data 
	 * Author: Mitchell
	 */
	final ObservableList<TransferTimeFrom> data = FXCollections.observableArrayList(

	);
	final ObservableList<TransferTimeFrom> data2 = FXCollections.observableArrayList(

	);
	final ObservableList<TransferTimeFrom> data3 = FXCollections.observableArrayList(

	);
	final ObservableList<NewEmailTransferTime> emailData = FXCollections.observableArrayList(

	);
	
	EmailHead runnable;
	boolean hasStarted = false;

	//Observable List for the drop down times - MR
	final ObservableList<String> optionAM = FXCollections.observableArrayList(
			"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","8:15 AM","8:30 AM","8:45 AM","9:00 AM",
			"9:15 AM","9:30 AM","9:45 AM","10:00 AM","10:15 AM","10:30 AM","10:45 AM","11:00 AM","11:15 AM","11:30 AM","11:45 AM","12:00 PM",
			"12:15 PM","12:30 PM","12:45 PM","1:00 PM","1:15 PM","1:30 PM","1:45 PM","2:00 PM","2:15 PM","2:30 PM","2:45 PM","3:00 PM",
			"3:15 PM","3:30 PM","3:45 PM","4:00 PM","4:15 PM","4:30 PM","4:45 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM",
			"11:00 PM"
		);
	final ObservableList<String> optionPM = FXCollections.observableArrayList(
			"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","8:15 AM","8:30 AM","8:45 AM","9:00 AM",
			"9:15 AM","9:30 AM","9:45 AM","10:00 AM","10:15 AM","10:30 AM","10:45 AM","11:00 AM","11:15 AM","11:30 AM","11:45 AM","12:00 PM",
			"12:15 PM","12:30 PM","12:45 PM","1:00 PM","1:15 PM","1:30 PM","1:45 PM","2:00 PM","2:15 PM","2:30 PM","2:45 PM","3:00 PM",
			"3:15 PM","3:30 PM","3:45 PM","4:00 PM","4:15 PM","4:30 PM","4:45 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM",
			"11:00 PM"
		);
	
	/*
	 * Adds new transfer time to the Table View.
	 * Author: Mitchell
	 */
	public void addButtonClicked() {
		String output = (String) NewTransferTimeAM.getValue();
		String output2 = (String) NewTransferTimePM.getValue();
		TransferTimeFrom newTransferTime = new TransferTimeFrom(output,output2); //Creates new object
		newTransferTime.setTransferTimeFrom(output); //Sets the new data in correct column
		newTransferTime.setTransferTimeTo(output2);

		tableID.getItems().add(newTransferTime); //Adds times to actual table in order to be displayed
		saveFields();
	}
	public void addButtonClicked2() {
		String output = (String) NewTransferTimeAM2.getValue();
		String output2 = (String) NewTransferTimePM2.getValue();
		TransferTimeFrom newTransferTime = new TransferTimeFrom(output,output2); //Creates new object
		newTransferTime.setTransferTimeFrom(output); //Sets the new data in correct column
		newTransferTime.setTransferTimeTo(output2);

		tableID2.getItems().add(newTransferTime); //Adds times to actual table in order to be displayed
		saveFields();
	}
	public void addButtonClicked3() {
		String output = (String) NewTransferTimeAM3.getValue();
		String output2 = (String) NewTransferTimePM3.getValue();
		TransferTimeFrom newTransferTime = new TransferTimeFrom(output,output2); //Creates new object
		newTransferTime.setTransferTimeFrom(output); //Sets the new data in correct column
		newTransferTime.setTransferTimeTo(output2);

		tableID3.getItems().add(newTransferTime); //Adds times to actual table in order to be displayed
		saveFields();
	}
	public void addEmailClicked() {
		String output = (String) NewEmailTime.getValue();
		NewEmailTransferTime newEmailTime = new NewEmailTransferTime(output); //Creates new object
		newEmailTime.setNewEmailTransferTime(output); //Sets the new data in correct column

		emailTableID.getItems().add(newEmailTime); //Adds times to actual table in order to be displayed
		saveFields();
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * Author: Mitchell & Victor
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		alert.setTitle("Error Dialog"); //For the delete button if nothing is being selected on table
		alert.setHeaderText("Ooops, there was nothing to delete!");
		alert.setContentText("Please select a time on the table to delete.");
		
		populateValues();
		
		light1.setStyle("-fx-fill: #FF0000;");
		light2.setStyle("-fx-fill: #FF0000;");
		light3.setStyle("-fx-fill: #FF0000;");
		light4.setStyle("-fx-fill: #FF0000;");
		light5.setStyle("-fx-fill: #FF0000;");
		light6.setStyle("-fx-fill: #FF0000;");
	  
		stopButton.setDisable(true);
		startButton.setDisable(false);
		emailstopButton.setDisable(true);
		emailstartButton.setDisable(false);

		TransferTimeFrom1.setCellValueFactory(new PropertyValueFactory<TransferTimeFrom, String>("TransferTimeFrom"));
		EmailTransferTime.setCellValueFactory(new PropertyValueFactory<NewEmailTransferTime, String>("NewEmailTransferTime"));


		NewTransferTimeAM.setValue("12:00 AM"); //Initial Values in drop down value.
		NewTransferTimeAM.setItems(optionAM);
		NewTransferTimePM.setValue("12:00 AM");
		NewTransferTimePM.setItems(optionPM);
		NewTransferTimeAM2.setValue("12:00 AM"); //Initial Values in drop down value.
		NewTransferTimeAM2.setItems(optionAM);
		NewTransferTimePM2.setValue("12:00 AM");
		NewTransferTimePM2.setItems(optionPM);
		NewTransferTimeAM3.setValue("12:00 AM"); //Initial Values in drop down value.
		NewTransferTimeAM3.setItems(optionAM);
		NewTransferTimePM3.setValue("12:00 AM");
		NewTransferTimePM3.setItems(optionPM);
		NewEmailTime.setValue("12:00 AM"); //Initial Values in drop down value.
		NewEmailTime.setItems(optionAM);
		
		submitButton.setOnAction(e -> addButtonClicked()); //Adds event to submit button.. Calls addButtonClicked Method
		submitButton2.setOnAction(e -> addButtonClicked2());
		submitButton3.setOnAction(e -> addButtonClicked3());
		emailSubmit.setOnAction(e -> addEmailClicked());
		
		
		//Applies the objects to the actual cells in the table
		TransferTimeFrom2.setCellValueFactory(new PropertyValueFactory<TransferTimeFrom, String>("TransferTimeFrom"));
		TransferTimeFrom3.setCellValueFactory(new PropertyValueFactory<TransferTimeFrom, String>("TransferTimeFrom"));
		TransferTimeTo1.setCellValueFactory(new PropertyValueFactory<TransferTimeTo, String>("TransferTimeTo"));
		TransferTimeTo2.setCellValueFactory(new PropertyValueFactory<TransferTimeTo, String>("TransferTimeTo"));
		TransferTimeTo3.setCellValueFactory(new PropertyValueFactory<TransferTimeTo, String>("TransferTimeTo"));
		tableID.setItems(data); //Displays data
		tableID2.setItems(data2);
		tableID3.setItems(data3);
		emailTableID.setItems(emailData);

		
		stopButton.setOnAction(this::handleStopAction); //Invokes the action
		startButton.setOnAction(this::handleStartAction);
		emailstopButton.setOnAction(this::handleemailStopAction); //Invokes the action
		emailstartButton.setOnAction(this::handleemailStartAction);
		
		
		/*
		 * Adds a listener to tell the program where you clicked on the table. Used in order to delete desired transfer time
		 * Author: Mitchell
		 */
		tableID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable,Object oldvalue, Object newValue) {
				index.set(data.indexOf(newValue));
				System.out.println("index is: " + data.indexOf(newValue));
			}
		});
		
		tableID2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable,Object oldvalue, Object newValue) {
				index.set(data2.indexOf(newValue));
				System.out.println("index is: " + data2.indexOf(newValue));
			}
		});
		
		tableID3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable,Object oldvalue, Object newValue) {
				index.set(data3.indexOf(newValue));
				System.out.println("index is: " + data3.indexOf(newValue));
			}
		});
		emailTableID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable,Object oldvalue, Object newValue) {
				index.set(emailData.indexOf(newValue));
				System.out.println("index is: " + emailData.indexOf(newValue));
			}
		});
		
		if (!file.isDirectory()){
			//Auto Creates file if it doesnt exist
			new File("C:/FTU").mkdir();
		}
		
	}

	// If start or stop button are clicked, change the fill color of circles
	 @FXML
	 private void handleStopAction(ActionEvent event) {
		 light1.setStyle("-fx-fill: #FF0000;");
		 light2.setStyle("-fx-fill: #FF0000;");
		 light3.setStyle("-fx-fill: #FF0000;");
	  
		 stopButton.setDisable(true);
		 startButton.setDisable(false);
		 
		 prog.stop();
		 prog1.stop();
		 prog2.stop();
		 prog3.stop();
		 prog4.stop();
		 prog5.stop();
		 prog6.stop();
		 prog7.stop();
		 prog8.stop();
		 prog9.stop();
		 prog10.stop();
		 prog11.stop();
	 }
	 
	 @FXML
	 private void handleStartAction(ActionEvent event) {
		 light1.setStyle("-fx-fill: #00ff0c;");
		 light2.setStyle("-fx-fill: #00ff0c;");
		 light3.setStyle("-fx-fill: #00ff0c;");
		 
		 stopButton.setDisable(false);
		 startButton.setDisable(true);
		
		 try{
			 //Indigo
			 prog = new CheckConnectivity("Date Opened: " + LocalTime.now(), source1.getText(), target1.getText(), TransferTimeFrom1, TransferTimeTo1, data);
			 prog1 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source2.getText(), target2.getText(), TransferTimeFrom1, TransferTimeTo1, data);
			 prog2 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source3.getText(), target3.getText(), TransferTimeFrom1, TransferTimeTo1, data);
			 prog3 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source4.getText(), target4.getText(), TransferTimeFrom1, TransferTimeTo1, data);
			 
			 //iGen
			 prog4 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source5.getText(), target5.getText(), TransferTimeFrom2, TransferTimeTo2, data2);
			 prog5 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source6.getText(), target6.getText(), TransferTimeFrom2, TransferTimeTo2, data2);
			 prog6 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source7.getText(), target7.getText(), TransferTimeFrom2, TransferTimeTo2, data2);
			 prog7 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source8.getText(), target8.getText(), TransferTimeFrom2, TransferTimeTo2, data2);
			 
			 //Memjet
			 prog8 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source9.getText(), target9.getText(), TransferTimeFrom3, TransferTimeTo3, data3);
			 prog9 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source10.getText(), target10.getText(), TransferTimeFrom3, TransferTimeTo3, data3);
			 prog10 = new CheckConnectivity("Date Opened: " + LocalTime.now(), source11.getText(), target11.getText(), TransferTimeFrom3, TransferTimeTo3, data3);
			 prog11= new CheckConnectivity("Date Opened: " + LocalTime.now(), source12.getText(), target12.getText(), TransferTimeFrom3, TransferTimeTo3, data3);
			 
		 }finally {
				prog.start();
				prog1.start();
				prog2.start();
				prog3.start();
				prog4.start();
				prog5.start();
				prog6.start();
				prog7.start();
				prog8.start();
				prog9.start();
				prog10.start();
				prog11.start();
			}
	 } 
	 
	 @FXML
	 private void handleemailStopAction(ActionEvent event) {
		 light4.setStyle("-fx-fill: #FF0000;");
		 light5.setStyle("-fx-fill: #FF0000;");
		 light6.setStyle("-fx-fill: #FF0000;");
		 
		 this.runnable.stop();
		 hasStarted = false;
		 emailText.appendText("Stopped (" + df.format(dateobj) + ")\n");
	  
		 emailstopButton.setDisable(true);
		 emailstartButton.setDisable(false);
	 }
	 
	 @FXML
	 private void handleemailStartAction(ActionEvent event) {
		 light4.setStyle("-fx-fill: #00ff0c;");
		 light5.setStyle("-fx-fill: #00ff0c;");
		 light6.setStyle("-fx-fill: #00ff0c;");
		 
		 if(!hasStarted){
			 runnable = new EmailHead(EmailTransferTime, emailData, emailText);
			 runnable.start();
			 hasStarted = true;
		 }
		 
		 //runnable.resume();
		 emailText.appendText("Running (" + df.format(dateobj) + ")\n");
		 
		 emailstopButton.setDisable(false);
		 emailstartButton.setDisable(true);
	 }
	 
	  /*
	   * This deletes the data on the table. Will concatenate the cells and move the values -1. Will not delete out of bounds.
	   * Author: Mitchell
	   */
	 public void onDeleteItem(ActionEvent event) {

		 int i = index.get();
		 if(tableID.getSelectionModel().isSelected(i)) { //Only will delete time if selected on table
			 if(i > -1) {
				 data.remove(i);
	
				 tableID.getSelectionModel().clearSelection();
				 saveFields();
			 }
		 }
			 else {
				 alert.showAndWait(); //Display error dialog if nothing was selected on table
			 }
	 }
	 
	 public void onDeleteItem2(ActionEvent event) { 
		 int i = index.get();
		 if(tableID2.getSelectionModel().isSelected(i)) {
			 if(i > -1) {
				 data2.remove(i);
	
				 tableID2.getSelectionModel().clearSelection();
				 saveFields();
			 }
		 }
		 else {
			 alert.showAndWait(); 
		 }
	 }
	 
	 public void onDeleteItem3(ActionEvent event) { 
		 int i = index.get();
		 if(tableID3.getSelectionModel().isSelected(i)) {
			 if(i > -1) {
				 data3.remove(i);
	
				 tableID3.getSelectionModel().clearSelection();
				 saveFields();
			 }
		}
		 else {
			 alert.showAndWait(); 
		 }
	 }
	 
	 public void onEmailDeleteItem(ActionEvent event) {

		 int i = index.get();
		 if(emailTableID.getSelectionModel().isSelected(i)) { //Only will delete time if selected on table
			 if(i > -1) {
				 emailData.remove(i);
	
				 emailTableID.getSelectionModel().clearSelection();
				 saveFields();
			 }
		 }
			 else {
				 alert.showAndWait(); //Display error dialog if nothing was selected on table
			 }
	 }
	 /*
	  * Function to populate data values
	  * 	- Saves Time
	  * 	- Saves Browse Button Fields
	  * 
	  * Author: Thomas Scheuneman
	  */
	 private void populateValues() {
		 //Create a JSONParser object to parse ou
		 JSONParser parser = new JSONParser();
		 
		 try {
			 if(json.exists()){
			 	//Parse our saved file
	            Object obj = parser.parse(new FileReader("C:/FTU/FTU.json"));
	            //Turn our file into a JSON object
	            JSONObject theObj = (JSONObject) obj;
	            

	            //Get our first section
	            
	            //Parge our time one values, assign to JSONObject
	            Object theDataOne = parser.parse(theObj.get("timeOneValues").toString());
	            JSONObject theObjOne = (JSONObject) theDataOne;
	            
	            //Counter is half size of array, since two values equals a dataset
	            int theCounter = theObjOne.size() / 2;
	            //Set variables
	            int numEntries = 0;
	            int incrementNum = 0;
	            //Loop through each dataset
	            for (int i = 0; i < theCounter; i++) {
	            //Get string values of counter.
	             String variable = String.valueOf(incrementNum);
	             //Get zero value of JSON object
	             String varOne = theObjOne.get(variable).toString();
	             incrementNum++;
	             //Get string value of +1
	             variable = String.valueOf(incrementNum);
	             //Get the value of JSON Object
	             String varTwo = theObjOne.get(variable).toString();
	             //Create new timeset with these two values
	             TransferTimeFrom Test = new TransferTimeFrom(varOne, varTwo);
	             //Enter into data table
	             data.add(numEntries, Test);
	             //Increment counters
	             numEntries++;
	             incrementNum++;
	            }

	            //Repeat for Two
	            Object theDataTwo = parser.parse(theObj.get("timeTwoValues").toString());
	            JSONObject theObjTwo = (JSONObject) theDataTwo;


	            theCounter = theObjTwo.size() / 2;
	            numEntries = 0;
	            incrementNum = 0;
	            for (int i = 0; i < theCounter; i++) {
	             String variable = String.valueOf(incrementNum);
	             String varOne = theObjTwo.get(variable).toString();
	             incrementNum++;
	             variable = String.valueOf(incrementNum);
	             String varTwo = theObjTwo.get(variable).toString();
	             TransferTimeFrom Test = new TransferTimeFrom(varOne, varTwo);
	             data2.add(numEntries, Test);
	             numEntries++;
	             incrementNum++;
	            }

	            //Repeat for Three
	            Object theDataThree = parser.parse(theObj.get("timeThreeValues").toString());
	            JSONObject theObjThree = (JSONObject) theDataThree;

	            theCounter = theObjThree.size() / 2;
	            numEntries = 0;
	            incrementNum = 0;
	            for (int i = 0; i < theCounter; i++) {
	             String variable = String.valueOf(incrementNum);
	             String varOne = theObjThree.get(variable).toString();
	             incrementNum++;
	             variable = String.valueOf(incrementNum);
	             String varTwo = theObjThree.get(variable).toString();
	             TransferTimeFrom Test = new TransferTimeFrom(varOne, varTwo);
	             data3.add(numEntries, Test);
	             numEntries++;
	             incrementNum++;
	            }
			            
	            //Set text for all of our browse buttons
	            target1.setText((String)theObj.get("target1"));
	            target2.setText((String)theObj.get("target2"));
	            target3.setText((String)theObj.get("target3"));
	            target4.setText((String)theObj.get("target4"));
	            target5.setText((String)theObj.get("target5"));
	            target6.setText((String)theObj.get("target6"));
	            target7.setText((String)theObj.get("target7"));
	            target8.setText((String)theObj.get("target8"));
	            target9.setText((String)theObj.get("target9"));
	            target10.setText((String)theObj.get("target10"));
	            target11.setText((String)theObj.get("target11"));
	            target12.setText((String)theObj.get("target12"));
	            
	            source1.setText((String)theObj.get("source1"));
	            source2.setText((String)theObj.get("source2"));
	            source3.setText((String)theObj.get("source3"));
	            source4.setText((String)theObj.get("source4"));
	            source5.setText((String)theObj.get("source5"));
	            source6.setText((String)theObj.get("source6"));
	            source7.setText((String)theObj.get("source7"));
	            source8.setText((String)theObj.get("source8"));
	            source9.setText((String)theObj.get("source9"));
	            source10.setText((String)theObj.get("source10"));
	            source11.setText((String)theObj.get("source11"));
	            source12.setText((String)theObj.get("source12"));
	            
			 }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 /*
	  * Function to save data
	  * 	- Saves Time
	  * 	- Saves Browse Button Fields
	  * 
	  * Author: Thomas Scheuneman
	  */
	 public void saveFields() {
		 //Declare varibles for size of tables.
		 int x = data.size();
		 int xy = data2.size();
		 int xz = data3.size();

		 //Create array to hold values of table fields.
		 String[][] timeOne = new String[x][2];
		 String[][] timeTwo = new String[xy][2];
		 String[][] timeThree = new String[xz][2];
		 
		 //Loop thrpugh all fields, set time From and time to for the table values.
         for(int counter = 0; counter < data.size(); counter++) {
        	 timeOne[counter][0] = TransferTimeFrom1.getCellData(counter);
         }
         for(int counter = 0; counter < data.size(); counter++) {
        	 timeOne[counter][1] = TransferTimeTo1.getCellData(counter);
         }
         //Loop thrpugh all fields, set time From and time to for the table values.
         for(int counter = 0; counter < xy; counter++) {
        	 timeTwo[counter][0] = TransferTimeFrom2.getCellData(counter);
         }
         for(int counter = 0; counter <xy; counter++) {
        	 timeTwo[counter][1] = TransferTimeTo2.getCellData(counter);
         }
         //Loop thrpugh all fields, set time From and time to for the table values.
         for(int counter = 0; counter < xz; counter++) {
        	 timeThree[counter][0] = TransferTimeFrom3.getCellData(counter);
         }
         for(int counter = 0; counter <xz; counter++) {
        	 timeThree[counter][1] = TransferTimeTo3.getCellData(counter);
         }
         
         //Create three JSON objects
         JSONObject timeOneJSON = new JSONObject();
         JSONObject timeTwoJSON = new JSONObject();
         JSONObject timeThreeJSON = new JSONObject();
         //Set var to 0
         int testVal = 0;
         //Loop through the number of variables, put them inside of a JSON object in sequential order.
	         for(int counter = 0; counter <x; counter++) {
	        	 timeOneJSON.put(testVal, timeOne[counter][0]);
	        	 testVal++;
	        	 timeOneJSON.put(testVal, timeOne[counter][1]);
	        	 testVal++;
	         }
	       //Loop through the number of variables, put them inside of a JSON object in sequential order.
	         testVal = 0;
	         for(int counter = 0; counter <xy; counter++) {
	        	 timeTwoJSON.put(testVal, timeTwo[counter][0]);
	        	 testVal++;
	        	 timeTwoJSON.put(testVal, timeTwo[counter][1]);
	        	 testVal++;
	         }
	       //Loop through the number of variables, put them inside of a JSON object in sequential order.
	         testVal = 0;
	         for(int counter = 0; counter <xz; counter++) {
	        	 timeThreeJSON.put(testVal, timeThree[counter][0]);
	        	 testVal++;
	        	 timeThreeJSON.put(testVal, timeThree[counter][1]);
	        	 testVal++;
	         }
         
	     //Get text for each browse button value
		 String strTarget1 = target1.getText();
		 String strTarget2 = target2.getText();
		 String strTarget3 = target3.getText();
		 String strTarget4 = target4.getText();
		 String strTarget5 = target5.getText();
		 String strTarget6 = target6.getText();
		 String strTarget7 = target7.getText();
		 String strTarget8 = target8.getText();
		 String strTarget9 = target9.getText();
		 String strTarget10 = target10.getText();
		 String strTarget11 = target11.getText();
		 String strTarget12 = target12.getText();
		 
		 String strSource1 = source1.getText();
		 String strSource2 = source2.getText();
		 String strSource3 = source3.getText();
		 String strSource4 = source4.getText();
		 String strSource5 = source5.getText();
		 String strSource6 = source6.getText();
		 String strSource7 = source7.getText();
		 String strSource8 = source8.getText();
		 String strSource9 = source9.getText();
		 String strSource10 = source10.getText();
		 String strSource11 = source11.getText();
		 String strSource12 = source12.getText();
 		 
		 //Create a new JSON object, this will be the value that we store data as.
		 JSONObject objString = new JSONObject();
		 //Set the JSON object with browse button text
		 	objString.put("target1", strTarget1);
		 	objString.put("target2", strTarget2);
		 	objString.put("target3", strTarget3);
		 	objString.put("target4", strTarget4);
		 	objString.put("target5", strTarget5);
		 	objString.put("target6", strTarget6);
		 	objString.put("target7", strTarget7);
		 	objString.put("target8", strTarget8);
		 	objString.put("target9", strTarget9);
		 	objString.put("target10", strTarget10);
		 	objString.put("target11", strTarget11);
		 	objString.put("target12", strTarget12);
		 	objString.put("source1", strSource1);
		 	objString.put("source2", strSource2);
		 	objString.put("source3", strSource3);
		 	objString.put("source4", strSource4);
		 	objString.put("source5", strSource5);
		 	objString.put("source6", strSource6);
		 	objString.put("source7", strSource7);
		 	objString.put("source8", strSource8);
		 	objString.put("source9", strSource9);
		 	objString.put("source10", strSource10);
		 	objString.put("source11", strSource11);
		 	objString.put("source12", strSource12);
		 	//Save JSON Object Data
		 		objString.put("timeOneValues", timeOneJSON);
		 		objString.put("timeTwoValues", timeTwoJSON);
		 		objString.put("timeThreeValues", timeThreeJSON);
		 	
		 		
		 	//Attempt to write to file
	        try (FileWriter file = new FileWriter("C:/FTU/FTU.json")) {
	        	//System Message and write JSON string to file
	        	 System.out.println("File Saved");
	            file.write(objString.toJSONString());
	            file.flush();
	            

	        } catch (IOException e) {
	        	System.out.println("It didn't work");
	            e.printStackTrace();
	        }

	 }
	 /*
	  * 
	  * Author: Victor
	  */
	 @FXML
	 protected void handleBrowseButton(ActionEvent event) {
		 Node source = (Node) event.getSource();
		 Window theStage = source.getScene().getWindow();
		 fc.setTitle("Choose Target Location");
		 File returnVal = fc.showDialog(theStage);
		   
		 if (event.getSource() ==  BrowseBut1) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source1.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut2) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target1.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut3) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source2.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut4) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target2.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut5) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source3.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut6) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target3.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut7) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source4.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut8) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target4.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut9) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source5.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut10) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target5.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut11) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source6.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut12) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target6.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut13) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source7.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut14) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target7.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut15) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source8.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut16) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target8.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut17) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source9.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut18) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target9.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut19) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source10.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut20) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target10.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut21) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source11.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut22) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target11.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut23) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 source12.setText(val); 
			 }
		 }
		 
		 else if (event.getSource() ==  BrowseBut24) {
			 if(!returnVal.equals(null)){
				 String val = returnVal.toString();
				 target12.setText(val); 
			 }
		 }
		 saveFields();
	 }
}
