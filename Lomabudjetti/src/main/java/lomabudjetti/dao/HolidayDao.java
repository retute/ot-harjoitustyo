
package lomabudjetti.dao;
import java.util.List;

import lomabudjetti.domain.Holiday;

public interface HolidayDao {

	Holiday create(Holiday holiday) throws Exception;
	void cancel(Holiday holiday) throws Exception;
	
	List<Holiday> getAll();

	Holiday findByDestination(String destination);
}
