package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private ArrayList<String> shopbasket;
	
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
    private Button shoppingbasket;
    
    @FXML
    void showShoppingbasket(ActionEvent event) throws SQLException, IOException {
    	try {
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
			OstoskorinäkymäController okcontroller = fxmlloader.getController();
			okcontroller.passSessionUser(usernamedisplayed.getText());
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showShoppingBasket();
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
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Verkkokauppa.fxml"));
			root = fxmlloader.load();
			VerkkokauppaController vkcontroller = fxmlloader.getController();
			vkcontroller.passSessionUser(usernamedisplayed.getText());
			vkcontroller.receiveShopBasket(shopbasket);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }
	public void passSessionUser(String user) {
		usernamedisplayed.setText(user);
	}
	public void initUserInfo(ArrayList<String> userinfo) {
		realnamedisplayed.setText(userinfo.get(0));
		addressdisplayed.setText(userinfo.get(1));
	}
	public void receiveShopBasket(ArrayList<String> shopbasket) {
    	this.shopbasket = shopbasket;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
