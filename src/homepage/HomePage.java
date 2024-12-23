package homepage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

// TODO: customized import statement. Please check your own package structure
// to make sure the program to work correctly.
import bmi_calculator.*;
import Pregnancy_calculator.*;
import Calorie_Calculator.*;
import OneRepMax.*;

/**
 * The HomePage class provides the primary interface for every calculator.
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version Version 1
 * Final Project
 * Due Date: 11/12/24
 * Sources used: Stack Overflow, https://www.calculator.net/fitness-and-health-calculator.html, Java, Java, Java.
 * No help obtained
 * We confirm that the above list of sources is complete AND that we have not talked to 
 * anyone else about the solution to this problem.
 */
@SuppressWarnings("serial")
public class HomePage extends JFrame {
	
	// card layout
	private CardLayout card;
	// 5 panels to add into the frame to use the card layout
	private JPanel homeCard, calorieCard, bmiCard, pregnancyCard, oneRepMaxCard;
	// all buttons needed to navigate the fitness calculators
	private JButton pregnancyCalculator, calorieCalculator, bmiCalculator, oneRepMaxCalculator;
	// these are 4 component calculators that will be added to the main frame to lay out.
	private OfficialBMIWindow bmiHub;
	private CalorieCalculator calorieHub;
	private PregnancyCalculator pregHub;
	private OneRepMaxCalculator oneRepMaxHub;
	// all the back buttons. Have to make 4 because we cannot share one for every card.
	private JButton[] backs;
	// title and size parameters
	private static final String TITLE = "Important Calculators for Modern People";
	private static final int HORRIZONTAL_SIZE = 600;
	private static final int VERTICAL_SIZE = 500;
	private static final int BACK_NUMBER = 4;
	
	private static final String HOME_CARD_NAME = "homeCard";
	private static final String CALORIE_CARD_NAME = "calorieCard";
	private static final String BMI_CARD_NAME = "bmiCard";
	private static final String PREGNANCY_CARD_NAME = "pregnancyCard";
	private static final String ONEREPMAX_CARD_NAME = "oneRepMaxCard";

	/**
	 * create a whole new window to all calculators
	 */
	public HomePage() {
		// get the frame ready to be shown
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(HORRIZONTAL_SIZE, VERTICAL_SIZE);
		setLocationRelativeTo(null);
		// create the main layout for the frame
		card = new CardLayout();
		getContentPane().setLayout(card);
		
		// create all component calculators needed
		bmiHub = new OfficialBMIWindow();
		calorieHub = new CalorieCalculator();
		pregHub = new PregnancyCalculator();
		oneRepMaxHub = new OneRepMaxCalculator();
		// create all necessary navigating buttons
		calorieCalculator = new JButton("Calories");
		bmiCalculator = new JButton("BMI");
		pregnancyCalculator = new JButton("Pregnancy");
		oneRepMaxCalculator = new JButton("One Rep Max");
		// instantiate the 4 buttons to go back to the home page
		makeBackButtons();
		// lay out the home page
		setHomeCard();
		// layout the page for calorie calculator
		setCalorieCard();
		// layout the page for bmi calculator
		setBMICard();
		// layout the page for pregnancy calculator
		setPregnancyCard();
		// layout the page for one rep max calculator
		setOneRepMaxCard();
		// make navigating buttons clickable
		addActionListener();
		// make the frame visible
		setVisible(true);
	}
	/**
	 * lay out the home page
	 */
	public void setHomeCard()
	{
		// panel
		homeCard = new JPanel();
		// grid layout
		homeCard.setLayout(new GridLayout(2, 2));
		// add components
		homeCard.add(calorieCalculator);
		homeCard.add(bmiCalculator);
		homeCard.add(pregnancyCalculator);
		homeCard.add(oneRepMaxCalculator);
		add(homeCard, HOME_CARD_NAME);
	}
	/**
	 * layout the page for calorie calculator
	 */
	public void setCalorieCard()
	{
		// panel
		calorieCard = new JPanel();
		// border layout
		calorieCard.setLayout(new BorderLayout());
		// add components
		calorieCard.add(backs[0], BorderLayout.NORTH);
		calorieCard.add(calorieHub, BorderLayout.CENTER);
		add(calorieCard, CALORIE_CARD_NAME);
	}
	/**
	 * layout the page for bmi calculator
	 */
	public void setBMICard()
	{
		// panel
		bmiCard = new JPanel();
		// border layout
		bmiCard.setLayout(new BorderLayout());
		// add components
		bmiCard.add(backs[1], BorderLayout.NORTH);
		bmiCard.add(bmiHub, BorderLayout.CENTER);
		add(bmiCard, BMI_CARD_NAME);
	}
	/**
	 * layout the page for pregnancy calculator
	 */
	public void setPregnancyCard()
	{
		// panel
		pregnancyCard = new JPanel();
		// border layout
		pregnancyCard.setLayout(new BorderLayout());
		// add components
		pregnancyCard.add(backs[2], BorderLayout.NORTH);
		pregnancyCard.add(pregHub, BorderLayout.CENTER);
		add(pregnancyCard, PREGNANCY_CARD_NAME);
	}
	/**
	 * layout the page for one rep max calculator
	 */
	public void setOneRepMaxCard()
	{
		// panel
		oneRepMaxCard = new JPanel();
		// border layout
		oneRepMaxCard.setLayout(new BorderLayout());
		// add components
		oneRepMaxCard.add(backs[3], BorderLayout.NORTH);
		oneRepMaxCard.add(oneRepMaxHub);
		// oneRepMaxCard.add(oneRepMaxHub, BorderLayout.CENTER);
		add(oneRepMaxCard, ONEREPMAX_CARD_NAME);
	}
	/**
	 * make navigating buttons clickable
	 */
	public void addActionListener()
	{
		calorieCalculator.addActionListener(new ActionListener() {
			/**
			 * calorie
			 * @param e action event
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				card.show(getContentPane(), CALORIE_CARD_NAME);
			}
			
		});
		bmiCalculator.addActionListener(new ActionListener() {
			/**
			 * bmi
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				card.show(getContentPane(), BMI_CARD_NAME);
			}
		});
		pregnancyCalculator.addActionListener(new ActionListener() {
			/**
			 * pregnancy
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				card.show(getContentPane(), PREGNANCY_CARD_NAME);
			}
		});
		oneRepMaxCalculator.addActionListener(new ActionListener() {
			/**
			 * one rep max
			 * @param e action event
			 */
			@Override
			public void actionPerformed(ActionEvent e)
			{
				card.show(getContentPane(), ONEREPMAX_CARD_NAME);
			}
		});
	}
	/**
	 * instantiates all the back buttons and register them with an action event to go 
	 * back to the home page
	 */
	public void makeBackButtons()
	{
		final String nameBackButtons = "Back to home page";
		backs = new JButton[BACK_NUMBER];
		for (int i = 0; i < backs.length; i++)
		{
			backs[i] = new JButton(nameBackButtons);
			backs[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					card.show(getContentPane(), HOME_CARD_NAME);
				}
			});
		}
	}
	
	/**
	 * main
	 * @param args program args
	 */
	public static void main(String[] args) {
		try {
            // Set the Metal Look and Feel
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Metal Look and Feel not supported.");
        }

		new HomePage();
	}
}
