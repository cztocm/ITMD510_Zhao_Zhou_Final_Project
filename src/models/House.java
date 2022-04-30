package models;

public class House {

	int id;
	double area;
	double price;
	String owner;
	String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public House(int id, double area, double price, String owner, String address) {
		this.id = id;
		this.area = area;
		this.price = price;
		this.owner = owner;
		this.address = address;
	}

	public House() {
	}

	public String toString() {

		String houseStr = "House :\nID- " + id;
		houseStr += "\nArea(sqft)- " + area;
		houseStr += "\nPrice- " + price;
		houseStr += "\nAddress- " + address;
		houseStr += "\nOwner-" + owner + "\n";

		return houseStr;
	}

}
