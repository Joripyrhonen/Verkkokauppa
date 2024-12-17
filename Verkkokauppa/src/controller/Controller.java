package controller;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
public class Controller extends Application {
	public static void main(String[] args) throws SQLException {
		launch(args);
	}
	
	//Viedään tietoja modelin suuntaan ja yritetään luoda yhteys tietokantaan
	public void dbConnection(String database) throws SQLException, ClassNotFoundException{
		if(database.equals("MySQL")) {
			DataBaseConnection dbc = new DataBaseConnection();
			dbc.createConnection(database);
		}
		else if(database.equals("MongoDB")) {
			System.out.println("MongoDB toiminnallisuutta ei ole luotu vielä");
		}
	}
	//Tarkastetaan käyttäjän tietoja sisäänkirjautumisessa, sisältää ainoastaan testausta toistaiseksi, ei tietokantayhteyttä
	public String userCheck(String username, String password) {
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
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			System.out.println("start  käynnistyy");
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttötemp.fxml"));
			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}