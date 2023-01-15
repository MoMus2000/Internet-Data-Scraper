package mmuhammad.scraper;

import mmuhammad.scraper.kijiji.OneBedroomApartmentScraper;
import mmuhammad.scraper.persistance.DbPersistance;
import mmuhammad.scraper.persistance.SqlitePersistance;

public class Main {
	public static void main(String[] args) {
		System.out.println("Booting scrapers ...");
		Scraper kijiji = new OneBedroomApartmentScraper("Numeric data", new String[]{"https://www.google.com"});
		kijiji.requestSite();
		System.out.println("Saving data ...");
		DbPersistance database = new SqlitePersistance(null, null);
		System.out.println(database);
		System.out.println("Done");
	}
}
