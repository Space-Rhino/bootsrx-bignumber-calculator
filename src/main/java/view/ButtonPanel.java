package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class represents a calculator button panel with the following buttons:
 * <p>
 * Button for each digit 0 through 9. (requirement 3.1.3)
 * <p>
 * Button for sign-toggle. (requirement 3.1.2)
 * <p>
 * Button for decimal. (requirement 3.1.5)
 * <p>
 * Standard window buttons. (requirement 3.1.6)
 * <p>
 * Buttons for clear all, clear, and delete functions. (requirement 3.1.7)
 * <p>
 * Buttons for arithmetic operators. (requirement 3.1.8)
 * <p>
 * Buttons for pi, square root, square, and inverse. (requirement 3.1.9)
 * 
 * @author  Shawn Crahen
 * @version 1.0
 * @see     JPanel
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	
	/**
	 * The graphical user interface that contains this.
	 */
	private Gui gui;
	
	/**
	 * A handler for digit input events.
	 */
	private DigitHandler digitHandler;
	
	/**
	 * A handler for operation input events.
	 */
	private OperationHandler operationHandler;
	
	/**
	 * A map of buttons - facilitates O(1) access to buttons.
	 */
	private Map<String, JButton> buttonMap;
	
	/**
	 * The operation button that is active.
	 */
	private JButton activeOperationButton;
	
	/**
	 * The last clicked button.
	 */
	private JButton lastClickedButton;
	
	/**
	 * Class constructor.
	 * 
	 * @param gui the graphical user interface that contains this
	 */
	public ButtonPanel(Gui gui) {
		this.gui = gui;
		digitHandler = new DigitHandler();
		operationHandler = new OperationHandler();
		buttonMap = new HashMap<>();
		
		// set aesthetic for this button panel
		setBackground(new Color(60, 60, 60));
		setPreferredSize(new Dimension(350, 350));
		setLayout(new GridLayout(6, 4, 5, 5));
		
		// add buttons to panel
		addButton("\u03C0", "PI", Color.gray, operationHandler);
		addButton("AC", "AC", new Color(200, 50, 50), operationHandler);
		addButton("C", "C", new Color(200, 50, 50), operationHandler);
		addButton("\u21E6", "BKSP", new Color(200, 50, 50), digitHandler);
		addButton("x" + "\u207B" + "\u00B9", "INV", Color.gray, operationHandler);
		addButton("x" + "\u00B2", "SQR", Color.gray, operationHandler);
		addButton("\u221A", "SQRT", Color.gray, operationHandler);
		addButton("/", "/", Color.gray, operationHandler);
		addButton("7", "7", Color.white, digitHandler);
		addButton("8", "8", Color.white, digitHandler);
		addButton("9", "9", Color.white, digitHandler);
		addButton("*", "*", Color.gray, operationHandler);
		addButton("4", "4", Color.white, digitHandler);
		addButton("5", "5", Color.white, digitHandler);
		addButton("6", "6", Color.white, digitHandler);
		addButton("-", "-", Color.gray, operationHandler);
		addButton("1", "1", Color.white, digitHandler);
		addButton("2", "2", Color.white, digitHandler);
		addButton("3", "3", Color.white, digitHandler);
		addButton("+", "+", Color.gray, operationHandler);
		addButton("+/-", "NEG", Color.white, operationHandler);
		addButton("0", "0", Color.white, digitHandler);
		addButton(".", ".", Color.white, digitHandler);
		addButton("=", "=", Color.gray, operationHandler);
	}
	
	/**
	 * Gets the operation button that is active.
	 * 
	 * @return the active operation button
	 */
	public JButton getActiveOperationButton() {
		return activeOperationButton;
	}
	
	/**
	 * Sets the operation button that is active.
	 * 
	 * @param activeOperation the operation button that is active
	 */
	public void setActiveOperationButton(JButton activeOperation) {
		activeOperationButton = activeOperation;
	}
	
	/**
	 * Gets a button in constant time.
	 * 
	 * @param  action the action of the desired button
	 * @return        the button
	 */
	public JButton getButton(String action) {
		return buttonMap.get(action);
	}
	
	/**
	 * Sets the last clicked button field.
	 * 
	 * @param button the button
	 */
	public void setLastClickedButton(JButton button) {
		lastClickedButton = button;
	}
	
	/**
	 * Adds a button to this.
	 * 
	 * @param label   the button's label
	 * @param action  the button's action
	 * @param color   the button's color
	 * @param handler the button's action listener
	 */
	private void addButton(String label, String action, Color color, ActionListener handler) {
		JButton button = new JButton(label);
		button.setActionCommand(action);
		button.addActionListener(handler);
		button.setBackground(color);
		button.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		buttonMap.put(action, button);
		add(button);
	}
	
	/**
	 * This class implements a handler for ActionEvents that correspond to digit
	 * entries. (requirement 3.2.1)
	 * 
	 * @author  Shawn Crahen
	 * @version 1.0
	 * @see     ActionListener
	 * @see     ActionEvent
	 */
	class DigitHandler implements ActionListener {
		
		/**
		 * Handles ActionEvents.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			lastClickedButton = ((JButton) e.getSource());
			gui.enterDigit(e.getActionCommand());
			gui.requestFocusInWindow();
		}
	}
	
	/**
	 * This class implements a handler for ActionEvents that correspond to operation
	 * entries. (requirement 3.2.1)
	 * 
	 * @author  Shawn Crahen
	 * @version 1.0
	 * @see     ActionListener
	 * @see     ActionEvent
	 */
	class OperationHandler implements ActionListener {
		
		/**
		 * Handles ActionEvents and button aesthetic
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			// set color of active operation button
			if ((action.equals("AC") || action.equals("=")) &&
					activeOperationButton != null) {
				activeOperationButton.setBackground(Color.gray);
			}
			if (action.equals("NEG") &&
					activeOperationButton != null &&
					!Character.isDigit(lastClickedButton.getActionCommand().charAt(0))) {
				activeOperationButton.setBackground(Color.gray);
			}
			if (action.equals("/") || action.equals("*") ||
					action.equals("+") || action.equals("-")) {
				if (activeOperationButton != null)
					activeOperationButton.setBackground(Color.gray);
				activeOperationButton = ((JButton) e.getSource());
				activeOperationButton.setBackground(Color.orange);
			}
			
			// handle event
			lastClickedButton = ((JButton) e.getSource());
			gui.enterOperation(action);
			gui.requestFocusInWindow();
		}
	}
}
