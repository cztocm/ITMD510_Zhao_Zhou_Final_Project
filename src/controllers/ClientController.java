package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.HouseModel;

public class ClientController implements Initializable {

	HouseModel cm;

	/*****
	 * TABLEVIEW intel
	 *********************************************************************/

	@FXML
	private TableView<HouseModel> tblHouses;
	@FXML
	private TableColumn<HouseModel, String> area;
	@FXML
	private TableColumn<HouseModel, String> price;
	@FXML
	private TableColumn<HouseModel, String> owner;
	@FXML
	private TableColumn<HouseModel, String> address;

	public void initialize(URL location, ResourceBundle resources) {
		area.setCellValueFactory(new PropertyValueFactory<HouseModel, String>("area"));
		price.setCellValueFactory(new PropertyValueFactory<HouseModel, String>("price"));
		owner.setCellValueFactory(new PropertyValueFactory<HouseModel, String>("owner"));
		address.setCellValueFactory(new PropertyValueFactory<HouseModel, String>("address"));
		// auto adjust width of columns depending on their content
		tblHouses.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblHouses));
		tblHouses.setVisible(false); // set invisible initially
	}

	public void customResize(TableView<?> view) {

		AtomicLong width = new AtomicLong();
		view.getColumns().forEach(col -> {
			width.addAndGet((long) col.getWidth());
		});
		double tableWidth = view.getWidth();

		if (tableWidth > width.get()) {
			view.getColumns().forEach(col -> {
				col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
			});
		}
	}

	public void viewHouses() throws IOException {
		tblHouses.getItems().setAll(cm.getHouses()); // load table data from HouseModel List
		tblHouses.setVisible(true); // set tableview to visible if not

	}

	/*****
	 * End TABLEVIEW intel
	 *********************************************************************/

	public void logout() {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public ClientController() {
		cm = new HouseModel();
	}

}
