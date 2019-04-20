package ro.usv.rf;

import ro.usv.rf.entities.Distances;

public class DistanceUtils {
	
	protected static Distances getDistances(double[] pattern1, double pattern2[], int nrOfPatterns)
	{
		Distances distances = new Distances();
		
		for (int feature = 0; feature<pattern1.length; feature ++)
		{
			
		}
		
		return distances;
	}
	
	protected static double calculateSimpleEuclidianDistance(double[] pattern1, double pattern2[])
	{
		double distance = 0.0;
		for (int feature = 0; feature<pattern1.length-1; feature ++)
		{
			distance += Math.pow((pattern1[feature] - pattern2[feature]), 2);
		}		
		return Math.floor(Math.sqrt(distance)*10000)/10000;
	}
	
	protected static double calculateSimpleEuclidianDistance(double[] pattern1, String[] pattern2)
	{
		double distance = 0.0;
		for (int feature = 0; feature<2; feature ++)
		{
			distance += Math.pow((pattern1[feature] - Double.valueOf(pattern2[feature])), 2);
		}		
		return Math.sqrt(distance);
	}
	
	protected static double[][] calculateDistanceMatrix(double[][] learningSet)
	{
		int numberOfPatterns = learningSet.length;
		double[][] distanceMatrix = new double[numberOfPatterns][numberOfPatterns];
		for (int i = 0; i < numberOfPatterns; i++)
		{
			for (int j = i+1; j < numberOfPatterns; j++)
			{
				distanceMatrix[i][j] = calculateSimpleEuclidianDistance(learningSet[i], learningSet[j]);
				distanceMatrix[j][i]=distanceMatrix[i][j];
			}
		}
		
		for (int i = 0; i < numberOfPatterns; i++)
		{
			for (int j = 0; j < numberOfPatterns; j++)
			{
				System.out.print(distanceMatrix[i][j] + " ");
			}
			System.out.println();
		}
		return distanceMatrix;
	}
	
	protected static double calculateCebisevDistance(double[] pattern1, double pattern2[])
	{
		double distance = 0.0;
		for (int feature = 0; feature<pattern1.length; feature ++)
		{
			double currentValue = Math.abs(pattern1[feature] - pattern2[feature]);
			if (currentValue > distance)
			{
				distance = currentValue;
			}
		}		
		return distance;
	}
	
	protected static double calculateCityBlockDistance(double[] pattern1, double pattern2[]) {
		double distance = 0.0;
		for (int feature = 0; feature < pattern1.length; feature++) {
			double currentValue = Math.abs(pattern1[feature] - pattern2[feature]);
			distance += currentValue;
		}
		return distance;
	}
	protected static double calculateMahalanobis(double[] pattern1, double pattern2[], int nrOfPatterns) {
		double distance = 0.0;
		for (int feature = 0; feature<pattern1.length; feature ++)
		{
			distance += Math.pow((pattern1[feature] - pattern2[feature]), nrOfPatterns);
		}	
		return Math.pow(distance, (double)1/nrOfPatterns);
	}
} 
 