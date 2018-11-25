package lomabudjetti.domain;

public class Activity {
    
    private String name;
    private int price;
    private int priorityNumber;
    
    public Activity(String name, int price, int priority) {
        this.name = name;
        this.price = price;
        
        if (priority > 0 && priority <= 10) {
            this.priorityNumber = priority;
        } else {
            this.priorityNumber = 0;
        }
    }
    
//    public void setName(String name) {
//    	this.name = name;
//    }
    
//    public void setPriorityNumber(int number) {
//    	if (number > 0 && number <= 10) {
//    		this.priorityNumber = number;
//    	} else {
//    		this.priorityNumber = 0;
//    	}
//    }
     
    public String getName() {
    	return this.name;
    }
    
    public int getPrice() {
    	return this.price;
    }
    
    public int getPriorityNumber() {
        return this.priorityNumber;
    }
     
    
}
