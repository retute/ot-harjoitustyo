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
		activities.add(activity);
		
		return activity;
	}

	public List<Activity> getAll() {
		return activities;
	}
	
}
