package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import model.Item;
import model.Purchase;

public class OstohistoriaController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> shopbasket;
	private ArrayList<Purchase> history;
	private ObservableList<Purchase> observablePurchaseList;
	private ObservableList<Item> purchaseDisplayed;
	private String usedDataBase;

	@FXML
	private TableView<Purchase> purchases;

	@FXML
	private TableColumn<Purchase, String> purchase;

	@FXML
	private TableView<Item> items;

	@FXML
	private TableColumn<Item, String> productamount;

	@FXML
	private TableColumn<Item, String> productname;

	@FXML
	private Button showitems;

	@FXML
	private Button ostohistoria;
	@FXML
	private Button back;

	@FXML
	private TextField infobox;

	@FXML
	private Label sessionuser;

	@FXML
	void backToBasket(ActionEvent event) throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/view/Ostoskorinäkymä.fxml"));
		root = fxmlloader.load();
		OstoskorinäkymäController okcontroller = fxmlloader.getController();
		okcontroller.passSessionUser(sessionuser.getText(), usedDataBase);
		okcontroller.receiveShopBasket(shopbasket);
		okcontroller.showBasketItems(event);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void passSessionUser(String user, String database) {
		sessionuser.setText(user);
		usedDataBase = database;
	}

	public void receiveShopBasket(ArrayList<String> shopbasket) {
		this.shopbasket = shopbasket;
	}

	public void initHistory(ArrayList<Purchase> purchasehistory) {
		this.history = purchasehistory;

	}

	@FXML
	public void initPurchaseHistory(ActionEvent event) {
		observablePurchaseList = FXCollections.observableArrayList();
		for (int i = 0; i < history.size(); i++) {
			observablePurchaseList.add(history.get(i));
		}
		if (observablePurchaseList.size() > 0) {
			purchases.setItems(observablePurchaseList);
		}
	}

	public void showItems() {
		purchaseDisplayed = FXCollections.observableArrayList();
		for (int i = 0; i < purchases.getSelectionModel().getSelectedItem().getItems().size(); i++) {
			purchaseDisplayed.add(purchases.getSelectionModel().getSelectedItem().getItems().get(i));
		}
		items.setItems(purchaseDisplayed);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productname.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		productamount.setCellValueFactory(new PropertyValueFactory<Item, String>("amount"));
		purchase.setCellValueFactory(new PropertyValueFactory<Purchase, String>("purchaseid"));
		showitems.setDisable(true);
	}

	@FXML
	void itemSelected(MouseEvent event) {
		if (!purchases.getSelectionModel().isEmpty()) {
			showitems.setDisable(false);
		}
	}
}