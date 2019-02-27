package application_v2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPress {
	@FXML
    private TextField textbox; //name of new press in dialog
	@FXML
	private Button confirmPress; //okay button in dialog
	@FXML
	private Button cancelPress; //cancel button in dialog
	
	private String pressName;
	private Stage window;
	
	public void display() {
		VBox root;
		try {
			root = FXMLLoader.load(getClass().getResource("/resources/AddPressWindow.fxml"));
			window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("File Transfer Utility");
		   
			window.setScene(new Scene(root));
			window.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	}
}
