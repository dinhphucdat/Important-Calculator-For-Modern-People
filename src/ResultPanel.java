package Calorie_Calculator;

import javax.swing.*;
import java.awt.*;

/** 
 * This class is the panel for displaying results in the Calorie Calculator application
 * @author Dat Dinh, Trey Fellner, Martin Miller, and Nikhil Jangir
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ResultPanel extends JPanel {

    private JLabel resultLabel; // Label for calories needed
    private JTextArea messageArea; // Text area for additional messages

    /**
     * Constructor for the ResultPanel class.
     */
    public ResultPanel() {
        setLayout(new BorderLayout()); 
        setBorder(BorderFactory.createTitledBorder("Your Result")); // Add a titled border

        Font customFont = new Font("Arial", Font.PLAIN, 18); // Font 

        resultLabel = new JLabel("Calories Needed: "); 
        resultLabel.setFont(customFont);

        messageArea = new JTextArea(6, 20); // Create a text area for messages
        messageArea.setFont(customFont);
        messageArea.setEditable(false); 
        messageArea.setLineWrap(true); 
        messageArea.setWrapStyleWord(true); 

        // Add a scroll pane for the message area
        JScrollPane scrollPane = new JScrollPane(messageArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(resultLabel, BorderLayout.NORTH); // Position correctly
        add(scrollPane, BorderLayout.CENTER); 
    }

    /**
     * Displays the calculated maintenance calories and additional messages
     * @param calories the calculated calories
     * @param activityLevel the activity level selected
     */
    public void displayResult(double calories, int activityLevel) {
        resultLabel.setText("Calories Needed: " + String.format("%.2f kcal", calories)); // Update label with calories

        String message; // Message based on activity level
        switch (activityLevel) {
            case 1:
                message = "Your caloric intake should be on the lower end due to low activity.";
                break;
            case 2:
                message = "You have a moderate level of activity so you will need to consume the given amount of calories.";
                break;
            case 3:
                message = "You exercise frequently and need more calories to fuel your activity.";
                break;
            default:
                message = "Invalid activity level.";
        }

        messageArea.setText(message); // Display the appropriate message
    }

    /**
     * Displays an error message.
     * @param errorMessage the error message to display
     */
    public void displayError(String errorMessage) {
        resultLabel.setText("Calories Needed: Error"); // Indicate an error in the label
        messageArea.setText(errorMessage); // Show the error message in the text area
    }
}








