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

/**
 * This class contains the GUI of the app 
 *
 * Last Modified: March 9th, 6:57pm
 */

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

        Scene sceneForLogin = new Scene(centerBox, 300,500);
        primaryStage.setTitle("Study Spots App");
        primaryStage.setScene(sceneForLogin);
        primaryStage.show();  
        
        //Main Menu Interface
        final ImageView mapImage = new ImageView();   
        Image image2 = new Image(Gui.class.getResourceAsStream("UofCMap.png"));
        mapImage.setImage(image2);

        VBox mainMenuGUI = new VBox();
        mainMenuGUI.setAlignment(Pos.CENTER);
        mainMenuGUI.setSpacing(15);

        Button surveyButton = new Button("Do Survey");
        Button pastButton = new Button("See Previuos Study Spots");
        Button signout = new Button("Sign Out");
        mainMenuGUI.getChildren().addAll(mapImage, surveyButton, pastButton, signout);
        mainMenuGUI.setStyle("-fx-background-color: #980E0E;");

        Scene sceneForMainMenu = new Scene(mainMenuGUI);
        
        // Survey Interface
        VBox surveyQuestionsMenu = new VBox (); 

        Label question1 = new Label ("On a scale of 1-10, what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)");
        Label question2 = new Label ("On a scale of 1-10, how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"); 
        Label question3 = new Label ("On a scale of 1-10, how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)");
        Label question4 = new Label ("On a scale of 1-10, how much seating space is ideal for your study spot? (1: limited, 10: plentiful)");
        Label question5 = new Label ("On a scale of 1-10, how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)");

        TextField question1Text = new TextField ();
        TextField question2Text = new TextField ();
        TextField question3Text = new TextField ();
        TextField question4Text = new TextField ();
        TextField question5Text = new TextField ();

        HBox submitOrGoBack = new HBox ();
        Button finishSurveyButton = new Button ("Submit Survey");
        Button goBackToMainMenuButtonFromSurvey = new Button ("Go back to Main Menu");
        submitOrGoBack.getChildren().addAll(finishSurveyButton,goBackToMainMenuButtonFromSurvey);

        //question1.setTextFill(Color.WHITE);
        //question2.setTextFill(Color.WHITE);
        //question3.setTextFill(Color.WHITE);
        //question4.setTextFill(Color.WHITE);
        //question5.setTextFill(Color.WHITE);

        question1.setFont(Font.font("Verdana", 15));
        question2.setFont(Font.font("Verdana", 15));
        question3.setFont(Font.font("Verdana", 15));
        question4.setFont(Font.font("Verdana", 15));
        question5.setFont(Font.font("Verdana", 15));

        question1Text.setMaxWidth(50);
        question2Text.setMaxWidth(50);
        question3Text.setMaxWidth(50);
        question4Text.setMaxWidth(50);
        question5Text.setMaxWidth(50);

        surveyQuestionsMenu.setSpacing(20);
        surveyQuestionsMenu.getChildren().addAll(question1,question1Text,question2,question2Text,question3,question3Text,question4,question4Text,question5,question5Text,submitOrGoBack);
        surveyQuestionsMenu.setStyle("-fx-background-color: #F5F5DC;");

        Scene sceneForSurveyMenu = new Scene (surveyQuestionsMenu);

        // Results Menu from Survey
        VBox resultsMenu = new VBox();

        Label firstStudySpot = new Label ("Your first Study Spot is: ");
        Label secondStudySpot = new Label ("Your second Study Spot is: ");
        Label thirdStudySpot = new Label ("Your third Study Spot is: ");

        Button goBackToMainMenuButtonFromResults = new Button ("Go back to Main Menu");

        firstStudySpot.setFont(Font.font("Verdana", 15));
        secondStudySpot.setFont(Font.font("Verdana", 15));
        thirdStudySpot.setFont(Font.font("Verdana", 15));

        firstStudySpot.setTextFill(Color.WHITE);
        secondStudySpot.setTextFill(Color.WHITE);
        thirdStudySpot.setTextFill(Color.WHITE);

        resultsMenu.setSpacing(50);
        resultsMenu.getChildren().addAll(firstStudySpot,secondStudySpot,thirdStudySpot,goBackToMainMenuButtonFromResults);
        resultsMenu.setStyle("-fx-background-color: #FA8072;");

        Scene sceneForResultsMenu = new Scene (resultsMenu);

        // Event Handlers
    	enterToAccount.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle (ActionEvent event) {
            	String username = txtUsername.getText();
                String password = txtPassword.getText();
                  
                if (userAccountList.credentialsValid(username, password)) {
                    primaryStage.setScene(sceneForMainMenu);
                    primaryStage.setTitle("Main Menu - City Move");
                }
                else if (!userAccountList.hasAccount(username)){
                    output.setText("Incorrect Username or \nAccount Does Not Exist!");
                }
                else {
                    output.setText("Incorrect Password.");
                }
            }
        });

        signupToAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent event) {
            	String username = txtUsername.getText();
                String password = txtPassword.getText();
                  
            	if (userAccountList.hasAccount(username)) {
                    output.setText("Account Already Exist!");
                }
                else {
                    if (username.isEmpty() || password.isEmpty()) {
                        output.setText("Please Enter Username and/or Password.");
                    }
                    else {
                        userAccountList.addAccount(username, password);
                        output.setText("Account Created \nPlease Log In.");
                    }
                }
            }
        });

        signout.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
                primaryStage.setScene(sceneForLogin); 
                primaryStage.setTitle("Study Spots App - City Move");
                txtPassword.setText("");
            }
        });

        // For Survey Menu
        surveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForSurveyMenu);
                primaryStage.setTitle("Survey - City Move");
            }
        });
        
        finishSurveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForResultsMenu);
                primaryStage.setTitle("Survey - City Move");
            }
        });

        goBackToMainMenuButtonFromSurvey.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForMainMenu);
                primaryStage.setTitle("Main Menu - City Move");
            }
        });
        
        // For Results Menu
        goBackToMainMenuButtonFromResults.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForMainMenu);
                primaryStage.setTitle("Main Menu - City Move");
            }
        });
    }
}
