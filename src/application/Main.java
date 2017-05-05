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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GUISCENEBUILDER.fxml"));

			Scene scene = new Scene(root,850,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("File Transfer Utility");
			primaryStage.show();
	        
		} catch(Exception e) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[]) null);
	}
}
