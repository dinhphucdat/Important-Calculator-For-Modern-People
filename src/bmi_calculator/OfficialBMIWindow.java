package bmi_calculator;

import javax.swing.*;
import java.awt.*;

/**
 * The OfficialBMIWindow class is the final displayer.
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
public class OfficialBMIWindow extends JPanel {
	// the calulator at the top-half and the displaying text area at the bottom-half
	private JPanel calculatorAndVisual, description;
	/**
	 * initializes everything
	 */
	public OfficialBMIWindow()
	{
		// instantiates calculator
		calculatorAndVisual = new JPanel();
		calculatorAndVisual.setLayout(new GridLayout(1, 2));
		BMICalculator calc = new BMICalculator();
		BMIResultPanel res = new BMIResultPanel();
		// add listener -> contract
		calc.addBMIListener(res);
		calculatorAndVisual.add(calc);
		calculatorAndVisual.add(res);
		// get the discription panel from the result panel
		//                               controls                      controls
		// --> FLOW: main calculator ---------------> result panel ----------------> description displaying text area
		description = res.getDescriptionPanel();
		description.setSize(getPreferredSize());
		setLayout(new GridLayout(2, 1));
		add(calculatorAndVisual);
		add(description);
	}
	
//	public static void main(String...strings)
//	{
//		JFrame jf = new JFrame();
//		jf.getContentPane().add(new OfficialBMIWindow());
//		jf.setSize(600, 500);
//		jf.setTitle("BMI Calculator Model");
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setVisible(true);
//		jf.setLocationRelativeTo(null);
//	}

}
