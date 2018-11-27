package lomabudjetti.dao;

import lomabudjetti.domain.Activity;
import java.util.*;

public interface ActivityDao {
	Activity create(Activity activity) throws Exception;
	
	List<Activity> getAll();
}
