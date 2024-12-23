package bmi_calculator;

import java.io.*;
import java.util.*;

/**
 * The BMICSVReader class reads BMI Description from a config file, then transfer that information
 * to the BMIDiscription class.
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version Version 1
 * Final Project
 * Due Date: 11/12/24
 * Sources used: Stack Overflow, https://www.calculator.net/fitness-and-health-calculator.html
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class BMICSVReader {
	
	// file name
	private static final String FILE = "BMIDescription.txt";
	// this is to end the content of one category
	private static final String TERMINATOR = "-----";
	// read the content and place them into a map with proper categories
	private Map<String, StringBuilder> bmiToDescription;
	/**
	 * create a map, then reads the file and add them into the map
	 */
	public BMICSVReader()
	{
		bmiToDescription = new HashMap<>();
		fillMap();
		readFile();
	}
	/**
	 * read the file, throw proper exception if needed
	 */
	private void readFile()
	{
		final String fileNotFound = "Place \"BMIDescription.txt\" at the same level as src and bin.";
		try (Scanner fileRead = new Scanner(new File(FILE)))
		{
			readEach(fileRead);
			
		} catch (FileNotFoundException e)
		{
			System.out.println(fileNotFound);
			System.exit(0);
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	/**
	 * read every line and parse them into a proper category
	 * @param fileRead pass in the scanner of the file
	 * @throws IOException when the map does not have all contents of all categories
	 */
	private void readEach(Scanner fileRead) throws IOException
	{
		// reader for all categories
		StringBuilder currentStringBuilder = null;
		while (fileRead.hasNextLine())
		{
			// get the next line
			String currentLine = fileRead.nextLine();
			// if that line is an actual category, assign the main reader to the value of that key
			// and start the reading process
			if (bmiToDescription.containsKey(currentLine.trim()))
			{
				currentStringBuilder = bmiToDescription.get(currentLine.trim());
			}
			// if that line is empty and no category was recorded, then skip that line
			else if (currentLine.isEmpty() && currentStringBuilder == null)
				continue;
			// if that line is empty but a category has already been recorded, then make that a new paragraph
			else if (currentLine.isEmpty() && currentStringBuilder != null)
			{
				currentStringBuilder.append("\n\n" + " ".repeat(5));
			}
			// if the line contains the terminating factor, the main reader releases that StringBuilder's value
			else if (currentLine.contains(TERMINATOR))
			{
				currentStringBuilder = null;
			}
			// if stringbuilder is not null and line is not empty, then append the current line into the string builder
			else if (currentStringBuilder != null)
			{
				currentStringBuilder.append(currentLine);
			}
			
			// Handle the case when a line does not belong to any category
	        else {
	            throw new IOException("Line does not belong to any known BMI category: " + currentLine);
	        }
			
			
		}
		// if not all catgories were recorded, throw an IOException
		for (Map.Entry<String, StringBuilder> entry : bmiToDescription.entrySet())
		{
			if (entry.getValue() == null)
				throw new IOException("File maintains category not exists, or it lacks some/ all 7 categories");
		}
	}
	/**
	 * fill the initial Map, with some space to indent for the very first line
	 */
	private void fillMap()
	{
		for (BMIResultPanel.Assessment item : BMIResultPanel.Assessment.values())
		{
			bmiToDescription.put(item.getTitle(), new StringBuilder(" ".repeat(5)));
		}
	}
	/**
	 * returns the map to the BMIDiscription object
	 */
	public Map<String, StringBuilder> getBMIMap()
	{
		return bmiToDescription;
	}
	
}
