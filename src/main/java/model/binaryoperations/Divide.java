package model.binaryoperations;

import model.operation.BinaryOperation;
import number.Number;

/**
 * This class implements the division operation. (requirement 3.3.4)
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     BinaryOperation
 */
public class Divide extends BinaryOperation {

	/**
	 * Class constructor.
	 */
	public Divide() {
		precedence = 2;
	}

	/**
	 * Executes division.
	 *
	 * @return                     the result of division
	 * @throws ArithmeticException if the divisor is zero
	 */
	@Override
	public Number executeBinary(Number number1, Number number2) throws ArithmeticException {
		return number1.divide(number2);
	}
}
