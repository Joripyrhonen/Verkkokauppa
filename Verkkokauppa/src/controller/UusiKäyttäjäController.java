package controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class UusiKäyttäjäController {
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
    	// mysql -tietokantaan yhdistävä tapahtuma
    			if (mysql.isSelected()) {
    				if(!username.getText().equals("") && !password.getText().equals("") && !realname.getText().equals("") && !address.getText().equals("")) {
    					try {
    						ConnectionController controller = new ConnectionController();
    						String connectionResult = controller.dbSignUp(mysql.getText().toString(), username.getText(), hash(password.getText()), realname.getText(), address.getText());
    						if(connectionResult.equals("Käyttäjä luotu.")) {
    							root = FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttö.fxml"));
    							stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    							scene = new Scene(root);
    							stage.setScene(scene);
    							stage.show();
    						}
    						else {
    							usertrue.setText(connectionResult);
    						}
    					}catch (Exception e) {
    						e.printStackTrace();
    					}
    				}
    				else if(username.getText().equals("") || password.getText().equals("") || realname.getText().equals("") || address.getText().equals("")) {
    					usertrue.setText("Syötä käyttäjätiedot.");
    				}
    			}
    			// mongodb -tietokantaan yhdistävä tapahtuma
    			else if (mongodb.isSelected()) {
    				ConnectionController connController = new ConnectionController();
    				try {
    					connController.dbSignUp(mongodb.getText(), username.getText(), password.getText(), realname.getText(), address.getText());
    				} catch (ClassNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    }

    @FXML
    void goBack(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttö.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
		for(int i = 0; i<hash.length; i++) {
			String hex = Integer.toHexString(255 & hash[i]);
			if(hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
