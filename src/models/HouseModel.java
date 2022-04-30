package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dao.DBConnect;

public class HouseModel extends DBConnect implements User<House> {

	double area;
	double price;
	String owner;
	String address;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public HouseModel() {
		conn = new DBConnect();
	}

	/* getters & setters */

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<HouseModel> getHouses() {
		List<HouseModel> houses = new ArrayList<>();
		String query = "SELECT area,price,owner,address FROM zhao_houses;";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				HouseModel house = new HouseModel();
				// grab record data by table field name into HouseModel account object
				house.setArea(resultSet.getDouble("area"));
				house.setPrice(resultSet.getDouble("price"));
				house.setOwner(resultSet.getString("owner"));
				house.setAddress(resultSet.getString("address"));
				houses.add(house); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching House: " + e);
		}
		System.out.println("All houses: " + houses);
		return houses; // return arraylist
	}

	@Override
	public House getClientInfo() {
		return null;
	}

}