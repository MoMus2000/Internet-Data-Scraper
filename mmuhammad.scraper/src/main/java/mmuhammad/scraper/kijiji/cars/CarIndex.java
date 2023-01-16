package mmuhammad.scraper.kijiji.cars;

import java.sql.Connection;

import mmuhammad.scraper.Scraper;
import mmuhammad.scraper.kijiji.KijijiScraper;


// Scrape kijiji for Canada wide car prices
// Remove outliers and junk cars

public class CarIndex extends KijijiScraper implements Scraper{

	public CarIndex(String type, String userUrls, Connection dbConnection) {
		super(type, userUrls, dbConnection);
		// TODO Auto-generated constructor stub
	}

}
