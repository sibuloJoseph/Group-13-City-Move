import java.time.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BackgroundImage;
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
import javafx.scene.control.ChoiceBox;
import logic.Schedule;

import com.sun.glass.events.KeyEvent;

/**
 * This class contains the GUI of the app 
 *
 * Last Modified: April 8th, 2019
 */

public class Gui extends Application {
    Label currentTime = new Label();
    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    private UserAccountList userAccountList = new UserAccountList();
    private String username, password;
    private IdealStudySpot userData = new IdealStudySpot();
    private StudySpotList studySpotList = new StudySpotList();
    private Schedule inputSchedule = new Schedule();
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
    private final ImageView mapImage = new ImageView();
    private Button myScheduleButton = new Button("My Schedule");
    private Image arrow = new Image(Gui.class.getResourceAsStream("YellowArrow.png"));


    //Variables of the Survey Interface
    private Button finishSurveyButton = new Button ("Submit Survey");
    private Button goBackToMainMenuButtonFromSurvey = new Button ("Main Menu");
    private Button signoutFromSurvey = new Button("Sign Out");

    //Array Lists for the Survey Interface
    private ArrayList<Label> surveyQuestions = new ArrayList<Label>();
    private ArrayList<Label> outputForInvalidQsValues = new ArrayList<Label>();
    private ArrayList<TextField> questionsText = new ArrayList<TextField>();
    private ArrayList<Boolean> questionsValueIsValid = new ArrayList<Boolean>();
    private ArrayList<Double> questionsValue = new ArrayList<Double>();
    private Button scheduleFromSurvey = new Button("My Schedule");

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
    private Button goBackToMainMenuButtonFromResults = new Button ("Main Menu");
    private Button signoutFromResults = new Button("Sign Out");
    private HBox surveyResultButtons = new HBox();
    private Button toMyScheduleFromSurveyResults = new Button("My Schedule");
    private ArrayList<StudySpot> bestSpotListBasedOnSchedule = inputSchedule.getBestSpotsWithSchedule(studySpotList);
    private ArrayList<StudySpot> bestSpotListBasedOnSurvey = studySpotList.getBestStudySpots();

    
    
    //Variables of the Schedule Interface 
    private Label scheduleDescription = new Label("Enter the locations of your classes at the appropiate time \nby choosing them from the Drop-down feature below");
    private final ImageView scheduleImageView = new ImageView();
    private Pane scheduleTime = new Pane();
    private Button doSurveyAtSchedule = new Button("Do Survey");
    private Button toMainMenuFromSchedule = new Button("Main Menu");
    private Button signoutFromSchedule = new Button("Sign Out");
    private HBox scheduleButtons = new HBox();
    private HBox moreScheduleButtons = new HBox();
    private ArrayList<ArrayList<ChoiceBox<StudySpot>>> dropDownStudySpotsArray = new ArrayList<ArrayList<ChoiceBox<StudySpot>>>();
    private Image scheduleImage = new Image(Gui.class.getResourceAsStream("schedule.png"));  
    private ChoiceBox<StudySpot> dropDownStudySpots;
    private Button bestStudyspotsAtSchedule = new Button("Best Study Spots");

    //Screen Interfaces
    private VBox loginInterface = new VBox();
    private VBox mainMenuGUI = new VBox();
    private VBox surveyQuestionsMenu = new VBox ();
    private VBox resultsMenu = new VBox();
    private VBox scheduleMenuGui = new VBox();
    
    //Scenes
    private Scene sceneForLogin;
    private Scene sceneForMainMenu;
    private Scene sceneForSurveyMenu;
    private Scene sceneForResultsMenu;
    private Scene sceneForScheduleMenu;

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
            /*else if(!(password.matches(".{4,}"))){ // Changed password to 4
                output.setText("Password Must be at Least \n7 Characters Long!");
            }*/
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
        for (int i = 0; i<5; i++) {
            questionsText.get(i).setText("");
        }
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

