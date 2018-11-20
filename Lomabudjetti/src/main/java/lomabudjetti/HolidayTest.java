package lomabudjetti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HolidayTest {

	Holiday holiday;
//	
//	@Before
//	public void setUp() {
//		holiday = new Holiday("Krabi", 1000);
//	}
	
	@Test
	public void holidayBudgetCorrect() {
		holiday = new Holiday("Krabi", 1000);
		assertEquals(1000, holiday.getBudget());
	}
	
	@Test
	public void holidayDestinationCorrect() {
		holiday = new Holiday("Krabi", 1000);
		assertEquals("Krabi", holiday.getDestination());
	}
}
