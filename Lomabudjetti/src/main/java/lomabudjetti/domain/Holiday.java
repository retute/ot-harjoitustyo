
package lomabudjetti.domain;
import java.util.List;

public class Holiday {

	private String destination;
	private int budget;
	private List<Activity> activities;
	private User user;
	private boolean coming;
	private int id;

	public Holiday(String destination, User user) {
		this.destination = destination;
		this.user = user;
		this.coming = true;
	}

	public Holiday(int id, String destination, User user, int budget, List<Activity> activities, boolean coming) {
		this.destination = destination;
		this.budget = budget;
		this.user = user;
		this.activities = activities;
		this.coming = coming;
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
	
//	public String toString() {
//		return "Destination: " + this.destination + ", Budget: " + this.budget;
//	}

//	public void crowBudget(int more) {
//		this.budget += more;
//	}
//
//	public void decreaseBudget(int less) {
//		this.budget -= less;
//	}

}
