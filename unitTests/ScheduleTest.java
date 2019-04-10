import static org.junit.Assert.*;
import org.junit.Test;
import logic.Schedule;
import logic.StudySpot;
import logic.StudySpotList;
import logic.IdealStudySpot;

/**
 * This is the unit test for the Schedule class.
 * Manual testing should also be done for this class, as rankings given are partially based on the current time.
 *
 * Last modified: April 10, 2019
 */
public class ScheduleTest {

    // Test constructors
    @Test
    public void test_default_constructor() {
        Schedule s = new Schedule();

        for(int i = 1;i < 6;i++) {
            for(int j = 8;j < 21;j++) {
                assertEquals("Classes should be intialized to null", s.getClass(i, j), null);
            }
        }
    }

    @Test
    public void test_copy_constructor() {
        Schedule s1 = new Schedule();

        s1.setClass(2, 10, new StudySpot("spot 1"));
        s1.setClass(5, 15, new StudySpot("spot 2"));
        s1.setClass(3, 12, null);

        Schedule s2 = new Schedule(s1);

        assertTrue("Copied Schedule should have same classes as original", "spot 1".equals(s2.getClass(2, 10).getName()));
        assertTrue("Copied Schedule should have same classes as original", "spot 2".equals(s2.getClass(5, 15).getName()));
        assertTrue("Copied Schedule should have same classes as original", null == s2.getClass(3, 12));
    }

    // Test setClass and getClass methods
    @Test
    public void test_setClass_earliest_times() {
        Schedule s = new Schedule();
        s.setClass(1, 8, new StudySpot("spot"));

        assertTrue("Class at time 8:00 Monday should be added", "spot".equals(s.getClass(1, 8).getName()));
    }

    @Test
    public void test_setClass_latest_times() {
        Schedule s = new Schedule();
        s.setClass(5, 20, new StudySpot("spot"));

        assertTrue("Class at time 20:00 Friday should be added", "spot".equals(s.getClass(5, 20).getName()));
    }

    @Test
    public void test_setClass_too_early() {
        Schedule s = new Schedule();
        s.setClass(1, 7, new StudySpot("spot"));

        assertTrue("Class should not be added at time 7:00 Monday", s.getClass(1, 7) == null);
    }

    @Test
    public void test_setClass_too_late() {
        Schedule s = new Schedule();
        s.setClass(1, 21, new StudySpot("spot"));

        assertTrue("Class should not be added at time 21:00 Monday", s.getClass(1, 21) == null);
    }

    @Test
    public void test_setClass_wrong_day() {
        Schedule s = new Schedule();
        s.setClass(6, 10, new StudySpot("spot"));

        assertTrue("Class should not be added at time 10:00 Saturday", s.getClass(6, 10) == null);
    }

    @Test
    public void test_setClass_average_time() {
        Schedule s = new Schedule();
        s.setClass(3, 12, new StudySpot("spot"));

        assertTrue("Class should be added at time 12:00 Wednesday", "spot".equals(s.getClass(3, 12).getName()));
    }

    @Test
    public void test_setClass_null() {
        Schedule s = new Schedule();
        s.setClass(3, 12, new StudySpot("spot"));
        s.setClass(3, 12, null);

        assertTrue("Class should be changed to null", s.getClass(3, 12) == null);
    }

    @Test
    public void test_setClass_privacy_leaks() {
        Schedule s = new Schedule();

        StudySpot spot = new StudySpot("spot");
        s.setClass(3, 12, spot);

        spot.setName("changed");

        assertFalse("Class in schedule should not be changed", "changed".equals(s.getClass(3, 12).getName()));
    }

    @Test
    public void test_getClass_privacy_leaks() {
        Schedule s = new Schedule();

        s.setClass(3, 12, new StudySpot("spot"));
        s.getClass(3, 12).setName("changed");

        assertFalse("Class in schedule should not be changed", "changed".equals(s.getClass(3, 12).getName()));
    }

    // Test getBestSpotsWithSchedule method
    // getBestSpotsWithSchedule method should also be tested manually, as complete automatic testing is impractical with time dependency
    @Test
    public void test_getBestSpotsWithSchedule_empty_Schedule() {
        Schedule s = new Schedule();
        StudySpotList studySpotList = new StudySpotList();

        StudySpot studySpotListBest = studySpotList.getBestStudySpots().get(0);
        StudySpot scheduleBest = s.getBestSpotsWithSchedule(studySpotList).get(0);

        assertTrue("Best class should be the same with an empty schedule", studySpotListBest.getName().equals(scheduleBest.getName()));
    }
}
