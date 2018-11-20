
package lomabudjetti;

import java.util.ArrayList;
import java.util.List;

public class Holiday {
	
	private String destination;
	private int budget;
	private List<Activity> activities;
	private User user;
	
	public Holiday(String destination, User user) {
            this.destination = destination;
            this.user = user;
        }
        
        public Holiday(String destination, int budget) {
            this.destination = destination;
            this.budget = budget;
        }
        
        public Holiday(String destination, User user, int budget, List<Activity> activities) {
		this.destination = destination;
		this.budget = budget;
                this.user = user;
                this.activities = activities;
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
	
	public String toString() {
		return "Destination: " + this.destination + ", Budget: " + this.budget;
	}
	
	public void crowBudget(int more) {
		this.budget += more;
	}
	
	public void decreaseBudget(int less) {
		this.budget -= less;
	}
    
}
