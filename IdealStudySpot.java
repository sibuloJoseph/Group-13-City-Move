/**
 * This extends the StudySpot class to represent user input and compare it to StudySpot objects.
 *
 * Last modified: February 28, 2019
 *
 */

import java.io.Serializable;

public class IdealStudySpot extends StudySpot implements Serializable {

    /**
     * Default constructor for IdealStudySpot object
     */
    public IdealStudySpot() {
        super();
    }

    /**
     * Copy constructor for IdealStudySpot object
     * @param idealStudySpotToCopy: the IdealStudySpot object to be copied
     */
    public IdealStudySpot(IdealStudySpot idealStudySpotToCopy) {
        super(idealStudySpotToCopy);
    }

    /**
     * Compares the given StudySpot to the IdealStudySpot and returns a double similarity value which
     * is lower if the study spots are more similar
     * @param studySpotToCompare: the StudySpot object to compare the ideal spot to
     * @return the double similarity value
     */
    public double compareTo(StudySpot studySpotToCompare) {
        double originalNoiseLevel = this.getNoiseLevel();
        double originalBathroomsNearby = this.getBathroomsNearby();
        double originalFoodNearby = this.getFoodNearby();
        double originalOutlets = this.getOutlets();
        double originalSeatingSpace = this.getSeatingSpace();

        double noiseCompareValue = (java.lang.Math.abs(originalNoiseLevel - studySpotToCompare.getNoiseLevel())) / originalNoiseLevel;
        double bathroomCompareValue = (java.lang.Math.abs(originalBathroomsNearby - studySpotToCompare.getBathroomsNearby())) / originalBathroomsNearby;
        double foodCompareValue = (java.lang.Math.abs(originalFoodNearby - studySpotToCompare.getFoodNearby())) / originalFoodNearby;
        double outletsCompareValue = (java.lang.Math.abs(originalOutlets - studySpotToCompare.getOutlets())) / originalOutlets;
        double seatingCompareValue = (java.lang.Math.abs(originalSeatingSpace - studySpotToCompare.getSeatingSpace())) / originalSeatingSpace;
        
        return noiseCompareValue + bathroomCompareValue + foodCompareValue + outletsCompareValue + seatingCompareValue;
    }

    /**
     * Tests the IdealStudySpot class
     */
    public static void main(String[] args) {
        IdealStudySpot i1 = new IdealStudySpot();
        i1.setNoiseLevel(2.0);
        i1.setBathroomsNearby(3.0);
        System.out.println(i1.getNoiseLevel());
        System.out.println(i1.getBathroomsNearby());

        IdealStudySpot i2 = new IdealStudySpot(i1);
        System.out.println(i2.getNoiseLevel());
        System.out.println(i2.getBathroomsNearby());

        StudySpot s1 = new StudySpot();
        System.out.println(i1.compareTo(s1));

    }
}
