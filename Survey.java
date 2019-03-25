import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * This class gets the input of the user of their most preferred criteria for study spots at the University of Calgary
 * based on a survey of 5 questions.
 *
 * Last modified: March 25, 2019
 */

public class Survey {

    /**
     * Displays the best study spots based on the given list to the console
     * @param studySpotList: the StudySpotList object to base the results on
     */
    public void displayResults(StudySpotList studySpotList) {
        ArrayList<StudySpot> bestSpotList = studySpotList.getBestStudySpots();
        System.out.println("Here are the top three study spots recommended for you:");
        System.out.println(bestSpotList.get(0).getName());
        System.out.println(bestSpotList.get(1).getName());
        System.out.println(bestSpotList.get(2).getName());
    }

    /**
     * Asks the user questions where the user gets to rank their preferences on 5 criteria to determine their ideal study spot
     * If the user inputs a double outside of the 1-10 scale then the question reprompts
     * @param question: a string representing the question to be asked in the survey  
     * @return criteriaInput: a double representing the value of the user's preference of the given criteria in a given 1-10 scale 
     */
    public double askSurveyQuestions (String question) {
        boolean isValid = false;
        Scanner keyboard = new Scanner (System.in);
        double criteriaInput = 0.0;

        // Prompts the user for a value until a valid one is entered
        while (!isValid) {
            if (criteriaInput >= 1.0 && criteriaInput <= 10.0) {
                isValid = true;
            }
            else {
                System.out.println("On a scale of 1-10, " + question);
                try {
                    criteriaInput = keyboard.nextDouble();
                }
                catch(InputMismatchException e) {
                    keyboard.nextLine();
                    System.out.println("Please enter a number as indicated.");
                }
            }
        }

        return criteriaInput;
    }

    /**
     * Main method for text based program
     */
    public static void main (String [] args) {

        UserAccountList userAccountList = new UserAccountList();
        StudySpotList ssl = new StudySpotList();
        Survey aSurvey = new Survey();
        IdealStudySpot userData = new IdealStudySpot();
        String username = "", password = "";
        Scanner keyboard = new Scanner(System.in);
        String action = "";

        boolean validLogin = false;

        while(!validLogin) {

            // Asks the user for a username and password
            System.out.println("Please enter a username.");
            username = keyboard.next();
            System.out.println("Please enter a password.");
            password = keyboard.next();
            while(!(password.matches(".{7,}"))) {
                System.out.println("Password must be at least 7 characters long.");
                System.out.println("Please try again.");
                password = keyboard.next();
            }

            // Asks the user to indicate whether they want to sign up or log in
            System.out.println("Please enter S to sign up or L to log in.");
            action = keyboard.next();
            while (!action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("L")) {
                System.out.println("Please enter a valid command.");
                action = keyboard.next();
            }

            // Checks to see if an account can be created with the credentials and does so if possible
            if (action.equalsIgnoreCase("S")) {
                if (userAccountList.hasAccount(username)) {
                    System.out.println("An account with that username already exists.");
                    System.out.println("Please try again.");
                }
                else {
                    userAccountList.addAccount(username, password);
                    validLogin = true;
                }
            }
            // Checks to see if there is an account with the credentials and logs the user in if there is
            else if (action.equalsIgnoreCase("L")) {
                if (!userAccountList.credentialsValid(username, password)) {
                    if (!userAccountList.hasAccount(username)) {
                        System.out.println("There is no account with that username.");
                    }
                    else {
                        System.out.println("Invalid Password");
                    }
                    System.out.println("Please try again.");
                }
                else {
                    validLogin = true;
                }
            }
        }

        // Main menu loop for the program
        while(true) {
            System.out.println("Please enter S to take the study spot survey, P to see your previous results, or Q to quit.");
            action = keyboard.next();
            while(!action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("P") && !action.equalsIgnoreCase("Q")) {
                System.out.println("Please enter a valid command.");
                action = keyboard.next();
            }

            // Presents the user with the survey questions and gives them their results
            if(action.equalsIgnoreCase("S")) {

                userData.setNoiseLevel(aSurvey.askSurveyQuestions("what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)"));
                userData.setBathroomsNearby(aSurvey.askSurveyQuestions("how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
                userData.setFoodNearby(aSurvey.askSurveyQuestions("how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
                userData.setSeatingSpace(aSurvey.askSurveyQuestions("how much seating space is ideal for your study spot? (1: limited, 10: plentiful)"));
                userData.setOutlets(aSurvey.askSurveyQuestions("how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));

                userAccountList.setUserData(username, password, userData);
                ssl.setUserIdeal(userData);
                aSurvey.displayResults(ssl);

            }

            // Displays the most recent results
            else if(action.equalsIgnoreCase("P")) {

                userData = userAccountList.getUserData(username, password);
                ssl.setUserIdeal(userData);
                aSurvey.displayResults(ssl);

            }

            // Ends the program
            else if(action.equalsIgnoreCase("Q")) {
                break;
            }
        }

        System.out.println("Thank you for using our study spot recommendation app.");
    }
    
}
