package controller;
import model.*;
import view.*;
import java.io.IOException;
import java.net.URL;
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
	private RadioButton mongodb;

	@FXML
	private RadioButton mysql;
	@FXML
	void signIn(ActionEvent event) throws SQLException, IOException {
		// mysql -tietokantaan yhdistävä tapahtuma
		if (mysql.isSelected()) {
			try {
//				Controller controller = new Controller();
//				controller.dbConnection(mysql.getText());
				root = FXMLLoader.load(getClass().getResource("/view/Verkkokauppa.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		// mongodb -tietokantaan yhdistävä tapahtuma
		else if (mongodb.isSelected()) {
			Controller controller = new Controller();
			try {
				controller.dbConnection(mongodb.getText());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
