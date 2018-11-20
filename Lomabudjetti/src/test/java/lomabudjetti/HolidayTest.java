package lomabudjetti;

// import lomabudjetti.Holiday;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HolidayTest {

	Holiday holiday;
        
	@Before
	public void setUp() {
		holiday = new Holiday("Krabi", 1000);
	}
	
	@Test
	public void holidayBudgetCorrect() {
		assertEquals(1000, holiday.getBudget());
	}
	
	@Test
	public void holidayDestinationCorrect() {
		assertEquals("Krabi", holiday.getDestination());
	}
}
