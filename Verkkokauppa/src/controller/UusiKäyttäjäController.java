package controller;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UusiKäyttäjäController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private ToggleGroup Database;

	@FXML
	private TextField address;

	@FXML
	private Button back;

	@FXML
	private RadioButton mongodb;

	@FXML
	private RadioButton mysql;
	@FXML
	private Button newuser;

	@FXML
	private PasswordField password;

	@FXML
	private TextField realname;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TextField username;

	@FXML
	private TextField usertrue;

	@FXML
	void createNewUser(ActionEvent event) throws SQLException {
		if (!username.getText().equals("") && !password.getText().equals("") && !realname.getText().equals("")
				&& !address.getText().equals("")) {
			try {
				ConnectionController controller = new ConnectionController();
				String connectionResult = controller.dbSignUp(Database.getSelectedToggle().getUserData().toString(),
						username.getText(), hash(password.getText()), realname.getText(), address.getText());
				if (connectionResult.equals("Käyttäjä luotu.")) {
					root = FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttö.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} else {
					usertrue.setText(connectionResult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (username.getText().equals("") || password.getText().equals("") || realname.getText().equals("")
				|| address.getText().equals("")) {
			usertrue.setText("Syötä käyttäjätiedot.");
		}
	}

	@FXML
	void goBack(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttö.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String hash(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(255 & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mysql.setUserData("MySQL");
		mongodb.setUserData("MongoDB");
	}
}
