package model.state;

import model.app.Calculator;
import model.operation.function.Clear;
import model.operation.function.Operation;

/**
 * This class is a concrete implementation of the State class which conforms to
 * this calculator's state diagram.
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see State
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
		if (op instanceof Clear) {
			calculator.executeOperation(op);
		} else {
			calculator.replaceOperation(op);
		}

		if (op.isBinary() || op instanceof Clear) {
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
