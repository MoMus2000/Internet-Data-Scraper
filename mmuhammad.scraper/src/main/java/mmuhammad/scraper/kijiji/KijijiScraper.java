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
import model.Model;
import model.Kijiji.KijijiModel;

public abstract class KijijiScraper implements Scraper {

	public String userUrls;
	
	public final int NUMBEROFPAGES = 10; 
	
	public Document doc;
	
	Timer time = new Timer();
	
	public List<Element> titles;
	public List<Element> descriptions;
	public List<Element> addresses;
	public List<Element> prices;
	public List<Element> links;
	public List<Model> scrapedData;
	
	public Set<String> urlSet = new HashSet<String>();
	
	
	public KijijiScraper(String userUrls) {
		this.userUrls = userUrls;
		this.titles = new ArrayList<Element>();
		this.descriptions = new ArrayList<Element>();
		this.addresses = new ArrayList<Element>();
		this.prices = new ArrayList<Element>();
		this.links = new ArrayList<Element>();
		this.scrapedData = new ArrayList<Model>();
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
	
	public List<Model> scrape() {
		
		for(int i=0; i<NUMBEROFPAGES; i++) {
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String currentUrl = userUrls;
		
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
			
			for(int j=0; j<this.prices.size(); j++) {
				String p;
				String t;
				String d= "";
				String a= "";
				
				p = this.filterPrice(this.prices.get(j));
				t = this.filterPrice(this.titles.get(j));
				
				try{d = this.descriptions.get(j).text();}catch(Exception e){}
				try{a = this.addresses.get(j).text();}catch(Exception e) {}
				
				d="";			
				
				this.scrapedData.add(new KijijiModel(t, p, d, a, this.getClass().getName().toString()));
			}
			
		}
		
		return this.scrapedData;
		
	}
	
	public String filterPrice(Element e) {
		String number = e.text().toString().replace("$", "").replaceAll(",", "");
		if(isNumeric(number)) {
			return number;
		}
		return "NAN";
	}
	
	
	public static boolean isNumeric(String str) { 
		try {  
		    Double.parseDouble(str);  
		    return true;
		  } 
		catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	
	public String filterTitles(Element e) {
		return e.getElementsByClass("title").text();
	}

}
