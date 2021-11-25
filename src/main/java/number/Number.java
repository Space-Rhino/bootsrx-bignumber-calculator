package number;

/**
 * This interface declares the arithmetic methods required for calculator logic.
 *
 * @author Shawn Crahen
 * @version 1.0
 */
public interface Number {

  /**
   * Adds two numbers. (requirement 3.3.1)
   *
   * @param other the augend
   * @return the result of addition
   */
  Number add(Number other);

  /**
   * Subtracts two numbers. (requirement 3.3.2)
   *
   * @param other the subtrahend
   * @return the result of subtraction
   */
  Number subtract(Number other);

  /**
   * Multiplies two numbers. (requirement 3.3.3)
   *
   * @param other the multiplicand
   * @return the result of multiplication
   */
  Number multiply(Number other);

  /**
   * Divides two numbers. (requirement 3.3.4)
   *
   * @param other the divisor
   * @return the result of division
   * @throws ArithmeticException if the divisor is zero
   */
  Number divide(Number other);

  /**
   * Squares a number. (requirement 3.3.11)
   *
   * @return this squared
   */
  Number square();

  /**
   * Calculates the square root of a number. (requirement 3.3.10)
   *
   * @return the square root of this
   */
  Number squareRoot();

  /**
   * Negates a number. (requirement 3.3.12)
   *
   * @return this negated
   * @throws ArithmeticException if this is negative
   */
  Number negate();

  /**
   * Calculates the inverse of a number. (requirement 3.3.13)
   *
   * @return the inverse of this
   * @throws ArithmeticException if this is zero
   */
  Number inverse();
}
