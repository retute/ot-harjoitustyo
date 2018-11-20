
package lomabudjetti;

public class User {
    
    private String username;
    private int id;
    private String password;
    

    public User() {
        
    }
    
    public User(int id, String username) {
        
    }
    
    public void setUserName(String name) {
        this.username = name;
    }
    
    public String getUsername() {
    	return this.username;
    }
}
