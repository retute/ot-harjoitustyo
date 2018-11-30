package lomabudjetti.domain;

public class Activity {
    
    private String name;
    private int price;
    // private int priorityNumber;
    private Holiday holiday;
    private int id;
    
    public Activity(String activity, Holiday holiday) {
    	this.name = activity;
    	this.holiday = holiday;
    }
    
    public Activity(int id, String activity, int price, Holiday holiday) {
        this.id = id;
    	this.name = activity;
        this.price = price;
        this.holiday = holiday;
        
//        if (priority > 0 && priority <= 10) {
//            this.priorityNumber = priority;
//        } else {
//            this.priorityNumber = 0;
//        }
    }    
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public int getPrice() {
    	return this.price;
    }
    
    public void setPrice(int price) {
    	this.price = price;
    }
    
    public Holiday getHoliday() {
    	return this.holiday;
    }
    
//    public int getPriorityNumber() {
//        return this.priorityNumber;
//    }
//    
//    public void setPriorityNumber(int number) {
//    	if (number > 0 && number <= 10) {
//    		this.priorityNumber = number;
//    	} else {
//    		this.priorityNumber = 0;
//    	}
//    }
     
    
}
