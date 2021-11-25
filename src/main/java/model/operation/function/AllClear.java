package model.operation.function;

import model.app.Calculator;

/**
 * This class implements the all clear operation. (requirement 3.3.8)
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Operation
 */
public class AllClear extends Operation {

  /** Class constructor. */
  public AllClear() {
    precedence = 4;
    isBinary = false;
  }

  /** Executes the all clear operation. */
  @Override
  public void execute(Calculator calculator) {
    calculator.allClear();
  }
}
