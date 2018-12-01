package lomabudjetti.dao;

import lomabudjetti.domain.Activity;

import java.util.ArrayList;
import java.util.List;
public class FileActivityDao implements ActivityDao {
	
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
//	Canceling activity will be possible. Creation will come lates 
//	because giving id should be different when deleting something from the list;
//	public void cancelActivity(Activity activity) {
//		if (activities.contains(activity)) {
//			activities.remove(activity);	
//		}
//	}

	public List<Activity> getAll() {
		return activities;
	}

	@Override
	public Activity create(Activity activity) throws Exception {
		activity.setId(giveId());
		activities.add(activity);
		return activity;
	}
	
}
