package bmi_calculator;

/**
 * The BMIListener interface provides a contract for all components that want to be controlled by 
 * the main calculator to use universally.
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version Version 1
 * Final Project
 * Due Date: 11/12/24
 * Sources used: Stack Overflow, https://www.calculator.net/fitness-and-health-calculator.html
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public interface BMIListener {
	/**
	 * displays the main text for the BMI
	 * @param bmiDisplay text to display
	 */
	void triggerBMIDisplay(String bmiDisplay);
	/**
	 * shows the actual BMI value. If that is null, then it would be used to
	 * display some sorts of exception
	 * @param bmiValue cvalue in double value to display
	 */
	void showActualBMI(Double bmiValue);
	/**
	 * shows the bmi prime value.
	 * @param bmiPrime bmi prime
	 */
	void showBMIPrime(double bmiPrime);

}
