package ro.usv.rf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Algorithm {
	public static void applyKnn(int k, ArrayList<Set<Distance>> distancesArray, String[][] learningSet) {
		double err = 0;
		k = k <= distancesArray.get(0).size() ? k : distancesArray.get(0).size();
		for (Set<Distance> distance : distancesArray) {
			Map<String, Integer> classesMap = new HashMap<String, Integer>();
			distance.stream().limit(k).forEach( d -> {
				String className = learningSet[d.position][learningSet[0].length - 1];
				if(classesMap.get(className) != null) {
					classesMap.put(className, classesMap.get(className) + 1);
				}
				else {
					classesMap.put(className, 1);
				}
			});
			Entry<String, Integer> maxElement = classesMap.entrySet().iterator().next();
			for (Entry<String, Integer> currentElem : classesMap.entrySet() ) {
				if(currentElem.getValue() > maxElement.getValue()) {
					maxElement = currentElem;
				}
			}
			err += maxElement.getValue() / (double)k;
		}
		System.out.println(err / distancesArray.size() * 100);
	}
	
	public static double[] convertPattern(String[] pattern) {
		return Arrays.stream(pattern).limit(pattern.length-1)
			.mapToDouble(Double::parseDouble).toArray();
	}
	
	public static double[][] convertLearningSet(String[][] learningSet) {
		double[][] newLearningSet = new double[learningSet.length][];
		for(int i = 0; i < learningSet.length; i++) {
			newLearningSet[i] = convertPattern(learningSet[i]);
		}
		return newLearningSet;
	}
	
	public static ArrayList<Set<Distance>> generateDistanceArray(String[][] evaluationSet, String[][] trainingSet) {
		double[][] newEvaluationSet = convertLearningSet(evaluationSet);
		double[][] newTrainingSet = convertLearningSet(trainingSet);
		
		ArrayList<Set<Distance>> distances = new ArrayList<Set<Distance>>();
		for (double[] pattern : newEvaluationSet) {
			Set<Distance> distanceSet = new TreeSet<Distance>();
			for (int i = 0; i < newTrainingSet.length; i++) {
				distanceSet.add(new Distance(DistanceUtils.euclidianDistance(pattern, newTrainingSet[i]), i));
			}
			distances.add(distanceSet);
		}
		return distances;
	}
}
