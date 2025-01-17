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
import model.DataBaseConnection;
import model.Item;

public class VerkkokauppaController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> shopbasket;
	
    @FXML
    private TextField infobox;

    @FXML
    private Button addtobasketitem;
    
    @FXML
    private Label sessionuser;
    
    @FXML
    private Button profile;
    
    @FXML
    private Button shoppingbasket;
    
    @FXML
    private GridPane productcategories;

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
    private TableView<Item> productlisting;

    @FXML
    private TableColumn<Item, String> productname;
    
    @FXML
    void itemSelected(MouseEvent event) {
		if (!productlisting.getSelectionModel().isEmpty()) {
			addtobasketitem.setDisable(false);
		}
    }

    @FXML
    void showJackets(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Takit");
    	ObservableList<Item> observableItemList = FXCollections.observableArrayList();
    	for (int i = 0; i<itemList.size(); i++) {
    		observableItemList.add(new Item(itemList.get(i)));
    	}
    	productlisting.setItems(observableItemList);
    	addtobasketitem.setDisable(true);
    }
    

    @FXML
    void showPants(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Housut");
    	ObservableList<Item> observableItemList = FXCollections.observableArrayList();
    	for (int i = 0; i<itemList.size(); i++) {
    		observableItemList.add(new Item(itemList.get(i)));
    	}
    	productlisting.setItems(observableItemList);
    	addtobasketitem.setDisable(true);
    }


    @FXML
    void showShirts(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Paidat");
    	ObservableList<Item> observableItemList = FXCollections.observableArrayList();
    	for (int i = 0; i<itemList.size(); i++) {
    		observableItemList.add(new Item(itemList.get(i)));
    	}
    	productlisting.setItems(observableItemList);
    	addtobasketitem.setDisable(true);
    }

    @FXML
    void showShoes(ActionEvent event) throws SQLException {
    	DataBaseConnection dbc = new DataBaseConnection();
    	ArrayList<String> itemList = dbc.getItemList("Kengät");
    	ObservableList<Item> observableItemList = FXCollections.observableArrayList();
    	for (int i = 0; i<itemList.size(); i++) {
    		observableItemList.add(new Item(itemList.get(i)));
    	}
    	productlisting.setItems(observableItemList);
    	addtobasketitem.setDisable(true);
    }
    
    @FXML
    void addTobasketItem(ActionEvent event) {
    	shopbasket.add(productlisting.getSelectionModel().getSelectedItem().getName());
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
			prflcontroller.receiveShopBasket(shopbasket);
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
    void showShoppingbasket(ActionEvent event) throws SQLException, IOException {
    	try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
			root = fxmlloader.load();
			OstoskorinäkymäController okcontroller = fxmlloader.getController();
			okcontroller.passSessionUser(sessionuser.getText());
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showBasketItems(event);
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
    public void initShopBasket(){
    	shopbasket = new ArrayList<String>();
    }
    public void receiveShopBasket(ArrayList<String> shopbasket) {
    	this.shopbasket = shopbasket;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productname.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		addtobasketitem.setDisable(true);
	}

}
