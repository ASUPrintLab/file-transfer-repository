package application;
	
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			 //BorderPane is the layout of the window with the path to retrieve the FXML *Created in SceneBuilder*
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/resources/GUISCENEBUILDER.fxml"));

			Scene scene = new Scene(root,850,700); //Size of the window for the program in pixels
			scene.getStylesheets().add(getClass().getResource("/resources/application.css").toExternalForm()); //Path to CSS
			primaryStage.setScene(scene); //Set style to window
			primaryStage.setTitle("File Transfer Utility"); //Title of Program listed on top-left window when launched
			primaryStage.show(); //Display
	        
		} catch(Exception e) { //Catch Exception and display SEVER null in console
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[]) null); //Launches the program
	}
}
