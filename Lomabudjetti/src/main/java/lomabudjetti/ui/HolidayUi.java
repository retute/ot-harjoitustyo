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

	private Scene allHolidays;
	private Scene newHolidayScene;
	private Scene loginScene;
	private Scene newUserScene;
	private Scene activityScene;

	private VBox holidayNodes;
	private VBox activityNodes;
	private Holiday holiday;
	private Stage stage;
	private Label userMsg = new Label();

	@Override
	public void init() throws Exception {
		Properties properties = new Properties();
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
		Label lbl = new Label(hol.getDestination() + ", \n Holiday's budget: " + hol.getBudget() + "€");
		lbl.setMinHeight(30);
		Button btn1 = new Button("Cancel");
		btn1.setOnAction(e -> {
			hs.cancelHoliday(hol);
			getHolidaysAsList();
		});

		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		holiBox.setPadding(new Insets(0, 5, 0, 5));

		holiBox.getChildren().addAll(lbl, reg, btn1);
		return holiBox;
	}

	public void getHolidaysAsList() {
		holidayNodes.getChildren().clear();
		List<Holiday> holidays = hs.getUsersHolidays(hs.getLoggedUser());
		holidays.forEach(holidayh -> {
			holidayNodes.getChildren().add(planHolidayNode(holidayh));
		});
	}

	public Node planActivityNode(Activity act) {
		HBox actiBox = new HBox(10);
		Label lbl = new Label(act.getName() + ", \n" + act.getPrice() + "€");
		lbl.setMinHeight(30);
		Button btn = new Button("Delete");
		btn.setOnAction(e -> {
			act.getHoliday().setBudget(act.getHoliday().getBudget() + act.getPrice());
			hs.deleteActivity(act);
			getActivitiesAsList();
		});
		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		actiBox.setPadding(new Insets(0, 5, 0, 5));

		actiBox.getChildren().addAll(lbl, reg, btn);
		return actiBox;
	}

	public void getActivitiesAsList() {
		activityNodes.getChildren().clear();
		List<Activity> activities = hs.findHolidayActivities(this.holiday);
		activities.forEach(activ -> {
			activityNodes.getChildren().add(planActivityNode(activ));
		});
	}

	public Scene setLoginScene() {
		BorderPane loginbp = new BorderPane();
		this.loginScene = new Scene(loginbp);

		GridPane logingp = new GridPane();
		logingp.setPadding(new Insets(10));
		logingp.setHgap(5);
		logingp.setVgap(5);

		Label lblUserName = new Label("Username");
		TextField loginInput = new TextField();
		Button btnLogin = new Button("Login");
		Button btnCreate = new Button("Sign in");
		btnCreate.setOnAction(e -> {
			userMsg.setTextFill(Color.BLACK);
			userMsg.setText("");
			stage.setScene(setSigninScene());
		});

		logingp.add(lblUserName, 0, 0);
		logingp.add(loginInput, 0, 2);
		logingp.add(btnLogin, 0, 4);
		logingp.add(btnCreate, 1, 4);
		logingp.add(userMsg, 0, 1);

		btnLogin.setOnAction(e -> {
			String username = loginInput.getText();
			if (hs.login(username)) {
				this.hs.login(username);
				stage.setTitle("User: " + username);
				stage.setScene(setHolidaylistScene());
				userMsg.setText("");
				loginInput.setText("");
			} else {
				userMsg.setTextFill(Color.RED);
				userMsg.setText("Something went wrong, try again!");
				loginInput.setText("");
			}
		});

		loginbp.setTop(new Text("Holiday Budget Login"));
		loginbp.setCenter(logingp);

		return this.loginScene;
	}

	public Scene setSigninScene() {
//		this.userMsg = new Label();
		BorderPane newUserbp = new BorderPane();

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
		createUserGP.add(userMsg, 0, 0);

		newUserBtn.setOnAction(e -> {
			String username = usernameInput.getText();
			if (usernameInput.getText().length() <= 3) {
				userMsg.setTextFill(Color.RED);
				userMsg.setText("Username is too short, try again");
				usernameInput.setText("");
			} else if (hs.createUser(username)) {
				userMsg.setTextFill(Color.GREEN);
				userMsg.setText("New user created successfully, now login!");
				stage.setScene(setLoginScene());
				usernameInput.setText("");
			} else {
				userMsg.setTextFill(Color.RED);
				userMsg.setText("Username isn't available, try again.");
				usernameInput.setText("");
			}
		});
		goBackBtn.setOnAction(e -> stage.setScene(loginScene));

		newUserbp.setTop(new Text("Create New Username"));
		newUserbp.setCenter(createUserGP);

		newUserScene = new Scene(newUserbp);

		return this.newUserScene;
	}

	public Scene setHolidaylistScene() {
		ScrollPane holidayScroll = new ScrollPane();
		BorderPane allHolidaybp = new BorderPane(holidayScroll);
		this.allHolidays = new Scene(allHolidaybp);
		userMsg = new Label();

		HBox menuHolidayPage = new HBox(10);
		Region reg = new Region();
		Button logoutBtn = new Button("Log out");
		Button createHoliday = new Button("Add new");
		menuHolidayPage.getChildren().addAll(logoutBtn, reg, createHoliday);
		allHolidaybp.setTop(menuHolidayPage);
		allHolidaybp.setRight(userMsg);

		HBox showHolidayMenu = new HBox(10);
		Label openText = new Label("Write the destination that you want to open: ");
		TextField openHoliday = new TextField();
		Button tryOpen = new Button("Open");

		showHolidayMenu.getChildren().addAll(openText, openHoliday, tryOpen);
		allHolidaybp.setBottom(showHolidayMenu);

		tryOpen.setOnAction(e -> {
			String text = openHoliday.getText();
			if (hs.findHoliday(text) != null) {
				this.holiday = hs.findHoliday(text);
				stage.setScene(setActivityScene());
				userMsg.setTextFill(Color.BLACK);
				userMsg.setText("Add new activity to the list.");
				openHoliday.setText("");
			} else {
				userMsg.setTextFill(Color.RED);
				userMsg.setText("Check that you wrote the name of the holiday right!");
			}
		});

		logoutBtn.setOnAction(e -> {
			stage.setScene(setLoginScene());
			this.userMsg.setTextFill(Color.GREEN);
			this.userMsg.setText("Logged out successfully!");
			this.hs.logOut();
		});

		createHoliday.setOnAction(e -> {
			userMsg.setText("");
			stage.setScene(setNewHolidayScene());
		});

		holidayNodes = new VBox(10);
		holidayNodes.setMaxHeight(300);
		holidayNodes.setMaxWidth(300);
		getHolidaysAsList();

		holidayScroll.setContent(holidayNodes);
		allHolidaybp.setCenter(holidayScroll);

		return this.allHolidays;
	}

	public Scene setNewHolidayScene() {
		BorderPane newHolidaybp = new BorderPane();
		newHolidayScene = new Scene(newHolidaybp, 200, 250);
		HBox menuBox = new HBox();
		menuBox.setSpacing(10);
		Button backHolidays = new Button("Back to Holidays");
		Button logoutBtn = new Button("Logout");
		backHolidays.setOnAction(e -> stage.setScene(setHolidaylistScene()));
		menuBox.setSpacing(10);
		menuBox.getChildren().addAll(backHolidays, logoutBtn);

		newHolidaybp.setTop(menuBox);

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
			this.userMsg.setTextFill(Color.GREEN);
			this.userMsg.setText("Logged out successfully!");
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
		ScrollPane activityScroll = new ScrollPane();
		HBox menu = new HBox(10);
		Region reg = new Region();
		Button logout = new Button("Log out");
		Button goBack = new Button("Go Back");
		menu.getChildren().addAll(logout, reg, goBack);
		activitiesbp.setTop(menu);

		logout.setOnAction(e -> {
			this.stage.setScene(loginScene);
			this.userMsg.setTextFill(Color.GREEN);
			this.userMsg.setText("Logged out successfully!");
			this.hs.logOut();
		});
		goBack.setOnAction(e -> {
			this.stage.setScene(allHolidays);
		});

		activityNodes = new VBox(10);
		activityNodes.setMaxHeight(300);
		activityNodes.setMaxWidth(300);
		getActivitiesAsList();

		activityScroll.setContent(activityNodes);
		activitiesbp.setCenter(activityScroll);

		VBox addActivity = new VBox(10);
		activitiesbp.setRight(addActivity);

		Label actName = new Label("Activity's name: ");
		TextField nameInput = new TextField();
		Label actPrice = new Label("Activity's price (€): ");
		TextField priceInput = new TextField();
		Button addAct = new Button("Add");

		addAct.setOnAction(e -> {
			if (nameInput.getText().isEmpty() || priceInput.getText().isEmpty()) {
				userMsg.setTextFill(Color.RED);
				userMsg.setText("Give the activity and its price. Budget left: " + this.holiday.getBudget() + "€");
				nameInput.setText("");
				priceInput.setText("");
			} else {
				try {
					int price = Integer.parseInt(priceInput.getText());
					String name = nameInput.getText();
					int holidaybudget = this.holiday.getBudget();
					if (this.holiday.getBudget() >= price) {
						hs.planActivity(name, price, this.holiday);
						this.holiday.setBudget(holidaybudget - price);
						userMsg.setTextFill(Color.GREEN);
						userMsg.setText("Activity added. Budget left: " + this.holiday.getBudget() + "€");
						nameInput.setText("");
						priceInput.setText("");
						getActivitiesAsList();
					} else {
						userMsg.setTextFill(Color.RED);
						userMsg.setText("You don't have enough money for this activity. Budget left: " + this.holiday.getBudget() + "€");
						nameInput.setText("");
						priceInput.setText("");
						getActivitiesAsList();
					}
				} catch (NumberFormatException ex) {
					userMsg.setTextFill(Color.RED);
					userMsg.setText("Give the price as numbers. Budget left: " + this.holiday.getBudget() + "€");
					priceInput.setText("");
				}
			}

		});

		addActivity.getChildren().addAll(userMsg, actName, nameInput, actPrice, priceInput, addAct);

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

}
