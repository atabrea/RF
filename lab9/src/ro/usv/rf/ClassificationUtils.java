package ro.usv.rf;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassificationUtils {
	
	public static int[] classifySet(String[][] learningSet, int nrOfClasses)
	{
		int[] iClass = new int[learningSet.length];
		String[][] kernels = new String[nrOfClasses][learningSet[0].length];
		
		for(int i = 0; i < nrOfClasses; i++) {
			kernels[i] = learningSet[i];
		}
		
		
		boolean done = false;
		while(!done) {
			done = true;
			
			for(int i = 0; i < learningSet.length; i++) {
				double minDistance = Double.MAX_VALUE;
				for(int j = 0; j < kernels.length; j++) {
					double distance = calculateDistance(learningSet[i], kernels[j]);
					if(distance < minDistance) {
						minDistance = distance;
						iClass[i] = j;
					}
				}
			}
			
			// modify kernels
			
		}
		
		return iClass;
	}
	
	private static double calculateDistance(String[] pattern1, String[] pattern2) {

		double[] _pattern1 = Arrays.stream(pattern1)
                .mapToDouble(Double::parseDouble)
                .toArray();
		double[] _pattern2 = Arrays.stream(pattern2)
                .mapToDouble(Double::parseDouble)
                .toArray();
		return DistanceUtils.calculateSimpleEuclidianDistance(_pattern1, _pattern2);
	}

}
