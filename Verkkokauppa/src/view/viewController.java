package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class viewController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	//Visual.fxml näkymän elementtejä
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
	
	//tämä on vaan testausta varten, poistuu oletettavasti lopullisesta
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
				e.printStackTrace();
			}
		}
		// mongodb -tietokantaan yhdistävä tapahtuma
		else if (mongodb.isSelected()) {
			Controller controller = new Controller();
			controller.dbConnection(mongodb.getText());
		}
		try {
		root = FXMLLoader.load(getClass().getResource("Verkkokauppa.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Verkkokauppa.fxml näkymän elementtejä
    @FXML
    private Button profile;
    
    //tämä myös ProfileView:ssä
    @FXML
    private Button shoppingbasket;
    
    @FXML
    private GridPane products;
    
    @FXML
    private Button jackets;

    @FXML
    private Button pants;

    @FXML
    private Button shirts;

    @FXML
    private Button shoes;

    @FXML
    void showJackets(ActionEvent event) {

    }

    @FXML
    void showPants(ActionEvent event) {

    }

    @FXML
    void showShirts(ActionEvent event) {

    }

    @FXML
    void showShoes(ActionEvent event) {

    }
    
    @FXML
    void showProfile(ActionEvent event) throws SQLException, IOException {
		try {
			root = FXMLLoader.load(getClass().getResource("Profiilinäkymä.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    //tämä myös Profiilinäkymässä
    @FXML
    void showShoppingbasket(ActionEvent event) {

    }
    
    //ProfileView näkymän elementtejä
    
	@FXML
	private Label usernamedisplayed;
	
    @FXML
    private Label addressdisplayed;

    @FXML
    private GridPane purchasehistory;

    @FXML
    private Label realnamedisplayed;

    @FXML
    private Button returntostore;

    @FXML
    void returnToStore(ActionEvent event) throws SQLException, IOException {
    	try {
    		root = FXMLLoader.load(getClass().getResource("Verkkokauppa.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
