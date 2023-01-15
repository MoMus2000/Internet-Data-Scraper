package mmuhammad.scraper;

import java.util.ArrayList;
import java.util.List;


import mmuhammad.scraper.kijiji.housing.TwoBedroomApartmentScraper;
import mmuhammad.scraper.kijiji.housing.TwoBedroomBasementScraper;
import mmuhammad.scraper.persistance.DbPersistance;
import mmuhammad.scraper.persistance.SqlitePersistance;

public class Main {
	public static void main(String[] args) {
		System.out.println("Booting scrapers ...");
		
		List<Scraper> scrapers = new ArrayList<Scraper>(); 
		
		String[] twoBedroomApartmentLinks = {
			/* GTA */	"https://www.kijiji.ca/b-apartments-condos/markham-york-region/2+bedrooms-apartment/page-{i}/c37l1700274a27949001a29276001?ll=43.872291%2C-79.482061&address=266+Lady+Valentina+Ave%2C+Maple%2C+ON+L6A+0E1%2C+Canada&ad=offering&radius=68.0",
			/* STC */	"https://www.kijiji.ca/b-apartments-condos/st-catharines/2+bedrooms-apartment/page-{i}/c37l80016a27949001a29276001?radius=10.0&address=St.+Catharines%2C+ON&ll=43.159375,-79.246863",
			/* DUR */	"https://www.kijiji.ca/b-apartments-condos/oshawa-durham-region/2+bedrooms-apartment/page-{i}/c37l1700275a27949001a29276001",
			/* HAM */	"https://www.kijiji.ca/b-apartments-condos/hamilton/2+bedrooms-apartment/page-{i}/c37l80014a27949001a29276001?ll=43.255721%2C-79.871102&address=Hamilton%2C+ON&radius=20.0",
			/* WIN */	"https://www.kijiji.ca/b-apartments-condos/windsor-area-on/2+bedrooms-apartment/page-{i}/c37l1700220a27949001a29276001?ll=42.314937%2C-83.036363&address=Windsor%2C+ON&radius=20.0"
		};
		
		String [] twoBedroomBasementLinks = {
			/* GTA */ "https://www.kijiji.ca/b-apartments-condos/markham-york-region/2+bedrooms-apartment/page-{i}/c37l1700274a27949001a29276001?ll=43.872291%2C-79.482061&address=266+Lady+Valentina+Ave%2C+Maple%2C+ON+L6A+0E1%2C+Canada&ad=offering&radius=68.0",
			/* STC */ "https://www.kijiji.ca/b-apartments-condos/st-catharines/2+bedrooms-basement+apartment/page-{i}/c37l80016a27949001a29276001?radius=10.0&address=St.+Catharines%2C+ON&ll=43.159375,-79.246863",
			/* DUR */ "https://www.kijiji.ca/b-apartments-condos/oshawa-durham-region/2+bedrooms-basement+apartment/page-{i}/c37l1700275a27949001a29276001",
			/* HAM */ "https://www.kijiji.ca/b-apartments-condos/hamilton/2+bedrooms-basement+apartment/page-{i}/c37l80014a27949001a29276001?ll=43.255721%2C-79.871102&address=Hamilton%2C+ON&radius=20.0",
			/* WIN */ "https://www.kijiji.ca/b-apartments-condos/windsor-area-on/2+bedrooms-basement+apartment/page-{i}/c37l1700220a27949001a29276001?ll=42.314937%2C-83.036363&address=Windsor%2C+ON&radius=20.0"
		};
		
		scrapers.add(new TwoBedroomApartmentScraper("Numeric data", twoBedroomApartmentLinks));
		scrapers.add(new TwoBedroomBasementScraper("Numeric data", twoBedroomBasementLinks));
		for(Scraper sc : scrapers) {
			sc.scrape();
		}
		
		System.out.println("Saving data ...");
		DbPersistance database = new SqlitePersistance(null, null);
		System.out.println(database);
		System.out.println("Done");
	}
}
