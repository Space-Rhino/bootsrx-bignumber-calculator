package model.operation.function;

import java.util.Deque;
import model.app.Calculator;
import number.Number;

/**
 * This class provides a skeletal implementation of a generic calculator operation. To implement an
 * operation, extend this class and override the execute(Calculator) method.
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Calculator
 */
public abstract class Operation {

  /**
   * The precedence of this operation. Higher values equate to higher precedence.
   *
   * <p>This value is used to preserve order of operations.
   */
  protected int precedence;

  /** True if the concrete implementation is a binary operation. */
  protected boolean isBinary;

  /** The stack of operands. */
  protected Deque<Number> stack;

  /**
   * Returns the precedence of this operation.
   *
   * @return the precedence of this operation
   */
  public int getPrecedence() {
    return precedence;
  }

  /**
   * Returns whether this operation is a binary operation.
   *
   * @return true if the operation is a binary operation; else false
   */
  public boolean isBinary() {
    return isBinary;
  }

  /**
   * Executes this operation.
   *
   * @param calculator the Calculator object for this operation
   */
  public abstract void execute(Calculator calculator);
}
