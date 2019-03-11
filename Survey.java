import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class gets the input of the user of their most preferred criteria for study spots at the University of Calgary
 * based on a survey of 5 questions.
 *
 * Last modified: March 11th, 2019
 */

public class Survey {
    private double noiseLevel, bathroomsNearby, foodNearby, seatingSpace, outlets;

    /**
     * Default Constructor for Survey object
     */ 
    public Survey () {
        this.noiseLevel = 1.0;
        this.bathroomsNearby = 1.0;
        this.foodNearby = 1.0;
        this.seatingSpace = 1.0;
        this.outlets = 1.0;
    }

    /**
     * Constructor for Survey object which takes the noiseLevel, bathroomsNearby, foodNearby, seatingSpace and outlets as arguments
     * @param noiseLevel: double to set as the initial noiseLevel
     * @param bathroomsNearby: double to set as the initial amount for bathroomsNearby  
     * @param foodNearby: double to set as the initial amount for foodNearby
     * @param seatingSpace: double to set as the initial amount for seatingSpace
     * @param outlets: double to set as the initial amount for outlets
     */ 
    public Survey (double noiseLevel, double bathroomsNearby, double foodNearby, double seatingSpace, double outlets) {
        this.noiseLevel = noiseLevel;
        this.bathroomsNearby = bathroomsNearby;
        this.foodNearby = foodNearby;
        this.seatingSpace = seatingSpace;
        this.outlets = outlets;
    }

    /**
     * Copy Constructor for Survey object
     * @param surveyToCopy: Survey object to be copied
     */ 
    public Survey (Survey surveyToCopy) {
        this.noiseLevel = surveyToCopy.noiseLevel;
        this.bathroomsNearby = surveyToCopy.bathroomsNearby;
        this.foodNearby = surveyToCopy.foodNearby;
        this.seatingSpace = surveyToCopy.seatingSpace;
        this.outlets = surveyToCopy.outlets;
    }

    /**
     * Asks the user questions where the user gets to rank their preferences on 5 criteria to determine their ideal study spot
     * If the user inputs a double outside of the 1-10 scale then the question reprompts
     * @param question: a string representing the question to be asked in the survey  
     * @return criteriaInput: a double representing the value of the user's preference of the given criteria in a given 1-10 scale 
     */
    public double askSurveyQuestions (String question) {
        boolean isTrue = false;
        Scanner keyboard = new Scanner (System.in);
        System.out.println("On a scale of 1-10, " + question);
        double criteriaInput = keyboard.nextDouble();
        while (isTrue == false) {
            if (criteriaInput >= 1.0 && criteriaInput <= 10.0) {
                isTrue = true; 
                return criteriaInput;
            }
            else {
                System.out.println("On a scale of 1-10, " + question);
                criteriaInput = keyboard.nextDouble();
                isTrue = false;
            }
        }
        return 0.0;
    }

    /**
     * Tests for the Survey class
     */
    public static void main (String [] args) {
        Survey aSurvey = new Survey ();
        IdealStudySpot idealStudySpot1 = new IdealStudySpot ();
        idealStudySpot1.setNoiseLevel(aSurvey.askSurveyQuestions("what's the acceptable level of noise for you at your ideal study spot? (1: no noise at all, 10: I can work in a loud place.)"));
        idealStudySpot1.setBathroomsNearby(aSurvey.askSurveyQuestions("how important is having bathrooms nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
        idealStudySpot1.setFoodNearby(aSurvey.askSurveyQuestions("how important is having food places nearby your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
        idealStudySpot1.setSeatingSpace(aSurvey.askSurveyQuestions("how much seating space is ideal for your study spot? (1: limited, 10: plentiful)"));
        idealStudySpot1.setOutlets(aSurvey.askSurveyQuestions("how important is the availability of power outlets at your ideal study spot? (1: not important to me, 10: Extremely important to me.)"));
       
        StudySpotList ssl = new StudySpotList ();
        ssl.setUserIdeal(idealStudySpot1);
        ArrayList<StudySpot> bestSpotList = ssl.getBestStudySpots();
        System.out.println(bestSpotList.get(0).getName());
        System.out.println(bestSpotList.get(1).getName());
        System.out.println(bestSpotList.get(2).getName());
    }
    
}