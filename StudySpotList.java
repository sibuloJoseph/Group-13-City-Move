import java.util.ArrayList;

/**
 * This class outputs result of the best study spots for the user based on their answers and preferences.
 * 
 * Last modified: February 20, 2019
 */


public class StudySpotList{
    private IdealStudySpot userIdeal;
    private ArrayList<StudySpot> studySpotList = new ArrayList<StudySpot>();

    /**
     * Default constructor for the StudySpotList object
     */
    public StudySpotList(){
    }

    /**
     * Copy Constructor for StudySpot object
     * @param studySpotlistToCopy StudySpotList object to be copied
     */
    public StudySpotList(StudySpotList studySpotlistToCopy){
        this.userIdeal = studySpotlistToCopy.userIdeal;
        this.studySpotList = studySpotlistToCopy.studySpotList;
    }

    /**
     * Sets the userIdeal object to the given IdealStudySpot parameter.   
     * @param userIdeal variable from the IdealStudySpot class.
     */
    public void setUserIdeal(IdealStudySpot userIdeal){
        this.userIdeal = userIdeal;
    }

    /**
     * Returns the ideal StudySpot based on the user's input.
     */
    public IdealStudySpot getUserIdeal(){
        return userIdeal;
    }

    /**
     * Returns the list of StudySpots.
     */
    public ArrayList<StudySpot> getStudySpotList(){
        return studySpotList;
    }

    /**
     * Returns the best and  most ideal study spots based on the user's input.
     */
    public ArrayList<StudySpot> getBestStudySpots(){ 
        return studySpotList;
    }

    public static void main(String[] args){
        
    }


