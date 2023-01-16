package model;

import java.sql.Connection;

public abstract class Model {
	public String date;
	public String topic;
	
	public abstract void setCurrentDate();
	public abstract void saveToDb(Connection connection);
}
