package presenter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.SwingUtilities;
import model.app.Calculator;
import view.Gui;

/**
 * This class is the link between the view and model. It implements PropertyChangeListener enabling
 * the class to observe property change events sent from the model. This class is also the driver
 * for the program.
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see PropertyChangeListener
 */
public class Presenter implements PropertyChangeListener {

  /** The calculator view. */
  private static Gui gui;

  /** The calculator model. */
  private final Calculator calculator;

  /** Class constructor. Sets this as a PropertyChangeListener of the model's display register. */
  public Presenter() {
    calculator = new Calculator();
    calculator.getDisplay().addPropertyChangeListener(this);
  }

  /**
   * The driver for this program.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    Presenter presenter = new Presenter();
    // instantiate GUI in event dispatch thread
    SwingUtilities.invokeLater(
        () -> {
          gui = new Gui(presenter);
          gui.setVisible(true);
          gui.requestFocusInWindow();
        });
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

  /** Updates the view's display upon receipt of a PropertyChangeEvent. */
  @Override
  public void propertyChange(PropertyChangeEvent e) {
    gui.updateDisplay(e.getNewValue().toString());
  }
}
