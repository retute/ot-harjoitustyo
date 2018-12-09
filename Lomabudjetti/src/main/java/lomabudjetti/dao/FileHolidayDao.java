package lomabudjetti.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lomabudjetti.domain.Holiday;
import lomabudjetti.domain.User;

public class FileHolidayDao implements HolidayDao {
	
	public List<Holiday> holidays;
	private String file;
	
	public FileHolidayDao(String file, UserDao users) throws Exception {
		this.holidays = new ArrayList<>();
		this.file = file;
		try {
			Scanner readTxt = new Scanner(new File(this.file));
			while (readTxt.hasNextLine()) {
				String[] parts = readTxt.nextLine().split(";");
				String destination = parts[0];
				int budget = Integer.parseInt(parts[1]);
				User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[2])).findFirst().orElse(null);
				Holiday test = new Holiday(destination, budget, user);
				holidays.add(test);
			}
			readTxt.close();
		} catch (Exception e) {
			FileWriter writeTxt = new FileWriter(new File(file));
			writeTxt.close();
		}
	}
	
	private int giveId() {
		return holidays.size();
	}
	
	private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Holiday holiday : holidays) {
                writer.write(holiday.getDestination() + ";" + holiday.getBudget() + ";" + holiday.getUser().getUsername() + "\n");
            }
        }
	}
	
//	public Holiday plan(Holiday holiday) throws Exception {
//		holiday.setId(giveId());
//		holidays.add(holiday);
//		return holiday;
//	}
	
	public List<Holiday> getAll() {
		return holidays;
	}
	
	@Override
	public Holiday findByDestination(String destination) {
		return holidays.stream().filter(h -> h.getDestination().equals(destination)).findFirst().orElse(null);
	}

	@Override
	public Holiday create(Holiday holiday) throws Exception {
		holiday.setId(giveId());
		holidays.add(holiday);
		save();
		return holiday;
	}

	public void setPast(int id) throws Exception {
		for (Holiday holiday : holidays) {
			if(holiday.getId() == id) {
				holiday.setNotComing();
			}
		}
		save();
	}

}
