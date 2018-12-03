package lomabudjetti.dao;

import lomabudjetti.domain.Activity;
import lomabudjetti.domain.Holiday;
import lomabudjetti.domain.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileActivityDao implements ActivityDao {
	
	public List<Activity> activities;
	private String file;
	
	public FileActivityDao(String file, HolidayDao holidayDao) throws Exception {
		this.activities = new ArrayList<>();
		this.file = file;
		
//        try {
//            Scanner reader = new Scanner(new File(file));
//            while (reader.hasNextLine()) {
//                String[] parts = reader.nextLine().split(";");
//                int id = Integer.parseInt(parts[0]);
//                boolean done = Boolean.parseBoolean(parts[2]);
//                Holiday holiday = holidayDao.getAll().stream().filter(u->u.getUsername().equals(parts[5])).findFirst().orElse(null); 
//                Activity activity = new Activity(id, , done, user);
//                todos.add(todo);
//            }
//        } catch (Exception e) {
//            FileWriter writer = new FileWriter(new File(file));
//            writer.close();
//        }
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
