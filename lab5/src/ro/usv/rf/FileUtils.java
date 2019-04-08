package ro.usv.rf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils 
{
	private static final String inputFileValuesSeparator = ",";
	private static final String outputFileValuesSeparator = ",";
	
	protected static Object[][] readLearningSetFromFile(String fileName) throws USVInputFileCustomException
	{
		//Start with an ArrayList<ArrayList<Double>>
		List<ArrayList<Object>> learningSet = new ArrayList<ArrayList<Object>>();
		// read file into stream, try-with-resources
		try  {
			Stream<String> stream = Files.lines(Paths.get(fileName));
			learningSet = stream.skip(1).map(FileUtils::convertLineToLearningSetRow).collect(Collectors.toList());
		} 
		catch (FileNotFoundException fnfe)
		{
			throw new USVInputFileCustomException(" We cannot find the scepicified file on USV computer");
		}	
		catch (IOException ioe) {
			throw new USVInputFileCustomException(" We encountered some errors while trying to read the specified file: " + ioe.getMessage());
		}
		catch (Exception e) {
			throw new USVInputFileCustomException(" Other errors: " + e.getMessage());
		}	
		//  convert ArrayList<ArrayList<Double>> to double[][] for performance
		return convertToBiDimensionalArray(learningSet);
	}
	
	private static Object[][] convertToBiDimensionalArray(List<ArrayList<Object>> learningSet) {
		
		Object[][] learningSetArray = new Object[learningSet.size()][];
		
		for (int n = 0; n < learningSet.size(); n++) {
			ArrayList<Object> rowListEntry = learningSet.get(n);
			
			// get each row double values
			Object[] rowArray = new Object[learningSet.get(n).size()];
			
			for (int p = 0; p < learningSet.get(n).size(); p++) 
			{				
				rowArray[p] = rowListEntry.get(p);
			}
			learningSetArray[n] = rowArray;
			
		}
		return learningSetArray;
	}
	
	private static ArrayList<Object> convertLineToLearningSetRow(String line)
	{
		ArrayList<Object> learningSetRow = new ArrayList<Object>();
		String[] stringValues = line.split(inputFileValuesSeparator);
		//we need to convert from string to double
		for (int p = 0; p < stringValues.length; p++)
		{
			try 
			{
				learningSetRow.add(Double.valueOf(stringValues[p]));
			}
			catch(NumberFormatException e)
			{
				learningSetRow.add(stringValues[p]);
			}
		}
		return learningSetRow;
	}
	
	protected static void writeLearningSetToFile(String fileName, double[][] normalizedSet)
	{
		// first create the byte array to be written
		StringBuilder stringBuilder = new StringBuilder();
		for(int n = 0; n < normalizedSet.length; n++) //for each row
		{
			//for each column
			 for(int p = 0; p < normalizedSet[n].length; p++) 
			 {
				//append to the output string
				 stringBuilder.append(normalizedSet[n][p]+"");
				 //if this is not the last row element
			      if(p < normalizedSet[n].length - 1)
			      {
			    	  //then add separator
			    	  stringBuilder.append(outputFileValuesSeparator);
			      }
			 }
			//append new line at the end of the row
			 stringBuilder.append("\n"); 
		}
		try {
			Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
