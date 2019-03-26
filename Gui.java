import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import logic.StudySpot;
import logic.IdealStudySpot;
import logic.StudySpotList;
import account.UserAccountList;

import com.sun.glass.events.KeyEvent;

/**
 * This class contains the GUI of the app 
 *
 * Last Modified: March 25, 2019
 */

public class Gui extends Application {
    private UserAccountList userAccountList = new UserAccountList();
    private String username, password;
    private IdealStudySpot userData = new IdealStudySpot();
    private StudySpotList studySpotList = new StudySpotList();
    private Stage primaryStage;
    

    //Variables of the Login Interface
    private Label output = new Label ("");
    private Label logInUsername = new Label ("Username:");
    private Label logInPassword = new Label ("Password:");
    private TextField txtUsername = new TextField();
    private PasswordField txtPassword = new PasswordField();
    private Button enterToAccount = new Button("Login");
    private Button signupToAccount = new Button("Signup");
    private final ImageView selectedImage = new ImageView();   
    private Image image1 = new Image(Gui.class.getResourceAsStream("Logo.jpg"));

    //Varibles of the Main Menu Interface
    private Button surveyButton = new Button("Do Survey");
    private Button pastButton = new Button("See Previous Study Spots");
    private Button signoutFromMainMenu = new Button("Sign Out");
    private Label spotClickedOn = new Label ("\n\n\n\n\n\n\n");
    private final ImageView logoImageInMainMenu = new ImageView();  
    private StudySpot clickedStudySpot;
    private Label indicatorArrow = new Label();
    private Pane studySpotIndicator = new Pane();
    private final ImageView invisibleMap = new ImageView(); 
    private final ImageView mapImage = new ImageView();  



    //Variable of the Survey Interface
    private Label question1 = new Label ("On a scale of 1-10, what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)");
    private Label question2 = new Label ("On a scale of 1-10, how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"); 
    private Label question3 = new Label ("On a scale of 1-10, how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)");
    private Label question4 = new Label ("On a scale of 1-10, how much seating space is ideal for your study spot? (1: limited, 10: plentiful)");
    private Label question5 = new Label ("On a scale of 1-10, how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)");
    private Label outputForInvalidQ1Value = new Label ("");
    private Label outputForInvalidQ2Value = new Label ("");
    private Label outputForInvalidQ3Value = new Label ("");
    private Label outputForInvalidQ4Value = new Label ("");
    private Label outputForInvalidQ5Value = new Label ("");
    private TextField question1Text = new TextField ();
    private TextField question2Text = new TextField ();
    private TextField question3Text = new TextField ();
    private TextField question4Text = new TextField ();
    private TextField question5Text = new TextField ();
    private Button finishSurveyButton = new Button ("Submit Survey");
    private Button goBackToMainMenuButtonFromSurvey = new Button ("Go back to Main Menu");
    private Boolean valueIsValid1 = false;
    private Boolean valueIsValid2 = false;
    private Boolean valueIsValid3 = false;
    private Boolean valueIsValid4 = false;
    private Boolean valueIsValid5 = false;
    private Double question1Value = new Double(1.0);
    private Double question2Value = new Double(1.0);
    private Double question3Value = new Double(1.0);
    private Double question4Value = new Double(1.0);
    private Double question5Value = new Double(1.0);
    private Button signoutFromSurvey = new Button("Sign Out");

    //Variables of the Results Menu Interface
    private HBox displayForFirstSpot = new HBox(5);
    private HBox displayForSecondSpot = new HBox(5);
    private HBox displayForThirdSpot = new HBox(5);
    private Label firstStudySpot = new Label ("Your first Study Spot is: ");
    private Label secondStudySpot = new Label ("Your second Study Spot is: ");
    private Label thirdStudySpot = new Label ("Your third Study Spot is: ");
    private Label first = new Label ("");
    private Label second = new Label ("");
    private Label third = new Label ("");
    private Button goBackToMainMenuButtonFromResults = new Button ("Go back to Main Menu");
    private Button signoutFromResults = new Button("Sign Out");


    //Screen Interfaces
    private VBox loginInterface = new VBox();
    private VBox mainMenuGUI = new VBox();
    private VBox surveyQuestionsMenu = new VBox ();
    private VBox resultsMenu = new VBox();

