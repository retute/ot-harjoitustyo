package lomabudjetti.domain;

public class Activity {
    
    private String name;
    private int price;
    private Holiday holiday;
    
    public Activity(String name, int price, Holiday holiday) {
    	this.name = name;
        this.price = price;
        this.holiday = holiday;

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
       
}
