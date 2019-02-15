package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application_v2.AddPress;
import application_v2.Press;
import application_v2.PressManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	@FXML
    private Button addPress; //Add press button in Accordion
	@FXML
    private Button addTransferLocation; //Add Transfer Location '+'
	@FXML
    private Label editTransferLocation; //Add Transfer Location '+'
	@FXML
    private Button addTransferTime; //Add Transfer Time '+'
	@FXML
    private VBox pressList; //Press list in GUI
	@FXML
    private TextField pressName; //name of new press in dialog
	
	Parent root;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		addPress.setOnAction(this::handleNewPress);
		addTransferLocation.setOnAction(this::handleNewTranferLocation);
		addTransferTime.setOnAction(this::handleNewTranferTime);
	}
	
	@FXML
	private void handleNewPress(ActionEvent event) {
		addTransferLocation.setDisable(true); //Disables adding
		addTransferTime.setDisable(true);
		try {
			VBox root = FXMLLoader.load(getClass().getResource("/resources/AddPressWindow.fxml"));
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.getIcons().add(new Image("/resources/icon.png"));
			window.setTitle("Add New Press");
			window.setScene(new Scene(root));
			window.showAndWait(); //Wait until window closes
			if (PressManager.getRecentAdded()) { //If recently added press
				addPressToScene();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleEditTranferLocation() {
		try {
			VBox root = FXMLLoader.load(getClass().getResource("/resources/TransferLocationWindow.fxml"));
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.getIcons().add(new Image("/resources/icon.png"));
			window.setTitle("Edit Location");
			window.setScene(new Scene(root));
			window.showAndWait(); //Wait until window closes
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Handles event for adding a new transfer location
	 */
	@FXML
	private void handleNewTranferLocation(ActionEvent event) {
		try {
			VBox root = FXMLLoader.load(getClass().getResource("/resources/TransferLocationWindow.fxml"));
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.getIcons().add(new Image("/resources/icon.png"));
			window.setTitle("Edit Location");
			window.setScene(new Scene(root));
			window.showAndWait(); //Wait until window closes

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Handles event for adding a new transfer time
	 */
	@FXML
	private void handleNewTranferTime(ActionEvent event) {
		
	}
	
	@FXML
	private void handlePress(ActionEvent event) {
		Press press = PressManager.getPress(String.valueOf(event.getSource().hashCode())); //Get the press info
		addTransferLocation.setDisable(false); //Enables adding
		addTransferTime.setDisable(false);
		System.out.println(press.getName());
	}
	
	/*
	 * Adds press name to GUI
	 */
	@FXML
	public void addPressToScene() {
		//Get the most recent press added to hashmap
		Press press = PressManager.getRecentPress();
		PressManager.removePress(press.getKey()); //Remove temp obj in hashmap
		
		Button newPress = new Button(press.getName());
		newPress.getStyleClass().add("addPress");
		newPress.setMinWidth(addPress.getWidth());
		newPress.setUserData(press);
		
		press.setKey(String.valueOf(newPress.hashCode()));
		PressManager.addPress(press.getName(), String.valueOf(newPress.hashCode())); //Add new press with the same hash code as the button
		System.out.println(press.getKey());
		//Set action to handle the new press button
		newPress.setOnAction(this::handlePress);
		
		pressList.getChildren().add(0,newPress); //Add button to top of children

	}

}
