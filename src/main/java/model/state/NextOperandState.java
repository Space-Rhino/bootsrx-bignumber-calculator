package model.state;

import model.operation.Calculator;
import model.operation.Operation;

/**
 * @author  Shawn Crahen
 * @version 1.0
 *
 */
public class NextOperandState extends State {

	/**
	 * Class constructor.
	 * 
	 * @param calculator the calculator object associated with this state
	 */
	public NextOperandState(Calculator calculator) {
		super(calculator);
	}

	/**
	 * Executes the "enter digit" state transition from the "next operand state."
	 */
	@Override
	public State enterDigit(String digit) {
		calculator.resetDisplay();
		calculator.sendDigitToDisplay(digit);
		return calculator.buildingOperand;
	}

	/**
	 * Executes the "enter operation" state transition from the "next operand
	 * state."
	 */
	@Override
	public State enterOperation(Operation op) {
		if (op instanceof model.operation.Clear) {
			calculator.executeOperation(op);
		} else {
			calculator.replaceOperation(op);
		}

		if (op.isBinary() || op instanceof model.operation.Clear) {
			return this;
		} else {
			return calculator.ready;
		}
	}

	/**
	 * Executes the "enter constant" state transition from the "next operand state."
	 */
	@Override
	public State enterConstant(Operation op) {
		calculator.executeOperation(op);
		return calculator.nextOperation;
	}

}
