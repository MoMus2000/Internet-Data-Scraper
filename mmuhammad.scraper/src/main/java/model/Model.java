package model;


public abstract class Model {
	public String topic;
	public String title;
	public String price;
	public String descriptions;
	public String address;
	public String date;
	public String className;
	
	public Model(String title, String price, String description, String address, String className) {
		this.title = title;
		this.price = price;
		this.descriptions = description;
		this.address = address;
		this.topic = className;
	}
	
	
	public abstract void setCurrentDate();
	public abstract void saveToDb();
	
	public String toString() {
		return this.title + " "+ this.price;
	}
}
