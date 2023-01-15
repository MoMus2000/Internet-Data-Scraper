package mmuhammad.scraper.persistance;

public abstract class DbPersistance {
	public String databaseType;
	public String databasePath;
	
	public DbPersistance(String databaseType, String databasePath) {
		this.databasePath = databasePath;
		this.databaseType = databaseType;
	}
	
	public abstract void saveToDatabase();
	public abstract void fetchFromDatabase();
	public abstract void createTable();
	public abstract void deleteTable();
}
