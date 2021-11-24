package model.operation.unary;

import number.Number;

/**
 * This class implements the square operation. (requirement 3.3.11)
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     UnaryOperation
 */
public class Square extends UnaryOperation {

	/**
	 * Executes the square operation.
	 *
	 * @param  number the operand
	 * @return        number squared
	 */
	@Override
	public Number executeUnary(Number number) {
		return number.multiply(number);
	}
}
