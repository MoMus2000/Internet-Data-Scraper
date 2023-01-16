package electronics;

import java.sql.Connection;

import mmuhammad.scraper.Scraper;
import mmuhammad.scraper.kijiji.KijijiScraper;

public class AppleWatch extends KijijiScraper implements Scraper {

	public AppleWatch(String userUrls, Connection dbConnection) {
		super(userUrls, dbConnection);
		// TODO Auto-generated constructor stub
	}

}
