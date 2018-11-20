
package lomabudjetti;

import java.util.List;

public class Holiday {
	
	private String destination;
	private int budget;
	private List<Activity> activities;
	private User user;
	
	public void holiday(String destination, int budget) {
		this.destination = destination;
		this.budget = budget;
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
    
}
