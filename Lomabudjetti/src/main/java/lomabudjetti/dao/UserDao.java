
package lomabudjetti.dao;

import lomabudjetti.domain.User;
import java.util.*;

public interface UserDao {
    User create(User user) throws Exception;
    
    User findByUsername(String username);
    
    List<User> getAll();
}
