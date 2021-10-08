package model;

import number.Number;

/**
 * This class implements the square root operation. (requirement 3.3.10)
 * 
 * @author  Shawn Crahen
 * @version 1.1
 * @see     UnaryOperation
 */
public class SquareRoot extends UnaryOperation {

	/**
	 * Executes the square root operation.
	 * 
	 * @param  number              the operand
	 * @return                     the square root of number
	 * @throws ArithmeticException if number is negative
	 */
	@Override
	public Number executeUnary(Number number) throws ArithmeticException {
		return number.squareRoot();
	}
}
