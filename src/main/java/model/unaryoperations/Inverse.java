package model.unaryoperations;

import model.operation.UnaryOperation;
import number.Number;

/**
 * This class implements the inverse operation. (requirement 3.3.13)
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     UnaryOperation
 */
public class Inverse extends UnaryOperation {

	/**
	 * Executes the inverse operation.
	 *
	 * @param  number              the operand
	 * @return                     the inverse of number
	 * @throws ArithmeticException if number is zero
	 */
	@Override
	public Number executeUnary(Number number) throws ArithmeticException {
		return number.inverse();
	}
}
