package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Item;
import model.Purchase;

public class OstoskorinäkymäController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> shopbasket;
	private ArrayList<Purchase> purchaseHistory;
	private ObservableList<Item> observableItemList;
	
    @FXML
    private Button purchasehistory;
    
    @FXML
    private TextField infobox;
    
    @FXML
    private Button purchaseitems;

    @FXML
    private Button removebasketitem;
    
    @FXML
    private TableView<Item> shopbasketRepresentation;

    @FXML
    private TableColumn<Item, String> productname;
    
    @FXML
    private TableColumn<Item, String> productamount;

    @FXML
    void removeBasketItem(ActionEvent event) throws IOException {
    	shopbasket.remove(shopbasketRepresentation.getSelectionModel().getSelectedItem().getName());
    	for(int i = 0; i < observableItemList.size(); i++) {
    		if(observableItemList.get(i).getName().equals(shopbasketRepresentation.getSelectionModel().getSelectedItem().getName())) {
    			if(observableItemList.get(i).getAmount()==1) {
    				observableItemList.remove(shopbasketRepresentation.getSelectionModel().getSelectedItem());
    			}
    			else {
    				observableItemList.get(i).reduceAmount();
    			}
    		}
    	}
    	showBasketItems(event);
    	if (observableItemList.size() > 0) {
    		removebasketitem.setDisable(true);
		}
    }

    @FXML
    void purchaseItems(ActionEvent event) throws IOException, SQLException {
    	if(shopbasket.size()<1) {
    		infobox.setText("Ostoskori on tyhjä");
    	}
    	else {
    	ConnectionController controller = new ConnectionController();
    	controller.makePurchase(shopbasket, sessionuser.getText());
		initShopBasket();
		showBasketItems(event);
		infobox.setText("Osto suoritettu");
    	}
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
		ohcontroller.initPurchaseHistory();
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
		observableItemList = FXCollections.observableArrayList();
    	for (int i = 0; i<shopbasket.size(); i++) {
    		observableItemList.add(new Item(shopbasket.get(i)));
    	}
	}

    @FXML
	void showBasketItems(ActionEvent event) {
		observableItemList = FXCollections.observableArrayList();
		if (shopbasket.size() > 0) {
			observableItemList.add(new Item(shopbasket.get(0)));
		}
		for (int i = 1; i < shopbasket.size(); i++) {
			for (int j = 0; j < observableItemList.size(); j++) {
				if (observableItemList.get(j).getName().equalsIgnoreCase(shopbasket.get(i))) {
					observableItemList.get(j).increaseAmount();
					break;
				}
				else if(observableItemList.size()>(j+1)) {
					
				}
				else {
					observableItemList.add(new Item(shopbasket.get(i)));
					break;
				}
			}
		}
		shopbasketRepresentation.setItems(observableItemList);
		if (observableItemList.size() > 0) {
			purchaseitems.setDisable(false);
		}
		else {
			purchaseitems.setDisable(true);
		}
	}
    
	public void initShopBasket(){
    	shopbasket = new ArrayList<String>();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productname.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		productamount.setCellValueFactory(new PropertyValueFactory<Item, String>("amount"));
		removebasketitem.setDisable(true);
	    purchaseitems.setDisable(true);
	}
	@FXML
	void itemSelected(MouseEvent event) {
		if (!shopbasketRepresentation.getSelectionModel().isEmpty()) {
			removebasketitem.setDisable(false);
		}
	}

}
