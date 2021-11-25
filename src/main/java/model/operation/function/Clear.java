package model.operation.function;

import model.app.Calculator;

/**
 * This class implements the clear operation. (requirement 3.3.7)
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Operation
 */
public class Clear extends Operation {

  /** Class constructor. */
  public Clear() {
    precedence = 4;
    isBinary = false;
  }

  /** Executes the clear operation. */
  @Override
  public void execute(Calculator calculator) {
    calculator.clear();
  }
}
