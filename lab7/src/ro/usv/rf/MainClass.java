package ro.usv.rf;

import java.util.List;
import java.util.Map;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			System.out.println("Learning set: ");
			Map<Integer, List<double[]>> groupClass = DistanceUtils.groupClass(learningSet);
			double[][] wMatrix = DistanceUtils.calculateWMatrix(groupClass);
			
			double[] element = {1,1,2,1,1};
			System.out.println(DistanceUtils.findClass(element, wMatrix));
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}