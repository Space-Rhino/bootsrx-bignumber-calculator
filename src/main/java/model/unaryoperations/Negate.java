package model.unaryoperations;

import model.operation.UnaryOperation;
import number.Number;

/**
 * This class implements the negation operation. (requirement 3.3.12)
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     UnaryOperation
 */
public class Negate extends UnaryOperation {

	/**
	 * Executes the negation operation.
	 *
	 * @param  number the operand
	 * @return        number negated
	 */
	@Override
	public Number executeUnary(Number number) {
		return number.negate();
	}
}
