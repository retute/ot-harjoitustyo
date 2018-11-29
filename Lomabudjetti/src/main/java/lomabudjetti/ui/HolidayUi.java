package lomabudjetti.ui;
import lomabudjetti.domain.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class HolidayUi extends Application {
	
	private HolidayService hs;
	
	private Scene holidayScene; //list of holidays
	private Scene loginScene; //log in page
	private Scene newUserScene;	//create a new user page
	private Scene activityScene;	//holiday's activity list
	private Label begin = new Label();
	
	public Node createHoliday(Holiday holiday) {
		// HBox box = new HBox(10);
		
		return null;
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Holiday Budget Login");	//login page - toimii
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10,50,50,50));
		
		//Add HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(10));
		
		//Add GridPane
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(20,20,20,20));
		gp.setHgap(5);
		gp.setVgap(5);
		
		//implementing nodes to gp layout
		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Button btnLogin = new Button("Login");
		final Label loginInput = new Label();
		Button btnCreate = new Button("Create");
		
		//Add nodes to gp layout
		gp.add(lblUserName, 0, 0);
		gp.add(txtUserName, 1, 0);
		gp.add(btnLogin, 1, 1);
		gp.add(loginInput, 1, 2);
		gp.add(btnCreate,2, 1);
		
		Text text = new Text("Holiday Budget Login");
		hb.getChildren().add(text);
		
		bp.setId("bp");
		gp.setId("root");
		btnLogin.setId("btnLogin");
		text.setId("text");
		
		btnLogin.setOnAction(e->{
			String username = loginInput.getText();
			begin.setText("User: " + username);
			if(hs.login(username)) {
				
			} else {
				
			}
		});
		
		bp.setTop(hb);
		bp.setCenter(gp);
		
		Scene scene = new Scene(bp);
		this.loginScene = scene;
		stage.setScene(scene);
		
		stage.show();
		
	}
	
	@Override
	public void stop() {
		System.out.println("Bye!");
	}
	
    public static void main(String[] args) {
    	launch(HolidayUi.class);
    }

}
