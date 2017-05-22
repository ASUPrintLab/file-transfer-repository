package application;
	
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage mainStage;
	TableViewController tvc;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.mainStage = primaryStage;
		
		try {
			 //BorderPane is the layout of the window with the path to retrieve the FXML *Created in SceneBuilder*
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/resources/GUISCENEBUILDER.fxml"));

			Scene scene = new Scene(root,835,700); //Size of the window for the program in pixels
			scene.getStylesheets().add(getClass().getResource("/resources/application.css").toExternalForm()); //Path to CSS
			primaryStage.setScene(scene); //Set style to window
			primaryStage.setTitle("File Transfer Utility"); //Title of Program listed on top-left window when launched
			primaryStage.getIcons().add(new Image("/resources/Blah.jpg"));
			primaryStage.setOnCloseRequest(confirmCloseEventHandler);
			primaryStage.show(); //Display
			primaryStage.setResizable(false);
	        
		} catch(Exception e) { //Catch Exception and display SEVER null in console
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}
	
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Please dont leave me (¤_¤)"
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
        closeConfirmation.setHeaderText("Did you talk to Chris first?");
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
}
