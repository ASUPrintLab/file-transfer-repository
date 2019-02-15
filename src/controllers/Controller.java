package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application_v2.AddPress;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {
	
	@FXML
    private Button addPress; //Add press button in Accordion
	@FXML
    private AnchorPane pressList; //Press list in GUI
	@FXML
    private TextField pressName; //name of new press in dialog
	
	Parent root;
	
//	Main stage;
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		addPress.setOnAction(this::handleNewPress);
	}
	
	@FXML
	private void handleNewPress(ActionEvent event) {
		try {
			VBox root = FXMLLoader.load(getClass().getResource("/resources/AddPressWindow.fxml"));
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("File Transfer Utility");
		   
			window.setScene(new Scene(root));
			window.showAndWait();
			addPressToScene();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Adds press name to GUI
	 */
	@FXML
	public void addPressToScene() {
		Button newPress = new Button("Test");
		newPress.getStyleClass().add("addPress");
		pressList.getChildren().add(newPress);
		
		
		
//		pressList.getChildren().add(0,newPress);
//		Scene newScene = pressList.getScene();
////		Main.setScene(newScene );
		ObservableList<Node> list = pressList.getChildren();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
//		Main.RefreshStage();

	}

}
