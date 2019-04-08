package usv;

import ro.usv.rf.FileUtils;
import ro.usv.rf.USVInputFileCustomException;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			System.out.println("Learning set: ");
			for (double[] line : learningSet) {
				for (double element : line) {
					System.out.print(element + " ");
				}
				System.out.println();
			}
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}