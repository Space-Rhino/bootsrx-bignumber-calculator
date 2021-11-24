package model.operation.binary;

import number.Number;

/**
 * This class implements the addition operation. (requirement 3.3.1)
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     BinaryOperation
 */
public class Add extends BinaryOperation {

	/**
	 * Class constructor.
	 */
	public Add() {
		precedence = 1;
	}

	/**
	 * Executes addition.
	 *
	 * @return the result of addition
	 */
	@Override
	public Number executeBinary(Number number1, Number number2) {
		return number1.add(number2);
	}
}
