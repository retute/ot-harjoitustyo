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
import javafx.scene.paint.Color;

public class HolidayUi extends Application {

	private HolidayService hs;

	private Scene holidayScene;
	private Scene allHolidays; // list of holidays
	private Scene newHolidayScene;
	private Scene loginScene; // log in page
	private Scene newUserScene; // create a new user page
	private Scene activityScene; // holiday's activity list
	private VBox holidayNodes;
	private Holiday holiday;

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

	public Node planHolidayNode(Holiday hol) {
		HBox holiBox = new HBox(10);
		Label lbl = new Label(hol.getDestination());
		lbl.setMinHeight(30);
		Button btn = new Button("Cancel");
		btn.setOnAction(e -> {
			hs.getHolidays().remove(hol);
			getHolidaysAsList();
		});

		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		holiBox.setPadding(new Insets(0, 5, 0, 5));

		holiBox.getChildren().addAll(lbl, reg, btn);
		return holiBox;
	}

	public void getHolidaysAsList() {
		holidayNodes.getChildren().clear();
		List<Holiday> holidays = hs.getHolidays();
		holidays.forEach(holiday -> {
			holidayNodes.getChildren().add(planHolidayNode(holiday));
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Holiday Budget");
		stage.setHeight(250);
		stage.setWidth(500);

//		LOGIN SCENE
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
		logingp.add(btnLogin, 0, 4);
		logingp.add(btnCreate, 1, 4);
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
				userMessage.setTextFill(Color.RED);
				userMessage.setText("Something went wrong, try again!");
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
		Button goBackBtn = new Button("Login page");

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

		// SHOW HOLIDAYS SCENE
		ScrollPane holidayScroll = new ScrollPane();
		BorderPane allHolidaybp = new BorderPane(holidayScroll);
		holidayScene = new Scene(allHolidaybp);
		Label findResultMsg = new Label();
		Label holiMsg = new Label();

		// menu buttonst to top of borderpane
		HBox menuHolidayPage = new HBox(10);
		Region reg = new Region();
		Button logoutBtn = new Button("Log out");
		Button createHoliday = new Button("Add new");
		menuHolidayPage.getChildren().addAll(logoutBtn, reg, createHoliday);
		allHolidaybp.setTop(menuHolidayPage);

		HBox showHolidayMenu = new HBox(10);
		TextField openHoliday = new TextField();
		Button tryOpen = new Button("Open");
		showHolidayMenu.getChildren().addAll(openHoliday, tryOpen);
		allHolidaybp.setBottom(showHolidayMenu);
		

		tryOpen.setOnAction(e -> {
			String text = openHoliday.getText();
			if (hs.findHoliday(text) != null) {
				stage.setScene(activityScene);
				this.holiday = hs.findHoliday(text);
			} else {
				findResultMsg.setTextFill(Color.RED);
				findResultMsg.setText("Can't open the holiday, please try again.");
			}
		});

		logoutBtn.setOnAction(e -> {
			stage.setScene(loginScene);
			userMessage.setTextFill(Color.GREEN);
			userMessage.setText("Logged out successfully!");
			this.hs.logOut();
		});

		createHoliday.setOnAction(e -> {
			holiMsg.setText("");
			stage.setScene(newHolidayScene);
		});

		holidayNodes = new VBox(10);
		holidayNodes.setMaxHeight(300);
		holidayNodes.setMaxWidth(300);
		getHolidaysAsList();

		holidayScroll.setContent(holidayNodes);

		allHolidaybp.setCenter(holidayScroll);

//		CREATE HOLIDAY SCENE
		BorderPane newHolidaybp = new BorderPane();
		newHolidayScene = new Scene(newHolidaybp, 200, 250);
		// menubox for "logout" and "go back" buttons
		HBox menuBox = new HBox();
		menuBox.setSpacing(10);
		Button backHolidays = new Button("Back to Holidays");
		backHolidays.setOnAction(e -> stage.setScene(holidayScene));
		menuBox.setSpacing(10);
		menuBox.getChildren().addAll(backHolidays, logoutBtn);

		newHolidaybp.setTop(menuBox);

		resultMsg.setText("Message");

		GridPane createHolidayGP = new GridPane();
		createHolidayGP.setPadding(new Insets(10));
		createHolidayGP.setVgap(5);
		createHolidayGP.setHgap(5);

		Label createHolidaylbl = new Label("Add new holiday");
		Label destinationlbl = new Label("Destination");
		TextField destination = new TextField();
		Label budgetlbl = new Label("Budget (only numbers)");
		TextField budget = new TextField();
		Button createHolidayBtn = new Button("Create");
		Button showHolidaysBtn = new Button("Go Back");

		createHolidayBtn.setOnAction(e -> {
			if (destination.getText().isEmpty() || budget.getText().isEmpty()) {
				holiMsg.setTextFill(Color.RED);
				holiMsg.setText("Give the destination and the budget.");
				destination.setText("");
				budget.setText("");
			} else {
				try {
					int number = Integer.parseInt(budget.getText());
					hs.planHoliday(destination.getText(), number);
					holiMsg.setTextFill(Color.GREEN);
					holiMsg.setText("Holiday plan added to the list.");
					destination.setText("");
					budget.setText("");
					getHolidaysAsList();
				} catch (NumberFormatException ex) {
					budget.setText("");
					holiMsg.setText("Give the budget as numbers.");
				}
			}
		});
		showHolidaysBtn.setOnAction(e -> stage.setScene(allHolidays));

		createHolidayGP.add(createHolidaylbl, 0, 1);
		createHolidayGP.add(destinationlbl, 0, 2);
		createHolidayGP.add(destination, 1, 2);
		createHolidayGP.add(budgetlbl, 0, 3);
		createHolidayGP.add(budget, 1, 3);
		createHolidayGP.add(createHolidayBtn, 0, 4);
		createHolidayGP.add(showHolidaysBtn, 2, 4);
		createHolidayGP.add(holiMsg, 0, 0);

		newHolidaybp.setCenter(createHolidayGP);

		// show holiday scene

		// create action scene
//		
		stage.setScene(loginScene);

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
