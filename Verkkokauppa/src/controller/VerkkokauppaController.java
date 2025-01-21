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
import javafx.stage.Stage;
import model.Item;

public class VerkkokauppaController implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> shopbasket;
	private ArrayList<String> categoryitems;
	private ArrayList<String> categories;
	private String usedDataBase;

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
	private Button signout;

	@FXML
	private TableView<Item> categorylisting;

	@FXML
	private TableColumn<Item, String> categoryname;

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
	void showCategoryItems(MouseEvent event) throws SQLException {
		if (!categorylisting.getSelectionModel().isEmpty()) {
			ConnectionController controller = new ConnectionController();
			categoryitems = controller.getCategoryItems(usedDataBase,
					categorylisting.getSelectionModel().getSelectedItem().getCategory());
			ObservableList<Item> observableItemList = FXCollections.observableArrayList();
			for (int i = 0; i < categoryitems.size(); i++) {
				observableItemList.add(new Item(categoryitems.get(i)));
			}
			productlisting.setItems(observableItemList);
		}
	}

	@FXML
	void addTobasketItem(ActionEvent event) {
		shopbasket.add(productlisting.getSelectionModel().getSelectedItem().getName());
	}

	@FXML
	void signOut(ActionEvent event) throws SQLException, IOException {
		try {
			root = FXMLLoader.load(getClass().getResource("/view/Aloitusnäyttö.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
			prflcontroller.passSessionUser(sessionuser.getText(), usedDataBase);
			prflcontroller.receiveShopBasket(shopbasket);
			ConnectionController controller = new ConnectionController();
			ArrayList<String> prflinfo = controller.getUserInfo(usedDataBase, sessionuser.getText());
			prflcontroller.initUserInfo(prflinfo);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
			okcontroller.passSessionUser(sessionuser.getText(), usedDataBase);
			okcontroller.receiveShopBasket(shopbasket);
			okcontroller.showBasketItems(event);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void passSessionUser(String user, String database) throws SQLException {
		sessionuser.setText(user);
		usedDataBase = database;
		ConnectionController controller = new ConnectionController();
		categories = controller.getCategories(usedDataBase);
		ObservableList<Item> oli = FXCollections.observableArrayList();
		for (int i = 0; i < categories.size(); i++) {
			Item item = new Item();
			item.setCategory(categories.get(i));
			oli.add(item);
		}
		categorylisting.setItems(oli);
	}

	public void initShopBasket() {
		shopbasket = new ArrayList<String>();
	}

	public void receiveShopBasket(ArrayList<String> shopbasket) {
		this.shopbasket = shopbasket;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productname.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryname.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		addtobasketitem.setDisable(true);
	}

}
