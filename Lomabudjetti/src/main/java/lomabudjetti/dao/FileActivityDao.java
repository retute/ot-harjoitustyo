package lomabudjetti.dao;

import lomabudjetti.domain.Activity;

import java.util.ArrayList;
import java.util.List;
public class FileActivityDao {
	
	List<Activity> activities;
	
	public FileActivityDao() throws Exception {
		this.activities = new ArrayList<>();
	}
	
	private int giveId() {
		return this.activities.size();
	}
	
	public Activity plan(Activity activity) {
		activity.setId(giveId());
		
		if (activity.getHoliday().getBudget() -activity.getPrice() >= 0) {
			activities.add(activity);
		}
		return activity;
	}
	
	public void cancelActivity(Activity activity) {
		if (activities.contains(activity)) {
			activities.remove(activity);	
		}
	}

	public List<Activity> getAll() {
		return activities;
	}
	
}
