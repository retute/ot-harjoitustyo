package lomabudjetti.ui;
import lomabudjetti.domain.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import java.util.*;
import javafx.scene.Scene;

public class HolidayUi extends Application {
	
	private HolidayService hs;
	
	private Scene holidayScene; //list of holidays
	private Scene logInScene; //log in page
	private Scene newUserScene;	//create a new user page
	private Scene activityScene;	//holiday's activity list
	
	public Node createHoliday(Holiday holiday) {
		HBox box = new HBox(10);
		
		return null;
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		VBox login = new VBox(10);
		HBox input = new HBox(10);
		
	}
	
	@Override
	public void stop() {
		System.out.println("Bye!");
	}
	
    public static void main(String[] args) {
    	launch(HolidayUi.class);
    }

}
