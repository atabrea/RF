package ro.usv.rf;

import java.util.Random;

public class MainClass {


	public static void main(String[] args) {
		Object[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("data.csv");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			double[][] counties = new double[][] {
				{25.89, 47.56},
				{24, 45.15},
				{25.33, 45.44}
			};

			double[][] distanceMatrix = DistanceUtils.calculateDistanceMatrix(counties, learningSet);
			
			int[] ks = new int[] { 9,11,17,31, 12000 };

			for(int i = 0; i < counties.length; i++)
			{
				System.out.println("Pentru setul " + (i + 1) + ":");
				for(int j = 0; j < ks.length; j++)
				{
					System.out.print("  k = " + ks[j] +": ");
					System.out.println(DistanceUtils.findCounty(ks[j], distanceMatrix, learningSet, i));
				}
			}
			
			findKMax(learningSet);
			
			
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finished learning set operations");
		}
	}
	
	public static void findKMax(Object[][] learningSet) {
		Random r = new Random();
		int nrCities = r.nextInt(10);
		double[][] counties = new double[nrCities][2];
		
		if(nrCities > learningSet.length) {
			System.err.println("nrCities too great");
			return;
		}
		
		int citiesStart = r.nextInt(learningSet.length - nrCities);
		int citiesEnd = citiesStart + nrCities;
		
		for(int i = citiesStart; i < citiesEnd; i++) {
			counties[i-citiesStart][0] = (Double)learningSet[i][0];
			counties[i-citiesStart][1] = (Double)learningSet[i][1];
		}
		
		double[][] distanceMatrix = DistanceUtils.calculateDistanceMatrix(counties, learningSet);
		
		System.out.println("K max este " + DistanceUtils.findMaxK(0, learningSet.length, distanceMatrix, learningSet));
	}

}