    //Scenes
    private Scene sceneForLogin;
    private Scene sceneForMainMenu;
    private Scene sceneForSurveyMenu;
    private Scene sceneForResultsMenu;

    /**
     * Method that checks the conditions when logging in.
     * in the Login Screen
     */
    public void loginCondition(){
        username = txtUsername.getText();
        password = txtPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
                output.setText("Please Enter Username and/or Password.");
            }
        else{
            if (userAccountList.credentialsValid(username, password)) {
                toMainMenu();
            }
            else if (!userAccountList.hasAccount(username)){
                output.setText("Incorrect Username or \nAccount Does Not Exist!");
            }
            else {
                output.setText("Incorrect Password.");
            }
        }
    }

    /**
     * Method that checks the conditions when signing up.
     * in the login Screen
     */
    public void signUpCondition(){
        username = txtUsername.getText();
        password = txtPassword.getText();
       
        if (userAccountList.hasAccount(username)) {
            output.setText("Account Already Exists!");
        }
        else {
            if (username.isEmpty() || password.isEmpty()) {
                output.setText("Please Enter Username and/or Password.");
            }
            else if(!(password.matches(".{7,}"))){
                output.setText("Password Must be at Least 7 Characters Long!");
            }
            else {
                userAccountList.addAccount(username, password);
                output.setText("Account Created \nPlease Log In.");
            }
        }
    }

    /**
     * Method that would take the user to the Survey screen when
     * the "Do Survey" button is clicked or when the "Enter" key is pressed.
     * in the Main Menu screen.
     */
    public void getToSurveyMenu(){
        question1Text.setText("");
        question2Text.setText("");
        question3Text.setText("");
        question4Text.setText("");
        question5Text.setText("");
        primaryStage.hide();
        primaryStage.setScene(sceneForSurveyMenu);
        primaryStage.setTitle("Survey - City Move");
        primaryStage.show();
    }

    /**
     * Method that allows the user to see their previous study spots results
     * when the "See Previous Study Spots" button is pressed
     * in the Main Menu Screen. 
     */
    public void checkPreviousStudySpots(){
        userData = userAccountList.getUserData(username, password);
        studySpotList.setUserIdeal(userData);
        ArrayList<StudySpot> bestSpotList = studySpotList.getBestStudySpots();

        first.setText(bestSpotList.get(0).getName());
        second.setText(bestSpotList.get(1).getName());
        third.setText(bestSpotList.get(2).getName());
        primaryStage.hide();
        primaryStage.setScene(sceneForResultsMenu);
        primaryStage.setTitle("Survey Results - City Move");
        primaryStage.show();

    }

    /**
     * Method that Signs out the user and takes them back to the Login Screen
     * when the "Signout" button is pressed.
     */
    public void signOut(){
        primaryStage.hide();
        txtPassword.setText("");
        output.setText("");
        primaryStage.setScene(sceneForLogin);
        primaryStage.setTitle("Login - City Move");
        primaryStage.show();
    }

    /**
     * Method that would take the User back to the Main Menu Screen.
     */
    public void toMainMenu(){
        primaryStage.hide();
        primaryStage.setScene(sceneForMainMenu);
        primaryStage.setTitle("Main Menu - City Move");
        primaryStage.show();
        if(mainMenuGUI.getChildren().contains(spotClickedOn)){
            mainMenuGUI.getChildren().remove(spotClickedOn);
            mainMenuGUI.getChildren().add(logoImageInMainMenu);
            if(studySpotIndicator.getChildren().contains(indicatorArrow)){
                studySpotIndicator.getChildren().remove(indicatorArrow);
            }
        }
    }

    /**
     * Method that checks the conditions of each question when doing the survey
     * in the Survey Menu
     */
    public void doSurvey(){
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
            outputForInvalidQ5Value.setText("Input Must Be a Number!");
            valueIsValid5 = false;
            if(question5Text.getText().isEmpty()){
                outputForInvalidQ5Value.setText("Please Enter a Valid Number.");
                valueIsValid5 = false;
            }
        }
    }

    /**
     * Method that takes the user's input from each question in the Survey Screen
     * which then takes the user to the Survey Results Screen where the 3 best studyspots is shown 
     * when the "Submit Survey" button is pressed.
     */
    public void outputSurveyResults(){
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
            primaryStage.hide();
            primaryStage.setScene(sceneForResultsMenu);
            primaryStage.setTitle("Survey Results - City Move");
            primaryStage.show();
        }
    }
    
    /**
     * Main method for the Gui
     * @param primetimeStage: our Primary Stage where we show our Gui interface.
     */
    public void start(Stage primetimeStage) throws Exception {
        primaryStage = primetimeStage;

        //User Log in interface.
    	loginInterface.setAlignment(Pos.CENTER);
        
        //UofC Logo 
		selectedImage.setImage(image1);

    	txtUsername.setMaxWidth(200);
        txtPassword.setMaxWidth(200);
        
        HBox loginSignup = new HBox();
        loginSignup.setAlignment(Pos.CENTER);
        loginSignup.getChildren().addAll(enterToAccount, signupToAccount);

    	loginInterface.setSpacing(20);	
      	logInUsername.setFont(Font.font("Verdana", 20));
      	logInPassword.setFont(Font.font("Verdana", 20));
    	logInUsername.setTextFill(Color.WHITE);
        logInPassword.setTextFill(Color.WHITE);
        output.setTextFill(Color.WHITE);
    
        loginInterface.getChildren().addAll(selectedImage, logInUsername, txtUsername, logInPassword, txtPassword, loginSignup, output);
        loginInterface.setStyle("-fx-background-color: #980E0E;");

        sceneForLogin = new Scene(loginInterface, 300,500);
        primaryStage.setTitle("Login-City Move");
        primaryStage.setScene(sceneForLogin);
        primaryStage.setResizable(false);
        primaryStage.show();

         

        //Main Menu Interface
        //UofC Map
        Image uofCMap = new Image(Gui.class.getResourceAsStream("UofCMap.png"));
        mapImage.setImage(uofCMap);

        studySpotIndicator.getChildren().add(mapImage);
        logoImageInMainMenu.setImage(image1); 

        mainMenuGUI.setAlignment(Pos.CENTER);
        mainMenuGUI.setSpacing(15);
        mainMenuGUI.setStyle("-fx-background-color: #980E0E;");

        spotClickedOn.setFont(Font.font("Verdana", 20));
        spotClickedOn.setTextFill(Color.WHITE);

        //For the buttons in the Main Menu
        HBox mainMenuButtons = new HBox();
        mainMenuButtons.setSpacing(100);
        mainMenuButtons.setAlignment(Pos.CENTER);
        mainMenuButtons.getChildren().addAll(surveyButton, pastButton, signoutFromMainMenu);

        mainMenuGUI.getChildren().addAll(mainMenuButtons, studySpotIndicator, logoImageInMainMenu);
        sceneForMainMenu = new Scene(mainMenuGUI);


        //Survey Interface
        HBox submitOrGoBack = new HBox ();
        submitOrGoBack.getChildren().addAll(finishSurveyButton,goBackToMainMenuButtonFromSurvey, signoutFromSurvey);

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
        surveyQuestionsMenu.getChildren().addAll(question1,question1Text,outputForInvalidQ1Value,question2,question2Text,outputForInvalidQ2Value,question3,question3Text,outputForInvalidQ3Value,question4,question4Text,outputForInvalidQ4Value,question5,question5Text,outputForInvalidQ5Value, submitOrGoBack);
        surveyQuestionsMenu.setStyle("-fx-background-color: #F5F5DC;");
        sceneForSurveyMenu = new Scene (surveyQuestionsMenu);


        //Survey Results Interface
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

        HBox surveyResultButtons = new HBox();
        surveyResultButtons.getChildren().addAll(goBackToMainMenuButtonFromResults, signoutFromResults);

        resultsMenu.setSpacing(50);
        resultsMenu.getChildren().addAll(displayForFirstSpot,displayForSecondSpot,displayForThirdSpot, surveyResultButtons);
        resultsMenu.setStyle("-fx-background-color: #980E0E;");
        sceneForResultsMenu = new Scene (resultsMenu, 1200, 300);

        // Event Handler to Login
    	enterToAccount.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle (ActionEvent event) {
                loginCondition();
            }
        });

        // Event Handler to Login with 'Enter' Key
        /*loginInterface.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                loginCondition();
            }
        });*/

        //Event Handler to Signup
        signupToAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent event) {
                signUpCondition();
            }
        });
	  

        //Event Handler to Signout from the Main Menu and back to the Login screen.
        signoutFromMainMenu.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
		        signOut();   
            }
        });

        //Event Handler to Signout from the Survey Screen and back to the Login screen.
        signoutFromSurvey.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
		        signOut();   
            }
        });

        //Event Handler to Signout from the Survey Result Screen and back to the Login screen.
        signoutFromResults.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
		        signOut();   
            }
        });

        //Event Handler to get to the Survey Screen
        surveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                getToSurveyMenu();
            }
        });
        
        //Event Handler to get to the Survey Screen with 'Enter' Key, sign out with the "BackSpace" Key, and check previous survey results with the "P" Key
        /*mainMenuGUI.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                getToSurveyMenu();
            }
            else if(event.getCode() == KeyCode.BACK_SPACE){
                signOut();
            }
            else if(event.getCode() == KeyCode.P){
                checkPreviousStudySpots();
            }
        });*/
	  
	    //Event Handler to get to Previous Study Spots
        pastButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                checkPreviousStudySpots();
            }
        });

        
        //Event Handler to submit survey responses
        finishSurveyButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                doSurvey();
                outputSurveyResults();
                
            }
        });

        //Event Handler for submitting survey using 'Enter' key, to go back to the main menu with the "BackSpace" key, and to sign out with the "S" key
        /*surveyQuestionsMenu.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                doSurvey();
                outputSurveyResults();
            }
            else if(event.getCode() == KeyCode.BACK_SPACE){
                toMainMenu(); 
            }
            else if(event.getCode() == KeyCode.S){
                signOut();
            }
        });*/



        //Event Handler to go back to the Main Menu screen from the Survey screen
        goBackToMainMenuButtonFromSurvey.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                toMainMenu(); 
            }
        });

        //Event Handler to go back to the Main Menu Screen from the Survey Results Screen with 'Enter' key or to sign out with the "S" key.
        /*resultsMenu.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                toMainMenu();
            } 
            else if(event.getCode() == KeyCode.BACK_SPACE){
                signOut();
            }
        });*/

        
        //Event Handler to go back to the Main Menu Screen from the Results Menu screen.
        goBackToMainMenuButtonFromResults.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                toMainMenu();
            }
        });

        // Event Click Handler on Interactive Map in Main Menu
        mapImage.setOnMouseClicked  (e -> {
            //System.out.println("["+e.getX()+", "+e.getY()+"]");
            clickedStudySpot = studySpotList.getLocation(e.getX(), e.getY());

            if(clickedStudySpot != null){
                Image arrow = new Image(Gui.class.getResourceAsStream("YellowArrow.png"));
                indicatorArrow.setGraphic(new ImageView(arrow));
                indicatorArrow.setLayoutX(e.getX());
                indicatorArrow.setLayoutY(e.getY());

                mainMenuGUI.getChildren().remove(spotClickedOn);
                studySpotIndicator.getChildren().remove(indicatorArrow);

                spotClickedOn.setText(clickedStudySpot.getName()
                +" \n\nLevel of Noise(1-10): "+clickedStudySpot.getNoiseLevel()
                +" \nBathrooms Nearby(1-10): "+clickedStudySpot.getBathroomsNearby()
                +" \nFood Nearby(1-10): "+clickedStudySpot.getFoodNearby()
                +" \nSeating Space(1-10): "+clickedStudySpot.getSeatingSpace()
                +" \nOutlets Nearby(1-10): "+clickedStudySpot.getOutlets());

                mainMenuGUI.getChildren().remove(logoImageInMainMenu);
                mainMenuGUI.getChildren().add(spotClickedOn);
                studySpotIndicator.getChildren().add(indicatorArrow);
                
            }
        
            else if(clickedStudySpot == null && mainMenuGUI.getChildren().contains(spotClickedOn)){
                mainMenuGUI.getChildren().remove(spotClickedOn);
                mainMenuGUI.getChildren().add(logoImageInMainMenu);
                if(studySpotIndicator.getChildren().contains(indicatorArrow)){
                    studySpotIndicator.getChildren().remove(indicatorArrow);
                }
            }
         }); 
    }
}
