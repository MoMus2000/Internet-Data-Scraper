package mmuhammad.scraper;

import java.util.ArrayList;
import java.util.List;

import mmuhammad.scraper.kijiji.cars.CarIndex;
import model.SqlitePersistance;
import model.Metrics.MetricCalc;

public class Main {
	public static void main(String[] args) {
		System.out.println("Booting scrapers ...");
		
		SqlitePersistance database = new SqlitePersistance("/Users/mmuhammad/Desktop/projects/mmuhammad.net/mmuhammad.net/db/lenslocked_dev.db");
		
		
		List<Scraper> scrapers = new ArrayList<Scraper>();
		
		scrapers.add(new CarIndex("https://www.kijiji.ca/b-cars-vehicles/canada/page-{i}/c27l0?ad=offering"));

		
		for(Scraper sc : scrapers) {
			new MetricCalc(sc.scrape());
		}
		
		System.out.println("Saving data ...");
		System.out.println(database);
		System.out.println("Done");
	}
}
