package controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBConnect;
import application.DynamicTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private TextField txtArea;
	@FXML
	private TextField txtPrice;
	@FXML
	private TextField txtOwner;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtUpdateArea;
	@FXML
	private TextField txtUpdatePrice;
	@FXML
	private TextField txtUpdateOwner;
	@FXML
	private TextField txtUpdateAddress;
	@FXML
	private TextField txtUpdateId;
	@FXML
	private TextField txtDeleteId;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public AdminController() {
		conn = new DBConnect();
	}

	public void viewHouses() {

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		d.buildData("Select id,area,price,owner,address from zhao_houses");
	}

	public void updateHouse() {

		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);

	}

	public void deleteHouse() {

		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
	}

	public void addHouse() {

		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);

	}

	public void submit() {

		// INSERT INTO TABLE
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "insert into zhao_houses(area,price,owner,address) values ('" + txtArea.getText() + "','"
					+ txtPrice.getText() + "','" + txtOwner.getText() + "','" + txtAddress.getText() + "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			System.out.println("House Record created");

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void submitUpdate() {

		System.out.println("Update Submit button pressed");

		try {
			// Execute a query
			System.out.println("Updateing records into the table...");
			String sql = "UPDATE `zhao_houses` set `area` = ?, `price`= ? ,`owner` = ?,`address` = ? where id = ?";
			PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);

			// Include all object data to the database table
			pstmt.setDouble(1, Double.valueOf(txtUpdateArea.getText()));
			pstmt.setDouble(2, Double.valueOf(txtUpdatePrice.getText()));
			pstmt.setString(3, txtUpdateOwner.getText());
			pstmt.setString(4, txtUpdateAddress.getText());
			pstmt.setInt(5, Integer.valueOf(txtUpdateId.getText()));
			System.out.println(sql);
			pstmt.executeUpdate();
			System.out.println("House Record Updated");

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public void submitDelete() {

		System.out.println("Delete Submit button pressed");
		// INSERT INTO TABLE
		try {
			// Execute a query
			System.out.println("Deleting records from the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "delete from zhao_houses where id =" + txtDeleteId.getText();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			System.out.println("House Record deleted");

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

}
