package model;

/**
 * @author  Shawn Crahen
 * @version 2.1
 *
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

	/**
	 * Executes the "enter digit" state transition from the "ready state."
	 */
	@Override
	public State enterDigit(String digit) {
		if (!digit.equals("BKSP"))
			calculator.resetCalculator();
		else if (!calculator.getOperandStack().isEmpty()) {
			calculator.getOperandStack().pop();
		}
		calculator.sendDigitToDisplay(digit);
		return calculator.buildingOperand;
	}

	/**
	 * Executes the "enter operation" state transition from the "ready state."
	 * Handles NumberFormatException when display is an invalid representation of
	 * BigNumber.
	 */
	@Override
	public State enterOperation(Operation op) {
		// if no operands, push display to stack
		if (calculator.getOperandStack().isEmpty()) {
			try {
				calculator.pushDisplayToOperandStack();
			} catch (NumberFormatException e) {
				if (op instanceof model.AllClear || op instanceof model.Clear)
					calculator.pushOperation(op);
				else
					calculator.updateDisplay("Invalid operation");
				return this;
			}
		} else {
			if (op instanceof model.Clear)
				calculator.getOperandStack().pop();
		}
		calculator.pushOperation(op);

		if (op.isBinary() || op instanceof model.Clear)
			return calculator.nextOperand;
		else
			return this;
	}

	/**
	 * Executes the "enter constant" state transition from the "ready state."
	 */
	@Override
	public State enterConstant(Operation op) {
		calculator.resetCalculator();
		calculator.executeOperation(op);
		calculator.pushDisplayToOperandStack();
		return this;
	}

}
