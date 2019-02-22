/**
 * This extends the StudySpot class to represent user input and compare it to StudySpot objects.
 *
 * Last modified: February 22, 2019
 *
 */

public class IdealStudySpot extends Survey {

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
    public IdealStudySpot(Survey idealStudySpotToCopy) {
        super(idealStudySpotToCopy);
        System.out.print(idealStudySpotToCopy); //This part is only a test, it will be removed later.//
    }

    /**
     * Compares the given StudySpot to the IdealStudySpot and returns a double similarity value which
     * is higher if the study spots are more similar
     * @param studySpotToCompare: the StudySpot object to compare the ideal spot to
     * @return the double similarity value
     */
    public double compareTo(StudySpot studySpotToCompare) {
        return 0.0;
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