package lomabudjetti.ui;
import lomabudjetti.domain.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;

public class HolidayUi extends Application {
	
	private HolidayService hs;

	@Override
	public void start(Stage window) throws Exception {
		Button logIn = new Button("Log In");
		Button createAccount = new Button("Create Account");
		Label text = new Label("Lomabudjetti");
		
		BorderPane components = new BorderPane();
		components.setTop(text);
		components.setLeft(logIn);
		components.setRight(createAccount);	
		
		Scene view = new Scene(components);
		
		window.setScene(view);
		window.show();
		
	}
	
    public static void main(String[] args) {
    	launch(HolidayUi.class);
    }

}
