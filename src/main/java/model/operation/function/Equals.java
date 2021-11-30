package model.operation.function;

import model.app.Calculator;

/**
 * This class implements the Equals operation. (requirement 3.3.5)
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Operation
 */
public class Equals extends Operation {

  /** Class Constructor */
  public Equals() {
    precedence = 4;
    isBinary = false;
  }

  /** Executes the equals operation. */
  @Override
  public void execute(Calculator calculator) {
    calculator.equals();
  }
}
