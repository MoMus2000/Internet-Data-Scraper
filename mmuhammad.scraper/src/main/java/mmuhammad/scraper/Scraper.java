package mmuhammad.scraper;

import java.util.List;

import org.jsoup.nodes.Element;

public interface Scraper {
	public void requestSite(String url);
	public void scrape();
	public List<Element> parseData(String className, List<Element> dataList);
}
