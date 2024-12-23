package bmi_calculator;

import javax.swing.*;

import java.util.*;
import java.awt.*;

/**
 * The BMIDiscription class receives a map from BMICSVReader and displays proper content.
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version Version 1
 * Final Project
 * Due Date: 11/12/24
 * Sources used: Stack Overflow, https://www.calculator.net/fitness-and-health-calculator.html
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
@SuppressWarnings("serial")
public class BMIDiscription extends JPanel {
	// main description field
	private JTextArea descriptionField;
	// make it scrollable
	private JScrollPane scrollpane;
	// map received from the BMICSVReader
	private Map<String, StringBuilder> bmiMap;
	/**
	 * set layout, get the map, additional setting to display contents
	 */
	public BMIDiscription()
	{
		// set layout
		setLayout(new BorderLayout());
		// get the map
		bmiMap = new BMICSVReader().getBMIMap();
		// border
		setBorder(BorderFactory.createTitledBorder("Your result description"));
		// new text area
		descriptionField = new JTextArea();
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		descriptionField.setEditable(false);
		// make it scrollable
		scrollpane = new JScrollPane(descriptionField);
		// add the final component into this container
		add(scrollpane, BorderLayout.CENTER);
	}
	/**
	 * pass in the category, display the corresponsing description
	 * @param category
	 */
	public void setTextArea(String category)
	{
		descriptionField.setText(bmiMap.get(category).toString());
	}
	/**
	 * ...displayed when user hits clear...
	 */
	public void clear()
	{
		String textDisplayed = "Age, height (in both ft and inch) are integers.\n"
				+ "Weight in pounds could be a floating point number";
		descriptionField.setText(textDisplayed);
	}
	/**
	 * make sure the text start from the top
	 */
	public void setCaretPosition()
	{
		descriptionField.setCaretPosition(0);
	}

}
