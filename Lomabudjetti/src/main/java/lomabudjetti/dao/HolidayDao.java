package lomabudjetti.dao;
import lomabudjetti.domain.*;
import java.util.*;

public interface HolidayDao {

	Holiday create(Holiday holiday) throws Exception;
	
	List<Holiday> getAll();
	
	void setPast(int id) throws Exception;
}
