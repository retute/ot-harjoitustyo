
package lomabudjetti;

import java.util.List;

public class User {
    
    private String username;
    private int id;
    private String password;
    private List<Holiday> holidays;
    
    public User(int id, String username) {
        this.username = username;
        this.id = id;
    }
    
    public void setUserName(String name) {
        this.username = name;
    }
    
    public String getUsername() {
    	return this.username;
    }
}
