
package lomabudjetti.domain.*;
import lomabudjetti.dao.*;

public class HolidayService {
    
	private UserDao userDao;
	private ActivityDao activityDao;
	private HolidayDao holidayDao;
	private User user;
	private Holiday holiday;
    
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
    
    public boolean createActivity(String name) {
    	Activity activity = new Activity(name, holiday);
    	try {
    		activityDao.create(activity);
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
    
    public User getLoggedinUser() {
    	return user;
    }
    
    public void logOut() {
    	user = null;
    }
    
}
