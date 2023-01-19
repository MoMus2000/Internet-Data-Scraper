package model.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.Model;

public class MetricCalc implements IMetrics{

	List<Double> metric = new ArrayList<Double>();
	
	public MetricCalc(List<Model> data) {
		System.out.println("Intially the size was "+ data.size());
		for(Model mod : data) {
			try {
				if(mod.price == "NAN") continue;
				this.metric.add(Double.parseDouble(mod.price));
			}
			catch(Exception e){
				System.out.println(e);
				continue;
			}  
		}
		this.removeOutliers();
		System.out.println("After removing outliers the size was + "+ this.metric.size());
		this.calculateMetrics();
	}
	
	public Double minPrice() {
		return Collections.min(this.metric);
	}

	public Double maxPrice() {
		return Collections.max(this.metric);
	}
	
	public Double medianPrice() {
        return this.getMedian( this.metric.stream().mapToDouble(Double::doubleValue).toArray() );
    }

	public Double meanPrice() {
		return this.metric.stream().mapToDouble(a->a).average().orElse(0.0);
	}

	public void removeOutliers() {
		System.out.println("SORTING");
		Collections.sort(this.metric);
		double[] data = this.metric.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
		double[] data1 = null;
        double[] data2 = null;
        if (data.length % 2 == 0) {
            data1 = Arrays.copyOfRange(data, 0, data.length / 2);
            data2 = Arrays.copyOfRange(data, data.length / 2, data.length);
        } else {
            data1 = Arrays.copyOfRange(data, 0, data.length / 2);
            data2 = Arrays.copyOfRange(data, data.length / 2 + 1, data.length);
        }
        double q1 = this.getMedian(data1);
        double q3 = this.getMedian(data2);
        
        double iqr = q3 - q1;
        double lowerFence = q1 - 1.5 * iqr;
        double upperFence = q3 + 1.5 * iqr;
        
        this.metric.removeIf(element -> (element <= lowerFence));
        this.metric.removeIf(element -> (element >= upperFence));
	}
	
	public double getMedian(double[] data1) {
        if (data1.length % 2 == 0)
            return (data1[data1.length / 2] + data1[data1.length / 2 - 1]) / 2;
        else
            return data1[data1.length / 2];
    }

	public void calculateMetrics() {
		System.out.println("The minimum price : "+ this.minPrice());
		System.out.println("The max price : " + this.maxPrice());
		System.out.println("The median price : " + this.medianPrice());
		System.out.println("The mean price : "+ this.meanPrice());
	}

}
