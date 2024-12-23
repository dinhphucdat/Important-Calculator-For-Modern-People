package bmi_calculator;

/**
 * The BMIModel class provides the main model for all GUI to use.
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version Version 1
 * Final Project
 * Due Date: 11/12/24
 * Sources used: Stack Overflow, https://www.calculator.net/fitness-and-health-calculator.html
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
public class BMIModel {
	// height in feet
	private int heightFeet;
	// height in inch
	private int heightInch;
	// weight in pound
	private double weightPound;
	// ...all constants...
	private static final int INCH_TO_FOOT = 12;
	private static final int BMI_CONSTANT = 703;
	private static final int BMI_PRIME_UPPER_LIMIT = 25;
	/**
	 * ...assigns values
	 * @param heightFeet height in feet
	 * @param heightInch height in inch
	 * @param weightPound weight in pounds
	 */
	public BMIModel(int heightFeet, int heightInch, double weightPound)
	{
		this.setHeightFeet(heightFeet);
		this.setHeightInch(heightInch);
		this.setWeightPound(weightPound);
	}
	
	/**
	 * ...calculates the official height in iches
	 * @return official height in inches
	 */
	public double toOfficialHeight()
	{
		return heightFeet * INCH_TO_FOOT + heightInch;
	}
	
	/**
	 * calculates the bmi
	 * @return bmi
	 */
	public double computeBMI()
	{
		return BMI_CONSTANT * (weightPound / Math.pow(toOfficialHeight(), 2));
	}
	/**
	 * toString
	 */
	@Override
	public String toString()
	{
		return String.format("<html>BMI = %.1f kg/m<sup>2</sup></html>", computeBMI());
	}
	
	/**
	 * compute BMI prime
	 * @return BMI prime
	 */
	public double computePrime()
	{
		return computeBMI() / BMI_PRIME_UPPER_LIMIT;
	}


	/**
	 * get the height in feet
	 * @return the heightFeet
	 */
	public int getHeightFeet() {
		return heightFeet;
	}

	/**
	 * set the height in feet
	 * @param heightFeet the heightFeet to set
	 * @throws IllegalArgumentException if it is not positive
	 */
	public void setHeightFeet(int heightFeet) {
		if (heightFeet < 0)
			throw new IllegalArgumentException();
		this.heightFeet = heightFeet;
	}

	/**
	 * get the height in inches
	 * @return the heightInch
	 */
	public int getHeightInch() {
		return heightInch;
	}

	/**
	 * set the height in inches
	 * @param heightInch the heightInch to set
	 * @throws IllegalArgumentException if it is not positive
	 */
	public void setHeightInch(int heightInch) {
		if (heightInch < 0)
			throw new IllegalArgumentException();
		this.heightInch = heightInch;
	}

	/**
	 * get the weight in pounds
	 * @return the weightPound
	 */
	public double getWeightPound() {
		return weightPound;
	}

	/**
	 * set the weight in pounds
	 * @param weightPound the weightPound to set
	 * @throws IllegalArgumentException if it is not positive
	 */
	public void setWeightPound(double weightPound) {
		if (Double.compare(weightPound, 0.0) < 0)
			throw new IllegalArgumentException();
		this.weightPound = weightPound;
	}
	
}
