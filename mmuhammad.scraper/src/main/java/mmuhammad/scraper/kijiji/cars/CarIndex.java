package mmuhammad.scraper.kijiji.cars;

import mmuhammad.scraper.Scraper;
import mmuhammad.scraper.kijiji.KijijiScraper;


// Scrape kijiji for Canada wide car prices
// Remove outliers and junk cars

public class CarIndex extends KijijiScraper implements Scraper{

	public CarIndex(String type, String[] userUrls) {
		super(type, userUrls);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveToDB() {
		// TODO Auto-generated method stub
		
	}

}
