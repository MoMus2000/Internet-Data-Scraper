package model;

public abstract class DbPersistance {
	public String databasePath;
	
	
	
	public DbPersistance(String databasePath) {
		this.databasePath = databasePath;
	}
	
	public abstract void saveToDatabase(Model data);
	public abstract void fetchFromDatabase();
	public abstract void createTable();
	public abstract void deleteTable();
	
}
