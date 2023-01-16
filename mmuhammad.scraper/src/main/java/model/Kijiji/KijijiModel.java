package model.Kijiji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Model;


public class KijijiModel extends Model {
	String title;
	String price;
	String descriptions;
	String address;
	String date;
	
	public KijijiModel(String title, String price, String description, String address, String className) {
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

	@Override
	public void saveToDb(Connection connection) {
		try {
			PreparedStatement st = connection.prepareStatement(
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
