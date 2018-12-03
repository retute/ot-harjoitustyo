package lomabudjetti.dao;

import java.util.ArrayList;
import java.util.List;
import lomabudjetti.domain.Holiday;

public class FileHolidayDao implements HolidayDao {
	
	public List<Holiday> holidays;
	private String file;
	
	public FileHolidayDao(String file, UserDao user) throws Exception {
		this.holidays = new ArrayList<>();
		this.file = file;
	}
	
	private int giveId() {
		return holidays.size();
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
	public Holiday create(Holiday holiday) throws Exception {
		holiday.setId(giveId());
		holidays.add(holiday);
		return holiday;
	}

//	@Override
//	public void setPast(int id) throws Exception {
//		holidays.remove(id);
//	}

}
