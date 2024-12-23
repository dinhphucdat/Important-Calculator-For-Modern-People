package Pregnancy_calculator;

/*
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

@SuppressWarnings("serial")
public class ResultPanel extends JPanel {
    private JTextArea resultArea;

    public ResultPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Your Result"));

        resultArea = new JTextArea(15, 40);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setText(String text) {
        if (text == null || text.isEmpty()) {
            resultArea.setText("Please enter valid inputs to calculate the result."); // Default message
        } else {
            resultArea.setText(text); // Set the provided result text
        }
        resultArea.setCaretPosition(0); // Scroll to the top after setting text
    }

}
