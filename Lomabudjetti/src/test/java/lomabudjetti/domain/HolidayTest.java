package lomabudjetti.domain;

import lomabudjetti.dao.HolidayDao;
import lomabudjetti.domain.*;
import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
//        hdao = new HolidayDao();
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
        Activity ac = new Activity("JetSki", holiday);
        Activity act = new Activity("Snorkling", holiday);
        List<Activity> list = new ArrayList<>();

        list.add(ac);
        list.add(act);

        holiday.setActivities(list);

        assertEquals(list, holiday.getActivities());
    }
    
//    @Test
//    public void checkCostOfActivitiesGivesRightPrice() {
//    	
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
