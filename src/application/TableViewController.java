package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.LocalTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.DefaultStringConverter;
import application.CheckConnectivity;

public class TableViewController implements Initializable {
	
	CheckConnectivity prog;
	
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
	private TableColumn<TransferTimeFrom, String> TransferTimeFrom1;
	
	@FXML
	private TableColumn<TransferTimeFrom, String> TransferTimeFrom2;
	
	@FXML
	private TableColumn<TransferTimeFrom, String> TransferTimeFrom3;
	
//	@FXML
//	private TableView<TransferTimeTo> tableID;
//	
//	@FXML
//	private TableView<TransferTimeTo> tableID2;
//	
//	@FXML
//	private TableView<TransferTimeTo> tableID3;
	
	@FXML
	private TableColumn<TransferTimeTo, String> TransferTimeTo1;
	
	@FXML
	private TableColumn<TransferTimeTo, String> TransferTimeTo2;
	
	@FXML
	private TableColumn<TransferTimeTo, String> TransferTimeTo3;
	
	@FXML
    private Button startButton; // value will be injected by the FXMLLoader
	
	@FXML
    private Button stopButton;
	
	@FXML
    private Button submitButton;
	
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
	private ComboBox NewTransferTimeAM;
	
	@FXML
	private ComboBox NewTransferTimePM;
	
	//index for delete item
	private IntegerProperty index = new SimpleIntegerProperty();
//	private ObservableList<TransferTimeFrom> masterData;
//	private ObservableList<String> masterData2;
	
//	@FXML
//	private TableColumn<Product, String> emailTime;

	/*
	 * Creating all the table data
	 */
	final ObservableList<TransferTimeFrom> data = FXCollections.observableArrayList(
		new TransferTimeFrom("7:00 AM","8:30 AM"),
		new TransferTimeFrom("10:00 AM","11:10 AM"),
		new TransferTimeFrom("12:00 PM","1:30 PM"),
		new TransferTimeFrom("3:00 PM","4:30 PM")
	);
	final ObservableList<TransferTimeFrom> data2 = FXCollections.observableArrayList(
		new TransferTimeFrom("8:00 AM","8:30 AM"),
		new TransferTimeFrom("10:00 AM","11:10 AM"),
		new TransferTimeFrom("12:00 PM","1:30 PM"),
		new TransferTimeFrom("3:00 PM","4:30 PM")
	);
	final ObservableList<TransferTimeFrom> data3 = FXCollections.observableArrayList(
		new TransferTimeFrom("9:00 AM","8:30 AM"),
		new TransferTimeFrom("10:00 AM","11:10 AM"),
		new TransferTimeFrom("12:00 PM","1:30 PM"),
		new TransferTimeFrom("3:00 PM","4:30 PM")
	);
	
	final ObservableList<String> optionAM = FXCollections.observableArrayList(
			"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","8:15 AM","8:30 AM","8:45 AM","9:00 AM",
			"9:15 AM","9:30 AM","9:45 AM","10:00 AM","10:15 AM","10:30 AM","10:45 AM","11:00 AM","11:15 AM","11:30 AM","11:45 AM","12:00 AM",
			"12:15 PM","12:30 PM","12:45 PM","1:00 PM","1:15 PM","1:30 PM","1:45 PM","2:00 PM","2:15 PM","2:30 PM","2:45 PM","3:00 PM",
			"3:15 PM","3:30 PM","3:45 PM","4:00 PM","4:15 PM","4:30 PM","4:45 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM",
			"11:00 PM"
		);
	final ObservableList<String> optionPM = FXCollections.observableArrayList(
			"12:00 AM","1:00 AM","2:00 AM","3:00 AM","4:00 AM","5:00 AM","6:00 AM","7:00 AM","8:00 AM","8:15 AM","8:30 AM","8:45 AM","9:00 AM",
			"9:15 AM","9:30 AM","9:45 AM","10:00 AM","10:15 AM","10:30 AM","10:45 AM","11:00 AM","11:15 AM","11:30 AM","11:45 AM","12:00 AM",
			"12:15 PM","12:30 PM","12:45 PM","1:00 PM","1:15 PM","1:30 PM","1:45 PM","2:00 PM","2:15 PM","2:30 PM","2:45 PM","3:00 PM",
			"3:15 PM","3:30 PM","3:45 PM","4:00 PM","4:15 PM","4:30 PM","4:45 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM","10:00 PM",
			"11:00 PM"
		);
	
