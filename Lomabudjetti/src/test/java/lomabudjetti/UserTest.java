package lomabudjetti;
import lomabudjetti.domain.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User user;

    @Before
    public void setUp() {
    	user = new User("Alex");
    }

    @Test
    public void getUsernameGivesGivesTheCorrecctName() {
    	assertEquals("Alex", user.getUsername());
    }
}
