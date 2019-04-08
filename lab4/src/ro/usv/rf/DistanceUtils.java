package ro.usv.rf;

public class DistanceUtils {
	public static double calculateEuclidianDistance(double []x, double []y)
	{
		double sum = 0;
		int size = Math.min(x.length, y.length);
		for(int i = 0; i < size; i++)
		{
			sum += Math.pow(x[i] - y[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	public static double[][] calculateDistanceMatrix(double[][] learningSet)
	{
		double[][] distanceMatrix = new double[learningSet.length][learningSet.length];
		for(int i = 0; i < learningSet.length; i++)
		{
			for(int j = 0; j < learningSet.length; j++)
			{
				distanceMatrix[i][j] = Math.floor(calculateEuclidianDistance(learningSet[i], learningSet[j]) * 100) / 100.0;
			}
		}
		return distanceMatrix;
	}
	public static double findClass(double[][] learningSet, double[] line)
	{
		double min = Double.MAX_VALUE;
		int pos = 0;
		for(int i = 0; i < line.length; i++)
		{
			if(line[i] != 0 && min > line[i])
			{
				min = line[i];
				pos = i;
			}
		}
		int classCol = learningSet[pos].length-1;
		return learningSet[pos][classCol];
	}
}	
