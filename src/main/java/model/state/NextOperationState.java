package model.state;

import model.operation.Calculator;
import model.operation.Operation;

/**
 * @author  Shawn Crahen
 * @version 1.0
 *
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
		calculator.resetDisplay();
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
		try {
			calculator.pushDisplayToOperandStack();
		} catch (NumberFormatException e) {
			if (op instanceof model.operation.AllClear || op instanceof model.operation.Clear) {
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
