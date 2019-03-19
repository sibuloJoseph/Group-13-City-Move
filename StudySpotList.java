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
    private IdealStudySpot userIdeal = new IdealStudySpot();
    private ArrayList<StudySpot> studySpotList = new ArrayList<StudySpot>();
    private Scanner studySpotsFromTxt;
    private StudySpot studySpot =new StudySpot();

    /**
     * Default constructor for the StudySpotList object
     */
    public StudySpotList(){
        try {
            studySpotsFromTxt = new Scanner(new File("StudySpotsListV1.0.0.txt"));
            while (studySpotsFromTxt.hasNext()){

                studySpot = new StudySpot();
                studySpot.setName(studySpotsFromTxt.nextLine());
                studySpot.setNoiseLevel(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setBathroomsNearby(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setFoodNearby(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setSeatingSpace(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setOutlets(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setX1(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setX2(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setY1(Double.parseDouble(studySpotsFromTxt.nextLine()));
                studySpot.setY2(Double.parseDouble(studySpotsFromTxt.nextLine()));
                this.getStudySpotList().add(studySpot);
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
        return this.userIdeal;
    }

    /**
     * Returns the list of StudySpots after getting the study spots from the StudySpotsList text file.
     */
    public ArrayList<StudySpot> getStudySpotList(){
        return this.studySpotList;

    }

    /**
     * Returns the best and  most ideal study spots based on the user's input.
     */
    public ArrayList<StudySpot> getBestStudySpots(){
        //Create an ArrayList for the comparison values from the compareTo() method in the IdealStudySpot Class
        ArrayList<Double> comparisonValues = new ArrayList<Double>();
        for(int x=0; x<this.getStudySpotList().size(); x++){
            comparisonValues.add(this.getUserIdeal().compareTo(new StudySpot(this.getStudySpotList().get(x))));
        }
        
        //Sort the comparisonValues array in ascending order and match it with the studySpotList array so that 
        //the studySpotList array will have the best study spot in the first index. 
        double temp;
        StudySpot tempss;
        for(int i = 1; i < this.getStudySpotList().size(); i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(comparisonValues.get(j) > comparisonValues.get(j+1)) {
                    temp = comparisonValues.get(j);
                    comparisonValues.set(j, comparisonValues.get(j+1));
                    comparisonValues.set(j+1, temp);
                    tempss = this.getStudySpotList().get(j);
                    this.getStudySpotList().set(j, this.getStudySpotList().get(j+1));
                    this.getStudySpotList().set(j+1, tempss);
                }
            }
        }
        return this.getStudySpotList();
    }

    public StudySpot getLocation(double x, double y){
        StudySpot s = null;        
        for(int i=0; i<studySpotList.size(); i++){
            if(studySpotList.get(i).getX1()<x && studySpotList.get(i).getX2()>x){
                if(studySpotList.get(i).getY1()<y && studySpotList.get(i).getY2()>y){
                    s = new StudySpot(studySpotList.get(i));
                    break;
                }
            }
        }
        
        return s;
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
