package lomabudjetti;

public class Activity {
    
    private String name;
    private int price;
    private int priorityNumber;
    
    public void Activity(String name, int price, int priority) {
        this.name = name;
        this.price = price;
        this.priorityNumber = priority;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setPrice(int price) {
    	this.price = price;
    }
    
    public void setPriorityNumber(int number) {
    	if (number > 0 && number <= 10) {
    		this.priorityNumber = number;
    	} else {
    		this.priorityNumber = 0;
    	}
    }
     
    public String getName() {
    	return this.name;
    }
    
    public int getPrice() {
    	return this.price;
    }
     
    
}
