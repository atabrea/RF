package ro.usv.rf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class DistanceUtils {
	public static double calculateEuclidianDistance(double []x, Object []y)
	{
		double sum = 0;
		int size = Math.min(x.length, y.length);
		for(int i = 0; i < size; i++)
		{
			sum += Math.pow((Double)x[i] - (Double)y[i], 2);
		}
		return Math.sqrt(sum);
	}

	public static double[][] calculateDistanceMatrix(double[][] counties, Object[][] learningSet)
	{
		double[][] distanceMatrix = new double[counties.length][learningSet.length];
		for(int i = 0; i < counties.length; i++)
		{
			for(int j = 0; j < learningSet.length; j++)
			{
				distanceMatrix[i][j] = calculateEuclidianDistance(counties[i], learningSet[j]);
			}
		}
		return distanceMatrix;
	}

	public static java.util.Map.Entry<String, Integer> findCounty(int k, double[][] distanceMatrix, Object[][] learningSet, int line)
	{
		TreeMap<Double, Integer> mins = new TreeMap<Double, Integer>();

		int i = 0;
		while(mins.size() < k)
		{
			if(i >= learningSet.length) {
				System.out.println("i too long");
				return null;
			}
			double value = distanceMatrix[line][i];
			mins.put(value, i++);
		}

		for(; i < learningSet.length; i++)
		{
			double value = distanceMatrix[line][i];
			mins.put(value, i);
			ArrayList<Double> keys = new ArrayList<Double>(mins.keySet());
			if(mins.size() > k)
			{
				mins.remove(keys.get(keys.size()-1));
			}
		}

		return classOf(mins.values(), learningSet);
	}

	static java.util.Map.Entry<String, Integer> classOf(Collection<Integer> values, Object[][] learningSet)
	{
		HashMap<String, Integer> knn = new HashMap<String, Integer>();
		for (Integer value : values) {
			String key = (String)learningSet[value][learningSet[value].length - 1];
			if(knn.get(key) == null)
			{
				knn.put(key, 1);
			}
			else 
			{
				knn.put(key, knn.get(key) + 1);
			}
		}

		return knn.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get();
	}
	
	static double TOLERANCE = 0.01;
	static int findMaxK(int kStart, int kEnd, double[][] distanceMatrix, Object[][] learningSet)
	{
		if(kStart >= kEnd)
		{
			return kStart;
		}
		
		int m = (kStart + kEnd) / 2;
		
		double sum = 0;
		for (int i = 0; i < distanceMatrix.length; i++) 
		{
			sum += findCounty(m, distanceMatrix, learningSet, i).getValue() / (double)m;
		}
		
		double avg = sum / distanceMatrix.length;
		if( avg > (0.9 - TOLERANCE) )
		{
			return findMaxK(m, kEnd, distanceMatrix, learningSet);
		}

		return findMaxK(kStart, m, distanceMatrix, learningSet);
	}
}	
