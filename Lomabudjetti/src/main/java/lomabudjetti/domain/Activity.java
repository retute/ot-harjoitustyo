package lomabudjetti.domain;

public class Activity {
    
    private String name;
    private int price;
    private Holiday holiday;
    
    public Activity(String activity, Holiday holiday) {
    	this.name = activity;
    	this.holiday = holiday;
    }
    
    public Activity(String activity, int price, Holiday holiday) {
    	this.name = activity;
        this.price = price;
        this.holiday = holiday;

    }    
    
//    public void setId(int id) {
//    	this.id = id;
//    }
//    
//    public int getId() {
//    	return this.id;
//    }
    
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
       
}
