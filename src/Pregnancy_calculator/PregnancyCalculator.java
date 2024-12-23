package Pregnancy_calculator;

/**
 * Lab 13
Due Date: 12/8/24
Authors: Martin Miller, Dat Dinh Phuc, Trey Fellner, Nikhil Jangir
No sources used
No help obtained
We confirm that the above list of sources is complete AND that we have not talked to 
anyone else about the solution to this problem.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * PregnancyCalculator is a GUI application that provides functionality to calculate 
 * various pregnancy-related dates, such as due dates, last period date, conception date, pregnancy according to Ultrasounds and IVF transfer date.
 * 
 * The application uses Java Swing for the GUI interface.
 * 
 * <p>Features:</p>
 * <ul>
 *   <li>Calculate estimated due date or pregnancy date based on last period date, cycle length, or IVF transfer date.</li>
 *   <li>Determine the pregnancy age using an ultrasound date.</li>
 *   <li>Compute the last menstrual period date from the due date.</li>
 *   <li>Interactive input fields that adjust based on the selected calculation type.</li>
 *   <li>Displays results such as due date, current week, and progress percentage.</li>
 * </ul>
 */
@SuppressWarnings("serial")
public class PregnancyCalculator extends JPanel {
	
	/**
	 * Initializing the required variables that are used in this calculator.
	 */
    private JComboBox<String> calculationType;
    private JPanel inputPanel;
    private JTextField dateInputField;
    private JTextField cycleLengthField;
    private JComboBox<String> embryoAgeField;
    private JTextField weeksField, daysField;
    private ResultPanel resultPanel;
    private JButton calculateButton;
    
    private static final String DATE_FORMAT = "(yyyy-mm-dd)";
    
    private static final String[] CALC_METHODS = {
            "Last Period Date",
            "Ultrasound Date",
            "IVF Transfer Date",
            "Due Date",
            "Conception Date"
    };

