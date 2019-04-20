package ro.usv.rf;

public class ClassificationUtils 
{
	protected static int[] classifySet(String[][] entrySet, int numberOfClasses, int numberOfFeatures)
	{
		String[][] kernels = new String[numberOfClasses][numberOfFeatures];
		int[] iClass = new int[entrySet.length];

		boolean done = false;
		
		for (int i = 0 ; i< numberOfClasses; i++)
		{
			kernels[i] = entrySet[i];
		}
				
		while (!done)
		{
			done = true;
			double[][] centersOfGravity = new double[numberOfClasses][numberOfFeatures];
			int[] patternsCount = new int[numberOfClasses];
			int kMin = 0;
			for (int i = 0 ; i< entrySet.length; i++)
			{
				double minDistance = Double.MAX_VALUE;				
				for (int k = 0; k < numberOfClasses; k++)
				{
					double distanceToKernel = calculateDistance(entrySet[i], kernels[k]);
					if (distanceToKernel < minDistance)
					{
						minDistance = distanceToKernel;
						kMin = k;
					}
				}
				patternsCount[kMin]++;
				for (int j = 0 ; j < numberOfFeatures; j++)
				{
					centersOfGravity[kMin][j] += Double.valueOf(entrySet[i][j]);
				}
				if (iClass[i] != kMin)
				{
					iClass[i] = kMin;
					done = false;
				}
			}
			if (!done)
			{
				for (int k = 0; k < numberOfClasses; k++)
				{
					for (int j = 0 ; j < numberOfFeatures; j++)
					{
						centersOfGravity[k][j] /= patternsCount[k];
					}
					String[][] classPatternsSet = new String[patternsCount[k]][numberOfFeatures];
					int counter = 0;
					for (int nr = 0 ; nr < entrySet.length; nr++)
					{
						if (iClass[nr] == k)
						{
							classPatternsSet[counter] = entrySet[nr];
							counter++;
						}
					}
					kernels[k] = updateKernel(classPatternsSet, centersOfGravity[k]);
				}
				
			}
		}
		return iClass;
	}
	

	
//	private static String[] initializeKernel(String[] initialValues)
//	{
//		String[] kernel = new String[initialValues.length];
//		
//		for (int i = 0; i<initialValues.length; i++)
//		{
//			kernel[i] = initialValues[i];
//		}
//		return kernel;
//	}
	
	private static double calculateDistance(String[] currentPattern, double[] otherPattern)
	{
		double sum = 0;
		for (int i = 0; i < currentPattern.length; i++ )
		{
			sum += Math.pow((Double.valueOf(currentPattern[i])- otherPattern[i]), 2);
		}
		return Math.sqrt(sum);
	}
	

	private static double calculateDistance(String[] currentPattern, String[] kernel)
	{
		double sum = 0;
		for (int i = 0; i < currentPattern.length; i++ )
		{
			sum += Math.pow((Double.valueOf(currentPattern[i])- Double.valueOf(kernel[i])), 2);
		}
		return Math.sqrt(sum);
	}
	
	private static String[] updateKernel(String[][] entrySet, double[] centerOfGravity)
	{
		double minDistance = Double.MAX_VALUE;
		int closestPattern = 0;
		for (int i = 0; i<entrySet.length; i++)
		{
			double distanceToKernel = calculateDistance(entrySet[i], centerOfGravity);
			if (distanceToKernel < minDistance)
			{
				minDistance = distanceToKernel;
				closestPattern = i;
			}
				
		}
		return entrySet[closestPattern];
	}
}
