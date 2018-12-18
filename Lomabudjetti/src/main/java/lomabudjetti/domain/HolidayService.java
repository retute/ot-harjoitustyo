
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

    /**
     *Metodi luo loman ja yrittää tallentaa sen pysyvästi holidayDao-luokkaan.
     *Jos tallennus ei onnistu, niin metodi palauttaa false. Muuten palauttaa true.
     * 
     * @param destination Lomakohde String-muuttujana
     * @param budget Lomabudjetti int-muuttujana
     * 
     * @return true/false
     */
	public boolean planHoliday(String destination, int budget) {
		Holiday holiday = new Holiday(destination, budget, user);
		try {
			holidayDao.create(holiday);
		} catch (Exception exception) {
			return false;
		}
		return true;
	}
	
	public boolean cancelHoliday(Holiday hol) {
		try {
				holidayDao.cancel(hol);
		} catch (Exception ex) {
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
	
	public boolean deleteActivity(Activity act) {
		try {
			activityDao.deleteActivity(act);
		} catch (Exception ex) {
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

	public boolean login(String username) {
		User check = userDao.findByUsername(username);
		if (check != null) {
			user = check;
			return true;
		}
		return false;
	}

	public User getLoggedUser() {
		return user;
	}

	public void logOut() {
		user = null;
	}

}
