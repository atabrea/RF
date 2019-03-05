package ro.usv.rf;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StatisticsUtils 
{

	protected static double calculateFeatureAverage(Double[] feature) {
		Map<Double, Integer> counterMap = getFeatureDistincElementsCounterMap(feature);
		double featureAverage = 0;

		double sum1 = 0;
		double sum2 = 0;

		sum1 = counterMap.keySet().stream()
				.mapToDouble(x -> calculateSum1(x, counterMap.get(x))).sum();
		sum2 = counterMap.values().stream()
				.mapToInt(x -> x).sum();
		featureAverage = sum1 / sum2;
		System.out.println("The feature average is: " +  featureAverage);
		return featureAverage;
}
	
	protected static Map<Double, Integer> getFeatureDistincElementsCounterMap(Double feature[])
	{
		Map<Double, Integer> counterMap = new HashMap<Double, Integer>();
		for (int j = 0; j < feature.length; j++) {
			if (counterMap.containsKey(feature[j])) {
				int count = counterMap.get(feature[j]);
				counterMap.put((feature[j]), ++count);
			} else {
				counterMap.put((feature[j]), 1);
			}
		}
		return counterMap;
	}
	
	private static Double calculateSum1(double value, int count)
	{
		return count * value;
	}
	
	private static Double calculateSum1(double value, double weight)
	{
		return weight * value;
	}
	
	private static Double calculateDif(double value, double weight)
	{
		return value - weight;
	}

	protected static double calculateFeatureWeightedAverage(Double[] feature, Double[] weights) {
		double featureWeightedAverage = 0.0;
		Map<Double, Double> weightsMap = getFeatureElementsSumWeightsMap(feature, weights);
		
		double sum1 = 0;
		double sum2 = 0;

		sum1 = weightsMap.keySet().stream()
				.mapToDouble(x -> calculateSum1(x, weightsMap.get(x))).sum();
		sum2 = weightsMap.values().stream()
				.mapToDouble(x -> x).sum();
		featureWeightedAverage = sum1 / sum2;
		
		return featureWeightedAverage;
	}
	
	protected static Map<Double, Double> getFeatureElementsSumWeightsMap(Double feature[], Double[] weights)
	{
		Map<Double, Double> weightsMap = new HashMap<Double, Double>();
		for (int j = 0; j < feature.length; j++) {
			if (weightsMap.containsKey(feature[j])) {
				double weight = weightsMap.get(feature[j]);
				weightsMap.put((feature[j]), weight + weights[j]);
			} else {
				weightsMap.put((feature[j]), weights[j]);
			}
		}
		return weightsMap;
	}
	
	protected static double calculateFrequencyOfOccurence(Map<Double, Integer> counterMap, double featureElement) {
		double frequencyOfOccurence = 0.0;
		
		
		double sum = counterMap.values().stream()
			.mapToDouble(x -> x).sum();
		double sum2 = counterMap.get(featureElement);
		
		frequencyOfOccurence = sum2 / sum;
		
		return frequencyOfOccurence;
	}
	
	protected static double calculateFeatureDispersion(Double[] feature, double featureWeightedAverage) {
		double featureDispersion = 0.0;
		
		double n = feature.length - 1;
		double featureAvg = calculateFeatureAverage(feature);
		
		double sum = Stream.of(feature)
			.mapToDouble(x -> Math.pow(calculateDif(x, featureAvg), 2)).sum();
		
		featureDispersion = sum / n;
		
		return featureDispersion;
	}
	
	protected static double calculateCovariance (Double[] feature1, Double[] feature2,
			double feature1WeightedAverage,double feature2WeightedAverage) {
		double covariance = 0.0;
		// your code here
		return covariance;
	}
	
	protected static double calculateCorrelationCoefficient  (double covariance, double feature1Dispersion, 
			double feature2Dispersion ) {
		double correlationCoefficient = 0.0;
		// your code here
		return correlationCoefficient;
	}
	
	protected static double calculateAverageSquareDeviation (double featureDispersion ) {
		double averageSquareDeviation = 0.0;
		// your code here
		return averageSquareDeviation;
	}
}
