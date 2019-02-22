package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application_v2.AddPress;
import application_v2.Press;
import application_v2.PressManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
    private VBox transferLocList; //Location list in GUI
	@FXML
    private ScrollPane scrollpane; //ScrollPane
	@FXML
    private TextField connectionName; //name of connection in dialog
	
//	Parent root;
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Always show vertical scroll bar
		scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		VBox.setVgrow(scrollpane, Priority.ALWAYS);
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
			createComponent();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Creates new ui component for Transfer Location
	 */
	private void createComponent() {
		Pane pane = new Pane();
		VBox vbox = new VBox();
		pane.getStyleClass().add("locationDiv");
		pane.setPrefHeight(146.00);
		pane.prefWidth(424.00);
		pane.setMinHeight(146);
		Label label = new Label("Connection");
		TextField textfield1 = new TextField();
		textfield1.setPromptText("C:\\Users\\ASUprint\\Desktop\\Enfocus\\Switch\\ASU Print Online\\Output\\Ready for Print");
		TextField textfield2 = new TextField();
		textfield2.setPromptText("J:\\CMYK+Spot");
		textfield1.getStyleClass().add("textboxtext");
		textfield2.getStyleClass().add("textboxtext");
		textfield1.setPrefWidth(400);
		textfield2.setPrefWidth(400);

		label.getStyleClass().add("connections");
		label.setPadding(new Insets(25, 0, 5, 25));
		label.setPrefHeight(35);
		label.setOnMouseClicked(event -> handleEditTranferLocation());
		vbox.getChildren().add(0,label);
		vbox.getChildren().add(1,textfield1);
		vbox.getChildren().add(2,textfield2);
		pane.getChildren().add(0,vbox);
		
		transferLocList.getChildren().add(transferLocList.getChildren().size(),pane);
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
