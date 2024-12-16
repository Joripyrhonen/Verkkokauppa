package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("Aloitusnäyttö.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		launch(args);
//		Connection conn = null;
//		Statement stmnt = null;
//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/verkkokauppa", "root", "Jatski85");
//		stmnt = conn.createStatement();
//		String sql = "INSERT INTO Verkkokauppa.users (username, password)"
//				+"VALUES ('pepe2', 'salasana2');";
//		stmnt.execute(sql);
	}
}
