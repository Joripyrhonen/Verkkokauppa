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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Purchase;

public class OstoskorinäkymäController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> shopbasket;
	ArrayList<Purchase> purchaseHistory;
	
    @FXML
    private Button purchasehistory;
    
    @FXML
    private TextField infobox;
    
    @FXML
    private Button purchaseitems;
    
    @FXML
    private Label basketitem1;
    
    @FXML
    private Label basketitem2;

    @FXML
    private Label basketitem3;

    @FXML
    private Label basketitem4;

    @FXML
    private Button removebasketitem1;

    @FXML
    private Button removebasketitem2;

    @FXML
    private Button removebasketitem3;

    @FXML
    private Button removebasketitem4;
    
    @FXML
    void removeBasketItem1(ActionEvent event) throws IOException {
    	if(basketitem1.getText() != "") {
    		shopbasket.remove(basketitem1.getText());
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
    		OstoskorinäkymäController okcontroller = fxmlloader.getController();
    		okcontroller.passSessionUser(sessionuser.getText());
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showShoppingBasket();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    	}
    }

    @FXML
    void removeBasketItem2(ActionEvent event) throws IOException {
    	if(basketitem2.getText() != "") {
    		shopbasket.remove(basketitem2.getText());
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
    		OstoskorinäkymäController okcontroller = fxmlloader.getController();
    		okcontroller.passSessionUser(sessionuser.getText());
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showShoppingBasket();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    	}
    }

    @FXML
    void removeBasketItem3(ActionEvent event) throws IOException {
    	if(basketitem3.getText() != "") {
    		shopbasket.remove(basketitem3.getText());
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
    		OstoskorinäkymäController okcontroller = fxmlloader.getController();
    		okcontroller.passSessionUser(sessionuser.getText());
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showShoppingBasket();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    	}
    }

    @FXML
    void removeBasketItem4(ActionEvent event) throws IOException {
    	if(basketitem4.getText() != "") {
    		shopbasket.remove(basketitem4.getText());
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
    		OstoskorinäkymäController okcontroller = fxmlloader.getController();
    		okcontroller.passSessionUser(sessionuser.getText());
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showShoppingBasket();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
    	}
    }
    @FXML
    void purchaseItems(ActionEvent event) throws IOException, SQLException {
    	ConnectionController controller = new ConnectionController();
    	controller.makePurchase(shopbasket, sessionuser.getText());
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
		root = fxmlloader.load();
		OstoskorinäkymäController okcontroller = fxmlloader.getController();
		okcontroller.passSessionUser(sessionuser.getText());
		okcontroller.initShopBasket();
		infobox.setText("Osto suoritettu");
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    @FXML
    void showPurchaseHistory(ActionEvent event) throws IOException, SQLException {
    	ConnectionController controller = new ConnectionController();
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostohistoria.fxml"));
    	purchaseHistory = controller.purchaseHistory(sessionuser.getText());
		root = fxmlloader.load();
		OstohistoriaController ohcontroller = fxmlloader.getController();
		ohcontroller.passSessionUser(sessionuser.getText());
		ohcontroller.receiveShopBasket(shopbasket);
		ohcontroller.initHistory(purchaseHistory);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
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
			prflcontroller.receiveShopBasket(shopbasket);
			shopbasket = new ArrayList<String>();
			ConnectionController controller = new ConnectionController();
			ArrayList<String> prflinfo = controller.getUserInfo(sessionuser.getText());
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
    void returnToStore(ActionEvent event) throws SQLException, IOException {
    	try {
    		FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(getClass().getResource("/view/Verkkokauppa.fxml"));
			root = fxmlloader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			VerkkokauppaController vkcontroller = fxmlloader.getController();
			vkcontroller.passSessionUser(sessionuser.getText());
			vkcontroller.receiveShopBasket(shopbasket);
    		stage.setScene(scene);
    		stage.show();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }
	public void passSessionUser(String user) {
		sessionuser.setText(user);
	}
	public void receiveShopBasket(ArrayList<String> shopbasket) {
    	this.shopbasket = shopbasket;
    }
	public void showShoppingBasket() {
		if(shopbasket.size()==1) {
			basketitem1.setText(shopbasket.get(0));
		}
		else if(shopbasket.size()==2) {
			basketitem1.setText(shopbasket.get(0));
			basketitem2.setText(shopbasket.get(1));
		}
		else if(shopbasket.size()==3) {
			basketitem1.setText(shopbasket.get(0));
			basketitem2.setText(shopbasket.get(1));
			basketitem3.setText(shopbasket.get(2));

		}
		else if(shopbasket.size()==4) {
			basketitem1.setText(shopbasket.get(0));
			basketitem2.setText(shopbasket.get(1));
			basketitem3.setText(shopbasket.get(2));
			basketitem4.setText(shopbasket.get(3));

		}
	}
	public void showShoppingBasketWithArrayList(ArrayList<String> shopbasket) {
		if(shopbasket.size()==1) {
			basketitem1.setText(shopbasket.get(0));
		}
		else if(shopbasket.size()==2) {
			basketitem1.setText(shopbasket.get(0));
			basketitem2.setText(shopbasket.get(1));
		}
		else if(shopbasket.size()==3) {
			basketitem1.setText(shopbasket.get(0));
			basketitem2.setText(shopbasket.get(1));
			basketitem3.setText(shopbasket.get(2));

		}
		else if(shopbasket.size()==4) {
			basketitem1.setText(shopbasket.get(0));
			basketitem2.setText(shopbasket.get(1));
			basketitem3.setText(shopbasket.get(2));
			basketitem4.setText(shopbasket.get(3));

		}
	}

	public void initShopBasket(){
    	shopbasket = new ArrayList<String>();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
