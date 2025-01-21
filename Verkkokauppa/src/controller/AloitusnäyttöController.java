package controller;

import java.io.IOException;
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

public class AloitusnäyttöController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private Button newuser;

	@FXML
	private PasswordField password;

	@FXML
	private Button signin;

	@FXML
	private TextField username;

	@FXML
	private TextField usertrue;

	@FXML
	private ToggleGroup Database;

	@FXML
	private RadioButton mongodb;

	@FXML
	private RadioButton mysql;

	@FXML
	void signIn(ActionEvent event) throws SQLException, IOException {
		if (!username.getText().equals("") && !password.getText().equals("")) {
			try {
				ConnectionController controller = new ConnectionController();
				String connectionResult = controller.dbConnection(Database.getSelectedToggle().getUserData().toString(),
						username.getText(), hash(password.getText()));
				if (connectionResult.equals("Kirjautuminen onnistui.")) {
					FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Verkkokauppa.fxml"));
					root = fxmlloader.load();
					VerkkokauppaController vkcontroller = fxmlloader.getController();
					vkcontroller.passSessionUser(username.getText(),
							Database.getSelectedToggle().getUserData().toString());
					vkcontroller.initShopBasket();
					scene = new Scene(root);
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} else {
					usertrue.setText(connectionResult);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (username.getText().equals("") || password.getText().equals("")) {
			usertrue.setText("Syötä käyttäjätiedot.");
		}
	}

	@FXML
	void signUp(ActionEvent event) throws SQLException {
		try {
			root = FXMLLoader.load(getClass().getResource("/view/UusiKäyttäjä.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mysql.setUserData("MySQL");
		mongodb.setUserData("MongoDB");
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
}