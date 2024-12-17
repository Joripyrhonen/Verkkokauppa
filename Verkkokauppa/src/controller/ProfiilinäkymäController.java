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

public class ProfiilinäkymäController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
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
    void showShoppingbasket(ActionEvent event) throws SQLException, IOException {
    	try {
			root = FXMLLoader.load(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void showProfile(ActionEvent event) throws SQLException, IOException {
//		try {
//			root = FXMLLoader.load(getClass().getResource("/view/Profiilinäkymä.fxml"));
//			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//			scene = new Scene(root);
//			stage.setScene(scene);
//			stage.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }
    
    @FXML
    void returnToStore(ActionEvent event) throws SQLException, IOException {
    	try {
    		root = FXMLLoader.load(getClass().getResource("/view/Verkkokauppa.fxml"));
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
