package lomabudjetti.dao;

import lomabudjetti.domain.*;
import java.util.*;

public interface ActivityDao {
	Activity create(Activity activity) throws Exception;
	
	List<Activity> getAll();
}
