package lomabudjetti.domain;

import lomabudjetti.dao.HolidayDao;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HolidayTest {

    Holiday holiday;
    User user;
    HolidayDao hdao;

    @Before
    public void setUp() {
        user = new User("Mike");
        holiday = new Holiday("Krabi", 1000, user);
    }

    @Test
    public void holidayBudgetIsCorrect() {
        holiday.setBudget(1000);
        assertEquals(1000, holiday.getBudget());
    }

    @Test
    public void holidayDestinationCorrect() {
        assertEquals("Krabi", holiday.getDestination());
    }

    @Test
    public void holidayComingTrueWhenTrue() {
        assertTrue(holiday.isComing());
    }

    @Test
    public void holidaySetActivitiesAreCorrect() {
        Activity ac = new Activity("JetSki", 50, holiday);
        Activity act = new Activity("Snorkling", 80, holiday);
        List<Activity> list = new ArrayList<>();

        list.add(ac);
        list.add(act);

        holiday.setActivities(list);

        assertEquals(list, holiday.getActivities());
    }
//    
//    @Test
//    public void checkCostOfActivitiesGivesRightPrice() {
//    	assertEquals(holiday.checkCostOfActivities(), 130);
//    }

//
//    @Test
//    public void setNotComingWorks() {
//
//    }
//
//    @Test
//    public void addActivityWorks() {
//
//    }
}
