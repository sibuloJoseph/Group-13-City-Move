import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import logic.StudySpotList;
import logic.StudySpot;
import logic.IdealStudySpot;

/**
 * This is the unit test for the StudySpotList class.
 *
 * Last Modified: March 25, 2019
 */

public class StudySpotListTest {

    // Test constructors
    @Test
    public void test_default_constructor() {
        StudySpotList s = new StudySpotList();
        assertFalse("userIdeal should be initalized", s.getUserIdeal() == null);
        assertFalse("studySpotList should be initialized", s.getStudySpotList() == null);
        assertTrue("studySpotList should not be empty", s.getStudySpotList().size() > 0);
    }

    @Test
    public void test_copy_constructor() {
        StudySpotList s1 = new StudySpotList();
        IdealStudySpot i = new IdealStudySpot();
        i.setNoiseLevel(5.0);
        s1.setUserIdeal(i);

        String name = s1.getStudySpotList().get(0).getName();

        StudySpotList s2 = new StudySpotList(s1);

        assertEquals("Instance variables should have same values as original", 5.0, s2.getUserIdeal().getNoiseLevel(), 0.0000001);
        assertEquals("Instance variables should have same values as original", name, s2.getStudySpotList().get(0).getName());
    }

    // Test setUserIdeal method
    @Test
    public void test_setUserIdeal_IdealStudySpot() {
        StudySpotList s = new StudySpotList();
        IdealStudySpot i = new IdealStudySpot();
        i.setNoiseLevel(2.0);
        i.setOutlets(3.0);

        s.setUserIdeal(i);
        assertEquals("userIdeal should be changed to the given object", 2.0, s.getUserIdeal().getNoiseLevel(), 0.0000001);
        assertEquals("userIdeal should be changed to the given object", 3.0, s.getUserIdeal().getOutlets(), 0.0000001);
    }

    @Test
    public void test_setUserIdeal_null() {
        StudySpotList s = new StudySpotList();
        s.setUserIdeal(null);
        assertFalse("userIdeal should not be changed to null", s.getUserIdeal() == null);
    }

    @Test
    public void test_setUserIdeal_privacy_leaks() {
        StudySpotList s = new StudySpotList();
        IdealStudySpot i = new IdealStudySpot();
        s.setUserIdeal(i);
        i.setNoiseLevel(5.0);

        assertEquals("userIdeal should not have changed", 1.0, s.getUserIdeal().getNoiseLevel(), 0.0000001);
    }

    // Test getUserIdeal method
    @Test
    public void test_getUserIdeal_privacy_leaks() {
        StudySpotList s = new StudySpotList();
        IdealStudySpot i = s.getUserIdeal();
        i.setNoiseLevel(5.0);

        assertEquals("userIdeal should not have changed", 1.0, s.getUserIdeal().getNoiseLevel(), 0.0000001);
    }

    // Test getStudySpotList method
    @Test
    public void test_getStudySpotList_privacy_leaks1() {
        StudySpotList s = new StudySpotList();
        ArrayList<StudySpot> studySpots = s.getStudySpotList();
        StudySpot temp = studySpots.get(0);
        studySpots.set(1, temp);

        assertFalse("studySpotList should not have changed", temp.getName().equals(s.getStudySpotList().get(1).getName()));
    }

    @Test
    public void test_getStudySpotList_privacy_leaks2() {
        StudySpotList s = new StudySpotList();
        ArrayList<StudySpot> studySpots = s.getStudySpotList();
        studySpots.get(0).setName("Changed");

        assertFalse("studySpotList should not have changed", "Changed".equals(s.getStudySpotList().get(0).getName()));
    }

    // Test getBestStudySpots method
    @Test
    public void test_getBestStudySpots_KNB() {
        StudySpotList s = new StudySpotList();
        IdealStudySpot i = new IdealStudySpot();
        i.setNoiseLevel(9.0);
        i.setBathroomsNearby(8.0);
        i.setFoodNearby(5.0);
        i.setSeatingSpace(3.0);
        i.setOutlets(2.0);
        s.setUserIdeal(i);

        String name = "Kinesiology Block (KNB)";
        assertEquals("First spot should be KNB", name, s.getBestStudySpots().get(0).getName());
    }
    @Test
    public void test_getBestStudySpots_null_userIdeal() {
        StudySpotList s = new StudySpotList();
        s.setUserIdeal(null);

        // Checking for null pointer exception
        s.getBestStudySpots();
    }

    @Test
    public void test_getBestStudySpots_privacy_leaks1() {
        StudySpotList s = new StudySpotList();
        ArrayList<StudySpot> studySpots = s.getBestStudySpots();
        studySpots.clear();

        assertFalse("studySpotList should not be emptied", s.getStudySpotList().size() == 0);
    }

    @Test
    public void test_getBestStudySpots_privacy_leaks2() {
        StudySpotList s = new StudySpotList();
        ArrayList<StudySpot> studySpots = s.getBestStudySpots();
        studySpots.get(0).setName("Changed");

        assertFalse("Best spot list should not have been changed", "Changed".equals(s.getBestStudySpots().get(0).getName()));
    }

    // Test getLocation method
    @Test
    public void test_getLocation_KNB() {
        StudySpotList s = new StudySpotList();

        String name = "Kinesiology Block (KNB)";
        assertEquals("Study spot at location should be KNB", name, s.getLocation(150.0, 340.0).getName());
    }

    @Test
    public void test_getLocation_null() {
        StudySpotList s = new StudySpotList();

        assertEquals("There should be no study spot at the location", null, s.getLocation(1.0, 1.0));
    }

    @Test
    public void test_getLocation_privacyleaks() {
        StudySpotList s = new StudySpotList();

        s.getLocation(150.0, 340.0).setName("Changed");
        assertFalse("Name should not be changed", "Changed".equals(s.getLocation(150.0, 340.0).getName()));
    }
}
