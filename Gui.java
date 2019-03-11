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
import java.util.ArrayList;

/**
 * This class contains the GUI of the app 
 *
 * Last Modified: March 10th, 9:36pm
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
        Button pastButton = new Button("See Previous Study Spots");
        Button signout = new Button("Sign Out");
        mainMenuGUI.getChildren().addAll(mapImage, surveyButton, pastButton, signout);
        mainMenuGUI.setStyle("-fx-background-color: #980E0E;");

        Scene sceneForMainMenu = new Scene(mainMenuGUI);
        
        
        // Survey Interface
        VBox surveyQuestionsMenu = new VBox (); 

        Label question1 = new Label ("On a scale of 1-10, what's the acceptable level of noise for you at your ideal study spot? (1: I can work in a loud place., 10: no noise at all.)");
        Label question2 = new Label ("On a scale of 1-10, how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"); 
        Label question3 = new Label ("On a scale of 1-10, how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)");
        Label question4 = new Label ("On a scale of 1-10, how much seating space is ideal for your study spot? (1: limited, 10: plentiful)");
        Label question5 = new Label ("On a scale of 1-10, how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)");

        Label outputForInvalidQ1Value = new Label ("");
        Label outputForInvalidQ2Value = new Label ("");
        Label outputForInvalidQ3Value = new Label ("");
        Label outputForInvalidQ4Value = new Label ("");
        Label outputForInvalidQ5Value = new Label ("");

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

        surveyQuestionsMenu.setSpacing(15);
        surveyQuestionsMenu.getChildren().addAll(question1,question1Text,outputForInvalidQ1Value,question2,question2Text,outputForInvalidQ2Value,question3,question3Text,outputForInvalidQ3Value,question4,question4Text,outputForInvalidQ4Value,question5,question5Text,outputForInvalidQ5Value,submitOrGoBack);
        surveyQuestionsMenu.setStyle("-fx-background-color: #F5F5DC;");

        Scene sceneForSurveyMenu = new Scene (surveyQuestionsMenu);


        //Results Menu from Survey
        VBox resultsMenu = new VBox();
        HBox displayForFirstSpot = new HBox(5);
        HBox displayForSecondSpot = new HBox(5);
        HBox displayForThirdSpot = new HBox(5);

        Label firstStudySpot = new Label ("Your first Study Spot is: ");
        Label secondStudySpot = new Label ("Your second Study Spot is: ");
        Label thirdStudySpot = new Label ("Your third Study Spot is: ");

        Label first = new Label ("");
        Label second = new Label ("");
        Label third = new Label ("");

        Button goBackToMainMenuButtonFromResults = new Button ("Go back to Main Menu");

        displayForFirstSpot.getChildren().addAll(firstStudySpot,first);
        displayForSecondSpot.getChildren().addAll(secondStudySpot,second);
        displayForThirdSpot.getChildren().addAll(thirdStudySpot,third);

        first.setFont(Font.font("Verdana", 15));
        second.setFont(Font.font("Verdana", 15));
        third.setFont(Font.font("Verdana", 15));

        firstStudySpot.setFont(Font.font("Verdana", 15));
        secondStudySpot.setFont(Font.font("Verdana", 15));
        thirdStudySpot.setFont(Font.font("Verdana", 15));

        firstStudySpot.setTextFill(Color.WHITE);
        secondStudySpot.setTextFill(Color.WHITE);
        thirdStudySpot.setTextFill(Color.WHITE);

        resultsMenu.setSpacing(50);
        resultsMenu.getChildren().addAll(displayForFirstSpot,displayForSecondSpot,displayForThirdSpot,goBackToMainMenuButtonFromResults);
        resultsMenu.setStyle("-fx-background-color: #980E0E;");

        Scene sceneForResultsMenu = new Scene (resultsMenu, 700, 300);

        // Event Handler to Login
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

        //Event Handler to Signup
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

        //Event Handler to Signout from the Main Menu and back to the Login screen.
        signout.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
                primaryStage.setScene(sceneForLogin); 
                primaryStage.setTitle("Study Spots App - City Move");
                txtPassword.setText("");
            }
        });

        //Event Handler to get to the Survey Menu
        surveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForSurveyMenu);
                primaryStage.setTitle("Survey - City Move");
            }
        });
        
        //Event Handler to submit survey responses
        finishSurveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                Boolean valueIsValid1 = false;
                Boolean valueIsValid2 = false;
                Boolean valueIsValid3 = false;
                Boolean valueIsValid4 = false;
                Boolean valueIsValid5 = false;

                Double question1Value = new Double(1.0);
                Double question2Value = new Double(1.0);
                Double question3Value = new Double(1.0);
                Double question4Value = new Double(1.0);
                Double question5Value = new Double(1.0);

                try {
                    question1Value = Double.parseDouble(question1Text.getText()); 
                    if (question1Value >= 1.0 && question1Value <= 10.0) {
                        outputForInvalidQ1Value.setText("");
                        valueIsValid1 = true;
                    }
                    else {
                        outputForInvalidQ1Value.setText("Invalid Value. Please enter a value from 1-10.");
                        valueIsValid1 = false;
                        }
                    
                } catch (NumberFormatException e){
                    //TODO: handle exception
                    outputForInvalidQ1Value.setText("Cannot Input Strings!");
                    valueIsValid1 = false;

                }

                try {
                    question2Value = Double.parseDouble(question2Text.getText());
                    if (question2Value >= 1.0 && question2Value <= 10.0) {
                        outputForInvalidQ2Value.setText("");
                        valueIsValid2 = true;
                    }
                    else {
                        outputForInvalidQ2Value.setText("Invalid Value. Please enter a value from 1-10.");
                        valueIsValid2 = false;
                        }
                } catch (NumberFormatException e) {
                    //TODO: handle exception
                    outputForInvalidQ2Value.setText("Cannot Input Strings!");
                        valueIsValid2 = false;
                }

                try {
                    question3Value = Double.parseDouble(question3Text.getText());
                    if (question3Value >= 1.0 && question3Value <= 10.0) {
                        outputForInvalidQ3Value.setText("");
                        valueIsValid3 = true;
                    }
                    else {
                        outputForInvalidQ3Value.setText("Invalid Value. Please enter a value from 1-10.");
                        valueIsValid3 = false;
                        }
                } catch (NumberFormatException e) {
                    //TODO: handle exception
                    outputForInvalidQ3Value.setText("Cannot Input Strings!");
                    valueIsValid3 = false;
                }

                try {
                    question4Value = Double.parseDouble(question4Text.getText());
                    if (question4Value >= 1.0 && question4Value <= 10.0) {
                        outputForInvalidQ4Value.setText("");
                        valueIsValid4 = true;
                    }
                    else {
                        outputForInvalidQ4Value.setText("Invalid Value. Please enter a value from 1-10.");
                        valueIsValid4 = false;
                        }
                } catch (NumberFormatException e) {
                    //TODO: handle exception
                    outputForInvalidQ4Value.setText("Cannot Input Strings!");
                    valueIsValid4 = false;
                }

                try {
                    question5Value = Double.parseDouble(question5Text.getText());
                    if (question5Value >= 1.0 && question5Value <= 10.0) {
                        outputForInvalidQ5Value.setText("");
                        valueIsValid5 = true;
                    }
                    else {
                        outputForInvalidQ5Value.setText("Invalid Value. Please enter a value from 1-10.");
                        valueIsValid5 = false;
                        }
                } catch (NumberFormatException e) {
                    //TODO: handle exception
                    outputForInvalidQ5Value.setText("Cannot Input Strings!");
                    valueIsValid5 = false;
                }

                if (valueIsValid1 && valueIsValid2 && valueIsValid3 && valueIsValid4 && valueIsValid5 == true) {
                    Survey aPerson = new Survey();
                    aPerson.setNoiseLevel(question1Value);
                    aPerson.setBathroomsNearby(question2Value);
                    aPerson.setFoodNearby(question3Value);
                    aPerson.setSeatingSpace(question4Value);
                    aPerson.setOutlets(question5Value);

                    IdealStudySpot idealStudySpot = new IdealStudySpot (aPerson);
                    StudySpotList ssl = new StudySpotList ();
                    ssl.setUserIdeal(idealStudySpot);
                    ArrayList<StudySpot> bestSpotList = ssl.getBestStudySpots();
                    
                    primaryStage.setScene(sceneForResultsMenu);
                    primaryStage.setTitle("Survey Results - City Move");
                    
                    first.setText(bestSpotList.get(0).getName());
                    second.setText(bestSpotList.get(1).getName());
                    third.setText(bestSpotList.get(2).getName());
                }
            }
        });

        //Event Handler to go back to the Main Menu screen from the Survey screen
        goBackToMainMenuButtonFromSurvey.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForMainMenu);
                primaryStage.setTitle("Main Menu - City Move");
            }
        });
        
        //Event Handler to go back to the Main Menu Screen from the Results Menu screen.
        goBackToMainMenuButtonFromResults.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                primaryStage.setScene(sceneForMainMenu);
                primaryStage.setTitle("Main Menu - City Move");
            }
        });
    }
}
