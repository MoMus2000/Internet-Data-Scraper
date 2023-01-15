package mmuhammad.scraper.kijiji;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import mmuhammad.scraper.Scraper;

public abstract class KijijiScraper implements Scraper {

	public String type;
	public String[] userUrls;
	
	public List<String> titles;
	public List<String> descriptions;
	public List<String> addresses;
	public List<String> prices;
	public List<String> links;
	
	public Set<String> urls = new HashSet<String>();
	
	
	public KijijiScraper(String type, String[] userUrls) {
		this.type = type;
		this.userUrls = userUrls;
		this.titles = new ArrayList<String>();
		this.descriptions = new ArrayList<String>();
		this.addresses = new ArrayList<String>();
		this.prices = new ArrayList<String>();
		this.links = new ArrayList<String>();
	}
	
	public void requestSite() {
		try {
			Document doc = Jsoup.connect(
					"https://www.kijiji.ca/b-apartments-condos/markham-york-region/2+bedrooms-basement+apartment/page-0/c37l1700274a27949001a29276001?ll=43.872291%2C-79.482061&address=266+Lady+Valentina+Ave%2C+Maple%2C+ON+L6A+0E1%2C+Canada&ad=offering&radius=68.0"
					)
					.get();
			System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	public abstract void parseData();

}
