package electronics;

import java.sql.Connection;

import mmuhammad.scraper.Scraper;
import mmuhammad.scraper.kijiji.KijijiScraper;

public class PS5 extends KijijiScraper implements Scraper {

	public PS5(String type, String userUrls, Connection dbConnection) {
		super(type, userUrls, dbConnection);
		// TODO Auto-generated constructor stub
	}


}
