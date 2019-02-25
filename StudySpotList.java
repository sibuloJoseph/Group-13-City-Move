import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class outputs result of the best study spots for the user based on their answers and preferences.
 * 
 * Last modified: February 23, 2019 @ 3:10 PM

 */
public class StudySpotList{
    private IdealStudySpot userIdeal;
    private ArrayList<StudySpot> studySpotList = new ArrayList<StudySpot>();
    private Scanner studySpotsFromTxt;
     

    /**
     * Default constructor for the StudySpotList object
     */
    public StudySpotList() throws FileNotFoundException{
        try {
            studySpotsFromTxt = new Scanner(new File("StudySpotsListV1.0.0.txt")).useDelimiter("\n");
            while (studySpotsFromTxt.hasNext()){
                int numOfSpots = studySpotList.size();
                studySpotList.add(new StudySpot(studySpotsFromTxt.nextLine()));
            }
        } 
        catch (Exception e) {
            //TODO: handle exception
            System.exit(1);
        }
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
     * Returns the list of StudySpots after getting the study spots from the StudySpotsList text file.
     */
    public ArrayList<StudySpot> getStudySpotList(){
        return studySpotList;

    }

    /**
     * Returns the best and  most ideal study spots based on the user's input.
     */
    public ArrayList<StudySpot> getBestStudySpots(){
        ArrayList<StudySpot> comparisonValues = new ArrayList<StudySpot>();
        comparisonValues.add(userIdeal.compareTo(new StudySpot()));
        return comparisonValues;
    }

    /**
    *Test the StudySpotList class
    */
    public static void main(String[] args) throws FileNotFoundException{
        StudySpotList list1 = new StudySpotList();

        System.out.println(list1.getUserIdeal());
        System.out.println(list1.getStudySpotList()); 
        System.out.println(list1.getBestStudySpots()); 
    }
}
