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
	
	public Node createHolidayNode(Holiday holiday) {
		 HBox box = new HBox(10);
		 Label label = new Label(holiday.getDestination());
		 Button deleteHoliday = new Button("Delete");
		 Button showHoliday = new Button("Show");
		 
		 deleteHoliday.setOnAction(e->{
			 hs.getHolidays().remove(holiday.getId());
		 });
		 showHoliday.setOnAction(e->{
			 holiday.getActivities();
		 });
		
		 box.setPadding(new Insets(0,5,0,5));
		 box.getChildren().addAll(label, deleteHoliday);
		return box;
	}
	
	public Node createActvityNode(Activity activity) {
		
		return null;
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Holiday Budget");
		
//		Login scene		
		Label userMessage = new Label();
		userMessage.setText("message will be here");
		BorderPane bp = new BorderPane();
		
		
		//Add GridPane
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(5);
		gp.setVgap(5);
		
		//implementing nodes to gp layout
		Label lblUserName = new Label("Username");
		TextField loginInput = new TextField();
		Button btnLogin = new Button("Login");
		Button btnCreate = new Button("Create Username");
		btnCreate.setOnAction(e->stage.setScene(newUserScene));

		//Add nodes to gp layout
		gp.add(lblUserName, 0, 0);
		gp.add(loginInput, 0, 2);
		gp.add(btnLogin, 0, 3);
		gp.add(btnCreate,1, 3);
		
		gp.add(userMessage, 0, 1);
		btnLogin.setOnAction(e->{
			String username = loginInput.getText();
			if(hs.login(username)) {
				stage.setTitle("User: " + username);
				stage.setScene(holidayScene);
//				this.hs.login(username);
			} else {
				stage.setScene(newUserScene);
//				userMessage.setText("Username does not exist, try again or create a new one");
			}
		});
		
		bp.setTop(new Text("Holiday Budget Login"));
		bp.setCenter(gp);
		
		this.loginScene = new Scene(bp);
		
		
		//create new user scene
		BorderPane bpNewU = new BorderPane();
		
		Label loginMsg = new Label();
		loginMsg.setText("Message");
		
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
		createUserGP.add(btnNew, 0, 2);
		createUserGP.add(btnBack, 2, 2);
		createUserGP.add(loginMsg, 2, 3);
		
		//actions for buttons create and go back
		btnNew.setOnAction(e-> {
			if (usernameInput.getText().length() <= 3) {
				loginMsg.setText("Username is too short, try again");
				stage.setScene(newUserScene);
			} else if(hs.createUser(usernameInput.getText())) {
				stage.setScene(holidayScene);
			}
			
			stage.setScene(holidayScene);
			
		});
		btnBack.setOnAction(e-> stage.setScene(loginScene));

		bpNewU.setTop(new Text("Create New Username"));
		bpNewU.setCenter(createUserGP);
		
		newUserScene = new Scene(bpNewU);
		
		//show holidays scene
		BorderPane bpHoliday = new BorderPane();
		
		//menu buttonst to top of borderpane
		HBox menuHolidayPage = new HBox(10);
		Button logout = new Button("Log out");
		//text input to create new holiday and button for that
		Button createHoliday = new Button("Add new");
		menuHolidayPage.getChildren().addAll(logout, createHoliday);
		bpHoliday.setTop(menuHolidayPage);
		logout.setOnAction(e->{
			stage.setScene(loginScene);
			this.hs.logOut();
		});
		GridPane holidaysGP = new GridPane();
		holidaysGP.setPadding(new Insets(10));
		
		ListView holidays = new ListView();
		
		
		

		
		//create holiday scene
		
		//show actions scene
		
		//create action scene
//		
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
