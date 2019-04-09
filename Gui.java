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
    private Button scheduleFromSurvey = new Button("My Schedule");

    
    //Variables of the Schedule Interface 
    private Label scheduleDescription = new Label("Enter the locations of your classes at the appropiate time \nby choosing them from the Drop-down feature below");
    private final ImageView scheduleImageView = new ImageView();
    private Pane scheduleTime = new Pane();
    private Button generateSchedule = new Button("Generate Schedule");
    private Button doSurveyAtSchedule = new Button("Do Survey");
    private Button toMainMenuFromSchedule = new Button("Main Menu");
    private Button signoutFromSchedule = new Button("Sign Out");
    private HBox scheduleButtons = new HBox();
    private HBox moreScheduleButtons = new HBox();
    private ArrayList<ChoiceBox<StudySpot>> dropDownStudySpotsArray = new ArrayList<ChoiceBox<StudySpot>>();
    private Image scheduleImage = new Image(Gui.class.getResourceAsStream("schedule1.1.png"));  
    private ChoiceBox<StudySpot> dropDownStudySpots;
    private Button returnToMySchedule = new Button("My Schedule");

    //Variable of the Schedule Result Interface 
    private final ImageView scheduleResultImageView = new ImageView();
    private Image scheduleResultImage = new Image(Gui.class.getResourceAsStream("schedule1.2.png"));  
    private Pane scheduleTimeResult = new Pane();
    private HBox scheduleResultButtons = new HBox();
    private Button createNewSchedule = new Button("Create New Schedule");
    private Button doSurveyAtScheduleResult = new Button("Do Survey");
    private Button toMainMenuFromScheduleResult = new Button("Main Menu");
    private Button signoutFromScheduleResult = new Button("Sign Out");
    private ArrayList<Label> studySpotsEachHourArray = new ArrayList<Label>();
    private Label studySpotsEachHour;
    private Button bestStudyspotsAtScheduleResultMenu = new Button("Best Study Spots");
    private HBox moreScheduleResultButtons = new HBox();



    
    //Screen Interfaces
    private VBox loginInterface = new VBox();
    private VBox mainMenuGUI = new VBox();
    private VBox surveyQuestionsMenu = new VBox ();
    private VBox resultsMenu = new VBox();
    private VBox scheduleMenuGui = new VBox();
    private VBox scheduleResultsGui = new VBox();
     
    //Scenes
    private Scene sceneForLogin;
    private Scene sceneForMainMenu;
    private Scene sceneForSurveyMenu;
    private Scene sceneForResultsMenu;
    private Scene sceneForScheduleMenu;
    private Scene sceneForScheduleResult;

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
        ArrayList<StudySpot> bestSpotList = inputSchedule.getBestSpotsWithSchedule(studySpotList);        

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
            questionsValue.set(0,Double.parseDouble(questionsText.get(0).getText())); 
            if (questionsValue.get(0) >= 1.0 && questionsValue.get(0) <= 10.0) {
                outputForInvalidQsValues.get(0).setText("");
                questionsValueIsValid.set(0,true);
            }
            else {
                outputForInvalidQsValues.get(0).setText("Invalid Value. Please enter a value from 1-10.");
                questionsValueIsValid.set(0,false);
                }
        } catch (NumberFormatException e){
            outputForInvalidQsValues.get(0).setText("Input Must Be a Number!");
            questionsValueIsValid.set(0,false);
            if (questionsText.get(0).getText().isEmpty()){
                outputForInvalidQsValues.get(0).setText("Please Enter a Valid Number.");
                questionsValueIsValid.set(0,false);
            }
        }

        //Question 2
        try {
            questionsValue.set(1,Double.parseDouble(questionsText.get(1).getText()));
            if (questionsValue.get(1) >= 1.0 && questionsValue.get(1) <= 10.0) {
                outputForInvalidQsValues.get(1).setText("");
                questionsValueIsValid.set(1,true);
            }
            else {
                outputForInvalidQsValues.get(1).setText("Invalid Value. Please enter a value from 1-10.");
                questionsValueIsValid.set(1,false);
                }
        } catch (NumberFormatException e) {
            outputForInvalidQsValues.get(1).setText("Input Must Be a Number!");
            questionsValueIsValid.set(1,false);
            if (questionsText.get(1).getText().isEmpty()){
                outputForInvalidQsValues.get(1).setText("Please Enter a Valid Number.");
                questionsValueIsValid.set(1,false);
            }
        }

        //Question 3
        try {
            questionsValue.set(2,Double.parseDouble(questionsText.get(2).getText()));
            if (questionsValue.get(2) >= 1.0 && questionsValue.get(2) <= 10.0) {
                outputForInvalidQsValues.get(2).setText("");
                questionsValueIsValid.set(2,true);
            }
            else {
                outputForInvalidQsValues.get(2).setText("Invalid Value. Please enter a value from 1-10.");
                questionsValueIsValid.set(2,false);
                }
        } catch (NumberFormatException e) {
            outputForInvalidQsValues.get(2).setText("Input Must Be a Number!");
            questionsValueIsValid.set(2,false);
            if (questionsText.get(2).getText().isEmpty()){
                outputForInvalidQsValues.get(2).setText("Please Enter a Valid Number.");
                questionsValueIsValid.set(2,false);
            }
        }

        //Question 4
        try {
            questionsValue.set(3,Double.parseDouble(questionsText.get(3).getText()));
            if (questionsValue.get(3) >= 1.0 && questionsValue.get(3) <= 10.0) {
                outputForInvalidQsValues.get(3).setText("");
                questionsValueIsValid.set(3,true);
            }
            else {
                outputForInvalidQsValues.get(3).setText("Invalid Value. Please enter a value from 1-10.");
                questionsValueIsValid.set(3,false);
                }
        } catch (NumberFormatException e) {
            outputForInvalidQsValues.get(3).setText("Input Must Be a Number!");
            questionsValueIsValid.set(3,false);
            if (questionsText.get(3).getText().isEmpty()){
                outputForInvalidQsValues.get(3).setText("Please Enter a Valid Number.");
                questionsValueIsValid.set(3,false);
            }
        }

        //Question 5
        try {
            questionsValue.set(4,Double.parseDouble(questionsText.get(4).getText()));
            if (questionsValue.get(4) >= 1.0 && questionsValue.get(4) <= 10.0) {
                outputForInvalidQsValues.get(4).setText("");
                questionsValueIsValid.set(4,true);
            }
            else {
                outputForInvalidQsValues.get(4).setText("Invalid Value. Please enter a value from 1-10.");
                questionsValueIsValid.set(4,false);
                }
        } catch (NumberFormatException e) {
            outputForInvalidQsValues.get(4).setText("Input Must Be a Number!");
            questionsValueIsValid.set(4,false);
            if (questionsText.get(4).getText().isEmpty()){
                outputForInvalidQsValues.get(4).setText("Please Enter a Valid Number.");
                questionsValueIsValid.set(4,false);
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
     * Method that takes the user to the generated schedule menu
     */
    public void getGeneratedSchedule(){
        primaryStage.hide();
        primaryStage.setScene(sceneForScheduleResult);
        primaryStage.setTitle("My Schedule - City Move");
        primaryStage.show();
    }

    /**
     * Method that creates the schedule of the user
     */
    public void generateSchedule(){
        inputSchedule = userAccountList.getUserSchedule(username, password);
        for(int i = 0; i<13; i++){
            dropDownStudySpotsArray.get(i).setValue(null);
            System.out.println(inputSchedule.getClass(i+8));
            studySpotsEachHourArray.get(i).setText(inputSchedule.getClass(i+8)+"");
            if(inputSchedule.getClass(i+8) == null){
                studySpotsEachHourArray.get(i).setText("");
            }
        }
        getGeneratedSchedule();
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

        mainMenuGUI.getChildren().addAll(mainMenuButtons, studySpotIndicator, logoImageInMainMenu);
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
        Label surveyResultDescription = new Label("Based on your Schedule and/or the Survey you took, the Best Study Spots for you are:");
        surveyResultDescription.setTextFill(Color.PALEGOLDENROD);
        surveyResultDescription.setFont(Font.font("Verdana", 25));
        resultsMenu.setSpacing(50);
        resultsMenu.getChildren().addAll(surveyResultDescription, displayForFirstSpot,displayForSecondSpot,displayForThirdSpot, surveyResultButtons);
        resultsMenu.setStyle("-fx-background-color: #980E0E;");
        sceneForResultsMenu = new Scene (resultsMenu);

        //Generate Schedule Interface
        scheduleImageView.setImage(scheduleImage);
        for(int i = 0; i<13; i++){
            dropDownStudySpots = new ChoiceBox<>();
            dropDownStudySpots.getItems().add(null);
            for(int j = 0; j<studySpotList.getStudySpotList().size(); j++){
                dropDownStudySpots.getItems().addAll(studySpotList.getStudySpotList().get(j));
            }
            dropDownStudySpots.setMaxWidth(303);
            dropDownStudySpots.setLayoutX(143);
            dropDownStudySpots.setLayoutY(90+(30*i));
            dropDownStudySpotsArray.add(dropDownStudySpots);
        }
        scheduleTime.getChildren().add(scheduleImageView);
        for(int i = 0; i<13; i++){
            scheduleTime.getChildren().add(dropDownStudySpotsArray.get(i));

        }
        scheduleButtons.getChildren().addAll(generateSchedule, returnToMySchedule, doSurveyAtSchedule);
        moreScheduleButtons.getChildren().addAll(toMainMenuFromSchedule, signoutFromSchedule);
        scheduleMenuGui.setStyle("-fx-background-color: #ffe699;");
        scheduleMenuGui.setSpacing(15);
        scheduleMenuGui.getChildren().addAll(scheduleTime, scheduleButtons, moreScheduleButtons);
        sceneForScheduleMenu = new Scene (scheduleMenuGui);

        //Schedule Result Interface 
        scheduleResultImageView.setImage(scheduleResultImage);
        
        for(int sohaib = 0; sohaib<13; sohaib++){
            studySpotsEachHour = new Label("");
            studySpotsEachHour.setFont(Font.font("Verdana", 20));
            studySpotsEachHour.setTextFill(Color.web("#C00000"));;
            studySpotsEachHour.setMaxWidth(303);
            studySpotsEachHour.setLayoutX(145);
            studySpotsEachHour.setLayoutY(90+(30*sohaib));
            studySpotsEachHourArray.add(studySpotsEachHour);
        }
        scheduleTimeResult.getChildren().addAll(scheduleResultImageView);
        for(int bajwa = 0; bajwa<13; bajwa++){
            scheduleTimeResult.getChildren().add(studySpotsEachHourArray.get(bajwa));
        }
        
        scheduleResultButtons.getChildren().addAll(createNewSchedule, bestStudyspotsAtScheduleResultMenu, doSurveyAtScheduleResult);
        moreScheduleResultButtons.getChildren().addAll(toMainMenuFromScheduleResult, signoutFromScheduleResult);
        scheduleResultsGui.setStyle("-fx-background-color: #ffe699;");
        scheduleResultsGui.setSpacing(15);
        scheduleResultsGui.getChildren().addAll(scheduleTimeResult, scheduleResultButtons, moreScheduleResultButtons);
        sceneForScheduleResult = new Scene (scheduleResultsGui);
        

        //Temporary** To get the x and y values of the grid.
        scheduleImageView.setOnMouseClicked  (s -> {
            System.out.println("["+s.getX()+", "+s.getY()+"]");
        }); 

        //Event handler to create a new schedule at the Schedule Result Screen
        createNewSchedule.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                getToScheduleMenu();
            }
        });

        //Event handler to generate a schedule at the Schedule Screen
        generateSchedule.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                for(int k = 0; k<13; k++){
                    inputSchedule.setClass(k+8, dropDownStudySpotsArray.get(k).getValue());
                }
                userAccountList.setUserSchedule(username, password, inputSchedule);
                generateSchedule();
            }
        });

        // Event Handler to go to the schedule menu from main menu
        myScheduleButton.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                generateSchedule();
            }
        });

        //Event Handler to go to the current generated Schedule
        returnToMySchedule.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                getGeneratedSchedule();
            }
        });

        //Event Handler that will take the user to the list of best study spots from the Schedule Result Screen
        bestStudyspotsAtScheduleResultMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                checkPreviousStudySpots();
            }
        });


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

        //Event Handler to access Schedule Menu from Survey
        scheduleFromSurvey.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                getGeneratedSchedule();
            }
        });

        //Event Handler to go back to the Main Menu Screen from the Schedule Screen.
        toMainMenuFromSchedule.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                toMainMenu();
            }
        });

        //Event Handler to go to the Survey Menu Screen from Schedule Screen.
        doSurveyAtSchedule.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                getToSurveyMenu();
            }
        });

        //Event Handler to go signout from the application from Schedule Screen.
        signoutFromSchedule.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                signOut();
            }
        });

        //Event Handler to go back to the Main Menu Screen from the Schedule Result Screen.
        toMainMenuFromScheduleResult.setOnAction (new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                toMainMenu();
            }
        });

        //Event Handler to go to the Survey Menu Screen from Schedule Result Screen.
        doSurveyAtScheduleResult.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
                getToSurveyMenu();
            }
        });

        //Event Handler to go signout from the application from Schedule Result Screen.
        signoutFromScheduleResult.setOnAction(new EventHandler<ActionEvent> () {
            public void handle (ActionEvent event) {
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
    }
}