        first.setText(bestSpotListBasedOnSchedule.get(0).getName());
        second.setText(bestSpotListBasedOnSchedule.get(1).getName());
        third.setText(bestSpotListBasedOnSchedule.get(2).getName());
        
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
        Calendar cal = Calendar.getInstance();
        currentTime.setText(dateFormat.format(cal.getTime()));
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
        for(int f=0; f<5; f++){
            try {
                questionsValue.set(f, Double.parseDouble(questionsText.get(f).getText())); 
                if (questionsValue.get(f) >= 1.0 && questionsValue.get(f) <= 10.0) {
                    outputForInvalidQsValues.get(f).setText("");
                    questionsValueIsValid.set(f,true);
                }
                else {
                    outputForInvalidQsValues.get(f).setText("Invalid Value. Please enter a value from 1-10.");
                    questionsValueIsValid.set(f,false);
                    }
            } catch (NumberFormatException e){
                outputForInvalidQsValues.get(f).setText("Input Must Be a Number!");
                questionsValueIsValid.set(f,false);
                if (questionsText.get(f).getText().isEmpty()){
                    outputForInvalidQsValues.get(f).setText("Please Enter a Valid Number.");
                    questionsValueIsValid.set(f,false);
                }
            }
        }
    }

    /**
     * Method that takes the user's input from each question in the Survey Screen
     * which then takes the user to the Survey Results Screen where the 3 best studyspots is shown 
     * when the "Submit Survey" button is pressed.
     */
    public void outputSurveyResults(){
        if (questionsValueIsValid.get(0) && questionsValueIsValid.get(1) && questionsValueIsValid.get(2) && questionsValueIsValid.get(3) && questionsValueIsValid.get(4) == true ) {
            
            userData.setNoiseLevel(questionsValue.get(0));
            userData.setBathroomsNearby(questionsValue.get(1));
            userData.setFoodNearby(questionsValue.get(2));
            userData.setSeatingSpace(questionsValue.get(3));
            userData.setOutlets(questionsValue.get(4));

            userAccountList.setUserData(username, password, userData);
            studySpotList.setUserIdeal(userData);

            first.setText(bestSpotListBasedOnSurvey.get(0).getName());
            second.setText(bestSpotListBasedOnSurvey.get(1).getName());
            third.setText(bestSpotListBasedOnSurvey.get(2).getName());
            primaryStage.hide();
            primaryStage.setScene(sceneForResultsMenu);
            primaryStage.setTitle("Survey Results - City Move");
            primaryStage.show();
        }
    }

    /**
     * Method that takes the user into the My Schedule Menu
     */
    public void getToScheduleMenu () {
        // Drop downs all need to be empty
        primaryStage.hide();
        primaryStage.setScene(sceneForScheduleMenu);
        primaryStage.setTitle("Create Schedule - City Move");
        primaryStage.show();
    }

    /**
     * Method that creates the schedule of the user
     */
    public void generateSchedule(){
        inputSchedule = userAccountList.getUserSchedule(username, password);
        StudySpot currentSpot;
        for(int alex = 0; alex<5; alex++){
            for(int i = 0; i<13; i++){
                dropDownStudySpotsArray.get(alex).get(i).getItems().clear();
                dropDownStudySpotsArray.get(alex).get(i).getItems().add(null);
                for(int j = 0; j<studySpotList.getStudySpotList().size(); j++){
                    dropDownStudySpotsArray.get(alex).get(i).getItems().addAll(studySpotList.getStudySpotList().get(j));
                }
            }
            for(int i = 0; i<13; i++){
                currentSpot = inputSchedule.getClass(alex+1, i+8);
                dropDownStudySpotsArray.get(alex).get(i).getItems().add(currentSpot);
                dropDownStudySpotsArray.get(alex).get(i).setValue(currentSpot);
                //System.out.println(currentSpot);
            }
        } 
    }

    /**
     * Method that saves the created schedule of the user
     */
    public void saveSchedule(){
        for(int m = 0; m<5; m++){
            for(int k = 0; k<13; k++){
                inputSchedule.setClass(m+1, k+8, dropDownStudySpotsArray.get(m).get(k).getValue());
            }
            userAccountList.setUserSchedule(username, password, inputSchedule);
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
        mainMenuButtons.getChildren().addAll(myScheduleButton,surveyButton, pastButton, signoutFromMainMenu);
        //Current Time
        Calendar cal = Calendar.getInstance();
        currentTime.setText(dateFormat.format(cal.getTime()));
        currentTime.setFont(Font.font("Verdana", 30));
        currentTime.setTextFill(Color.ANTIQUEWHITE);

        mainMenuGUI.getChildren().addAll(mainMenuButtons, studySpotIndicator, currentTime, logoImageInMainMenu );
        sceneForMainMenu = new Scene(mainMenuGUI);


        //Survey Interface
        HBox submitOrGoBack = new HBox ();
        submitOrGoBack.getChildren().addAll(finishSurveyButton, scheduleFromSurvey ,goBackToMainMenuButtonFromSurvey, signoutFromSurvey);

        for (int i = 0; i < 5; i++) {
            surveyQuestions.add(new Label());
            surveyQuestions.get(i).setFont(Font.font("Verdana", 15));
            outputForInvalidQsValues.add(new Label());

            questionsText.add(new TextField());
            questionsText.get(i).setMaxWidth(50);

            questionsValueIsValid.add(false);
            questionsValue.add(1.0);
        }

        surveyQuestions.get(0).setText("On a scale of 1-10, what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)");
        surveyQuestions.get(1).setText("On a scale of 1-10, how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"); 
        surveyQuestions.get(2).setText("On a scale of 1-10, how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)");
        surveyQuestions.get(3).setText("On a scale of 1-10, how much seating space is ideal for your study spot? (1: limited, 10: plentiful)");
        surveyQuestions.get(4).setText("On a scale of 1-10, how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)");

        surveyQuestionsMenu.setSpacing(15);
        surveyQuestionsMenu.getChildren().addAll(surveyQuestions.get(0),questionsText.get(0),outputForInvalidQsValues.get(0),surveyQuestions.get(1),questionsText.get(1),outputForInvalidQsValues.get(1),surveyQuestions.get(2),questionsText.get(2),outputForInvalidQsValues.get(2),surveyQuestions.get(3),questionsText.get(3),outputForInvalidQsValues.get(3),surveyQuestions.get(4),questionsText.get(4),outputForInvalidQsValues.get(4), submitOrGoBack);
        surveyQuestionsMenu.setStyle("-fx-background-color: #ffe699;");
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

        surveyResultButtons.getChildren().addAll(toMyScheduleFromSurveyResults, goBackToMainMenuButtonFromResults, signoutFromResults);
        Label surveyResultDescription = new Label("Based on your Schedule and/or the Survey you took, the Best Study Spots for you are:");
        surveyResultDescription.setTextFill(Color.PALEGOLDENROD);
        surveyResultDescription.setFont(Font.font("Verdana", 25));
        resultsMenu.setSpacing(50);
        resultsMenu.getChildren().addAll(surveyResultDescription, displayForFirstSpot,displayForSecondSpot,displayForThirdSpot, surveyResultButtons);
        resultsMenu.setStyle("-fx-background-color: #980E0E;");
        sceneForResultsMenu = new Scene (resultsMenu);

        //Schedule Interface
        scheduleImageView.setImage(scheduleImage);
        scheduleTime.getChildren().add(scheduleImageView);
        for(int sohaib = 0; sohaib<5; sohaib++){
            dropDownStudySpotsArray.add(new ArrayList<ChoiceBox<StudySpot>>());
            for(int i = 0; i<13; i++){
                dropDownStudySpots = new ChoiceBox<>();
                dropDownStudySpots.getItems().add(null);
                for(int j = 0; j<studySpotList.getStudySpotList().size(); j++){
                    dropDownStudySpots.getItems().addAll(studySpotList.getStudySpotList().get(j));
                }
                dropDownStudySpots.setMaxWidth(230);
                dropDownStudySpots.setMinWidth(230);
                dropDownStudySpots.setLayoutX(143+(232*sohaib));
                dropDownStudySpots.setLayoutY(60+(30*i));
                dropDownStudySpotsArray.get(sohaib).add(dropDownStudySpots);
            }
        }
        for(int bajwa = 0; bajwa<5; bajwa++){
            for(int i = 0; i<13; i++){
                scheduleTime.getChildren().add(dropDownStudySpotsArray.get(bajwa).get(i));
            }
        }
        scheduleButtons.getChildren().addAll(bestStudyspotsAtSchedule, doSurveyAtSchedule, toMainMenuFromSchedule, signoutFromSchedule);
        scheduleMenuGui.setStyle("-fx-background-color: #ffe699;");
        scheduleMenuGui.setSpacing(15);
        scheduleMenuGui.getChildren().addAll(scheduleTime, scheduleButtons, moreScheduleButtons);
        sceneForScheduleMenu = new Scene (scheduleMenuGui);

        //Temporary** To get the x and y values of the grid.
        scheduleImageView.setOnMouseClicked  (s -> {
            System.out.println("["+s.getX()+", "+s.getY()+"]");
        }); 

        // Event Handler to go to the schedule menu from main menu
        myScheduleButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                generateSchedule();
                getToScheduleMenu();                
            }
        });

        //Event Handler that will take the user to the list of best study spots from the Schedule Result Screen
        bestStudyspotsAtSchedule.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                saveSchedule();
                generateSchedule();
                checkPreviousStudySpots();
            }
        });

        // Event Handler to Login
        enterToAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle (ActionEvent event) {
                loginCondition();
            }
        });

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

        //Event Handler to go back to the Main Menu screen from the Survey screen
        goBackToMainMenuButtonFromSurvey.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                toMainMenu(); 
            }
        });

        //Event Handler to go to the schedule window from the survey results window
        toMyScheduleFromSurveyResults.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                saveSchedule();
                generateSchedule();
                getToScheduleMenu();
            
            }
        });
        
        //Event Handler to go back to the Main Menu Screen from the Results Menu screen.
        goBackToMainMenuButtonFromResults.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                toMainMenu();
            }
        });

        //Event Handler to access Schedule Menu from Survey
        scheduleFromSurvey.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                generateSchedule();
                saveSchedule();
                getToScheduleMenu();
                
            }
        });

        //Event Handler to go back to the Main Menu Screen from the Schedule Screen.
        toMainMenuFromSchedule.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                generateSchedule();
                saveSchedule();
                toMainMenu();
            }
        });

        //Event Handler to go to the Survey Menu Screen from Schedule Screen.
        doSurveyAtSchedule.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                generateSchedule();                
                saveSchedule();
                getToSurveyMenu();
            }
        });

        //Event Handler to go signout from the application from Schedule Screen.
        signoutFromSchedule.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                generateSchedule();                
                saveSchedule();
                signOut();
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

         // Event Handler to Login with 'Enter' Key
        /*loginInterface.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                loginCondition();
            }
        });*/
        
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

        //Event Handler to go back to the Main Menu Screen from the Survey Results Screen with 'Enter' key or to sign out with the "S" key.
        /*resultsMenu.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                toMainMenu();
            } 
            else if(event.getCode() == KeyCode.BACK_SPACE){
                signOut();
            }
        });*/
    }
}
