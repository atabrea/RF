package ro.usv.rf;

import java.util.Arrays;

public class DistanceUtils {
	public static double euclidianDistance(double[] pattern, double[] pattern2 ) {
		double sum = 0;
		for(int i = 0; i < pattern.length; i++) {
			sum += pattern[i] + pattern2[i]; 
		}
		return Math.sqrt(sum);
	}
}
