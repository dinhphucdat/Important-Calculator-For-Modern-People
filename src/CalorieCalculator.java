package Calorie_Calculator;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
/** Name: Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * Group project
 * Due: TBD
 * Help obtained: none 
 * Academic honesty statement: I confirm that the above list of sources is complete 
 * AND that I have not talked to anyone else about the solution to this problem.
 */

/** This class is the main panel for the calorie calculator application
 * @author Trey Fellner
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CalorieCalculator extends JPanel {

    private InputPanel inputPanel; // Panel for user inputs
    private ResultPanel resultPanel; // Panel for displaying results

    /**
     * Constructor for the CalorieCalculator class
     */
    public CalorieCalculator() {
        setLayout(new BorderLayout());

        inputPanel = new InputPanel();
        resultPanel = new ResultPanel();

        //set up listener to handle calculations
        inputPanel.setCalculateListener(new InputPanel.CalculateListener() {
            @Override
            public void onCalculate(double height, double weight, int activityLevel) {
                try {
                    //convert height to cm and weight to kg
                    double heightCm = height * 2.54;
                    double weightKg = weight * 0.453592;

                    // Calculate Basal Metabolic Rate (BMR)
                    double bmr = 10 * weightKg + 6.25 * heightCm + 5;

                    // Determine activity multiplier based on activity level
                    double activityMultiplier;
                    switch (activityLevel) {
                        case 1:
                            activityMultiplier = 1.2; // Low Activity
                            break;
                        case 2:
                            activityMultiplier = 1.55; // Moderate Activity
                            break;
                        case 3:
                            activityMultiplier = 1.9; // High Activity
                            break;
                        default:
                            resultPanel.displayError("Invalid activity level selected");
                            return;
                    }

                    //calculate maintenance calories
                    double maintenanceCalories = bmr * activityMultiplier;
                    resultPanel.displayResult(maintenanceCalories, activityLevel);
                } catch (Exception ex) {
                    resultPanel.displayError("Invalid input");
                }
            }

            @Override
            public void onError(String errorMessage) {
                resultPanel.displayError(errorMessage);
            }
        });

        // Add panels to the layout
        add(inputPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }
    /** This class is the main class for launching the Calorie Calculator application
     */
    public static void main(String[] args) {
            try {
                // Set the Metal Look and Feel 
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                System.out.println("Metal Look and Feel not supported.");
            }

            // Create the main application window
            JFrame frame = new JFrame("Calories to Maintain Weight Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CalorieCalculator());
            frame.setSize(600, 400);
            frame.setVisible(true);
        }
    }