    public PregnancyCalculator() {
        
    	/*
    	 * Initialize the frame properties
    	 */
        setSize(600, 500);
        setLayout(new BorderLayout());

        /*
         * Create the top panel for the menu
         */
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        JLabel calcTypeLabel = new JLabel("Calculate Based On:");
        topPanel.add(calcTypeLabel);

        /*
         * Dropdown menu for calculation type
         */
        calculationType = new JComboBox<>(CALC_METHODS);
        topPanel.add(calculationType);
        add(topPanel, BorderLayout.NORTH);

        /*
         * Dynamic input panel with CardLayout
         */
        inputPanel = new JPanel(new CardLayout());
        add(inputPanel, BorderLayout.CENTER);
        
        // Initialization of input fields for each calculation type

        /*
         * Last Period Input
         */
        JPanel lastPeriodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        lastPeriodPanel.add(new JLabel(CALC_METHODS[0] + " " + DATE_FORMAT));
        dateInputField = new JTextField(10);
        lastPeriodPanel.add(dateInputField);
        lastPeriodPanel.add(new JLabel("Average Length of Your Cycles (in days):"));
        cycleLengthField = new JTextField(5);
        lastPeriodPanel.add(cycleLengthField);
        inputPanel.add(lastPeriodPanel, CALC_METHODS[0]);

        /*
         * Ultrasound Input
         */
        JPanel ultrasoundPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Added margin (horizontal, vertical)
        ultrasoundPanel.add(new JLabel(CALC_METHODS[1] + " " + DATE_FORMAT));
        JTextField ultrasoundDateField = new JTextField(10);
        ultrasoundPanel.add(ultrasoundDateField);
        ultrasoundPanel.add(new JLabel("Length of Pregnancy:"));
        weeksField = new JTextField(5);
        ultrasoundPanel.add(weeksField);
        ultrasoundPanel.add(new JLabel("weeks"));
        daysField = new JTextField(5);
        ultrasoundPanel.add(daysField);
        ultrasoundPanel.add(new JLabel("days"));
        inputPanel.add(ultrasoundPanel, CALC_METHODS[1]);

        /*
         * IVF Transfer Input
         */
        JPanel ivfPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        ivfPanel.add(new JLabel(CALC_METHODS[2] + " " + DATE_FORMAT));
        JTextField transferDateField = new JTextField(10);
        ivfPanel.add(transferDateField);
        ivfPanel.add(new JLabel("Embryo Age:"));
        embryoAgeField = new JComboBox<>(new String[]{"Day 3", "Day 5", "Day 6"});
        ivfPanel.add(embryoAgeField);
        inputPanel.add(ivfPanel, CALC_METHODS[2]);

        /*
         * Due Date Input
         */
        JPanel dueDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        dueDatePanel.add(new JLabel(CALC_METHODS[3] + " " + DATE_FORMAT));
        JTextField dueDateField = new JTextField(10);
        dueDatePanel.add(dueDateField);
        inputPanel.add(dueDatePanel, CALC_METHODS[3]);

        /*
         * Conception Date Input
         */
        JPanel conceptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        conceptionPanel.add(new JLabel(CALC_METHODS[4] + " " + DATE_FORMAT));
        JTextField conceptionDateField = new JTextField(10);
        conceptionPanel.add(conceptionDateField);
        inputPanel.add(conceptionPanel, CALC_METHODS[4]);

        /*
         * Create button panel to hold the calculate button
         */
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 72, 40));
        calculateButton = new JButton("Calculate");
        buttonPanel.add(calculateButton);
        add(buttonPanel, BorderLayout.LINE_END);

        /*
         * adding the ResultPanel created in the other class
         */
        resultPanel = new ResultPanel();
        add(resultPanel, BorderLayout.SOUTH);

        /*
         * Add action listener for calculation type
         */
        calculationType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (inputPanel.getLayout());
                cl.show(inputPanel, (String) calculationType.getSelectedItem());
            }
        });

        /*
         * Add button action listener
         */
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCalculation();
            }
        });

        setVisible(true);
    }

    /**
     * Performs the calculation based on the selected calculation type and user inputs.
     * Updates the result panel with the computed information.
     */
    private void performCalculation() {
        try {
        	
        	/*
        	 * Retrieve the user input based on the selection.
        	 */
            String selectedOption = (String) calculationType.getSelectedItem();
            LocalDate resultDate = null;
            String resultText = "";
            LocalDate inputDate = LocalDate.parse(dateInputField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            final String cue = "Estimated Due Date:";

            /*
             * Adding conditions for each type of selection and implementing the formula accordingly.
             * All the formulas are fetched from the source website.
             */
            if (selectedOption.equals(CALC_METHODS[0])) {
                int cycleLength = Integer.parseInt(cycleLengthField.getText());
                resultDate = inputDate.plusDays(280 - (28 - cycleLength)); // Adjust for cycle length
                resultText = generateResult(resultDate, cue);
            } else if (selectedOption.equals(CALC_METHODS[1])) {
                int weeks = Integer.parseInt(weeksField.getText());
                int days = Integer.parseInt(daysField.getText());
                resultDate = inputDate.minusDays(weeks * 7 + days).plusDays(280);
                resultText = generateResult(resultDate, cue);
            } else if (selectedOption.equals(CALC_METHODS[2])) {
                int embryoDays = embryoAgeField.getSelectedIndex() == 0 ? 3 : (embryoAgeField.getSelectedIndex() == 1 ? 5 : 6);
                resultDate = inputDate.plusDays(266 - embryoDays);
                resultText = generateResult(resultDate, cue);
            } else if (selectedOption.equals(CALC_METHODS[3])) {
                resultDate = inputDate.minusDays(280); // Subtract 280 days to get LMP
                resultText = "Last Period Date: " + resultDate;
            } else if (selectedOption.equals(CALC_METHODS[4])) {
                resultDate = inputDate.plusDays(266); // Conception date + 266 days = due date
                resultText = generateResult(resultDate, cue);
            } else {
                resultText = "Invalid option selected.";
            }

            resultPanel.setText(resultText); // Update result panel with the calculated text
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Generates a formatted result string for display in the result panel.
     *
     * @param dueDate  The calculated due date.
     * @param prefix   A prefix message to indicate the type of calculation performed.
     * @return A formatted string containing the calculated information.
     */
    private String generateResult(LocalDate dueDate, String prefix) {
        LocalDate today = LocalDate.now();
        long totalDays = 280;
        long daysElapsed = today.toEpochDay() - dueDate.minusDays(totalDays).toEpochDay();
        int weeks = (int) (daysElapsed / 7);
        int days = (int) (daysElapsed % 7);
        double percentage = (daysElapsed * 100.0) / totalDays;

        return String.format(
                "%s %s\nYou are currently at week #%d (%d weeks %d days).\n"
                        + "You are %.2f%% of the way through your pregnancy.",
                prefix, dueDate, weeks, weeks, days, percentage);
    }

//    public static void main(String[] args) {
//    	/*
//    	 * Launching the application in the main method.
//    	 */
//        SwingUtilities.invokeLater(() -> new PregnancyCalculator());
//    }
//}
}