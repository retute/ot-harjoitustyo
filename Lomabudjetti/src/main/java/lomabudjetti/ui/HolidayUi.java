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
	
	private Scene holidayScene;
	private Scene logInScene;
	private Scene newUserScene;
	private Scene activityScene;
	
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
