package view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class viewController implements Initializable {
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
	private RadioButton mongodb;

	@FXML
	private RadioButton mysql;

	@FXML
	void signIn(ActionEvent event) throws SQLException {
		// mysql -tietokantaan yhdistävä tapahtuma
		if (mysql.isSelected()) {
			try {
				Controller controller = new Controller();
				controller.dbConnection(mysql.getText());
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// testailua

				//String pepepe = Controller.userCheck(username.getText(), password.getText());
				//usertrue.setText(pepepe);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// mongodb -tietokantaan yhdistävä tapahtuma
		else if (mongodb.isSelected()) {
			Controller controller = new Controller();
			controller.dbConnection(mongodb.getText());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
