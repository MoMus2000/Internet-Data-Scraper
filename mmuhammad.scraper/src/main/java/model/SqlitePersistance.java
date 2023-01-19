package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlitePersistance extends DbPersistance {
	
	public Connection connection;
	
	public SqlitePersistance(String databasePath) {
		super(databasePath);
		try {
			System.out.println("Connecting to database ...");
			this.connection = DriverManager.getConnection("jdbc:sqlite:"+databasePath);
			System.out.println("Connected to database ...");
			
			System.out.println("Checking if table exists ...");
			this.createTable();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveToDatabase(Model data) {
		
	}

	@Override
	public void fetchFromDatabase() {
		
	}

	@Override
	public void createTable() {
		try {
			DatabaseMetaData meta = this.connection.getMetaData();
			ResultSet res = meta.getTables(null, null, "JAVA_SCRAPER", 
				     new String[] {"TABLE"});
			boolean checkTable = res.next();
			if(!checkTable) {
				System.out.println("Table JAVA_SCRAPER missing, making now ...");
				Statement st = this.connection.createStatement();
				st.executeUpdate("CREATE TABLE JAVA_SCRAPER ("
						+  "Date varchar(255),"
						+  "Title varchar(255),"
						+  "Price varchar(255),"
						+  "Description varchar(255),"
						+  "Address varchar(255),"
						+  "JobName varchar(255)"
						+  ")");
				System.out.println("Made new table ...");
			}
			while(res.next()){
			     System.out.println(
			        "   "+res.getString("TABLE_CAT") 
			       + ", "+res.getString("TABLE_SCHEM")
			       + ", "+res.getString("TABLE_NAME")
			       + ", "+res.getString("TABLE_TYPE")
			       + ", "+res.getString("REMARKS")); 
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteTable() {
		
	}
	
	public static void main(String[] args) {
//		DbPersistance sqlite = new SqlitePersistance("/Users/mmuhammad/Desktop/projects/mmuhammad.net/mmuhammad.net/db/lenslocked_dev.db");
	}
}
