package number;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The BigNumber class is a concrete implementation of the Number interface. The
 * constructors and methods herein represent a subset of the
 * java.math.BigDecimal class, which this class is based on. Specifically,
 * BigNumber ensures explicit definition of precision using a
 * java.math.MathContext object.
 * 
 * @author  Shawn Crahen
 * @version 1.1
 * @see     number.Number
 * @see     java.math.BigDecimal
 *
 */
public class BigNumber implements Number {

	/**
	 * The BigDecimal representation of this BigNumber.
	 */
	protected BigDecimal number;

	/**
	 * The default java.math.MathContext object. This object is used in all
	 * constructors to explicitly set the precision of all BigNumber instances.
	 * (facilitates requirement 3.1.2)
	 */
	private static final MathContext DEFAULT_CONTEXT = new MathContext(100);

	/**
	 * Class constructor specifying a String number that is the basis for this
	 * BigNumber. The default context is used to set precision to 100.
	 * 
	 * @param  number                the string representation of the BigNumber
	 * @throws NumberFormatException if the String parameter is not a valid
	 *                               representation of a BigNumber
	 */
	public BigNumber(String number) throws NumberFormatException {
		this.number = new BigDecimal(number, DEFAULT_CONTEXT);
	}

	/**
	 * Class constructor specifying a BigDecimal that is the basis for this
	 * BigNumber. The default context is used to set precision to 100.
	 * 
	 * @param number the BigDecimal representation of the BigNumber
	 */
	public BigNumber(BigDecimal number) {
		this.number = new BigDecimal(number.toPlainString(), DEFAULT_CONTEXT);
	}

	@Override
	public Number add(Number other) {
		return new BigNumber(number.add(((BigNumber) other).number, DEFAULT_CONTEXT));
	}

	@Override
	public Number subtract(Number other) {
		return new BigNumber(number.subtract(((BigNumber) other).number, DEFAULT_CONTEXT));
	}

	@Override
	public Number multiply(Number other) {
		return new BigNumber(number.multiply(((BigNumber) other).number, DEFAULT_CONTEXT));
	}

	/**
	 * Divides two numbers. (requirement 3.3.4)
	 * 
	 * @param  other               the divisor
	 * @return                     the result of division
	 * @throws ArithmeticException if the divisor is zero
	 */
	@Override
	public Number divide(Number other) throws ArithmeticException {
		return new BigNumber(number.divide(((BigNumber) other).number, DEFAULT_CONTEXT));
	}

	@Override
	public Number square() {
		return new BigNumber(number.pow(2, DEFAULT_CONTEXT));
	}

	/**
	 * Calculates the square root of a number. (requirement 3.3.10)
	 * 
	 * @return                     the square root of this
	 * @throws ArithmeticException if this is negative
	 */
	@Override
	public Number squareRoot() throws ArithmeticException {
		return new BigNumber(number.sqrt(DEFAULT_CONTEXT));
	}

	@Override
	public Number negate() {
		return new BigNumber(number.negate());
	}

	@Override
	public Number inverse() {
		return (new BigNumber("1")).divide(this);
	}

	/**
	 * @return the String representation of this BigNumber
	 */
	@Override
	public String toString() {
		return number.toString();
	}
}