	public void addButtonClicked() {
		String output = (String) NewTransferTimeAM.getValue();
		String output2 = (String) NewTransferTimePM.getValue();
		TransferTimeFrom newTransferTime = new TransferTimeFrom(output,output2);
		newTransferTime.setTransferTimeFrom(output);
		newTransferTime.setTransferTimeTo(output2);

		tableID.getItems().add(newTransferTime);
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TransferTimeFrom1.setCellValueFactory(new PropertyValueFactory<TransferTimeFrom, String>("TransferTimeFrom"));

		NewTransferTimeAM.setValue("12:00 AM");
		NewTransferTimeAM.setItems(optionAM);
		NewTransferTimePM.setValue("12:00 AM");
		NewTransferTimePM.setItems(optionPM);
		
		submitButton.setOnAction(e -> addButtonClicked());
		
		TransferTimeFrom2.setCellValueFactory(new PropertyValueFactory<TransferTimeFrom, String>("TransferTimeFrom"));
		TransferTimeFrom3.setCellValueFactory(new PropertyValueFactory<TransferTimeFrom, String>("TransferTimeFrom"));
		TransferTimeTo1.setCellValueFactory(new PropertyValueFactory<TransferTimeTo, String>("TransferTimeTo"));
		TransferTimeTo2.setCellValueFactory(new PropertyValueFactory<TransferTimeTo, String>("TransferTimeTo"));
		TransferTimeTo3.setCellValueFactory(new PropertyValueFactory<TransferTimeTo, String>("TransferTimeTo"));
		tableID.setItems(data);
		tableID2.setItems(data2);
		tableID3.setItems(data3);
		
		stopButton.setOnAction(this::handleStopAction);
		startButton.setOnAction(this::handleStartAction);
		
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
	}

	// Button was clicked, do something...
	 @FXML
	 private void handleStopAction(ActionEvent event) {
		 light1.setStyle("-fx-fill: #FF0000;");
		 light2.setStyle("-fx-fill: #FF0000;");
		 light3.setStyle("-fx-fill: #FF0000;");
	  
	 }
	 @FXML
	 private void handleStartAction(ActionEvent event) {
		 light1.setStyle("-fx-fill: #00ff0c;");
		 light2.setStyle("-fx-fill: #00ff0c;");
		 light3.setStyle("-fx-fill: #00ff0c;");
		 
		 // try {
		 //		prog = new CheckConnectivity("Date Opened: " + LocalTime.now(), fieldSource.getText(), fieldTarget.getText(), morn1.getText(), morn2.getText(), aft1.getText(),
		 //			aft2.getText(), aft3.getText(), aft4.getText(), eve1.getText(), eve2.getText());
		 // } catch (IllegalArgumentException e1) {
		 //		prog = new CheckConnectivity("Date Opened: " + LocalTime.now(), fieldSource.getText(), fieldTarget.getText(), "09:30", "09:45","10:30", "11:00", "12:45",
		 //			"13:00", "14:30", "14:45");
	     // }
		 // } finally {
		 //		prog.start();
		 // }
	  
	 }
	 
	 public void onDeleteItem(ActionEvent event) {
		 int i = index.get();
		 if(i > -1) {
			 data.remove(i);

			 tableID.getSelectionModel().clearSelection();
			 
			 System.out.print("Data for time: " +  data.toString());
		}
	 }
	 
	 public void onDeleteItem2(ActionEvent event) { 
		 int i = index.get();
		 if(i > -1) {
			 data2.remove(i);

			 tableID2.getSelectionModel().clearSelection();
		}
	 }
	 
	 public void onDeleteItem3(ActionEvent event) { 
		 int i = index.get();
		 if(i > -1) {
			 data3.remove(i);

			 tableID3.getSelectionModel().clearSelection();
		}
	 }
	 
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
	 }
}

//http://stackoverflow.com/questions/26962788/fxmlloader-how-to-access-the-components-by-fxid   ---> For accessing the buttons in XML
