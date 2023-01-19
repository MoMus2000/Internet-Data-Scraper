package model.Kijiji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Model;


public class KijijiModel extends Model{
	public String title;
	public String price;
	public String descriptions;
	public String address;
	public String date;
	public static Connection conn;
	
	public KijijiModel(String title, String price, String description, String address, String className) {
		super(title, price, description, address, className);
		this.title = title;
		this.price = price;
		this.descriptions = description;
		this.address = address;
		this.topic = className;
		this.setCurrentDate();
	}

	public void setCurrentDate() {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		this.date = dtf.format(now);
	}
	
	public static void setConnection(Connection conn) {
		KijijiModel.conn = conn; 
	}
	

	@Override
	public void saveToDb() {
		try {
			PreparedStatement st = KijijiModel.conn.prepareStatement(
					"INSERT INTO JAVA_SCRAPER"
					+"(Date, Title, Price, Description, Address, JobName) "
					+"VALUES(?, ?, ?, ?, ?, ?)");
			
			st.setString(1, this.date);
			st.setString(2, this.title);
			st.setString(3, this.price);
			st.setString(4, this.descriptions);
			st.setString(5, this.address);
			st.setString(6, this.topic);
			st.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
