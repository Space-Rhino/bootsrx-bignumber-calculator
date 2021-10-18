package model;

/**
 * @author  Shawn Crahen
 * @version 2.1
 *
 */
public class BuildingOperandState extends State {

	/**
	 * Class constructor.
	 * 
	 * @param calculator the calculator object associated with this state
	 */
	public BuildingOperandState(Calculator calculator) {
		super(calculator);
	}

	/**
	 * Executes the "enter digit" state transition from the "building operand
	 * state."
	 */
	@Override
	public State enterDigit(String digit) {
		calculator.sendDigitToDisplay(digit);
		return this;
	}

	/**
	 * Executes the "enter operation" state transition from the "building operand
	 * state." Handles NumberFormatException when display is an invalid
	 * representation of BigNumber.
	 */
	@Override
	public State enterOperation(Operation op) {
		if (!(op instanceof model.Clear)) {
			try {
				calculator.pushDisplayToOperandStack();
			} catch (NumberFormatException e) {
				if (op instanceof model.AllClear)
					calculator.pushOperation(op);
				else
					calculator.updateDisplay("Invalid operation");
				return calculator.ready;
			}
		}
		calculator.pushOperation(op);

		if (op.isBinary() || op instanceof model.Clear)
			return calculator.nextOperand;
		else
			return calculator.ready;
	}

	/**
	 * Executes the "enter constant" state transition from the "building operand
	 * state."
	 */
	@Override
	public State enterConstant(Operation op) {
		calculator.executeOperation(op);
		return calculator.nextOperation;
	}

}
