package bmi_calculator;

import java.awt.*;
import javax.swing.*;

/**
 * The BMIResultPanel class displays and visualizes BMI results.
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
public class BMIResultPanel extends JPanel implements BMIListener {
	/**
	 * this enum provides every numerical information about all bmi categories
	 * if the bounds don't exist then they will be null
	 */
	public enum Assessment {
		SEVERE_THINNESS("Severe thinness", null, 16.0),
		MODERATE_THINNESS("Moderate Thinness", 16.0, 17.0),
		MILD_THINNESS("Mild Thinness", 17.0, 18.5),
		NORMAL("Normal", 18.5, 25.0),
		OVERWEIGHT("Overweight", 25.0, 30.0),
		OBESE_CLASS_1("Obese Class I", 30.0, 35.0),
		OBESE_CLASS_2("Obese Class II", 35.0, 40.0),
		OBESE_CLASS_3("Obese Class III", 40.0, null);
		
		private String title;
		private Double lowerBound;
		private Double upperBound;
		/**
		 * assigns value
		 * @param title title
		 * @param lowerBound lower bound
		 * @param upperBound upper bound
		 */
		private Assessment(String title, Double lowerBound, Double upperBound)
		{
			this.title = title;
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
		}
		/**
		 * get the title
		 * @return title
		 */
		public String getTitle()
		{
			return title;
		}
		/**
		 * takes in a bmi value, returns the proper category
		 * @param bmiValue bmi
		 * @return category
		 */
		public static String determineAssessment(double bmiValue)
		{
			String unk = "Unknown Assessment";
			for (Assessment item : Assessment.values())
			{
				if ((item.lowerBound == null || Double.compare(bmiValue, item.lowerBound) >= 0)
						&& (item.upperBound == null) || Double.compare(bmiValue, item.upperBound) < 0)
					return item.title;
			}
			return unk; // or some default value
		}
	}
	
	// all labels and stuff
	private JLabel resultLabel, assessmentGeneralTermLabel, primeLabel;
	
	// panels
	private JPanel resultMainPanel, primePanel;
	private BMIVisualScale visualizingPanel;
	
	// text description
	private BMIDiscription bMIDescription;
	/**
	 * sets up, layout every component...
	 */
	public BMIResultPanel()
	{
		// ... 2 font sizes...
		int fontSizeBig = 18;
		int fontSizeSmall = 12;
		// ... border ...
		this.setBorder(BorderFactory.createTitledBorder("Your result"));
		// instantiate labels
		resultLabel = new JLabel(BMICalculator.RESET_STATE);
		resultLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));
		assessmentGeneralTermLabel = new JLabel();
		assessmentGeneralTermLabel.setFont(new Font("Arial", Font.PLAIN, fontSizeSmall));
		primeLabel = new JLabel(BMICalculator.RESET_STATE);
		primeLabel.setFont(new Font("Arial", Font.BOLD, fontSizeBig));

		
		// instantiate panels
		resultMainPanel = new JPanel();
		visualizingPanel = new BMIVisualScale();
		primePanel = new JPanel();
		
		
		primePanel.add(primeLabel);

		
		// setLayout
		resultMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		resultMainPanel.add(resultLabel);
		resultMainPanel.add(assessmentGeneralTermLabel);
		
		//   ---> TODO: set up the chart visualization for BMI
		this.setSize(800, 800);
		// set layout, add components
		this.setLayout(new BorderLayout(1, 1));
		this.add(resultMainPanel, BorderLayout.NORTH);
		this.add(visualizingPanel, BorderLayout.CENTER);
		this.add(primePanel, BorderLayout.SOUTH);
		
		// instantiate description
		bMIDescription = new BMIDiscription();
		
	}
	/**
	 * trigger display
	 */
	@Override
	public void triggerBMIDisplay(String bmiDisplay)
	{
		resultLabel.setText(bmiDisplay);
	}
	/**
	 * shows the actual bmi value, trigger the text area to display proper description
	 */
	@Override
	public void showActualBMI(Double bmiValue)
	{
		// bmiValue is not null --> code is functional
		if (bmiValue != null)
		{
			// display general assessment
			assessmentGeneralTermLabel.setText(String.format("(%s)", Assessment.determineAssessment(bmiValue)));
			// set the cursor for the visualizing panel
			visualizingPanel.setBMICursor(bmiValue);
			// trigger description display
			bMIDescription.setTextArea(Assessment.determineAssessment(bmiValue));
			// text should be at the start
			bMIDescription.setCaretPosition();
		}
		else
		{
			// bmiValue is null --> reset everything and display error
			assessmentGeneralTermLabel.setText("");
			visualizingPanel.setBMICursor(0);
			bMIDescription.clear();
		}
	}
	/**
	 * show bmi prime
	 */
	@Override
	public void showBMIPrime(double bmiPrime)
	{
		primeLabel.setText(String.format("BMI Prime = %.1f", bmiPrime));
	}
	/**
	 * get the description panel to add it to the text area to the south
	 * @return description panel
	 */
	public JPanel getDescriptionPanel()
	{
		return bMIDescription;
	}

}
