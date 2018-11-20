
package lomabudjetti;

public class User {
    
    private String username;
    private int id;
    private String password;
    
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
