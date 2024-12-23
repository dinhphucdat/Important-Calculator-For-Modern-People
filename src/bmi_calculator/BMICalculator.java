package bmi_calculator;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

/**
 * The BMICalculator class provides the calculator and also the main control for other GUI components.
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
public class BMICalculator extends JPanel {
	
	// welcome message or message after hitting clear
	public static final String RESET_STATE = "Come on calculating your BMI!";
	
	// BMI Model
	private BMIModel mainModel;
	
	// minimal components
	private JTextField ageField, heightFeetField, heightInchField, weightField;
	private JRadioButton maleCheckButton, femaleCheckButton;
	private ButtonGroup genderButtons;
	private JButton calculateActButton, clearActButton;
	private JLabel ageLabel, genderLabel, heightLabel, 
				feetUnitLabel, inchUnitLabel, weightLabel, poundUnitLabel;
	
	// bigger containers
	private JPanel ageGroup, genderGroup, heightGroup, weightGroup, actionGroup;
	
	// trans-container
	private BMIListener displayContainer;
	
	/**
	 * create a new calculator with every component layed out neatly
	 */
	public BMICalculator()
	{
		// instantiate main model
		mainModel = new BMIModel(0, 0, 0);
		
		// instantiate JTextField
		instantiateTextFields();
		
		// instantiate RadioButton & register into ButtonGroup
		instantiateRadioButtons();
		
		// instantiate Act Buttons
		instantiateActButtons();
		
		// instantiate Labels
		instantiateLabels();
		
		// layout everything ...
		layoutEverything();
		
		// pack, adapt to the size of components
		// setSize(300, 200);
	}
	/**
	 * add another component that can hear out this control
	 * @param displayContainer another component
	 */
	public void addBMIListener(BMIListener displayContainer)
	{
		this.displayContainer = displayContainer;
	}
	/**
	 * instantiates all the text fields in the GUI
	 */
	public void instantiateTextFields()
	{
		// small text field size
		int smallFieldCharWide = 5;
		// bigger text field size
		int bigFieldCharWide = 3 * smallFieldCharWide;
		ageField = new JTextField(bigFieldCharWide);
		heightFeetField = new JTextField(smallFieldCharWide);
		heightInchField = new JTextField(smallFieldCharWide);
		weightField = new JTextField(bigFieldCharWide);
	}
	/**
	 * instantiates radio buttons for gender, then add them into the button group
	 */
	public void instantiateRadioButtons()
	{
		// instantiates radio buttons
		maleCheckButton = new JRadioButton("Male");
		femaleCheckButton = new JRadioButton("Female");
		genderButtons = new ButtonGroup();
		// add them into a button group to prevent multiple selections
		genderButtons.add(maleCheckButton);
		genderButtons.add(femaleCheckButton);
		maleCheckButton.setSelected(true);
	}
	/**
	 * if we press calculates, a chain of actions will happen...
	 */
	public void instantiateActButtons()
	{
		// ...instantiates all buttons
		calculateActButton = new JButton("Calculate");
		clearActButton = new JButton("Clear");
		// ...add action listener for "calculate"...
		calculateActButton.addActionListener(new ActionListener() {
			// error messages
			final String NUMBER_FORMAT_ERROR = "Alert: invalid input!";
			final String GUIDE_IF_ERROR = "Age, height in feet and inch are positive integers. "
					+ "Weight in pounds can be a positive floating number";
			/**
			 * Age, height in feet and inch are positive integers. 
			 * Weight in pounds can be a positive floating number.
			 * If not, then an error message will be displayed
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					// get information from the text field...
					if(Integer.parseInt(ageField.getText()) <= 0)
						throw new IllegalArgumentException();
					int heightFeet = Integer.parseInt(heightFeetField.getText());
					int heightInch = Integer.parseInt(heightInchField.getText());
					double weightPound = Double.parseDouble(weightField.getText());
					// ...pass them into the main model
					mainModel.setHeightFeet(heightFeet);
					mainModel.setHeightInch(heightInch);
					mainModel.setWeightPound(weightPound);
					// ...controls the other components we just added into this main control
					displayContainer.triggerBMIDisplay(mainModel.toString());
					displayContainer.showActualBMI(mainModel.computeBMI());
					displayContainer.showBMIPrime(mainModel.computePrime());
				}
				catch (NumberFormatException ex)
				{
					// ...display short error alert
					displayContainer.triggerBMIDisplay(NUMBER_FORMAT_ERROR);
					// ...the distinction between if an exception is thrown is that 
					// if there is a real Object or not
					displayContainer.showActualBMI(null);
					displayContainer.showBMIPrime(0);
					// error log window
					JOptionPane.showMessageDialog(BMICalculator.this, GUIDE_IF_ERROR,
							NUMBER_FORMAT_ERROR, JOptionPane.ERROR_MESSAGE);
				}
				catch (IllegalArgumentException ex)
				{
					// same as NumberFormatException
					displayContainer.triggerBMIDisplay(NUMBER_FORMAT_ERROR);
					displayContainer.showActualBMI(null);
					displayContainer.showBMIPrime(0);
					JOptionPane.showMessageDialog(BMICalculator.this, GUIDE_IF_ERROR,
							NUMBER_FORMAT_ERROR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		/**
		 * if we hit clear...
		 */
		clearActButton.addActionListener(new ActionListener() {
			
			/**
			 * ...clear all text fields, resets everything
			 * @param e ActionEvent
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ageField.setText("");
				heightFeetField.setText("");
				heightInchField.setText("");
				weightField.setText("");
				// reset everything
				displayContainer.triggerBMIDisplay(RESET_STATE);
				displayContainer.showActualBMI(null);
				displayContainer.showBMIPrime(0);
			}
		});
	}
	/**
	 * instantiates all the labels
	 */
	public void instantiateLabels()
	{
		ageLabel = new JLabel("Age");
		genderLabel = new JLabel("Gender");
		heightLabel = new JLabel("Height");
		feetUnitLabel = new JLabel("feet");
		inchUnitLabel = new JLabel("inch");
		weightLabel = new JLabel("Weight");
		poundUnitLabel = new JLabel("lbs");
	}
	/**
	 * get everything layed out after being instantiated
	 */
	public void layoutEverything()
	{
		// set Layout & border for the main panel
		this.setLayout(new GridLayout(5, 1));
		this.setBorder(BorderFactory.createTitledBorder("BMI Calculator here"));
		
		// set Age layout
		ageGroup = new JPanel();
		ageGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		ageGroup.add(ageLabel);
		ageGroup.add(ageField);
		
		// set Gender layout
		genderGroup = new JPanel();
		genderGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		genderGroup.add(genderLabel);
		genderGroup.add(maleCheckButton);
		genderGroup.add(femaleCheckButton);
		
		// set the Height layout
		heightGroup = new JPanel();
		heightGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		heightGroup.add(heightLabel);
		heightGroup.add(heightFeetField);
		heightGroup.add(feetUnitLabel);
		heightGroup.add(heightInchField);
		heightGroup.add(inchUnitLabel);
		
		// set the Weight layout
		weightGroup = new JPanel();
		weightGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
		weightGroup.add(weightLabel);
		weightGroup.add(weightField);
		weightGroup.add(poundUnitLabel);
		
		// set Action Buttons layout
		actionGroup = new JPanel();
		actionGroup.setLayout(new FlowLayout(FlowLayout.CENTER));
		actionGroup.add(calculateActButton);
		actionGroup.add(clearActButton);
		
		// Bring everything together
		this.add(ageGroup);
		this.add(genderGroup);
		this.add(heightGroup);
		this.add(weightGroup);
		this.add(actionGroup);
	}

}
