import static org.junit.Assert.*;
import org.junit.Test;
import logic.StudySpot;

/**
 * This is the unit test for the StudySpot class.
 *
 * Last Modified: March 25, 2019
 */

public class StudySpotTest {

    // Test constructors
    @Test
    public void test_default_constructor() {
        StudySpot s = new StudySpot();

        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getNoiseLevel(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getFoodNearby(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getBathroomsNearby(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getOutlets(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getSeatingSpace(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getX1(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 2.0", 2.0, s.getX2(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getY1(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 2.0", 2.0, s.getY2(), 0.0000001);
        assertEquals("Name should be initialized to an empty string", "", s.getName());
    }

    @Test
    public void test_name_constructor() {
        StudySpot s = new StudySpot("Name");

        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getNoiseLevel(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getFoodNearby(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getBathroomsNearby(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getOutlets(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getSeatingSpace(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getX1(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 2.0", 2.0, s.getX2(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 1.0", 1.0, s.getY1(), 0.0000001);
        assertEquals("Numerical instance values should be initialized to 2.0", 2.0, s.getY2(), 0.0000001);
        assertEquals("Name should be initialized to \"Name\"", "Name", s.getName());
    }

    @Test
    public void test_copy_constructor() {
        StudySpot s1 = new StudySpot();
        s1.setName("Name");
        s1.setNoiseLevel(2.0);
        s1.setFoodNearby(3.0);
        s1.setBathroomsNearby(4.0);
        s1.setOutlets(5.0);
        s1.setSeatingSpace(6.0);
        s1.setX1(5.0);
        s1.setX2(10.0);
        s1.setY1(5.0);
        s1.setY2(20.0);

        StudySpot s2 = new StudySpot(s1);

        assertEquals("Instance variables should have the same values", s1.getNoiseLevel(), s2.getNoiseLevel(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getFoodNearby(), s2.getFoodNearby(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getBathroomsNearby(), s2.getBathroomsNearby(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getOutlets(), s2.getOutlets(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getSeatingSpace(), s2.getSeatingSpace(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getName(), s2.getName());

        assertEquals("Instance variables should have the same values", s1.getX1(), s2.getX1(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getX2(), s2.getX2(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getY1(), s2.getY1(), 0.0000001);
        assertEquals("Instance variables should have the same values", s1.getY2(), s2.getY2(), 0.0000001);
    }

    // Test setNoiseLevel method
    @Test
    public void test_setNoiseLevel_tooLow() {
        StudySpot s = new StudySpot();
        s.setNoiseLevel(0.0);
        assertEquals("Noise level should remain at 1.0", 1.0, s.getNoiseLevel(), 0.0000001);
    }

    @Test
    public void test_setNoiseLevel_minimum() {
        StudySpot s = new StudySpot();
        s.setNoiseLevel(1.0);
        assertEquals("Noise level should be 1.0", 1.0, s.getNoiseLevel(), 0.0000001);
    }

    @Test
    public void test_setNoiseLevel_average() {
        StudySpot s = new StudySpot();
        s.setNoiseLevel(5.0);
        assertEquals("Noise level should be 5.0", 5.0, s.getNoiseLevel(), 0.0000001);
    }

    @Test
    public void test_setNoiseLevel_maximum() {
        StudySpot s = new StudySpot();
        s.setNoiseLevel(10.0);
        assertEquals("Noise level should be 10.0", 10.0, s.getNoiseLevel(), 0.0000001);
    }

    @Test
    public void test_setNoiseLevel_tooHigh() {
        StudySpot s = new StudySpot();
        s.setNoiseLevel(11.0);
        assertEquals("Noise level should remain at 1.0", 1.0, s.getNoiseLevel(), 0.0000001);
    }

    // Test setFoodNearby method
    @Test
    public void test_setFoodNearby_tooLow() {
        StudySpot s = new StudySpot();
        s.setFoodNearby(0.0);
        assertEquals("Food nearby should remain at 1.0", 1.0, s.getFoodNearby(), 0.0000001);
    }

    @Test
    public void test_setFoodNearby_minimum() {
        StudySpot s = new StudySpot();
        s.setFoodNearby(1.0);
        assertEquals("Food nearby should be 1.0", 1.0, s.getFoodNearby(), 0.0000001);
    }

    @Test
    public void test_setFoodNearby_average() {
        StudySpot s = new StudySpot();
        s.setFoodNearby(5.0);
        assertEquals("Food nearby should be 5.0", 5.0, s.getFoodNearby(), 0.0000001);
    }

    @Test
    public void test_setFoodNearby_maximum() {
        StudySpot s = new StudySpot();
        s.setFoodNearby(10.0);
        assertEquals("Food nearby should be 10.0", 10.0, s.getFoodNearby(), 0.0000001);
    }

    @Test
    public void test_setFoodNearby_tooHigh() {
        StudySpot s = new StudySpot();
        s.setFoodNearby(11.0);
        assertEquals("Food nearby should remain at 1.0", 1.0, s.getFoodNearby(), 0.0000001);
    }

    // Test setBathroomsNearby method
    @Test
    public void test_setBathroomsNearby_tooLow() {
        StudySpot s = new StudySpot();
        s.setBathroomsNearby(0.0);
        assertEquals("Bathrooms nearby should remain at 1.0", 1.0, s.getBathroomsNearby(), 0.0000001);
    }

    @Test
    public void test_setBathroomsNearby_minimum() {
        StudySpot s = new StudySpot();
        s.setBathroomsNearby(1.0);
        assertEquals("Bathrooms nearby should be 1.0", 1.0, s.getBathroomsNearby(), 0.0000001);
    }

    @Test
    public void test_setBathroomsNearby_average() {
        StudySpot s = new StudySpot();
        s.setBathroomsNearby(5.0);
        assertEquals("Bathrooms nearby should be 5.0", 5.0, s.getBathroomsNearby(), 0.0000001);
    }

    @Test
    public void test_setBathroomsNearby_maximum() {
        StudySpot s = new StudySpot();
        s.setBathroomsNearby(10.0);
        assertEquals("Bathrooms nearby should be 10.0", 10.0, s.getBathroomsNearby(), 0.0000001);
    }

    @Test
    public void test_setBathroomsNearby_tooHigh() {
        StudySpot s = new StudySpot();
        s.setBathroomsNearby(11.0);
        assertEquals("Bathrooms nearby should remain at 1.0", 1.0, s.getBathroomsNearby(), 0.0000001);
    }

    // Test setOutlets method
    @Test
    public void test_setOutlets_tooLow() {
        StudySpot s = new StudySpot();
        s.setOutlets(0.0);
        assertEquals("Outlets should remain at 1.0", 1.0, s.getOutlets(), 0.0000001);
    }

    @Test
    public void test_setOutlets_minimum() {
        StudySpot s = new StudySpot();
        s.setOutlets(1.0);
        assertEquals("Outlets should be 1.0", 1.0, s.getOutlets(), 0.0000001);
    }

    @Test
    public void test_setOutlets_average() {
        StudySpot s = new StudySpot();
        s.setOutlets(5.0);
        assertEquals("Outlets should be 5.0", 5.0, s.getOutlets(), 0.0000001);
    }

    @Test
    public void test_setOutlets_maximum() {
        StudySpot s = new StudySpot();
        s.setOutlets(10.0);
        assertEquals("Outlets should be 10.0", 10.0, s.getOutlets(), 0.0000001);
    }

    @Test
    public void test_setOutlets_tooHigh() {
        StudySpot s = new StudySpot();
        s.setOutlets(11.0);
        assertEquals("Outlets should remain at 1.0", 1.0, s.getOutlets(), 0.0000001);
    }

    // Test setSeatingSpace method
    @Test
    public void test_setSeatingSpace_tooLow() {
        StudySpot s = new StudySpot();
        s.setSeatingSpace(0.0);
        assertEquals("Seating space should remain at 1.0", 1.0, s.getSeatingSpace(), 0.0000001);
    }

    @Test
    public void test_setSeatingSpace_minimum() {
        StudySpot s = new StudySpot();
        s.setSeatingSpace(1.0);
        assertEquals("Seating space should be 1.0", 1.0, s.getSeatingSpace(), 0.0000001);
    }

    @Test
    public void test_setSeatingSpace_average() {
        StudySpot s = new StudySpot();
        s.setSeatingSpace(5.0);
        assertEquals("Seating space should be 5.0", 5.0, s.getSeatingSpace(), 0.0000001);
    }

    @Test
    public void test_setSeatingSpace_maximum() {
        StudySpot s = new StudySpot();
        s.setSeatingSpace(10.0);
        assertEquals("Seating space should be 10.0", 10.0, s.getSeatingSpace(), 0.0000001);
    }

    @Test
    public void test_setSeatingSpace_tooHigh() {
        StudySpot s = new StudySpot();
        s.setSeatingSpace(11.0);
        assertEquals("Seating space should remain at 1.0", 1.0, s.getSeatingSpace(), 0.0000001);
    }

    // Test setName method
    @Test
    public void test_setName_string() {
        StudySpot s = new StudySpot();
        s.setName("Name");
        assertEquals("Name should be changed to \"Name\"", "Name", s.getName());
    }

    @Test
    public void test_setName_null() {
        StudySpot s = new StudySpot();
        s.setName(null);
        assertEquals("Name should remain as an empty string", "", s.getName());
    }

    // Test setX1 method
    @Test
    public void test_setX1_tooLow () {
        StudySpot s = new StudySpot();
        s.setX1(-1.0);
        assertEquals("X1 coordinate should remain at 1.0", 1.0, s.getX1(), 0.0000001);
    }

    @Test
    public void test_setX1__minimum() {
        StudySpot s = new StudySpot();
        s.setX1(0.0);
        assertEquals("X1 coordinate should be 0.0", 0.0, s.getX1(), 0.0000001);
    }

    @Test
    public void test_setX1_average() {
        StudySpot s = new StudySpot();
        s.setX1(100.5);
        assertEquals("X1 coordinate should be 100.5", 100.5, s.getX1(), 0.0000001);
    }

    @Test
    public void test_setX1_maximum() {
        StudySpot s = new StudySpot();
        s.setX1(764.5);
        assertEquals("X1 coordinate should be 764.5", 764.5, s.getX1(), 0.0000001);
    }

    @Test
    public void test_setX1_tooHigh() {
        StudySpot s = new StudySpot();
        s.setX1(765.0);
        assertEquals("X1 coordinate should be 1.0", 1.0, s.getX1(), 0.0000001);
    }

    // Test setX2 method
    @Test
    public void test_setX2_tooLow () {
        StudySpot s = new StudySpot();
        s.setX2(-1.0);
        assertEquals("X2 coordinate should remain at 2.0", 2.0, s.getX2(), 0.0000001);
    }

    @Test
    public void test_setX2__minimum() {
        StudySpot s = new StudySpot();
        s.setX2(0.0);
        assertEquals("X2 coordinate should be 0.0", 0.0, s.getX2(), 0.0000001);
    }

    @Test
    public void test_setX2_average() {
        StudySpot s = new StudySpot();
        s.setX2(100.5);
        assertEquals("X2 coordinate should be 100.5", 100.5, s.getX2(), 0.0000001);
    }

    @Test
    public void test_setX2_maximum() {
        StudySpot s = new StudySpot();
        s.setX2(764.5);
        assertEquals("X2 coordinate should be 764.5", 764.5, s.getX2(), 0.0000001);
    }

    @Test
    public void test_setX2_tooHigh() {
        StudySpot s = new StudySpot();
        s.setX2(765.0);
        assertEquals("X2 coordinate should be 2.0", 2.0, s.getX2(), 0.0000001);
    }

    // Test setY1 method
    @Test
    public void test_setY1_tooLow () {
        StudySpot s = new StudySpot();
        s.setY1(-1.0);
        assertEquals("Y1 coordinate should remain at 1.0", 1.0, s.getY1(), 0.0000001);
    }

    @Test
    public void test_setY1__minimum() {
        StudySpot s = new StudySpot();
        s.setY1(0.0);
        assertEquals("Y1 coordinate should be 0.0", 0.0, s.getY1(), 0.0000001);
    }

    @Test
    public void test_setY1_average() {
        StudySpot s = new StudySpot();
        s.setY1(100.5);
        assertEquals("Y1 coordinate should be 100.5", 100.5, s.getY1(), 0.0000001);
    }

    @Test
    public void test_setY1_maximum() {
        StudySpot s = new StudySpot();
        s.setY1(586.5);
        assertEquals("Y1 coordinate should be 586.5", 586.5, s.getY1(), 0.0000001);
    }

    @Test
    public void test_setY1_tooHigh() {
        StudySpot s = new StudySpot();
        s.setY1(587.0);
        assertEquals("Y1 coordinate should be 1.0", 1.0, s.getY1(), 0.0000001);
    }

    // Test setY2 method
    @Test
    public void test_setY2_tooLow () {
        StudySpot s = new StudySpot();
        s.setY2(-1.0);
        assertEquals("Y2 coordinate should remain at 2.0", 2.0, s.getY2(), 0.0000001);
    }

    @Test
    public void test_setY2__minimum() {
        StudySpot s = new StudySpot();
        s.setY2(0.0);
        assertEquals("Y2 coordinate should be 0.0", 0.0, s.getY2(), 0.0000001);
    }

    @Test
    public void test_setY2_average() {
        StudySpot s = new StudySpot();
        s.setY2(100.5);
        assertEquals("Y2 coordinate should be 100.5", 100.5, s.getY2(), 0.0000001);
    }

    @Test
    public void test_setY2_maximum() {
        StudySpot s = new StudySpot();
        s.setY2(586.5);
        assertEquals("Y2 coordinate should be 586.5", 586.5, s.getY2(), 0.0000001);
    }

    @Test
    public void test_setY2_tooHigh() {
        StudySpot s = new StudySpot();
        s.setY2(587.0);
        assertEquals("Y2 coordinate should be 1.0", 2.0, s.getY2(), 0.0000001);
    }

}
