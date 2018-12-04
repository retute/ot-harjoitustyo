package lomabudjetti.dao;


import lomabudjetti.domain.Holiday;
import lomabudjetti.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FileHolidayDaoTest {
	
	HolidayDao dao;
	User user;

	@Before
	public void setUp() throws Exception {
		user = new User("Santa");
	}

//	@Test
//	public void giveIdWorks() throws Exception {
//		Holiday hol = new Holiday("Paris", user);
//		dao.create(hol);
//		assertEquals(hol.getId(), dao.getAll().size());
//	}
	
}
