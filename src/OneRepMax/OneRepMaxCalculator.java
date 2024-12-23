package OneRepMax;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The OneRepMaxCalculator calculates the user's one rep max based on
 * the weight and number repetitions in an easier lift.
 * @author Martin Miller, Dat Dinh, Nikhil Jangir, Trey Fellner
 * UML Presentation Project
 * Due Date: 12/11/24
 * Help obtained: Used https://www.masterclass.com/articles/one-rep-max-calculator
 * for the formulas for one rep max.
 * Academic honesty statement: We confirm that the above list of sources is complete 
 * AND that we have not talked to anyone else about the solution to this problem.
 */

@SuppressWarnings("serial")
public class OneRepMaxCalculator extends JPanel implements ActionListener {

	// Fields for the GUI
	private JPanel inputPanel;
	private JPanel outputPanel;
	private JLabel promptWeight;
	private JLabel promptReps;
	private JLabel promptFormulaType;
	private JTextField inFieldWeight;
	private JTextField inFieldReps;
	private JTextArea display;
	private JButton calculateButton;
	private JComboBox<String> formulaDropdown;
	// Constants for calculator
	private static final String[] FORMULA_OPTIONS = {"Epley", "Brzycki", "Lombardi"};
	private static final Font PROGRAM_FONT = new Font("Arial", Font.PLAIN, 18);
	   
	/**
	 * The OneRepMaxCalculator constructor initializes the GUI for the
	 * one rep max calculator
	 */
	public OneRepMaxCalculator()
	{
		// Border layout for output and input
		setLayout(new BorderLayout());  
		// Initialize labels, text fields for input
		inputPanel = new JPanel();
		promptWeight = new JLabel(" Weight Lifted in Pounds:");
		promptWeight.setFont(PROGRAM_FONT);
		promptReps = new JLabel(" Number of Repetitions: ");
		promptReps.setFont(PROGRAM_FONT);
		promptFormulaType = new JLabel(" Formula to Use:");
		promptFormulaType.setFont(PROGRAM_FONT);
		inFieldWeight = new JTextField();
		inFieldWeight.setFont(PROGRAM_FONT);
		inFieldReps = new JTextField();
		inFieldReps.setFont(PROGRAM_FONT);
		calculateButton = new JButton("Calculate");
		calculateButton.setFont(PROGRAM_FONT);
	    calculateButton.addActionListener(this);
	    formulaDropdown = new JComboBox<>(FORMULA_OPTIONS);
	    formulaDropdown.setFont(PROGRAM_FONT);
	    // Set up layout for input
	    inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
	    // Add modules to input panel
	    inputPanel.add(promptWeight);
	    inputPanel.add(inFieldWeight);
	    inputPanel.add(promptReps);
	    inputPanel.add(inFieldReps);
	    inputPanel.add(promptFormulaType);
	    inputPanel.add(formulaDropdown);
	    inputPanel.add(new JPanel());
	    inputPanel.add(calculateButton);
	    // Initialize output fields
	    outputPanel = new JPanel();
	    outputPanel.setLayout(new BorderLayout());
	    outputPanel.setBorder(BorderFactory.createTitledBorder("Your One Rep Max Estimate"));
	    display = new JTextArea(6, 20);
		display.setFont(PROGRAM_FONT);
		display.setEditable(false);
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		// Add display to output panel
	    outputPanel.add(display, BorderLayout.CENTER);
	    // Add panels to calculator
	    add(inputPanel, BorderLayout.CENTER);
	    add(outputPanel, BorderLayout.SOUTH);
	} 
	
	/**
	 * The actionPerformed method calculates the one rep max and outputs it
	 * @param e The action event from pressing button
	 */
	@Override
	public void actionPerformed(ActionEvent e) { 
		try {
			// Variables initialized with data input into GUI
			double weight = Double.parseDouble(inFieldWeight.getText());
			double reps = Double.parseDouble(inFieldReps.getText());
			double epleyOneRepMax = weight * (1 + (0.033333 * reps));
			double brzyckiOneRepMax = weight * (36.0 / (37.0 - reps));
			double lombardiOneRepMax = weight * (Math.pow(reps, 0.1));
			
			// Switch case to determine which formula to use based on drop down
			switch(formulaDropdown.getSelectedIndex()) {
				// Each case calculates the one rep max and outputs
				case 0:
					display.setText(String.format("Epley Formula Result: %.2f pounds.\n", epleyOneRepMax));
					break;
				case 1:
					display.setText(String.format("Brzycki Formula Result: %.2f pounds\n", brzyckiOneRepMax));
					break;
				case 2:
					display.setText(String.format("Lombardi Formula Result: %.2f pounds\n", lombardiOneRepMax));
					break;
			}
		}
		
		// Output error message if incorrect input
		catch (NumberFormatException error) {
			display.setText("Please enter numbers into the weight and repetition fields.");
		}
    }
	
	/* Testing method:
	public static void main(String[] args) {
		JFrame test = new JFrame();
		OneRepMaxCalculator calculator = new OneRepMaxCalculator();
		test.add(calculator);
		test.setSize(600, 400);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    test.setVisible(true);
    }
    */
}