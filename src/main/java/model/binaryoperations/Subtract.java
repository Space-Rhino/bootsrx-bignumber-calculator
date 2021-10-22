package model.binaryoperations;

import model.operation.BinaryOperation;
import number.Number;

/**
 * This class implements the subtraction operation. (requirement 3.3.2)
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     BinaryOperation
 */
public class Subtract extends BinaryOperation {

	/**
	 * Class constructor.
	 */
	public Subtract() {
		precedence = 1;
	}

	/**
	 * Executes subtraction.
	 *
	 * @return the result of subtraction
	 */
	@Override
	public Number executeBinary(Number number1, Number number2) {
		return number1.subtract(number2);
	}
}
