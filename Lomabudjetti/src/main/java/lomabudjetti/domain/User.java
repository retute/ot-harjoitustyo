
package lomabudjetti.domain;

import java.util.List;

public class User {
    
    private String username;
    private String password;
    private List<Holiday> holidays;
    
    public User(String username) {
        this.username = username;
    }
    
    public void setUserName(String name) {
        this.username = name;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public void setList(List<Holiday> list) {
    	this.holidays = list;
    }
    
    public List<Holiday> getHolidays() {
    	return this.holidays;
    }
    
}
