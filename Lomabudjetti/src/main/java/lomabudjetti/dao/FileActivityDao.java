package lomabudjetti.dao;

import lomabudjetti.domain.Activity;
import lomabudjetti.domain.Holiday;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileActivityDao implements ActivityDao {
	
	public List<Activity> activities;
	private String file;
	
	public FileActivityDao(String file, HolidayDao holidays) throws Exception {
		this.activities = new ArrayList<>();
		this.file = file;
        try {
            Scanner reader = new Scanner(new File(this.file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                String activityName  = parts[0];
                int price = Integer.parseInt(parts[1]);
                Holiday holiday = holidays.getAll().stream().filter(h->h.getDestination().equals(parts[2])).findFirst().orElse(null);
                Activity activity = new Activity(activityName, price, holiday);
                activities.add(activity);
            }
            reader.close();
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
	}
	
	private void save() throws Exception {
		try (FileWriter writer = new FileWriter(new File(file))) {
			for (Activity activity : activities) {
				writer.write(activity.getName() + ";" + ";" + activity.getPrice() + ";" + activity.getHoliday().getDestination() + "\n");
			}
		}
	}
	
	
	public Activity create(Activity activity) throws Exception {
		if (activity.getHoliday().getBudget() -activity.getPrice() >= 0) {
			activities.add(activity);
		}
		save();
		return activity;
	}

	public void deleteActivity(Activity activity) throws Exception {
		if (activities.contains(activity)) {
			activities.remove(activity);	
		}
		save();
	}

	public List<Activity> getAll() {
		return activities;
	}

//	@Override
//	public Activity create(Activity activity) throws Exception {
//		activities.add(activity);
//		return activity;
//	}
	
}
