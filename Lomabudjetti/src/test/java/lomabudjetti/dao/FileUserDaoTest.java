package lomabudjetti.dao;


import java.io.File;
import lomabudjetti.domain.User;

import org.junit.Before;
import org.junit.Test;

public class FileUserDaoTest {
	
	File userFile;
	UserDao dao;
    User user;

	@Before
	public void setUp() throws Exception {
            user = new User("moihei");
            dao.create(user);
	}

//        @Test
//        public void saveMethodWorks() {
//            
//        }
//        
//        @Test
//        public void findByUserNameWorks() {
//            assertEquals(null, dao.findByUsername("moikka"));
//        }
	
//	@Test
//	public void userNotExistIsFound() {
//		User test = dao.findByUsername("test");
//		assertEquals(null, test);
//	}

}
