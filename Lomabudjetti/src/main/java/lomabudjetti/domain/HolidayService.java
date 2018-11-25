
package lomabudjetti.domain;
import lomabudjetti.dao.*;

public class HolidayService {
    
	private UserDao userDao;
	private HolidayDao holidayDao;
	private User user;
    
    public HolidayService(UserDao userDao, HolidayDao holidayDao) {
        this.userDao = userDao;
        this.holidayDao = holidayDao;
        
    }
    
    public boolean planHoliday(String destination) {
    	Holiday holiday = new Holiday(destination, user);
    	try {
    		holidayDao.create(holiday);
    	} catch (Exception exception) {
    		return false;
    	}
    	return true;
    }
    
    public boolean createUser(String username) {
    	if (userDao.findByUsername(username) == null) {
    		User user = new User(username);
    		try {
    			userDao.create(user);
    		} catch(Exception exception) {
    			return false;
    		}
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    
}
