package model.operation.function;

import model.app.Calculator;

/**
 * This class implements the pi operation. (requirement 3.3.9)
 *
 * @author Shawn Crahen
 * @version 1.0
 */
public class Pi extends Operation {

  /** String representation of pi with 100-digit precision. */
  private static final String PI_100_DIGITS =
      "3.141592653589793238462643383279502884197169399375105"
          + "82097494459230781640628620899862803482534211707";

  /** Class constructor. */
  public Pi() {
    precedence = 4;
    isBinary = false;
  }

  /** Updates the calculator display with pi. */
  @Override
  public void execute(Calculator calculator) {
    calculator.updateDisplay(PI_100_DIGITS);
  }
}
