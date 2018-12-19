/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lomabudjetti.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivityTest {

	Activity activity;
	Holiday holiday;
	User user;

	@Before
    public void setUp() {
		user = new User("Mike");
    	holiday = new Holiday("Paris", 500, user);
        activity = new Activity("Sleep", 20, holiday);
    }

	@Test
	public void setPriceGivesNewPriceForActivity() {
		activity.setPrice(30);
		assertEquals(30, activity.getPrice());
	}
	
	@Test
	public void getHolidayWorks() {
		assertEquals(this.holiday, activity.getHoliday());
	}

	@Test
	public void getPriceWorks() {
		assertEquals(this.activity.getPrice(), 20);
	}
}
