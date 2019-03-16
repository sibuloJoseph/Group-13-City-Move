import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

/**
This class handles a button click and outputs a message.
The handle method is invoked when the button is clicked.
*/
public class HandleButtonClick implements EventHandler<ActionEvent>{
    GuiCopy1 guiCopy = new GuiCopy1();
    String usernameFromGui = guiCopy.getUsername();
    String passwordFromGui = guiCopy.getPassword();
    UserAccountList userAccountListFromGui = guiCopy.getUserAccountList();

    public HandleButtonClick(){}

	@Override
	public void handle(ActionEvent event){
        usernameFromGui = guiCopy.txtUsername.getText();
        passwordFromGui = guiCopy.txtPassword.getText();

        if (usernameFromGui.isEmpty() || passwordFromGui.isEmpty()) {
            guiCopy.output.setText("Please Enter Username and/or Password.");
        }
        else{
            if (userAccountListFromGui.credentialsValid(usernameFromGui, passwordFromGui)) {
                guiCopy.primaryStage.setScene(guiCopy.sceneForMainMenu);
                guiCopy.primaryStage.setTitle("Main Menu - City Move");
            }
            else if (!userAccountListFromGui.hasAccount(usernameFromGui)){
                guiCopy.output.setText("Incorrect Username or \nAccount Does Not Exist!");
            }
            else {
                guiCopy.output.setText("Incorrect Password.");
            }
        }

    }
}
