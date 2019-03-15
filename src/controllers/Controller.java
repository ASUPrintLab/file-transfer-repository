package controllers;


import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.script.Bindings;

import animatefx.animation.FadeOut;
import animatefx.animation.GlowText;
import animatefx.animation.Pulse;
import application_v2.Locations;
import application_v2.Logs;
import application_v2.Main;
import application_v2.Press;
import application_v2.PressManager;
import application_v2.TransferTime;
import application_v2.Worker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
/*
 * Author: Mitchell Roberts
 * Author: Gaurav Deshpande
 */
public class Controller implements Initializable{

	@FXML
    private Button run; //start the program
	@FXML
    private Button stop; //stop the program
	@FXML
    private Button addPress; //Adding a press
	@FXML
    private ImageView running1; //arrow animation
	@FXML
    private ImageView running2; //arrow animation
	@FXML
    private ImageView running3; //arrow animation
	@FXML
    private ImageView running4; //arrow animation
	@FXML
    private ImageView running5; //arrow animation
	@FXML
    private ImageView running6; //arrow animation
	@FXML
    private ImageView running7; //arrow animation
	@FXML
    private ImageView addTransferLocation; //Add Transfer Location '+'
	@FXML
    private ImageView startIcon; //Add Transfer Location '+'
	@FXML
    private ImageView cancel;
	@FXML
    private ImageView delete;
	@FXML
    private ImageView stopIcon; //Add Transfer Location '+'
	@FXML
    private ImageView close; //The 'x' to close the program
	@FXML
    private Label notifier; //Event Log label
	@FXML
    private Label message1; //Event Log label
	@FXML
    private Label message2; //Event Log label
	@FXML
    private Label editTransferLocation; //Add Transfer Location '+'
	@FXML
    private ImageView addTransferTime; //Add Transfer Time '+'
	@FXML
    private ImageView edit; //Edit Transfer Time
	@FXML
    private VBox pressList; //Press list in GUI
	@FXML
    private VBox transferLocList; //Location list in GUI
	@FXML
    private VBox pressLocList; //Press list in GUI
	@FXML
    private ScrollPane scrollpane; //ScrollPane
	@FXML
    private TextField connectionName; //name of connection in dialog
	@FXML
	private TableView<TransferTime> timeTable; //Table
	@FXML
	private TableColumn<TransferTime, String> startTime; //Columns
	@FXML
	private TableColumn<TransferTime, String> endTime;
	@FXML
	private TableColumn<TransferTime, String> actions;
	@FXML
	private Circle circle;

	@FXML
	private Pane pressPane; //Used to identify the press pane for scrolling
	@FXML
	private Pane transferLocationPane; // Used to identify the transfer location pane for scrolling

	@FXML
	private MenuBar menuBar; // Used to identify the size of the program

	@FXML
	private ScrollPane scrollPaneAddPress; // Scroll pane created for adding a new press
	@FXML
	private Label pressLabel; // Representing the name of the presses
	@FXML
	private ScrollPane scrollPaneTransferLocation; // Scroll pane created for adding a new transfer location

	private Press selectedPress;
	private Pane pane;
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	ExecutorService executorService;

	private FadeOut action1;
	private FadeOut action2;
	private FadeOut action3;
	private FadeOut action4;
	private FadeOut action5;
	private FadeOut action6;
	private FadeOut action7;
	private Pulse playAction;

	private int pressListIncreased = 412;
	private int transferLocationIncreased = 400;

	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Always show vertical scroll bar that is displayed to the right of transfer locations
//		scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
//		VBox.setVgrow(scrollpane, Priority.ALWAYS);

		// Don't show the scroll bar for the transfer location section initially
		scrollPaneTransferLocation.setVbarPolicy(ScrollBarPolicy.NEVER);
		VBox.setVgrow(scrollPaneTransferLocation, Priority.NEVER);

		// Don't show the scroll bar for the add press section initially
		scrollPaneAddPress.setVbarPolicy(ScrollBarPolicy.NEVER);

		VBox.setVgrow(scrollPaneAddPress, Priority.NEVER);


		run.setOnAction(this::start);
		stop.setOnAction(this::stop);
		addPress.setOnAction(this::handleNewPress);
		stop.setDisable(true);


