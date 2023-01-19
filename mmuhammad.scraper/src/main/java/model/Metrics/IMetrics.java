package model.Metrics;

public interface IMetrics { 
	public Double minPrice();
	public Double maxPrice();
	public Double medianPrice();
	public Double meanPrice();
	
	public void removeOutliers();
	
	public void calculateMetrics();
}
