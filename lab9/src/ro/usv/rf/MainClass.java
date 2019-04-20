package ro.usv.rf;

public class MainClass {

	public static void main(String[] args) {
		String[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfFeatures = learningSet[0].length;
			String [] iclassNames = {"C1","C2"};
			
			int[] classArray = ClassificationUtils.classifySet(learningSet,iclassNames.length);
			
			for (int i=0; i<classArray.length; i++)
			{
//				System.out.println(iclassNames[classArray[i]] + " ");
				System.out.println(classArray[i]);
			}
			
		} catch (USVInputFileCustomException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
