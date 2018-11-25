
package lomabudjetti.dao;

import lomabudjetti.domain.*;
import java.util.*;

public interface UserDao {
    User create(User user);
    
    User findByUsername(String username);
    
    List<User> getAll();
}