		addTransferLocation.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //Handle mouse event on imageview
			if (selectedPress != null) {
				handleNewTranferLocation();
			}
	     });
		addTransferTime.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //Handle mouse event on imageview
			if (selectedPress != null) {
				handleNewTranferTime();
			}
	     });
		edit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //Handle mouse event on imageview
			if (selectedPress != null && !timeTable.getItems().isEmpty()) {
				handleEdit();
			}
	     });
		cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //Handle mouse event on imageview
			addTransferTime.setVisible(true);
			edit.setVisible(true);
			cancel.setVisible(false);
			delete.setVisible(false);
			actions.setVisible(false);
	     });
		delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //Handle mouse event on imageview
			handleTableRemoval();
	     });
		close.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //Handle mouse event on imageview
			handleClose(event);
	     });
		//Applies the objects to the actual cells in the table
		startTime.setCellValueFactory(new PropertyValueFactory<TransferTime, String>("startTime"));
		endTime.setCellValueFactory(new PropertyValueFactory<TransferTime, String>("stopTime"));
		actions.setCellValueFactory(new PropertyValueFactory<TransferTime, String>("edit"));
		actions.setVisible(false);
	}
	/*
	 * Handles the close 'x' button for the program
	 */
	private void handleClose(MouseEvent event) {
		Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,"Please dont leave me");
		Stage stage = (Stage) close.getScene().getWindow();

        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);

        exitButton.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event) {
        		//Shutdown and dont allow new task to enter pool
        		if (!PressManager.isEmpty()) {
        			executorService.shutdownNow();
        			for (int i = 0; i < workers.size(); i++) {
        				workers.get(i).cancel(true);
        			}	
        		}
        		Logs.closeFiles();
        		stage.close();
            }
        });

        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Are you sure you want to exit?");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(stage);
        closeConfirmation.setX(stage.getX() + 200);
        closeConfirmation.setY(stage.getY() + 100);
        closeConfirmation.showAndWait();

	}

	private void handleTableRemoval() {
		int size = timeTable.getItems().size();
		for (int i = 0; i < size; i++) { //Table will get smaller as you remove so double for loop to ensure all elements get removed
			for (int j = 0; j < timeTable.getItems().size(); j++) {
				if (timeTable.getItems().get(j).getEdit().isSelected()) { // Remove selected check boxes
					timeTable.getItems().remove(j);
					break;
				}
			}
		}
		List<TransferTime> collection = timeTable.getItems(); //Now update the list and press
		ArrayList<TransferTime> times = new ArrayList<TransferTime>();
		times.addAll(collection);
		selectedPress.setTransferTimes(times);
		PressManager.updatePress(selectedPress); //Update the press in the hashmap
	}

	private void handleEdit() {
		actions.setVisible(true);
		addTransferTime.setVisible(false);
		edit.setVisible(false);
		cancel.setVisible(true);
		delete.setVisible(true);
	}

	@FXML
	private void start(ActionEvent event) {
		Logs.writeToEvent("This is an event");
		Logs.writeToException("This is an exception");
		stop.setDisable(false);
		run.setDisable(true); //Disable button
		startAnimation(); //Start animation
		if (!PressManager.isEmpty()) {
			ArrayList<Press> list = PressManager.getAllPresses();

			executorService = Executors.newFixedThreadPool(list.size());
			for (Press press : list) {
				if (!press.locationsEmpty() && !press.timesEmpty()) { //Prevent empty presses from creating tasks
					Worker worker = new Worker(press);
					workers.add(worker);	
					message1.textProperty().bind(worker.messageProperty());
//					worker.messageProperty().addListener((obs, oldMsg, newMsg) -> {
//						System.out.println("Event happened!");
//						message2.setText(message1.getText());
//						message1.setText(newMsg);
//					});
					executorService.execute(worker); //Start running tasks
				}
			}
		}
	}
	/*
	 * Handles the animation of the start button
	 */
	private void startAnimation() {
		circle.setVisible(true);
		playAction = new Pulse(circle);
		playAction.setCycleCount(500).setDelay(Duration.valueOf("0ms")).play();
		Date date = new Date();
		String strDateFormat = "EEE, MMM d, hh:mm a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate = dateFormat.format(date);
		notifier.setText("Running... Started on " + formattedDate);
		stop.setStyle(null); //Remove style on stop button
		run.setStyle("-fx-effect: dropshadow(GAUSSIAN,  #0dff01, 15, 0, 0, 0);"); //Add gradient to run button
		//Display images
		running1.setVisible(true);
		running2.setVisible(true);
		running3.setVisible(true);
		running4.setVisible(true);
		running5.setVisible(true);
		running6.setVisible(true);
		running7.setVisible(true);
		action1 = new FadeOut(running1);
		action1.setCycleCount(500).setDelay(Duration.valueOf("50ms")).play();
		action1.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action1.setCycleCount(500).setDelay(Duration.valueOf("0ms")).play();
			}
        });
		action2 = new FadeOut(running2);
		action2.setCycleCount(500).setDelay(Duration.valueOf("100ms")).play();
		action2.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action2.setDelay(Duration.valueOf("0ms")).play();
			}
        });
		action3 = new FadeOut(running3);
		action3.setCycleCount(500).setDelay(Duration.valueOf("150ms")).play();
		action3.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action3.setDelay(Duration.valueOf("0ms")).play();
			}
        });
		action4 = new FadeOut(running4);
		action4.setCycleCount(500).setDelay(Duration.valueOf("200ms")).play();
		action4.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action4.setCycleCount(500).setDelay(Duration.valueOf("0ms")).play();
			}
        });
		action5 = new FadeOut(running5);
		action5.setCycleCount(500).setDelay(Duration.valueOf("250ms")).play();
		action5.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action5.setCycleCount(500).setDelay(Duration.valueOf("0ms")).play();
			}
        });
		action6 = new FadeOut(running6);
		action6.setCycleCount(500).setDelay(Duration.valueOf("300ms")).play();
		action6.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action6.setCycleCount(500).setDelay(Duration.valueOf("0ms")).play();
			}
        });
		action7 = new FadeOut(running7);
		action7.setCycleCount(500).setDelay(Duration.valueOf("350ms")).play();
		action7.setOnFinished(new EventHandler<ActionEvent>() { //Resets animation after it finishes
			@Override
			public void handle(ActionEvent arg0) {
				action7.setCycleCount(500).setDelay(Duration.valueOf("0ms")).play();
			}
        });


	}
	@FXML
	private void stop(ActionEvent event) {
		Logs.closeFiles();
		stop.setDisable(true);
		run.setDisable(false);

		//Stop all thread safely to prevent corrupted memory
		if (!PressManager.isEmpty()) {
			executorService.shutdownNow();
			for (int i = 0; i < workers.size(); i++) {
				workers.get(i).cancel(true);
			}	
		}

		stopAnimation(); //Stop the animation
	}
	/*
	 * This method stops the animation and adds the time stopped in the GUI
	 */
	private void stopAnimation() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		playAction.stop();
		circle.setVisible(false);
		notifier.setText("Stopped on " + dateFormat.format(date));
		stop.setStyle("-fx-effect: dropshadow(GAUSSIAN,  #ff0000, 15, 0, 0, 0);");
		run.setStyle(null);
		running1.setVisible(false);
		running2.setVisible(false);
		running3.setVisible(false);
		running4.setVisible(false);
		running5.setVisible(false);
		running6.setVisible(false);
		running7.setVisible(false);
		//Stop animations
		action1.stop();
		action2.stop();
		action3.stop();
		action4.stop();
		action5.stop();
		action6.stop();
		action7.stop();
	}
	@FXML
	private void handleNewPress(ActionEvent event) {
		clearGUI();
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
	private void handleNewTranferLocation() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TransferLocationWindow.fxml"));
			VBox root = (VBox) loader.load();
			LocationController mainController = loader.<LocationController>getController();
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.getIcons().add(new Image("/resources/icon.png"));
			window.setTitle("Edit Location");
			window.setScene(new Scene(root));
			window.showAndWait(); //Wait until window closes
			if (!mainController.getName().trim().isEmpty()) {
				createComponent(mainController.getName(), mainController.getSourceLoc(), mainController.getTargetLoc());
				addLocationToPress(mainController.getName(), mainController.getSourceLoc(), mainController.getTargetLoc());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Creates new ui component for Transfer Location
	 */
	private void createComponent(String name, String sourceLoc, String targetLoc) {
		pane = new Pane();
		VBox vbox = new VBox();
		pane.getStyleClass().add("locationDiv");
		pane.setPrefHeight(146.00);
		pane.prefWidth(380.00);
		pane.setMinHeight(146);
		Label label = new Label(name);
		TextField textfield1 = new TextField();
		TextField textfield2 = new TextField();
		textfield1.setText(sourceLoc);
		textfield2.setText(targetLoc);
		textfield1.getStyleClass().add("textboxtext");
		textfield2.getStyleClass().add("textboxtext");
		textfield1.setPrefWidth(400);
		textfield2.setPrefWidth(400);
		textfield2.setEditable(false);

		label.getStyleClass().add("connections");
		label.setPadding(new Insets(25, 0, 5, 25));
		label.setPrefHeight(35);
		label.setOnMouseClicked(event -> handleEditTranferLocation());
		vbox.getChildren().add(0,label);
		vbox.getChildren().add(1,textfield1);
		vbox.getChildren().add(2,textfield2);
		pane.getChildren().add(0,vbox);

		/* Once the Vbox increases more than the transfer locations pane create a scroll bar and continue
		*  to be able to add transfer locations
		*/
		if(transferLocList.getPrefHeight() > transferLocationPane.getPrefHeight()) {

			scrollPaneTransferLocation.setVbarPolicy(ScrollBarPolicy.ALWAYS);

			VBox.setVgrow(scrollPaneTransferLocation, Priority.ALWAYS);

			// Makes sure the scroll bar is set to the size of how many transfer locations there are
			scrollPaneTransferLocation.vvalueProperty().bind(transferLocList.heightProperty());


			// Adding a new transfer location associated with a press
			transferLocList.getChildren().add(transferLocList.getChildren().size(),pane);

		}
		else {
			System.out.println("The else line has been reached");
			transferLocationIncreased = transferLocationIncreased + 30;
			transferLocList.setPrefHeight(transferLocationIncreased);


			// Adding a new transfer location associated with a press
			transferLocList.getChildren().add(transferLocList.getChildren().size(),pane);


		}


	}
	/*
	 * Adds the new location to the press
	 */
	private void addLocationToPress(String name, String sourceLoc, String targetLoc) {
		ArrayList<Locations> locations = selectedPress.getLocations();
		if (locations != null) {
			locations.add(new Locations(name, sourceLoc, targetLoc));
		}
		else { //If location array is null lets create a new one
			locations = new ArrayList<Locations>();
			locations.add(new Locations(name, sourceLoc, targetLoc));
		}
		selectedPress.setLocations(locations);
		PressManager.updatePress(selectedPress); //Update the press in the hashmap
	}

	/*
	 * Handles event for adding a new transfer time
	 */
	@FXML
	private void handleNewTranferTime() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/TransferTimeWindow.fxml"));
			VBox root = (VBox) loader.load();
			TimeController mainController = loader.<TimeController>getController();
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.getIcons().add(new Image("/resources/icon.png"));
			window.setTitle("Edit Location");
			window.setScene(new Scene(root));
			window.showAndWait(); //Wait until window closes
			ObservableList<TransferTime> list = mainController.getTimes();
			if (mainController.ifSaved() && !list.isEmpty()) { //If list is not empty lets add them to the main table
				addTimesToTable(list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method add the new transfer times to the main table
	 */
	private void addTimesToTable(ObservableList<TransferTime> list) {
		ArrayList<TransferTime> times = new ArrayList<TransferTime>(); //initialize temp array for new times
		for (int i = 0; i < list.size(); i++) {
			TransferTime time = list.get(i);
			times.add(time);
			//Add times to table
			timeTable.getItems().add(time);
		}
		selectedPress.updateTimes(times);
		PressManager.updatePress(selectedPress); //Update Press in manager
		actions.setVisible(false);
	}


	/*
	 * Updates the GUI with the current Press info
	 */
	private void updateGUI() {
		clearGUI();
		ArrayList<Locations> locations = selectedPress.getLocations();
		ArrayList<TransferTime> times = selectedPress.getTransferTimes();
		if (locations != null) {
			for (int i = 0; i < locations.size(); i++) { //Lets add the locations back to the UI
				createComponent(locations.get(i).getName(), locations.get(i).getFromLocation(), locations.get(i).getToLocation());
			}
		}
		if (times != null) { //Now add the Transfer times back into the main table
			for (int i = 0; i < times.size(); i++) {
				TransferTime time = times.get(i);
				//Add times to table
				timeTable.getItems().add(time);
			}
		}

	}
	/*
	 * Clears the elements in the Locations pane and time table
	 */
	private void clearGUI() {
		if (transferLocList != null || pressLocList != null) {
			transferLocList.getChildren().clear();
			timeTable.getItems().clear();
		}
	}
	
	/*
	 * add message of recent transfer to GUI
	 */
	public void transferSentMsg(String msg) {
		System.out.println("Success");
		System.out.println(msg);
		message2.setText(message1.getText());
		message1.setText(msg);
		System.out.println(message1);
		System.out.println("Done");
	}

	// When new press is added set the buttons to a default style
	private void clearPresses() {
			// Creating a generic type to store all of the children in
			ObservableList<Node> tempButton = pressLocList.getChildren();
			for (int i = 0; i < tempButton.size(); i++) {
				Button newButton = (Button) tempButton.get(i);

				newButton.setStyle("-fx-background-color: #262626; -fx-text-fill: white;");
			}

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
		newPress.setMinWidth(pressLabel.getWidth());
		newPress.setUserData(press);


		press.setKey(newPress.hashCode());
		PressManager.addPress(press.getName(), newPress.hashCode()); //Add new press with the same hash code as the button
		selectedPress = press;


		clearPresses();
		//Set action to handle the new press button



		newPress.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            	clearPresses();

            		newPress.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");


                	selectedPress = PressManager.getPress(event.getSource().hashCode()); //Get the press info

                	updateGUI(); //Lets update the UI with the presses current info

                	newPress.setOnMouseClicked(new EventHandler<MouseEvent>() {

        	            @Override
        	            public void handle(MouseEvent event) {
        	                MouseButton button = event.getButton();
        	                // Allow right click only when a press is highlighted
        	                if(button==MouseButton.SECONDARY && newPress.hashCode()==selectedPress.getKey()){

        	                	Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

        	                    Stage stage = (Stage) close.getScene().getWindow();

        	                    Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);

        	                    exitButton.setOnAction(new EventHandler<ActionEvent>(){
        	                    	@Override
        	                    	public void handle(ActionEvent event) {
        	                    		// Specifying the press we want to remove from the PressManager
        	                    		ObservableList<Node> tempButton = pressLocList.getChildren();

        	                    		PressManager.removePress(selectedPress.getKey());
        	                    		pressLocList.getChildren().remove(tempButton.indexOf(newPress));

        	                    		clearGUI();
        	                        }
        	                    });
        	                    exitButton.setText("Delete");
        	                    closeConfirmation.setHeaderText("Are you sure you want to remove this press?");
        	                    closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        	                    closeConfirmation.initOwner(stage);
        	                    closeConfirmation.setX(stage.getX() + 200);
        	                    closeConfirmation.setY(stage.getY() + 100);
        	                    closeConfirmation.showAndWait();
        	                }
        	            }
        	        });
            	}

           });

		/* Once the Vbox increases more than the press pane create a scroll bar and continue
		*  to be able to add presses
		*/	if(pressLocList.getPrefHeight() > pressPane.getPrefHeight()) {
		
			scrollPaneAddPress.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			VBox.setVgrow(scrollPaneAddPress, Priority.ALWAYS);

			// Makes sure the scroll bar is set to the size of how many presses there are
			scrollPaneAddPress.vvalueProperty().bind(pressLocList.heightProperty());

			pressLocList.getChildren().add(0,newPress); //Add button to top of children

		}
		else {
			pressListIncreased = pressListIncreased + 4;
			pressLocList.setPrefHeight(pressListIncreased);

			pressLocList.getChildren().add(0,newPress); //Add button to top of children


		}


	}


}
