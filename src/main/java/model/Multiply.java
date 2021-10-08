package model;

import number.Number;

/**
 * This class implements the multiplication operation. (requirement 3.3.3)
 * 
 * @author  Shawn Crahen
 * @version 1.1
 * @see     BinaryOperation
 *
 */
public class Multiply extends BinaryOperation {

	/**
	 * Class constructor.
	 */
	public Multiply() {
		precedence = 2;
	}

	/**
	 * Executes multiplication.
	 * 
	 * @return the result of multiplication
	 */
	@Override
	public Number executeBinary(Number number1, Number number2) {
		return number1.multiply(number2);
	}
}
