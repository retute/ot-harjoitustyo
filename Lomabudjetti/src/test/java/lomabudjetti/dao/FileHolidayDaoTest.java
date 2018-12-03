package lomabudjetti.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lomabudjetti.domain.Holiday;
import lomabudjetti.domain.User;

class FileHolidayDaoTest {
	
	HolidayDao dao;
	User user;

	@BeforeEach
	void setUp() throws Exception {
		user = new User("Santa");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void giveIdWorks() {
		Holiday hol = new Holiday("Paris", user);
//		dao.create(hol);
		assertEquals(hol.getId(), dao.getAll().size());
	}
	
}
