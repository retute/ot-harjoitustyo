
package lomabudjetti;

import java.util.ArrayList;
import java.util.List;

public class Holiday {
	
	private String destination;
	private int budget;
	private ArrayList<Activity> activities;
	private User user;
	
	public Holiday(String destination, int budget) {
		this.destination = destination;
		this.budget = budget;
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public int getBudget() {
		return this.budget;
	}
	
	public String toString() {
		return "Destination: " + this.destination + ", Budget: " + this.budget;
	}
	
	public void crowBudget(int more) {
		this.budget += more;
	}
	
	public void decreaseBudget(int less) {
		this.budget -= less;
	}
//	public void deleteActivity(String name) {
//		for (int i = 0; i < activities.size(); i++) {
//			if (activities[i].getName() == name) {
//				}
//			}
//	}
    
}
