package ro.usv.rf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class DistanceUtils {
	public static Map<Integer, List<double[]>> groupClass(double[][] learningSet) {
		Map<Integer, List<double[]>> classMap = new HashMap<Integer, List<double[]>>();
		
		for (double[] set : learningSet) {
			int key = (int) set[set.length-1];
			List<double[]> classElements = classMap.get(key);
			if(classElements == null) {
				classElements = new ArrayList<double[]>();
				classMap.put(key, classElements);
			}
			classElements.add(set);
		}
		
		return classMap;
	}
	public static double[][] calculateWMatrix(Map<Integer, List<double[]>> groupClass) {
		double[][] wMatrix = new double[groupClass.size()][];
		int pos = 0;
		for (Entry<Integer, List<double[]>> pair : groupClass.entrySet()) {
			List<double[]> classElements = pair.getValue();
			wMatrix[pos] = new double[classElements.size() + 1];
			for(int j = 0; j < classElements.size(); j++) {
				double sum = 0;
				double[] element = classElements.get(j);
				int elementSize = element.length - 1;
				for(int i = 0; i < elementSize; i++) {
					sum += element[i];
				}
				wMatrix[pos][j] = sum / elementSize;
			}
			double sum = 0;
			for(int i = 0; i < wMatrix[pos].length - 1; i++) {
				sum += Math.pow(wMatrix[pos][i], 2);
			}
			wMatrix[pos][wMatrix[pos].length - 1] = sum * (-1.0/2);
			pos++;
		}
		return wMatrix;
	}
	
	public static int findClass(double[] element, double[][] wMatrix) {
		double maxProduct = Double.MIN_VALUE;
		int pos = 0;
		for (int i = 0; i < wMatrix.length; i++) {
			double product = calculateProduct(element, wMatrix[i]);
			if(product > maxProduct) {
				maxProduct = product;
				pos = i;
			}
		}
		return pos;
	}
	
	public static double calculateProduct(double[] v1, double[] v2) {
		double sum = 0;
		for(int i = 0; i < v1.length; i++) {
			sum += v1[i] * v2[i];
		}
		return sum;
	}
}
