package Calorie_Calculator;

import javax.swing.*;
import java.awt.*;

/** 
 * This class is the panel for user input in the Calorie Calculator application.
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version 1.0
 */

@SuppressWarnings("serial")
public class InputPanel extends JPanel {

	// Declare fields
    private JTextField heightField; //height input field
    private JTextField weightField; //weight input field
    private JComboBox<String> activityLevelDropdown; // Dropdown for selecting activity level
    private JButton calculateButton; // Button to trigger calculation
    private CalculateListener calculateListener; //listener for calculation events

    /**
     * Constructor for the InputPanel class.
     */
    public InputPanel() {
        setLayout(new GridLayout(4, 2, 10, 10)); //Set layout to grid

        Font customFont = new Font("Arial", Font.PLAIN, 18); // Set font

        // Create and style components
        JLabel heightLabel = new JLabel("Height (in inches):");
        heightLabel.setFont(customFont); // Set font for height 
        heightField = new JTextField();
        heightField.setFont(customFont);

        JLabel weightLabel = new JLabel("Weight (in lbs):");
        weightLabel.setFont(customFont); // Set font for weight 
        weightField = new JTextField();
        weightField.setFont(customFont);

        JLabel activityLabel = new JLabel("Activity Level:");
        activityLabel.setFont(customFont); // Set font for activity label
        activityLevelDropdown = new JComboBox<>(new String[]{
                "1–2 days (Low Activity)", 
                "3–4 days (Moderate Activity)", 
                "5–7 days (High Activity)"
        });
        activityLevelDropdown.setFont(customFont);

        calculateButton = new JButton("Calculate");
        calculateButton.setFont(customFont); // Set font for calculate button

        // Anonymous inner class for the button's action listener
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightField.getText()); // Parse height input
                    double weight = Double.parseDouble(weightField.getText()); // Parse weight input
                    int activityLevel = activityLevelDropdown.getSelectedIndex() + 1; // Get activity level
                    if (calculateListener != null) {
                        calculateListener.onCalculate(height, weight, activityLevel); // Trigger calculation
                    }
                } catch (NumberFormatException ex) {
                    if (calculateListener != null) {
                        calculateListener.onError("Please enter valid numbers for height and weight."); // Handle invalid input
                    }
                }
            }
        });

        // Add components to the panel
        add(heightLabel);
        add(heightField);
        add(weightLabel);
        add(weightField);
        add(activityLabel);
        add(activityLevelDropdown);
        add(new JLabel()); //alignment
        add(calculateButton);
    }

    /**
     * Sets the listener for handling calculation events.
     * @param listener the listener to set
     */
    public void setCalculateListener(CalculateListener listener) {
        this.calculateListener = listener;
    }

    /**
     * Listener interface for handling calculation events.
     */
    public interface CalculateListener {
        void onCalculate(double height, double weight, int activityLevel);

        void onError(String errorMessage);
    }
}






