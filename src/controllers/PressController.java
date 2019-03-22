package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application_v2.PressManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/*
 * Author: Mitchell Roberts
 */
public class PressController implements Initializable {
    /*
     * Variables for Press Window
     */
    @FXML
    private Button confirmPress; //okay button in dialog
    @FXML
    private Button cancelPress; //cancel button in dialog
    @FXML
    private TextField pressName; //name of new press in dialog


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        //Bind the event handler
        confirmPress.setOnAction(this::handleconfirm);


        cancelPress.setOnAction(this::handlecancel);

    }

    @FXML
    private void handleconfirm(ActionEvent event) {



        if (pressName.getText().trim().isEmpty()) { //Check if the textfield is empty
            PressManager.setRecentAdded(false);	
            System.out.println("This if statement has been reached");
        }
        else {
            PressManager.addPress(pressName.getText(), pressName.getText().hashCode());
            PressManager.setRecentAdded(true);	

        }

        Stage stage = (Stage) confirmPress.getScene().getWindow();
        stage.close(); //Close current window

    }

    @FXML
    private void handlecancel(ActionEvent event) {
        PressManager.setRecentAdded(false);	
        // get a handle to the stage
        Stage stage = (Stage) cancelPress.getScene().getWindow();
        stage.close(); //Close current window
    }


}
