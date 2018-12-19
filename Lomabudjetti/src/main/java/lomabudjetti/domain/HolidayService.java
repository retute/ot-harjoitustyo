
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
//	private Holiday holiday;

	public HolidayService(UserDao userDao, HolidayDao holidayDao, ActivityDao activityDao) {
		this.userDao = userDao;
		this.holidayDao = holidayDao;
		this.activityDao = activityDao;
	}

	/**
	 * Metodi luo loman ja yrittää tallentaa sen pysyvästi holidayDao-luokkaan. Jos
	 * tallennus ei onnistu, niin metodi palauttaa false. Muuten palauttaa true.
	 * 
	 * @param destination Lomakohde String-muuttujana
	 * @param budget      Lomabudjetti int-muuttujana
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

	/**
	 * Metodi poistaa käyttäjän tallentaman loman listasta ja palauttaa true, jos
	 * poisto onnistuu. Muuten palauttaa false.
	 *
	 * @param holiday Loma, joka halutaan perua
	 * 
	 * @return true/false
	 */
	public boolean cancelHoliday(Holiday holiday) {
		try {
			holidayDao.cancel(holiday);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public List<Holiday> getHolidays() {
		return holidayDao.getAll();
	}
	
	public List<Holiday> getUsersHolidays(User use) {
		use = user;
		List<Holiday> users = new ArrayList<>();
		for (Holiday hol : this.holidayDao.getAll()) {
			if (hol.getUser() == use) {
				users.add(hol);
			}
		}
		return users;		
	}

	public boolean planActivity(Activity activity) {
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
		for (Holiday hol : user.getHolidays()) {
			destinations.add(hol.getDestination());
		}
		return destinations;
	}

	public List<Activity> getActivities() {
		return activityDao.getAll();
	}

	public Holiday findHoliday(String destination) {
		return holidayDao.findByDestination(destination);
	}

	public List<Activity> findHolidayActivities(Holiday hol) {
//		List<Activity> all = new ArrayList<>();
//		all = activityDao.getAll();
//		for (Activity act : all) {
//			if (act.getHoliday() != hol) {
//				all.remove(act);
//			}
//		}
		return activityDao.getAll();
	}

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
