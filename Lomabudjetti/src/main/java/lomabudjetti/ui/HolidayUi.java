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
import javafx.scene.layout.VBox;

public class HolidayUi extends Application {
	
	private HolidayService hs;
	
	private Scene holidayScene; //list of holidays
	private Scene loginScene; //log in page
	private Scene newUserScene;	//create a new user page
	private Scene activityScene;	//holiday's activity list
	private Label begin = new Label();
	
	public Node createHoliday(Holiday holiday) {
		 HBox box = new HBox(10);
		 Label label = new Label(holiday.getDestination());
		 Button btnHoliday = new Button("Delete");
		 btnHoliday.setOnAction(e->{
			 holiday.setNotComing();
		 });
		
		return null;
	}
	
	public Node createActvity(Activity activity) {
		
		return null;
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Holiday Budget");	//login page - toimii
		
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10));
		
		//Add GridPane
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(5);
		gp.setVgap(5);
		
		//implementing nodes to gp layout
		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Button btnLogin = new Button("Login");
		Button btnCreate = new Button("Create Username");
		btnCreate.setOnAction(e->stage.setScene(newUserScene));

		//Add nodes to gp layout
		gp.add(lblUserName, 0, 0);
		gp.add(txtUserName, 1, 0);
		gp.add(btnLogin, 1, 1);
		gp.add(btnCreate,2, 1);
		
		btnLogin.setOnAction(e->{
			String username = txtUserName.getText();
			begin.setText("User: " + username);
			if(hs.login(username)) {
				stage.setScene(holidayScene);
			} else {
//				text.setText("Doesn't exist");
			}
		});
		
		bp.setTop(new Text("Holiday Budget Login"));
		bp.setCenter(gp);
		
		this.loginScene = new Scene(bp);
		
		
		//create new user scene
		BorderPane bpNewU = new BorderPane();
		
		GridPane createUserGP = new GridPane();
		createUserGP.setPadding(new Insets(10));
		createUserGP.setVgap(5);
		createUserGP.setHgap(5);
		
		Label createUser = new Label("Write username, min. 4 letters");
		final TextField usernameInput = new TextField();
		Button btnNew = new Button("Create");
		Button btnBack = new Button("Go Back");
		
		createUserGP.add(createUser, 0, 0);
		createUserGP.add(usernameInput, 0, 1);
		createUserGP.add(btnCreate, 0, 2);
		createUserGP.add(btnBack, 2, 2);
		
		//actions for buttons create and go back
		btnNew.setOnAction(e-> {
//			if (createUser.getText().length() <= 3) {
//				hb.getChildren().add(new Text("Username is too short"));
//			} else if(hs.createUser(username)) {
////				hb.getChildren().
//			}
			
			stage.setScene(holidayScene);
			
		});
		btnBack.setOnAction(e-> stage.setScene(loginScene));

		bpNewU.setTop(new Text("Create New Username"));
		bpNewU.setCenter(createUserGP);
		
		newUserScene = new Scene(bpNewU);
		
		
		//show holidays scene
		
		//create holiday scene
		
		//show actions scene
		
		//create action scene
		
		stage.setScene(newUserScene);
		
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
