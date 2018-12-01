package lomabudjetti.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import lomabudjetti.domain.User;

public class FileUserDao implements UserDao {

	private List<User> userList;
	private String file;

	public FileUserDao(String file) throws Exception {
		this.userList = new ArrayList<>();
		this.file = file;

		try {
			Scanner readTxt = new Scanner(new File(this.file));
			while (readTxt.hasNextLine()) {
				String part = readTxt.nextLine();
				User test = new User(part);
				userList.add(test);
			}
			readTxt.close();
		} catch (Exception e) {
			FileWriter writeTxt = new FileWriter(new File(file));
			writeTxt.close();
		}
	}

	@Override
	public User create(User user) {
		userList.add(user);
		return user;
	}
	
	private void save() throws Exception {
		try (FileWriter writer  = new FileWriter(new File(this.file))) {
			for (User user : userList) {
				writer.write(user.getUsername() + "\n");
			}
		}
	}

	@Override
	public User findByUsername(String username) {
		return userList.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);

	}

	@Override
	public List<User> getAll() {
		return this.userList;
	}

}
