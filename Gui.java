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
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Gui extends Application {
  private UserAccountList userAccountList = new UserAccountList();
  
  public void start(Stage primaryStage) throws Exception{
        //User Log in interface.
     	VBox centerBox = new VBox();
    	centerBox.setAlignment(Pos.CENTER);
    
        final ImageView selectedImage = new ImageView();   
        Image image1 = new Image(Gui.class.getResourceAsStream("Logo.jpg"));
		selectedImage.setImage(image1);
		centerBox.getChildren().add(selectedImage);
    
        Label output = new Label ("");
    	Label logInUsername = new Label ("Username:");
  		Label logInPassword = new Label ("Password:");
        TextField txtUsername = new TextField();
        PasswordField txtPassword = new PasswordField();
    	txtUsername.setMaxWidth(200);
        txtPassword.setMaxWidth(200);
        
        HBox loginSignup = new HBox();
        loginSignup.setAlignment(Pos.CENTER);
        Button enterToAccount = new Button("Login");
        Button signupToAccount = new Button("Signup");
        loginSignup.getChildren().addAll(enterToAccount, signupToAccount);


    	centerBox.setSpacing(20);	
      	logInUsername.setFont(Font.font("Verdana", 20));
      	logInPassword.setFont(Font.font("Verdana", 20));
    	logInUsername.setTextFill(Color.WHITE);
        logInPassword.setTextFill(Color.WHITE);
        output.setTextFill(Color.WHITE);
    
        centerBox.getChildren().addAll(logInUsername, txtUsername, logInPassword, txtPassword, loginSignup, output);
        centerBox.setStyle("-fx-background-color: #980E0E;");
        
        //Main Menu Interface
        final ImageView mapImage = new ImageView();   
        Image image2 = new Image(Gui.class.getResourceAsStream("UofCMap.png"));
        mapImage.setImage(image2);
        
        VBox mainMenuGUI = new VBox();
        mainMenuGUI.setAlignment(Pos.CENTER);
        mainMenuGUI.setSpacing(15);	
        Button surveyButton = new Button("Do Survey");
        Button pastButton = new Button("See Previuos Study Spots");
        mainMenuGUI.getChildren().addAll(mapImage, surveyButton, pastButton);
        mainMenuGUI.setStyle("-fx-background-color: #980E0E;");
        Scene sceneForMainMenu = new Scene(mainMenuGUI);

    	
    	enterToAccount.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle (ActionEvent event) {
            	String username = txtUsername.getText();
                String password = txtPassword.getText();
                  
                if(userAccountList.credentialsValid(username, password)){
                    enterToAccount.setOnAction(e -> primaryStage.setScene(sceneForMainMenu));
                    primaryStage.setTitle("Main Menu");
                    output.setText("You have to press enter again. \n //Needs to be fixed");
                }
                else {
                    output.setText("Invalid Username or Password.");
                }
            }
        });

        signupToAccount.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event) {
            	String username = txtUsername.getText();
                String password = txtPassword.getText();
                  
            	if(userAccountList.hasAccount(username)){
                    output.setText("Account Already Exist!");
                }
                else {
                    if(username.isEmpty() || password.isEmpty()){
                        output.setText("Please Enter Username and/or Password.");
                    }
                    else{
                        userAccountList.addAccount(username, password);
                        output.setText("Account Created \n Please Log In.");
                    }
                }
            }
        });

        
        Scene sceneForLogin = new Scene(centerBox, 300,500);
        primaryStage.setTitle("Study Spots App");
        primaryStage.setScene(sceneForLogin);
        primaryStage.show();  
    }
}
