/**
 * This implements the StudySpot class to describe study spots at the University of Calgary.
 *
 * Last modified: February 28, 2019
 *
 */

import java.io.Serializable;

public class StudySpot implements Serializable {
    private double noiseLevel, bathroomsNearby, foodNearby, outlets, seatingSpace;
    private String name;

    /**
     * Default constructor for StudySpot object
     */
    public StudySpot() {
        this("");
    }

    /**
     * Constructor for StudySpot object which takes the name as a parameter
     * @param name: String containing the name of the study spot
     */
    public StudySpot(String name) {
        noiseLevel = 1.0;
        bathroomsNearby = 1.0;
        foodNearby = 1.0;
        outlets = 1.0;
        seatingSpace = 1.0;
        this.name = name;
    }
    
    /**
     * Copy constructor for StudySpot object
     * @param studySpotToCopy: StudySpot object to be copied
     */
    public StudySpot(StudySpot studySpotToCopy) {
        this.noiseLevel = studySpotToCopy.noiseLevel;
        this.bathroomsNearby = studySpotToCopy.bathroomsNearby;
        this.foodNearby = studySpotToCopy.foodNearby;
        this.outlets = studySpotToCopy.outlets;
        this.seatingSpace = studySpotToCopy.seatingSpace;
        this.name = studySpotToCopy.name;
    }

    /**
     * Sets the indicator of noise level to the given value
     * @param noiseLevel: double representing the noise level of the study spot
     */
    public void setNoiseLevel(double noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    /**
     * Sets the indicator of nearby bathrooms to the given value
     * @param bathroomsNearby: double representing whether or not there are bathrooms near the study spot
     */
    public void setBathroomsNearby(double bathroomsNearby) {
        this.bathroomsNearby = bathroomsNearby;
    }

    /**
     * Sets the indicator of available food places to the given value
     * @param foodNearby: double representing the amount of food places near the study spot
     */
    public void setFoodNearby(double foodNearby) {
        this.foodNearby = foodNearby;
    }

    /**
     * Sets the indicator of available outlets to the given value
     * @param outlets: double representing whether power outlets are available
     */
    public void setOutlets(double outlets) {
        this.outlets = outlets;
    }

    /**
     * Sets the indicator of avialable seating space to the given value
     * @param seatingSpace: double indicating the amount of available seats
     */
    public void setSeatingSpace(double seatingSpace) {
        this.seatingSpace = seatingSpace;
    }

    /**
     * Sets the name of the study spot to the given name
     * @param name: String containing the name of the study spot
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a double representing the noise level of the study spot
     */
    public double getNoiseLevel() {
        return noiseLevel;
    }

    /**
     * Returns a double representing the presence of bathrooms near the study spot
     */
    public double getBathroomsNearby() {
        return bathroomsNearby;
    }

    /**
     * Returns a double representing the amount of food places near the study spot
     */
    public double getFoodNearby() {
        return foodNearby;
    }

    /**
     * Returns a double representing the availability of outlets at the study spot
     */
    public double getOutlets() {
        return outlets;
    }

    /**
     * Returns a double representing the amount of seating space available at the study spot
     */
    public double getSeatingSpace() {
        return seatingSpace;
    }

    /**
     * Returns a String containing the name of the study spot
     */
    public String getName() {
        return name;
    }

    /**
     * Tests the StudySpot class
     */
    public static void main(String[] args) {
        StudySpot s = new StudySpot();
        s.setNoiseLevel(1.0);
        s.setFoodNearby(2.0);
        s.setBathroomsNearby(3.0);
        s.setOutlets(4.0);
        s.setSeatingSpace(5.0);

        System.out.println(s.getNoiseLevel());
        System.out.println(s.getFoodNearby());
        System.out.println(s.getBathroomsNearby());
        System.out.println(s.getOutlets());
        System.out.println(s.getSeatingSpace());
        
        StudySpot s2 = new StudySpot(s);
        System.out.println(s2.getNoiseLevel());
        System.out.println(s2.getFoodNearby());
        System.out.println(s2.getBathroomsNearby());
        System.out.println(s2.getOutlets());
        System.out.println(s2.getSeatingSpace());

        s = new StudySpot("My study spot");
        System.out.println(s.getName());
        s.setName("Still a study spot");
        System.out.println(s.getName());

    }

}
