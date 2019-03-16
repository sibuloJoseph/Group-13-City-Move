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
import javafx.scene.control.Label;
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
 * This class contains the GUI of the app 
 *
 * Last Modified: March 12, 2019
 */

public class GuiCopy1 extends Application {
private UserAccountList userAccountList = new UserAccountList();
private IdealStudySpot userData = new IdealStudySpot();
private StudySpotList studySpotList = new StudySpotList();
private String username, password;
private Stage primaryStage;

//Variables of the Login Interface
private Label output = new Label ("");
private Label logInUsername = new Label ("Username:");
private Label logInPassword = new Label ("Password:");
private TextField txtUsername = new TextField();
private PasswordField txtPassword = new PasswordField();
private Button enterToAccount = new Button("Login");
private Button signupToAccount = new Button("Signup");

//Varibles of the Main Menu Interface
private Button surveyButton = new Button("Do Survey");
private Button pastButton = new Button("See Previous Study Spots");
private Button signout = new Button("Sign Out");

//Interface
private VBox loginInterface = new VBox();
private VBox mainMenuGUI = new VBox();
private VBox surveyQuestionsMenu = new VBox ();

//Scenes
Scene sceneForLogin;
Scene sceneForMainMenu;

//Getter Methods
public Stage getPrimaryStage(){return primaryStage;}
public UserAccountList getUserAccountList(){return userAccountList;}

//Getter Methods For the Login Interface Variables
public Label getOutput(){return output;}
public Label getLoginUsername(){return logInUsername;}
public Label getLoginPassword(){return logInPassword;}
public TextField getTxtUsername(){return txtUsername;}
public PasswordField getTxtPassword(){return txtPassword;}
public Button getEnterToAccount(){return enterToAccount;}
public Button getSignupToAccount(){return signupToAccount;}

//Getter Methods For the Main Menu Interface Variables
public Button getSurveyButton(){return surveyButton;}
public Button getPastButton(){return pastButton;}
public Button getSignoutButton(){return signout;}




public void start(Stage primeStage) throws Exception {
        primaryStage = primeStage;
        
        //User Log in interface.
        loginInterface.setAlignment(Pos.CENTER);
        loginInterface.setSpacing(20);

        final ImageView selectedImage = new ImageView();  
        Image image1 = new Image(Gui.class.getResourceAsStream("Logo.jpg"));
        selectedImage.setImage(image1);
        loginInterface.getChildren().add(selectedImage);
    
        txtUsername.setMaxWidth(200);
        txtPassword.setMaxWidth(200);
        
        HBox loginSignup = new HBox();
        loginSignup.setAlignment(Pos.CENTER);
        loginSignup.getChildren().addAll(enterToAccount, signupToAccount);

        logInUsername.setFont(Font.font("Verdana", 20));
        logInPassword.setFont(Font.font("Verdana", 20));
        logInUsername.setTextFill(Color.WHITE);
        logInPassword.setTextFill(Color.WHITE);
        output.setTextFill(Color.WHITE);
    
        loginInterface.getChildren().addAll(logInUsername, txtUsername, logInPassword, txtPassword, loginSignup, output);
        loginInterface.setStyle("-fx-background-color: #980E0E;");

        sceneForLogin = new Scene(loginInterface, 300,500);
        primaryStage.setTitle("Study Spots App");
        primaryStage.setScene(sceneForLogin);

        
        //Main Menu Interface
        final ImageView mapImage = new ImageView();   
        Image image2 = new Image(Gui.class.getResourceAsStream("UofCMap.png"));
        mapImage.setImage(image2);

        mainMenuGUI.setAlignment(Pos.CENTER);
        mainMenuGUI.setSpacing(15);

        mainMenuGUI.getChildren().addAll(mapImage, surveyButton, pastButton, signout);
        mainMenuGUI.setStyle("-fx-background-color: #980E0E;");

        sceneForMainMenu = new Scene(mainMenuGUI);
        
        
        // Survey Interface 
        Label question1 = new Label ("On a scale of 1-10, what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)");
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

        first.setFont(Font.font("Verdana", 25));
        second.setFont(Font.font("Verdana", 25));
        third.setFont(Font.font("Verdana", 25));
    
    
        first.setTextFill(Color.PALEGOLDENROD);
        second.setTextFill(Color.PALEGOLDENROD);
        third.setTextFill(Color.PALEGOLDENROD);

        firstStudySpot.setFont(Font.font("Verdana", 25));
        secondStudySpot.setFont(Font.font("Verdana", 25));
        thirdStudySpot.setFont(Font.font("Verdana", 25));

        firstStudySpot.setTextFill(Color.WHITE);
        secondStudySpot.setTextFill(Color.WHITE);
        thirdStudySpot.setTextFill(Color.WHITE);

        resultsMenu.setSpacing(50);
        resultsMenu.getChildren().addAll(displayForFirstSpot,displayForSecondSpot,displayForThirdSpot,goBackToMainMenuButtonFromResults);
        resultsMenu.setStyle("-fx-background-color: #980E0E;");

        Scene sceneForResultsMenu = new Scene (resultsMenu, 1200, 300);

        // Event Handler to Login
        HandleButtonClick buttonClick = new HandleButtonClick(new GuiCopy1());
        enterToAccount.setOnAction(buttonClick);

        // Event Handler to Login with 'Enter' Key
        loginInterface.setOnKeyPressed(event ->{
            username = txtUsername.getText();
            password = txtPassword.getText();

            if (event.getCode() == KeyCode.ENTER){
                if (username.isEmpty() || password.isEmpty()) {
                        output.setText("Please Enter Username and/or Password.");
                    }
                else{
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
            }
        });

        //Event Handler to Signup
        signupToAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent event) {
                username = txtUsername.getText();
                password = txtPassword.getText();
                
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
        primaryStage.hide();
                txtPassword.setText("");
                output.setText("");
                primaryStage.setScene(sceneForLogin);
                primaryStage.setTitle("Study Spots App - City Move");
        primaryStage.show();
            }
        });

        //Event Handler to get to the Survey Menu
        surveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                question1Text.setText("");
                question2Text.setText("");
                question3Text.setText("");
                question4Text.setText("");
                question5Text.setText("");
                primaryStage.setScene(sceneForSurveyMenu);
                primaryStage.setTitle("Survey - City Move");
            }
        });
        
        //Event Handler to get to the Survey Menu with 'Enter' Key
        mainMenuGUI.setOnKeyPressed(event ->{
            question1Text.setText("");
            question2Text.setText("");
            question3Text.setText("");
            question4Text.setText("");
            question5Text.setText("");
            primaryStage.setScene(sceneForSurveyMenu);
            primaryStage.setTitle("Survey - City Move");
        });
    
    //Event Handler to get to Past Study Spots
        pastButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                userData = userAccountList.getUserData(username, password);
                studySpotList.setUserIdeal(userData);
                ArrayList<StudySpot> bestSpotList = studySpotList.getBestStudySpots();

                first.setText(bestSpotList.get(0).getName());
                second.setText(bestSpotList.get(1).getName());
                third.setText(bestSpotList.get(2).getName());

                primaryStage.setScene(sceneForResultsMenu);
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

                //Question 1
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
                    outputForInvalidQ1Value.setText("Input Must Be a Number!");
                    valueIsValid1 = false;
                    if(question1Text.getText().isEmpty()){
                        outputForInvalidQ1Value.setText("Please Enter a Valid Number.");
                        valueIsValid1 = false;
                    }

                }

                //Question 2
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
                    outputForInvalidQ2Value.setText("Input Must Be a Number!");
                    valueIsValid2 = false;
                    if(question2Text.getText().isEmpty()){
                        outputForInvalidQ2Value.setText("Please Enter a Valid Number.");
                        valueIsValid2 = false;
                    }

                }

                //Question 3
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
                    outputForInvalidQ3Value.setText("Input Must Be a Number!");
                    valueIsValid3 = false;
                    if(question3Text.getText().isEmpty()){
                        outputForInvalidQ3Value.setText("Please Enter a Valid Number.");
                        valueIsValid3 = false;
                    }

                }

                //Question 4
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
                    outputForInvalidQ4Value.setText("Input Must Be a Number!");
                    valueIsValid4 = false;
                    if(question4Text.getText().isEmpty()){
                        outputForInvalidQ4Value.setText("Please Enter a Valid Number.");
                        valueIsValid4 = false;
                    }

                }

                //Question 5
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
                    outputForInvalidQ5Value.setText("Input Must Be a Number!");
                    valueIsValid5 = false;
                    if(question5Text.getText().isEmpty()){
                        outputForInvalidQ5Value.setText("Please Enter a Valid Number.");
                        valueIsValid5 = false;
                    }

                }

                if (valueIsValid1 && valueIsValid2 && valueIsValid3 && valueIsValid4 && valueIsValid5 == true) {

                    userData.setNoiseLevel(question1Value);
                    userData.setBathroomsNearby(question2Value);
                    userData.setFoodNearby(question3Value);
                    userData.setSeatingSpace(question4Value);
                    userData.setOutlets(question5Value);

                    userAccountList.setUserData(username, password, userData);
                    studySpotList.setUserIdeal(userData);
                    ArrayList<StudySpot> bestSpotList = studySpotList.getBestStudySpots();

                    first.setText(bestSpotList.get(0).getName());
                    second.setText(bestSpotList.get(1).getName());
                    third.setText(bestSpotList.get(2).getName());

                    primaryStage.setScene(sceneForResultsMenu);
                    primaryStage.setTitle("Survey Results - City Move");

                }
            }
        });

        //Event Handler for submitting survey using 'Enter' key
        surveyQuestionsMenu.setOnKeyPressed(event ->{
        
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
            
            if (event.getCode() == KeyCode.ENTER){
                //Question 1
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
                    outputForInvalidQ1Value.setText("Input Must Be a Number!");
                    valueIsValid1 = false;
                    if(question1Text.getText().isEmpty()){
                        outputForInvalidQ1Value.setText("Please Enter a Valid Number.");
                        valueIsValid1 = false;
                    }

                }

                //Question 2
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
                    outputForInvalidQ2Value.setText("Input Must Be a Number!");
                    valueIsValid2 = false;
                    if(question2Text.getText().isEmpty()){
                        outputForInvalidQ2Value.setText("Please Enter a Valid Number.");
                        valueIsValid2 = false;
                    }

                }

                //Question 3
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
                    outputForInvalidQ3Value.setText("Input Must Be a Number!");
                    valueIsValid3 = false;
                    if(question3Text.getText().isEmpty()){
                        outputForInvalidQ3Value.setText("Please Enter a Valid Number.");
                        valueIsValid3 = false;
                    }

                }

                //Question 4
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
                    outputForInvalidQ4Value.setText("Input Must Be a Number!");
                    valueIsValid4 = false;
                    if(question4Text.getText().isEmpty()){
                        outputForInvalidQ4Value.setText("Please Enter a Valid Number.");
                        valueIsValid4 = false;
                    }

                }

                //Question 5
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
                    outputForInvalidQ5Value.setText("Input Must Be a Number!");
                    valueIsValid5 = false;
                    if(question5Text.getText().isEmpty()){
                        outputForInvalidQ5Value.setText("Please Enter a Valid Number.");
                        valueIsValid5 = false;
                    }

                }

                if (valueIsValid1 && valueIsValid2 && valueIsValid3 && valueIsValid4 && valueIsValid5 == true) {

                    userData.setNoiseLevel(question1Value);
                    userData.setBathroomsNearby(question2Value);
                    userData.setFoodNearby(question3Value);
                    userData.setSeatingSpace(question4Value);
                    userData.setOutlets(question5Value);

                    userAccountList.setUserData(username, password, userData);
                    studySpotList.setUserIdeal(userData);
                    ArrayList<StudySpot> bestSpotList = studySpotList.getBestStudySpots();

                    first.setText(bestSpotList.get(0).getName());
                    second.setText(bestSpotList.get(1).getName());
                    third.setText(bestSpotList.get(2).getName());

                    primaryStage.setScene(sceneForResultsMenu);
                    primaryStage.setTitle("Survey Results - City Move");

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

        //Event Handler to go back to the Main Menu Screen from the Survey Screen with 'Enter' key
        resultsMenu.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
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

        
        primaryStage.show(); 
    }
}
