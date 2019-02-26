import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class outputs result of the best study spots for the user based on their answers and preferences.
 * 
 * Last modified: February 20, 2019 @ 5:04 PM

 */
public class StudySpotList{
    private IdealStudySpot userIdeal;
    private ArrayList<StudySpot> studySpotList = new ArrayList<StudySpot>();
     

    /**
     * Default constructor for the StudySpotList object
     */
    public StudySpotList(){
        try {
            Scanner studySpotsFromTxt = new Scanner(new File("StudySpotsListV1.0.0.txt"));
            StudySpot ss =new StudySpot();
            userIdeal = new IdealStudySpot();

            while (studySpotsFromTxt.hasNext()){

                ss = new StudySpot();
                ss.setName(studySpotsFromTxt.nextLine());
                ss.setNoiseLevel(Double.parseDouble(studySpotsFromTxt.nextLine()));
                ss.setBathroomsNearby(Double.parseDouble(studySpotsFromTxt.nextLine()));
                ss.setFoodNearby(Double.parseDouble(studySpotsFromTxt.nextLine()));
                ss.setSeatingSpace(Double.parseDouble(studySpotsFromTxt.nextLine()));
                ss.setOutlets(Double.parseDouble(studySpotsFromTxt.nextLine()));

                studySpotList.add(ss);
            }
        }
        catch (Exception e) {
            //TODO: handle exception
            System.out.println("No File"); 
            System.exit(0);
           

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
        //Create an ArrayList for the comparison values from the compareTo() method in the IdealStudySpot Class
        ArrayList<Double> comparisonValues = new ArrayList<Double>();
        for(int x=0; x<studySpotList.size(); x++){
            comparisonValues.add(userIdeal.compareTo(new StudySpot(studySpotList.get(x))));
        }
        
        //Sort the comparisonValues array and match it with the studySpotList array 
        //in order to sort the list of study spots from the best to the worst.
        double temp;
        StudySpot tempss;
        for(int i = 1; i < studySpotList.size(); i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(comparisonValues.get(j) > comparisonValues.get(j+1)) {
                    temp = comparisonValues.get(j);
                    comparisonValues.set(j, comparisonValues.get(j+1));
                    comparisonValues.set(j+1, temp);
                    tempss = studySpotList.get(j);
                    studySpotList.set(j, studySpotList.get(j+1));
                    studySpotList.set(j+1, tempss);
                }
            }
        }
        return studySpotList;
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
