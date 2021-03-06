package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {

    private static Stage mainStage;
    public Scene scene;
    private double xoffset;
    private double yoffset;

    @Override
    public void start(final Stage primaryStage) {

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
            javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("/resources/upload.png").toExternalForm());
            primaryStage.getIcons().add(image);
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
