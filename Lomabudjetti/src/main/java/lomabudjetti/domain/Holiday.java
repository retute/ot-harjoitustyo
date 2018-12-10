
package lomabudjetti.domain;
import java.util.List;


public class Holiday {

	private String destination;
	private int budget;
	private List<Activity> activities;
	private User user;
	private boolean coming;
	private int id;
	private int activitiesCost;

	public Holiday(String destination, int budget, User user) {
		this.destination = destination;
		this.budget = budget;
		this.user = user;
		this.coming = true;
		this.activitiesCost = 0;
	}

	public Holiday(int id, String destination, User user, int budget, List<Activity> activities) {
		this.destination = destination;
		this.budget = budget;
		this.user = user;
		this.activities = activities;
		this.coming = true;
		this.activitiesCost = 0;
                this.id = id;
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getBudget() {
		return this.budget;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public boolean isComing() {
		return coming;
	}
	
	public void setNotComing() {
		this.coming = false;
	}
	
//	/**
//	 * Metodi laskee valitun loman aktiviteettien hintojen yhteenlasketun summan. 
//	 *
//	 * @return aktiviteettien hintojen summa
//	 */
	public int checkCostOfActivities() {
		for(int i = 0; i < this.activities.size(); i++) {
			this.activitiesCost += this.activities.get(i).getPrice();
		}
		
		return this.activitiesCost;
	}
//	
//	/**
//	 * Metodi tarkastaa, riittääkö loman budjetti lisättävään aktiviteettiin. Jos budjetti ylittyy, niin metodi palauttaa false. Muuten metodi palauttaa true.
//	 * @param   activity Lisättävä aktiviteetti
//	 * 
//	 * @return true/false (tieto riittääkö budjetti)
//	 */
	public boolean moneyForTheActivity(Activity activity) {
		if (budget - activity.getPrice() < 0) {
			return false;
		}
		return true;
	}

//	public void crowBudget(int more) {
//		this.budget += more;
//	}
//
//	public void decreaseBudget(int less) {
//		this.budget -= less;
//	}

}
