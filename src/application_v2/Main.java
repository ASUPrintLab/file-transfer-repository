package application_v2;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {

	private static Stage mainStage;
	public Scene scene;
	private double xoffset;
	private double yoffset;
	
	@Override
	public void start(Stage primaryStage) {

		this.mainStage = primaryStage;

		try {
			 //VBox is the layout of the window with the path to retrieve the FXML *Created in SceneBuilder*
			VBox root = FXMLLoader.load(getClass().getResource("/resources/gui.fxml"));
			
			// Being able to move the program around
			root.setOnMousePressed(event -> {
				xoffset = event.getSceneX();
				yoffset = event.getSceneY();
			});
			root.setOnMouseDragged(e->{
				primaryStage.setX(e.getScreenX() - xoffset);
				primaryStage.setY(e.getScreenY() - yoffset);
			});
			
			Scene scene = new Scene(root, 912, 600, Color.BLACK); //Size of the window for the program in pixels
			scene.getStylesheets().add(getClass().getResource("/resources/stylesheet.css").toExternalForm()); //Path to CSS
			primaryStage.setScene(scene); //Set style to window
			primaryStage.setTitle("File Transfer Utility"); //Title of Program listed on top-left window when launched
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.getIcons().add(new Image("/resources/upload.png"));
			primaryStage.show(); //Display
			primaryStage.setResizable(false);

		} catch(Exception e) { //Catch Exception and display SEVER null in console
			Logs.writeToException(e.toString());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[]) null); //Launches the program
	}

	public static void RefreshStage() {

	}

	public static void setScene(Scene newScene) {
		mainStage.setScene(newScene);

	}
}
