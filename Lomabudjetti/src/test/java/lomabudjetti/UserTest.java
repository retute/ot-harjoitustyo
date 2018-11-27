package lomabudjetti;
import lomabudjetti.domain.*;
import java.util.*;

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
    public void getUsernameWorksCorrect() {
    	assertEquals(user.getUsername(), "Alex");
    }
    
    @Test
    public void setUsernameWorksCorrect() {
    	user.setUserName("Max");
    	assertEquals(user.getUsername(), "Max");
    }
    
    @Test
    public void setHolidaysWorksCorrect() {
    	Holiday hol = new Holiday("Paris", user);
    	Holiday holi = new Holiday("Krabi", user);
    	List<Holiday> list = new ArrayList<>();
    	list.add(hol);
    	list.add(holi);
    	user.setList(list);
    	
    	assertEquals(user.getHolidays(), list);
    }
}
