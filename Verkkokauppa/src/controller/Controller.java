package controller;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends Application {
	public static void main(String[] args) throws SQLException {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttö.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}