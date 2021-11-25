package model.state;

import model.app.Calculator;
import model.operation.function.AllClear;
import model.operation.function.Clear;
import model.operation.function.Operation;

/**
 * This class is a concrete implementation of the State class which conforms to this calculator's
 * state diagram.
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see State
 */
public class ReadyState extends State {

  /**
   * Class constructor.
   *
   * @param calculator the calculator object associated with this state
   */
  public ReadyState(Calculator calculator) {
    super(calculator);
  }

  /** Executes the "enter digit" state transition from the "ready state." */
  @Override
  public State enterDigit(String digit) {
    boolean isErrorState = (calculator.getDisplay().getValue()).matches(".*[a-z].*");

    // error state: set display to 0 and stay in ready state
    if (digit.equals("BKSP") && isErrorState) {
      calculator.clear();
      return this;
    }
    // not backspace
    else if (!digit.equals("BKSP")) {
      calculator.resetCalculator();
    }
    // operandStack has expired data
    else if (!calculator.getOperandStack().isEmpty()) {
      calculator.getOperandStack().pop();
    }

    calculator.sendDigitToDisplay(digit);
    return calculator.buildingOperand;
  }

  /**
   * Executes the "enter operation" state transition from the "ready state." Handles
   * NumberFormatException when display is an invalid representation of BigNumber.
   */
  @Override
  public State enterOperation(Operation op) {
    // if no operands, push display to stack
    if (calculator.getOperandStack().isEmpty()) {
      try {
        calculator.pushDisplayToOperandStack();
      } catch (NumberFormatException e) {
        if (op instanceof AllClear || op instanceof Clear) {
          calculator.pushOperation(op);
        } else {
          calculator.updateDisplay("Invalid operation");
        }
        return this;
      }
    } else {
      if (op instanceof Clear) {
        calculator.getOperandStack().pop();
      }
    }
    calculator.pushOperation(op);

    if (op.isBinary() || op instanceof Clear) {
      return calculator.nextOperand;
    } else {
      return this;
    }
  }

  /** Executes the "enter constant" state transition from the "ready state." */
  @Override
  public State enterConstant(Operation op) {
    calculator.resetCalculator();
    calculator.executeOperation(op);
    calculator.pushDisplayToOperandStack();
    return this;
  }
}
