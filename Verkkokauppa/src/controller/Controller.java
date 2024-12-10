package controller;
import model.*;
import view.*;
public class Controller {
	public static String userCheck(String username, String password) {
		User testUser = new User();
		testUser.setUserName("pepe");
		testUser.setPassword("password");
		String result;
		if(username.equals(testUser.getUserName()) && password.equalsIgnoreCase(testUser.getPassword())) {
			result = "username and password correct";
			return result;
		}
		else if(username.equals(testUser.getUserName()) && !password.equalsIgnoreCase(testUser.getPassword())){
			result = "password incorrect";
			return result;
		}
		else {
			result = "user does not exist";
			return result;
		}
	}
}