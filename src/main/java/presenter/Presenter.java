package presenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingUtilities;

import model.app.Calculator;
import view.Gui;

/**
 * This class is the link between the view and model. It implements
 * PropertyChangeListener enabling the class to observe property change events
 * sent from the model. This class is also the driver for the program.
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     PropertyChangeListener
 */
public class Presenter implements PropertyChangeListener {
	
	/**
	 * The calculator model.
	 */
	private Calculator calculator;
	
	/**
	 * The calculator view.
	 */
	private static Gui gui;
	
	/**
	 * Class constructor. Sets this as a PropertyChangeListener of the model's
	 * display register.
	 */
	public Presenter() {
		calculator = new Calculator();
		calculator.getDisplay().addPropertyChangeListener(this);
	}
	
	/**
	 * Passes an operation to the calculator model.
	 *
	 * @param opString the operation
	 */
	public void enterOperation(String opString) {
		calculator.enterOperation(opString);
	}
	
	/**
	 * Passes a digit to the calculator model.
	 *
	 * @param digit the digit
	 */
	public void enterDigit(String digit) {
		calculator.enterDigit(digit);
	}
	
	/**
	 * Updates the view's display upon receipt of a PropertyChangeEvent.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		gui.updateDisplay(e.getNewValue().toString());
	}
	
	/**
	 * The driver for this program.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Presenter presenter = new Presenter();
		SwingUtilities.invokeLater(new Runnable() {
			
			// instantiate GUI in event dispatch thread
			public void run() {
				gui = new Gui(presenter);
				gui.setVisible(true);
				gui.requestFocusInWindow();
			}
		});
	}
}
