package mmuhammad.scraper;

import java.util.List;

import org.jsoup.nodes.Element;

import model.Model;

public interface Scraper {
	public void requestSite(String url);
	public List<Model> scrape();
	public List<Element> parseData(String className, List<Element> dataList);
}
