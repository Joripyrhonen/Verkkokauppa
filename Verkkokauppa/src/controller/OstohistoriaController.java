package controller;

import java.io.IOException;
import java.net.URL;
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

public class OstohistoriaController implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<String> shopbasket;
	private ArrayList<String> purchasehistory;
	private ArrayList<Purchase> history;
		@FXML
		private Button ostohistoria;
	    @FXML
	    private Button back;

	    @FXML
	    private TextField infobox;

	    @FXML
	    private GridPane itemsinbasket;

	    @FXML
	    private Label p1i1;

	    @FXML
	    private Label p1i2;

	    @FXML
	    private Label p1i3;

	    @FXML
	    private Label p1i4;

	    @FXML
	    private Label p2i1;

	    @FXML
	    private Label p2i2;

	    @FXML
	    private Label p2i3;

	    @FXML
	    private Label p2i4;

	    @FXML
	    private Label purchaseid1;

	    @FXML
	    private Label purchaseid2;

	    @FXML
	    private Label sessionuser;

	    @FXML
	    void backToBasket(ActionEvent event) throws IOException {
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
		public void passSessionUser(String user) {
			sessionuser.setText(user);
		}

		public void receiveShopBasket(ArrayList<String> shopbasket) {
	    	this.shopbasket = shopbasket;			
		}
		public void initHistory(ArrayList<Purchase> purchasehistory) {
			this.history = purchasehistory;
//			for(int i=0;i<purchasehistory.size();i++) {
//				
//			}
		}
		@FXML
		public void initPurchaseHistory() {
			for(int i = 0 ; i<history.size(); i++) {
				System.out.println("historian koko "+history.size());
				System.out.println("i:n numero "+i);
				System.out.println("ostoid eka" +Integer.toString(history.get(0).getPurchaseid()));
				System.out.println("ostoid toka" +Integer.toString(history.get(0).getPurchaseid()));
				ArrayList<String> list = new ArrayList<String>();
				if(i==0) {
					purchaseid1.setText(Integer.toString(history.get(i).getPurchaseid()));

					list = history.get(i).getItemNames();
					
					if(list.size()==1) {
						p1i1.setText(list.get(0));
					}
					else if(list.size()==2) {
						p1i1.setText(list.get(0));
						p1i2.setText(list.get(1));
					}
					else if(list.size()==3) {
						p1i1.setText(list.get(0));
						p1i2.setText(list.get(1));
						p1i3.setText(list.get(2));

					}
					else if(list.size()==4) {
						p1i1.setText(list.get(0));
						p1i2.setText(list.get(1));
						p1i3.setText(list.get(2));
						p1i4.setText(list.get(3));

					}
				}
				if(i==1) {
					purchaseid2.setText(Integer.toString(history.get(i).getPurchaseid()));

					list = history.get(i).getItemNames();
					
					if(list.size()==1) {
						p2i1.setText(list.get(0));
					}
					else if(list.size()==2) {
						p2i1.setText(list.get(0));
						p2i2.setText(list.get(1));
					}
					else if(list.size()==3) {
						p2i1.setText(list.get(0));
						p2i2.setText(list.get(1));
						p2i3.setText(list.get(2));

					}
					else if(list.size()==4) {
						p2i1.setText(list.get(0));
						p2i2.setText(list.get(1));
						p2i3.setText(list.get(2));
						p2i4.setText(list.get(3));

					}
				}
			}
		}
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	}