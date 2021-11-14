package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class represents the display panel of a calculator. (requirement 3.1.2)
 * 
 * @author  Shawn Crahen
 * @version 1.0
 * @see     JPanel
 */
@SuppressWarnings("serial")
public class DisplayPanel extends JPanel {
	
	/**
	 * The text field for this display.
	 */
	private JTextField displayField;
	
	/**
	 * Class constructor.
	 */
	public DisplayPanel() {
		// set aesthetic
		setBackground(new Color(60, 60, 60));
		setLayout(new GridLayout(1, 1, 0, 0));
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		// build displayField
		displayField = new JTextField("0");
		displayField.setFont(new Font("SANS_SERIF", Font.BOLD, 16));
		displayField.setEditable(false);
		displayField.setBackground(new Color(225, 240, 236));
		displayField.setHorizontalAlignment(JTextField.RIGHT);
		displayField.setPreferredSize(new Dimension(910, 50));
		
		add(displayField);
	}
	
	/**
	 * Sets the text of this display.
	 * 
	 * @param text the display text
	 */
	public void setDisplay(String text) {
		displayField.setText(text);
	}
}
