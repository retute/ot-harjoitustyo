/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomabudjetti.domain;

import lomabudjetti.domain.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivityTest {

	Activity activity;
	Holiday holiday;
	User user;

	public ActivityTest() {
	}

	@Before
    public void setUp() {
		user = new User("Mike");
    	holiday = new Holiday("Paris", user);
        activity = new Activity("Sleep", holiday);
    }

//	@Test
//	public void priorityNumberIsCorrect() {
//		assertEquals(7, activity.getPriorityNumber());
//	}
//
//	@Test
//	public void priorityNumberIsCorrectWhenTryToSetItHigherThanTen() {
//		activity.setPriorityNumber(392);
//		assertEquals(0, activity.getPriorityNumber());
//	}
	
	@Test
	public void setPriceGivesNewPriceForActivity() {
		activity.setPrice(30);
		assertEquals(30, activity.getPrice());
	}
	
	@Test
	public void getHolidayWorks() {
		assertEquals(this.holiday, activity.getHoliday());
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	// @Test
	// public void hello() {}
}
