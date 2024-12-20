package controller;

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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OstoskorinäkymäController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
    @FXML
    private Label sessionuser;
    
    @FXML
    private GridPane itemsinbasket;
    
    @FXML
    private Button profile;
    
    @FXML
    private Button shoppingbasket;
    
    @FXML
    private Button returntostore;
    
    @FXML
    void showProfile(ActionEvent event) throws SQLException, IOException {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Profiilinäkymä.fxml"));
			root = fxmlloader.load();
			ProfiilinäkymäController prflcontroller = fxmlloader.getController();
			prflcontroller.passSessionUser(sessionuser.getText());
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void returnToStore(ActionEvent event) throws SQLException, IOException {
    	try {
    		FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(getClass().getResource("/view/Verkkokauppa.fxml"));
			root = fxmlloader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			VerkkokauppaController vkcontroller = fxmlloader.getController();
			vkcontroller.passSessionUser(sessionuser.getText());
    		stage.setScene(scene);
    		stage.show();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }
	public void passSessionUser(String user) {
		sessionuser.setText(user);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
