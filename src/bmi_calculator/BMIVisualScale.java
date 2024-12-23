package bmi_calculator;

import java.awt.*;
import javax.swing.*;

/**
 * The BMIVisualScale class visualizes BMI results.
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
public class BMIVisualScale extends JPanel {
	// height of the scale
	private int scaleHeight;
	// min and max bmi to display
    private final double minBMI = 10.0; // Minimum BMI
    private final double maxBMI = 40.0; // Maximum BMI
    private double currentBMI = -1; // Cursor position (-1 means no cursor)
	
    /**
     * paints the JPanel
     */
	@Override
	protected void paintComponent(Graphics g)
	{
		// set proportion the the frame height
		int proportionToFrameHeight = 5;
		scaleHeight = (int) (getHeight() / proportionToFrameHeight);
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Dimensions
        int width = getWidth();
        int height = getHeight();
        int scaleY = height / 2 - scaleHeight / 2;
        int padding = 10;

        // Draw rainbow gradient
        for (int x = padding; x < width - padding; x++) {
            float hue = (float) (x - padding) / (width - 2 * padding); // Map x to hue (0 to 1)
            g2.setColor(Color.getHSBColor(hue, 1.0f, 1.0f));
            g2.drawLine(x, scaleY, x, scaleY + scaleHeight);
        }

        // Draw cursor
        int cursorX;
        if (currentBMI < minBMI) {
            cursorX = padding; // Cursor at the start of the scale
        } else if (currentBMI > maxBMI) {
            cursorX = width - padding; // Cursor at the end of the scale
        } else {
        	// place cursor at the location proportional to the bmi Value
            cursorX = padding + (int) ((currentBMI - minBMI) * (width - 2 * padding) / (maxBMI - minBMI));
        }
        g2.setColor(Color.BLACK);
        int proportionCursorHeight = 8;
        // cursor height will be set to depend on the size of frame, so it is resizable
        int cursorHeight = (int) (getHeight() / proportionCursorHeight);
        // set the spece between components as half of the cursor height
        int spacing = cursorHeight / 2;
        // fillPolygon([array of x-coordinates], [array of y-coordinates], number of angles])
        g2.fillPolygon(
            new int[]{cursorX - spacing, cursorX, cursorX + spacing},
            new int[]{scaleY + scaleHeight + spacing, 
            		scaleY + scaleHeight + spacing + cursorHeight, scaleY + scaleHeight + spacing},
            3
        );
        

        
     // Draw numerical markers
        int markerSpacing = 5; // BMI units between markers
        int numMarkers = (int) ((maxBMI - minBMI) / markerSpacing) + 1; // Number of markers
        int fontSize = getHeight() / 9;
        g2.setColor(Color.BLACK);
        for (int i = 0; i < numMarkers; i++) {
            double bmiValue = minBMI + i * markerSpacing; // Calculate BMI value for this marker
            int markerX = padding + (int) ((bmiValue - minBMI) * (width - 2 * padding) / (maxBMI - minBMI)); // Map to X position
            String markerText = String.format("%.0f", bmiValue); // Format BMI value as integer
            g2.setFont(new Font(Font.DIALOG, Font.PLAIN, fontSize));
            g2.drawString(markerText, markerX - g2.getFontMetrics().stringWidth(markerText) / 2, scaleY - spacing); // Center text above scale
        }
    }
	
	/**
	 * when this is called to reset, then the cursor will be repainted
	 * @param bmi bmi value
	 */
    public void setBMICursor(double bmi) {
        currentBMI = bmi;
        repaint(); // Trigger a repaint to update the cursor
    }

}
