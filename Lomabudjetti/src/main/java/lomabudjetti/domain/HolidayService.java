
package lomabudjetti.domain;

import java.util.ArrayList;
import java.util.List;

import lomabudjetti.dao.ActivityDao;
import lomabudjetti.dao.HolidayDao;
import lomabudjetti.dao.UserDao;

public class HolidayService {

	private UserDao userDao;
	private ActivityDao activityDao;
	private HolidayDao holidayDao;
	private User user;
	private Holiday holiday;

	public HolidayService(UserDao userDao, HolidayDao holidayDao, ActivityDao activityDao) {
		this.userDao = userDao;
		this.holidayDao = holidayDao;
		this.activityDao = activityDao;
	}

	// add new holiday for the user logged in
	public boolean planHoliday(String destination, int budget) {
		Holiday holiday = new Holiday(destination, budget, user);
		try {
			holidayDao.create(holiday);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	// get user's all holidays
	public List<Holiday> getHolidays() {
		return holidayDao.getAll();
	}

	// creates activity, if the holiday budget is enough
	public boolean planActivity(String name) {
		Activity activity = new Activity(name, holiday);
		try {
			activityDao.create(activity);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	public List<String> getDestinations() {
		List<String> destinations = new ArrayList<>();
		for (int i = 0; i < this.holidayDao.getAll().size(); i++) {
			destinations.add(user.getHoliday(i));
		}
		return destinations;
	}

	// get holiday's all activities
	public List<Activity> getActivities() {
		return activityDao.getAll();
	}

	public Holiday findHoliday(String destination) {
		return holidayDao.findByDestination(destination);
	}

	// creates user if the username doesn't exist already
	public boolean createUser(String username) {
		if (userDao.findByUsername(username) == null) {
			User user = new User(username);
			try {
				userDao.create(user);
			} catch (Exception exception) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	// login: if username does exist then true and loging, other way false
	public boolean login(String username) {
		User check = userDao.findByUsername(username);
		if (check != null) {
			user = check;
			return true;
		}
		return false;
	}

	// returns user that is logged in
	public User getLoggedUser() {
		return user;
	}

	public void logOut() {
		user = null;
	}

}
