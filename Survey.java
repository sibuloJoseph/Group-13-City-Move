import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import logic.StudySpot;
import logic.IdealStudySpot;
import logic.StudySpotList;
import account.UserAccountList;
import logic.Schedule;

/**
 * This class gets the input of the user of their most preferred criteria for study spots at the University of Calgary
 * based on a survey of 5 questions.
 *
 * Last modified: April 10th, 2019
 */

public class Survey {

    /**
     * Displays the best study spots based on the given list to the console
     * @param studySpotList: the StudySpotList object to base the results on
     */
    public void displayResults(StudySpotList studySpotList, Schedule aSchedule) {
        ArrayList<StudySpot> bestSpotList = aSchedule.getBestSpotsWithSchedule(studySpotList);
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
        Schedule userScheduleData = new Schedule();
        int actionInt;
        int day = 0;

        boolean validLogin = false;

        while(!validLogin) {

            // Asks the user for a username and password
            System.out.println("Please enter a username.");
            username = keyboard.nextLine();
            System.out.println("Please enter a password.");
            password = keyboard.nextLine();
            while(!(password.matches(".{4,}"))) {
                System.out.println("Password must be at least 4 characters long.");
                System.out.println("Please try again.");
                password = keyboard.nextLine();
            }

            // Asks the user to indicate whether they want to sign up or log in
            System.out.println("Please enter S to sign up or L to log in.");
            action = keyboard.nextLine();
            while (!action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("L")) {
                System.out.println("Please enter a valid command.");
                action = keyboard.nextLine();
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
            System.out.println("Please enter S to take the study spot survey, C to create your schedule, P to see your previous results or Q to quit.");
            action = keyboard.nextLine();
            while(!action.equalsIgnoreCase("S") && !action.equalsIgnoreCase("P") && !action.equalsIgnoreCase("Q") && !action.equalsIgnoreCase("C")) {
                System.out.println("Please enter a valid command.");
                action = keyboard.nextLine();
            }

            // Presents the user with the survey questions and gives them their results
            if(action.equalsIgnoreCase("S")) {

                userData.setNoiseLevel(aSurvey.askSurveyQuestions("what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)"));
                userData.setBathroomsNearby(aSurvey.askSurveyQuestions("how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
                userData.setFoodNearby(aSurvey.askSurveyQuestions("how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
                userData.setSeatingSpace(aSurvey.askSurveyQuestions("how much seating space is ideal for your study spot? (1: limited, 10: plentiful)"));
                userData.setOutlets(aSurvey.askSurveyQuestions("how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));

                userAccountList.setUserData(username, password, userData);
                userScheduleData = userAccountList.getUserSchedule(username, password);
                ssl.setUserIdeal(userData);
                aSurvey.displayResults(ssl, userScheduleData);

            }

            // Displays the most recent results
            else if(action.equalsIgnoreCase("P")) {

                userData = userAccountList.getUserData(username, password);
                userScheduleData = userAccountList.getUserSchedule(username, password);
                ssl.setUserIdeal(userData);
                aSurvey.displayResults(ssl, userScheduleData);

            }
          
          	// Presents User with options to input schedule.
          	else if (action.equalsIgnoreCase("C")) {
                while (true) {
                    // Prompts the user for the day to fill in
                    System.out.println("Please enter what day you would like to create your schedule in: ");
                    System.out.println("Put M for Mon, T for Tues, W for Wed, R for Thurs and F for Fri");
                    action = keyboard.nextLine();
                
                    if (action.equalsIgnoreCase("M") || action.equalsIgnoreCase("T") || action.equalsIgnoreCase("W") || action.equalsIgnoreCase("R") || action.equalsIgnoreCase("F")) {
                        if (action.equalsIgnoreCase("M")) {
                            day = 1;
                        }
                        else if (action.equalsIgnoreCase("T")) {
                            day = 2;
                        }
                        else if (action.equalsIgnoreCase("W")) {
                            day = 3;
                        }
                        else if (action.equalsIgnoreCase("R")) {
                            day = 4;
                        }
                        else if (action.equalsIgnoreCase("F")) {
                            day = 5;
                        }
                    
                    // Loops through the 12 hours in day
                    for (int j = 8; j < 21; j++) {
                        System.out.println("Please enter Y if you have a class in this hour or N if you don't have a class at this time: " + j + ":00");
                        action = keyboard.nextLine();
                        
                        while (true) {
                            if (action.equalsIgnoreCase("Y")) {
                
                                System.out.println("Please enter the location of your study spot:");  
                                for (int k = 0; k < 12; k++) {
                                    System.out.println(k+1 + ": " + ssl.getStudySpotList().get(k).getName());
                                }
                                
                                // Gets valid class information from the user
                                boolean studySpotValid = false;
                                actionInt = 0;
                                while (!studySpotValid) {
                                try {
                                    action = keyboard.nextLine();
                                    actionInt = Integer.parseInt(action);
                                    if (actionInt >= 1 && actionInt <= ssl.getStudySpotList().size()) {
                                        studySpotValid = true;
                                        }
                                    else {
                                        System.out.println("Invalid Input: Please enter a value from 1 to 12");
                                        System.out.println("Please enter the location of your study spot:");  
                                        for (int k = 0; k < 12; k++) {
                                            System.out.println(k+1 + ": " + ssl.getStudySpotList().get(k).getName());
                                        }
                                    }
                                }
                                catch (NumberFormatException nfe) {
                                    action = keyboard.nextLine();
                                    System.out.println("Invalid Input: Please enter a value from 1 to 12");          
                                    }
                                }
                                userScheduleData.setClass(day, j, ssl.getStudySpotList().get(actionInt-1));
                                break;
                            
                            }
                            else if (action.equalsIgnoreCase("N")) {
                                userScheduleData.setClass(day, j, null);
                                break;
                            }
                            else {
                                // Deals with an invalid command for whether the user has a class
                                System.out.println("Invalid Input: Please enter Y or N");
                                action = keyboard.nextLine();
                            }

                        }
                        
                    }  
                    // Asks if the user wants to fill in another day
                    System.out.println("Do you want to fill in another day? Y for yes, N for no");
                    action = keyboard.nextLine();
                    while (true) {
                        if (action.equalsIgnoreCase("y")) {
                            System.out.println("OK");
                            break;
                        }
                        else if (action.equalsIgnoreCase("n")) {
                            break;
                        }
                        else {
                            System.out.println("Inavlid input: Do you want to fill in another day? Y for yes, N for no"); 
                            action = keyboard.nextLine(); 
                        }
                    }
                    if (action.equalsIgnoreCase("n")) {
                        break;
                    }
                    
                    }
                    else {
                        // Deals with the user entering an invalid day
                        System.out.println("Invalid Input: Put M for Mon, T for Tues, W for Wed, R for Thurs and F for Fri");
                        action = keyboard.nextLine();

                    }
        }
        userAccountList.setUserSchedule(username, password, userScheduleData);
        aSurvey.displayResults(ssl, userScheduleData);
    }

        // Ends the program
        else if (action.equalsIgnoreCase("Q")) {
            break;
        }

        System.out.println("Thank you for using our study spot recommendation app.");
    }
}
}
    
