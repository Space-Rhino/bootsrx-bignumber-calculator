package model.operation.unary;

import java.util.EmptyStackException;
import model.app.Calculator;
import model.operation.function.Operation;
import number.Number;

/**
 * This class provides a skeletal implementation of a generic unary operation. To implement a unary
 * operation, extend this class and override the executeUnary(Number) method.
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Operation
 * @see Number
 */
public abstract class UnaryOperation extends Operation {

  /** Class constructor. */
  protected UnaryOperation() {
    precedence = 3;
    isBinary = false;
  }

  /**
   * Executes this operation.
   *
   * <p>Calls executeUnary(Number), pushes the result back to the operand stack, then updates the
   * display.
   *
   * <p>If executeUnary(Number) throws an ArithmeticException, updates display indicating invalid
   * operation.
   *
   * @throws EmptyStackException if stack is empty
   */
  @Override
  public void execute(Calculator calculator) throws EmptyStackException {
    stack = calculator.getOperandStack();
    try {
      stack.push(executeUnary(stack.pop()));
      calculator.updateDisplay();
    } catch (ArithmeticException e) { // sqrt of negative number or inverse of zero
      calculator.updateDisplay("Invalid operation");
    }
  }

  /**
   * Executes this unary operation.
   *
   * @param number the operand
   * @return the result of this unary operation
   */
  public abstract Number executeUnary(Number number);
}
