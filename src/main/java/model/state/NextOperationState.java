package model.state;

import model.app.Calculator;
import model.operation.function.AllClear;
import model.operation.function.Operation;
import model.operation.function.Clear;

/**
 * This class is a concrete implementation of the State class which conforms to
 * this calculator's state diagram.
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see State
 */
public class NextOperationState extends State {

	/**
	 * Class constructor.
	 *
	 * @param calculator the calculator object associated with this state
	 */
	public NextOperationState(Calculator calculator) {
		super(calculator);
	}

	/**
	 * Executes the "enter digit" state transition from the "next operation state."
	 */
	@Override
	public State enterDigit(String digit) {
		if (!digit.equals("BKSP")) {
			calculator.resetDisplay();
		}
		calculator.sendDigitToDisplay(digit);
		return calculator.buildingOperand;
	}

	/**
	 * Executes the "enter operation" state transition from the "next operation
	 * state." Handles NumberFormatException when display is an invalid
	 * representation of BigNumber.
	 */
	@Override
	public State enterOperation(Operation op) {
		if (op instanceof Clear) {
			calculator.pushOperation(op);
			return calculator.nextOperand;
		}
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
		calculator.pushOperation(op);

		if (op.isBinary()) {
			return calculator.nextOperand;
		} else {
			return calculator.ready;
		}
	}

	/**
	 * Executes the "enter constant" state transition from the "next operation
	 * state."
	 */
	@Override
	public State enterConstant(Operation op) {
		calculator.executeOperation(op);
		return this;
	}

}
