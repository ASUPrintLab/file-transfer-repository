package application;
	
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
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
	
	@Override
	public void start(Stage primaryStage) {
		
		this.mainStage = primaryStage;
		
		try {
			 //BorderPane is the layout of the window with the path to retrieve the FXML *Created in SceneBuilder*
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/resources/GUISCENEBUILDER.fxml"));

			Scene scene = new Scene(root,850,700); //Size of the window for the program in pixels
			scene.getStylesheets().add(getClass().getResource("/resources/application.css").toExternalForm()); //Path to CSS
			primaryStage.setScene(scene); //Set style to window
			primaryStage.setTitle("File Transfer Utility"); //Title of Program listed on top-left window when launched
			primaryStage.getIcons().add(new Image("/resources/Blah.jpg"));
			
			primaryStage.setOnCloseRequest(confirmCloseEventHandler);
			
			primaryStage.show(); //Display
	        
		} catch(Exception e) { //Catch Exception and display SEVER null in console
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}
	
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Exit");
        closeConfirmation.setHeaderText("Please Stop Application First");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(mainStage);
        closeConfirmation.setX(mainStage.getX() + 200);
        closeConfirmation.setY(mainStage.getY() + 100);//+ mainStage.getHeight());

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
    };

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[]) null); //Launches the program
	}
}
