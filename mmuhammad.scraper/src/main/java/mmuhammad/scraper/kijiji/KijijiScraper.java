package mmuhammad.scraper.kijiji;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import mmuhammad.scraper.Scraper;

public abstract class KijijiScraper implements Scraper {

	public String type;
	public String[] userUrls;
	
	public Document doc;
	
	Timer time = new Timer();
	
	public List<Element> titles;
	public List<Element> descriptions;
	public List<Element> addresses;
	public List<Element> prices;
	public List<Element> links;
	
	public Set<String> urlSet = new HashSet<String>();
	
	
	public KijijiScraper(String type, String[] userUrls) {
		this.type = type;
		this.userUrls = userUrls;
		this.titles = new ArrayList<Element>();
		this.descriptions = new ArrayList<Element>();
		this.addresses = new ArrayList<Element>();
		this.prices = new ArrayList<Element>();
		this.links = new ArrayList<Element>();
	}
	
	public void requestSite(String url) {
		try {
			this.doc = Jsoup.connect(url)
					.get();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Element> parseData(String className, List<Element> dataList) {
		Elements elem = this.doc.getElementsByClass(className);
		for(Element e: elem) {
			dataList.add(e);
		}
		return dataList;
	}
	
	public void scrape() {
		for(int h=0; h<this.userUrls.length; h++) {
			System.out.println("Orignal \n"+userUrls[h]);
			
			for(int i=0; i<200; i++) {
				
				try {
					TimeUnit.SECONDS.sleep(5);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				String currentUrl = userUrls[h];
			
				currentUrl = currentUrl.replace("{i}", i+"");
				
				this.requestSite(currentUrl);
				
				this.urlSet.add(this.doc.baseUri());
				
				if(!this.urlSet.contains(currentUrl) && i > 1) {
					System.out.println("EARLY STOPPAGE - URL");
					System.out.println();
					break;
				}			
				
				System.out.println(currentUrl);
				
				this.prices = parseData("price", this.prices);
				this.descriptions = parseData("description", this.descriptions);
				this.addresses = parseData("location", this.addresses);
				this.titles = parseData("info-container", this.titles);
				
				for(Element price : this.prices) {
	//				System.out.println(price.text());
				}
				
				for(Element descriptions : this.descriptions) {
	//				System.out.println(descriptions.text());
				}
				
				for(Element addresses : this.addresses) {
	//				System.out.println(addresses.text());
				}
				
				for(Element titles : this.titles) {
	//				System.out.println(titles.text());
				}
			}
		}
		
	}
	
	public void filterPrice() {}
	
	public abstract void saveToDB();

}
