package ro.usv.rf;

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
			
			double[][] distanceMatrix = DistanceUtils.calculateDistanceMatrix(learningSet);
			System.out.println("Matricea de distante: ");
			for (double[] line : distanceMatrix) {
				for (double element : line) {
					System.out.print(element + " ");
				}
				System.out.println();
			}
			
			System.out.println("clasa lui 4: " + DistanceUtils.findClass( learningSet, learningSet[learningSet.length-1]));
		
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
