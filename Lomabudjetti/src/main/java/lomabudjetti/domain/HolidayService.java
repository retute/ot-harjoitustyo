
package lomabudjetti;

public class HolidayService {
    
    private User user;
    
    public HolidayService() {
        //jotain daojuttuja tänne
    }
    
    public void planHoliday(String destination) {
        Holiday holiday = new Holiday(destination, user);
        
    }
    
    
    
}
