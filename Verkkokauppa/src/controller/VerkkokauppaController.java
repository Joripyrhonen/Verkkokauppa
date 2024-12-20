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
import model.DataBaseConnection;

public class VerkkokauppaController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button addtobasketitem1;

    @FXML
    private Button addtobasketitem2;

    @FXML
    private Button addtobasketitem3;

    @FXML
    private Button addtobasketitem4;
    
    @FXML
    private Label item1;

    @FXML
    private Label item2;

    @FXML
    private Label item3;

    @FXML
    private Label item4;
    
    @FXML
    private Label sessionuser;
    
    @FXML
    private Button profile;
    
    @FXML
    private Button shoppingbasket;
    
    @FXML
    private GridPane productcategories;

    @FXML
    private GridPane availableproducts;

    @FXML
    private Button jackets;

    @FXML
    private Button pants;

    @FXML
    private Button shirts;

    @FXML
    private Button shoes;

    @FXML
    private Button signout;

    @FXML
    void showJackets(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Takit");
    	for(int i=0;i<4;i++) {
    		if(i==0) {
    			item1.setText(itemList.get(i));
    		}
    		else if(i==1){
    			item2.setText(itemList.get(i));

    		}
    		else if(i==2){
    			item3.setText(itemList.get(i));

    		}
    		else if(i==3){
    			item4.setText(itemList.get(i));

    		}

    	}
    }

    @FXML
    void showPants(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Housut");
    	for(int i=0;i<4;i++) {
    		if(i==0) {
    			item1.setText(itemList.get(i));
    		}
    		else if(i==1){
    			item2.setText(itemList.get(i));

    		}
    		else if(i==2){
    			item3.setText(itemList.get(i));

    		}
    		else if(i==3){
    			item4.setText(itemList.get(i));

    		}

    	}
    }

    @FXML
    void showShirts(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Paidat");
    	for(int i=0;i<4;i++) {
    		if(i==0) {
    			item1.setText(itemList.get(i));
    		}
    		else if(i==1){
    			item2.setText(itemList.get(i));

    		}
    		else if(i==2){
    			item3.setText(itemList.get(i));

    		}
    		else if(i==3){
    			item4.setText(itemList.get(i));

    		}

    	}
    }

    @FXML
    void showShoes(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Kengät");
    	for(int i=0;i<4;i++) {
    		if(i==0) {
    			item1.setText(itemList.get(i));
    		}
    		else if(i==1){
    			item2.setText(itemList.get(i));

    		}
    		else if(i==2){
    			item3.setText(itemList.get(i));

    		}
    		else if(i==3){
    			item4.setText(itemList.get(i));

    		}

    	}
    }
    @FXML
    void addTobasketItem1(ActionEvent event) {

    }

    @FXML
    void addTobasketItem2(ActionEvent event) {

    }

    @FXML
    void addTobasketItem3(ActionEvent event) {

    }

    @FXML
    void addTobasketItem4(ActionEvent event) {

    }
    @FXML
    void signOut(ActionEvent event) throws SQLException, IOException {
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
    
    @FXML
    void showProfile(ActionEvent event) throws SQLException, IOException {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Profiilinäkymä.fxml"));
			root = fxmlloader.load();
			ProfiilinäkymäController prflcontroller = fxmlloader.getController();
			prflcontroller.passSessionUser(sessionuser.getText());
			ConnectionController controller = new ConnectionController();
			ArrayList<String> prflinfo = controller.getUserInfo(sessionuser.getText());
			System.out.println(prflinfo.size());
			prflcontroller.initUserInfo(prflinfo);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void showShoppingbasket(ActionEvent event) throws SQLException, IOException {
    	try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
			OstoskorinäkymäController okcontroller = fxmlloader.getController();
			okcontroller.passSessionUser(sessionuser.getText());
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
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
