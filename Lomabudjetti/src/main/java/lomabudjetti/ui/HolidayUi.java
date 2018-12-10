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
	private Stage stage;
	private Label userMsg;
	private Label loginPageMsg;
//	private Button logout;

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
			hs.cancelHoliday(hol);
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

	public Scene setLoginScene() {
		this.loginPageMsg = new Label("");
		BorderPane loginbp = new BorderPane();
		this.loginScene = new Scene(loginbp);

		// Add GridPane
		GridPane logingp = new GridPane();
		logingp.setPadding(new Insets(10));
		logingp.setHgap(5);
		logingp.setVgap(5);

		// implementing nodes to gp layout
		Label lblUserName = new Label("Username");
		TextField loginInput = new TextField();
		Button btnLogin = new Button("Login");
		Button btnCreate = new Button("Sign in");
		btnCreate.setOnAction(e -> stage.setScene(setSigninScene()));

		// Add nodes to gp layout
		logingp.add(lblUserName, 0, 0);
		logingp.add(loginInput, 0, 2);
		logingp.add(btnLogin, 0, 4);
		logingp.add(btnCreate, 1, 4);
		logingp.add(loginPageMsg, 0, 1);

		btnLogin.setOnAction(e -> {
			String username = loginInput.getText();
			if (hs.login(username)) {
				this.hs.login(username);
				stage.setTitle("User: " + username);
				stage.setScene(setHolidaylistScene());
				loginPageMsg.setText("");
				loginInput.setText("");
			} else {
				loginPageMsg.setTextFill(Color.RED);
				loginPageMsg.setText("Something went wrong, try again!");
				loginInput.setText("");
			}
		});

		loginbp.setTop(new Text("Holiday Budget Login"));
		loginbp.setCenter(logingp);

		return this.loginScene;
	}

	public Scene setSigninScene() {
		this.userMsg = new Label();
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
				stage.setScene(setSigninScene());
			} else if (hs.createUser(username)) {
				userMsg.setText("New user created successfully, now login!");
				stage.setScene(setLoginScene());
			} else {
				resultMsg.setText("Username isn't available, try again.");
			}
		});
		goBackBtn.setOnAction(e -> stage.setScene(loginScene));

		newUserbp.setTop(new Text("Create New Username"));
		newUserbp.setCenter(createUserGP);

		newUserScene = new Scene(newUserbp);

		return this.newUserScene;
	}

	// HOLIDAY LIST SCENE
	public Scene setHolidaylistScene() {
		ScrollPane holidayScroll = new ScrollPane();
		BorderPane allHolidaybp = new BorderPane(holidayScroll);
		this.allHolidays = new Scene(allHolidaybp);
		Label findResultMsg = new Label();
		userMsg = new Label();

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
				this.holiday = hs.findHoliday(text);
				try {
					stage.setScene(setActivityScene());
				} catch (NullPointerException ex) {
					findResultMsg.setTextFill(Color.RED);
					findResultMsg.setText("Couldn't open the holiday.");
				}
			} else {
				findResultMsg.setTextFill(Color.RED);
				findResultMsg.setText("Can't open the holiday, please try again.");
			}
		});

		logoutBtn.setOnAction(e -> {
			stage.setScene(setLoginScene());
			this.loginPageMsg.setTextFill(Color.GREEN);
			this.loginPageMsg.setText("Logged out successfully!");
			this.hs.logOut();
		});

		createHoliday.setOnAction(e -> {
			userMsg.setText("");
			stage.setScene(setNewholidayScene());
		});

		holidayNodes = new VBox(10);
		holidayNodes.setMaxHeight(300);
		holidayNodes.setMaxWidth(300);
		getHolidaysAsList();

		holidayScroll.setContent(holidayNodes);
		allHolidaybp.setCenter(holidayScroll);

		return this.allHolidays;
	}

	// NEW HOLIDAY SCENE
	public Scene setNewholidayScene() {
		BorderPane newHolidaybp = new BorderPane();
		newHolidayScene = new Scene(newHolidaybp, 200, 250);
		// menubox for "logout" and "go back" buttons
		HBox menuBox = new HBox();
		menuBox.setSpacing(10);
		Button backHolidays = new Button("Back to Holidays");
		Button logoutBtn = new Button("Logout");
		backHolidays.setOnAction(e -> stage.setScene(setHolidaylistScene()));
		menuBox.setSpacing(10);
		menuBox.getChildren().addAll(backHolidays, logoutBtn);

		newHolidaybp.setTop(menuBox);

//		reMsg.setText("Message");

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

		createHolidayBtn.setOnAction(e -> {
			if (destination.getText().isEmpty() || budget.getText().isEmpty()) {
				userMsg.setTextFill(Color.RED);
				userMsg.setText("Give the destination and the budget.");
				destination.setText("");
				budget.setText("");
			} else {
				try {
					int number = Integer.parseInt(budget.getText());
					hs.planHoliday(destination.getText(), number);
					userMsg.setTextFill(Color.GREEN);
					userMsg.setText("Holiday plan added to the list.");
					destination.setText("");
					budget.setText("");
					getHolidaysAsList();
				} catch (NumberFormatException ex) {
					budget.setText("");
					userMsg.setText("Give the budget as numbers.");
				}
			}
		});

		logoutBtn.setOnAction(e -> {
			stage.setScene(setLoginScene());
			this.loginPageMsg.setTextFill(Color.GREEN);
			this.loginPageMsg.setText("Logged out successfully!");
			this.hs.logOut();
		});

		createHolidayGP.add(createHolidaylbl, 0, 1);
		createHolidayGP.add(destinationlbl, 0, 2);
		createHolidayGP.add(destination, 1, 2);
		createHolidayGP.add(budgetlbl, 0, 3);
		createHolidayGP.add(budget, 1, 3);
		createHolidayGP.add(createHolidayBtn, 0, 4);
		createHolidayGP.add(userMsg, 0, 0);

		newHolidaybp.setCenter(createHolidayGP);

		return this.newHolidayScene;
	}

	public Scene setActivityScene() {
		this.stage.setTitle(hs.getLoggedUser().getUsername() + " goes to " + this.holiday.getDestination());
		BorderPane activitiesbp = new BorderPane();
		this.activityScene = new Scene(activitiesbp);

		return this.activityScene;
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("Holiday Budget");
		stage.setHeight(250);
		stage.setWidth(500);

		this.stage.setScene(setLoginScene());

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
