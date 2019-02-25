package application_v2;
	
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.TableViewController;
import controllers.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	private static Stage mainStage;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.mainStage = primaryStage;
		
		try {
			 //VBox is the layout of the window with the path to retrieve the FXML *Created in SceneBuilder*
			VBox root = FXMLLoader.load(getClass().getResource("/resources/gui.fxml"));
			Scene scene = new Scene(root, 900, 600, Color.BLACK); //Size of the window for the program in pixels
			scene.getStylesheets().add(getClass().getResource("/resources/stylesheet.css").toExternalForm()); //Path to CSS
			primaryStage.setScene(scene); //Set style to window
			primaryStage.setTitle("File Transfer Utility"); //Title of Program listed on top-left window when launched
			primaryStage.getIcons().add(new Image("/resources/upload.png"));
			primaryStage.setOnCloseRequest(confirmCloseEventHandler);
			primaryStage.show(); //Display
			primaryStage.setResizable(false);
	        
		} catch(Exception e) { //Catch Exception and display SEVER null in console
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	private static EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Please dont leave me"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
       
        exitButton.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event) {
               //System.exit(0);
               //possibly stop threads here
            }
        });
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Are you sure you want to exit?");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(mainStage);
        closeConfirmation.setX(mainStage.getX() + 200);
        closeConfirmation.setY(mainStage.getY() + 100);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[]) null); //Launches the program
	}

	public static void RefreshStage() {
		
	}

	public static void setScene(Scene newScene) {
		mainStage.setScene(newScene);
		
	}
}
