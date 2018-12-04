package lomabudjetti.ui;

import lomabudjetti.dao.FileActivityDao;
import lomabudjetti.dao.FileHolidayDao;
import lomabudjetti.dao.FileUserDao;
import lomabudjetti.domain.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.geometry.*;

import java.io.FileInputStream;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

public class HolidayUi extends Application {

	private HolidayService hs;

	private Scene holidayScene; 
	private Scene allHolidays; // list of holidays
	private Scene newHolidayScene;
	private Scene loginScene; // log in page
	private Scene newUserScene; // create a new user page
	private Scene activityScene; // holiday's activity list
	private Label begin = new Label();
	
	@Override
	public void init() throws Exception {
        Properties properties = new Properties();
//
        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String holidayFile = properties.getProperty("holidayFile");
        String activityFile = properties.getProperty("activityFile");

        FileUserDao userDao = new FileUserDao(userFile);
        FileHolidayDao holidayDao = new FileHolidayDao(holidayFile, userDao);
        FileActivityDao activityDao = new FileActivityDao(activityFile, holidayDao);
        
        hs = new HolidayService(userDao, holidayDao, activityDao);
	}

//	public Node createHolidayNode(Holiday holiday) {
//		HBox box = new HBox(10);
//		Label label = new Label(holiday.getDestination());
//		Button deleteHoliday = new Button("Delete");
//		Button showHoliday = new Button("Show");
//
//		deleteHoliday.setOnAction(e -> {
//			hs.getHolidays().remove(holiday.getId());
//		});
//		showHoliday.setOnAction(e -> {
//			
//		});
//
//		box.setPadding(new Insets(0, 5, 0, 5));
//		box.getChildren().addAll(label, deleteHoliday);
//		return box;
//	}

	public Node createActvityNode(Activity activity) {

		return null;
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Holiday Budget");

//		LLOGIN SCENE
		Label userMessage = new Label();
		userMessage.setText("");
		BorderPane loginbp = new BorderPane();

		// Add GridPane
		GridPane logingp = new GridPane();
		logingp.setPadding(new Insets(10));
		logingp.setHgap(5);
		logingp.setVgap(5);

		// implementing nodes to gp layout
		Label lblUserName = new Label("Username");
		TextField loginInput = new TextField();
		Button btnLogin = new Button("Login");
		Button btnCreate = new Button("Create Username");
		btnCreate.setOnAction(e -> stage.setScene(newUserScene));

		// Add nodes to gp layout
		logingp.add(lblUserName, 0, 0);
		logingp.add(loginInput, 0, 2);
		logingp.add(btnLogin, 0, 3);
		logingp.add(btnCreate, 1, 3);
		logingp.add(userMessage, 0, 1);

		btnLogin.setOnAction(e -> {
			String username = loginInput.getText();
			if (hs.login(username)) {
				this.hs.login(username);
				stage.setTitle("User: " + username);
				stage.setScene(holidayScene);
				userMessage.setText("");
				loginInput.setText("");
			} else {
				stage.setScene(newUserScene);
				userMessage.setText("Username does not exist, try again or create a new one");
				loginInput.setText("");
			}
		});

		loginbp.setTop(new Text("Holiday Budget Login"));
		loginbp.setCenter(logingp);
		this.loginScene = new Scene(loginbp);

		// CREATE USER SCENE
		BorderPane newUserbp = new BorderPane();

		Label resultMsg = new Label();
		resultMsg.setText("Message");

		GridPane createUserGP = new GridPane();
		createUserGP.setPadding(new Insets(20));
		createUserGP.setVgap(10);
		createUserGP.setHgap(20);

		Label createUser = new Label("Write username, min. 4 letters");
		TextField usernameInput = new TextField();
		Button newUserBtn = new Button("Create");
		Button goBackBtn = new Button("Go Back");

		createUserGP.add(createUser, 0, 1);
		createUserGP.add(usernameInput, 0, 2);
		createUserGP.add(newUserBtn, 0, 3);
		createUserGP.add(goBackBtn, 2, 3);
		createUserGP.add(resultMsg, 0, 0);

		// actions for buttons create and go back
		newUserBtn.setOnAction(e -> {
			String username = usernameInput.getText();
			if (usernameInput.getText().length() <= 3) {
				resultMsg.setText("Username is too short, try again");
				stage.setScene(newUserScene);
			} else if (hs.createUser(username)) {
				userMessage.setText("New user created successfully, now login!");
				stage.setScene(loginScene);
			} else {
				resultMsg.setText("Username isn't available, try again.");
			}
		});
		goBackBtn.setOnAction(e -> stage.setScene(loginScene));

		newUserbp.setTop(new Text("Create New Username"));
		newUserbp.setCenter(createUserGP);

		newUserScene = new Scene(newUserbp);

		//SHOW HOLIDAYS SCENE
		BorderPane allHolidaybp = new BorderPane();

		// menu buttonst to top of borderpane
		HBox menuHolidayPage = new HBox(10);
		Button logout = new Button("Log out");
		Button createHoliday = new Button("Add new");
		menuHolidayPage.getChildren().addAll(logout, createHoliday);
		allHolidaybp.setTop(menuHolidayPage);
		
		logout.setOnAction(e -> {
			stage.setScene(loginScene);
			userMessage.setText("Logged out successfully!");
			this.hs.logOut();
			userMessage.setText("");
		});
		createHoliday.setOnAction(e-> {
			stage.setScene(newHolidayScene);
		});
		
		GridPane holidaysGP = new GridPane();
		holidaysGP.setPadding(new Insets(10));
		holidaysGP.setVgap(10);
		holidaysGP.setHgap(10);
		
//		ListView holidays = new ListView();
//		allHolidaybp.setCenter(holidays);

		
		
		holidayScene = new Scene(allHolidaybp);
//
		//CREATE HOLIDAY SCENE
//		BorderPane newHolidaybp = new BorderPane();
////
//		Label holiMsg = new Label();
//		resultMsg.setText("Message");
////
//		GridPane createHolidayGP = new GridPane();
//		createHolidayGP.setPadding(new Insets(10));
//		createHolidayGP.setVgap(5);
//		createHolidayGP.setHgap(5);
//		
//		
//
//		Label createHolidaylbl = new Label("Add new holiday");
//		TextField destination = new TextField();
//		TextField budget = new TextField();
//		Button newHolidayBtn = new Button("Create");
//		Button showHolidaysBtn = new Button("Go Back");
//
//		createUserGP.add(createHolidaylbl, 0, 1);
//		createUserGP.add(destination, 0, 2);
//		createUserGP.add(budget, 0, 3);
//		createUserGP.add(newHolidayBtn, 2, 3);
//		createUserGP.add(sho, 0, 0);

		
		// show actions scene

		// create action scene
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
