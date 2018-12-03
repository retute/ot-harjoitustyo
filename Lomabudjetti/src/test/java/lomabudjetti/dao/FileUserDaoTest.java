package lomabudjetti.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lomabudjetti.domain.User;

class FileUserDaoTest {
	
	File userFile;
	UserDao dao;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
//	@Test
//	public void userNotExistIsFound() {
//		User test = dao.findByUsername("test");
//		assertEquals(null, test);
//	}

}
