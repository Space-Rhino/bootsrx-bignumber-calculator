package model;

/**
 * @author  Shawn Crahen
 * @version 2.1
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
			if (op instanceof model.AllClear || op instanceof model.Clear)
				calculator.pushOperation(op);
			else
				calculator.updateDisplay("Invalid operation");
			return this;
		}
		calculator.pushOperation(op);

		if (op.isBinary())
			return calculator.nextOperand;
		else
			return calculator.ready;
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
