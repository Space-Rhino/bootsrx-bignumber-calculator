package number;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

/**
 * The BigNumber class is a concrete implementation of the Number interface. The constructors and
 * methods herein represent a subset of the java.math.BigDecimal class, which this class is based
 * on. Specifically, BigNumber ensures explicit definition of precision using a
 * java.math.MathContext object.
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Number
 * @see BigDecimal
 */
public class BigNumber implements Number, Comparable<BigNumber> {

  /**
   * The default MathContext object. This object is used in all constructors to explicitly set the
   * precision of all BigNumber instances. (facilitates requirement 3.1.2)
   */
  private static final MathContext DEFAULT_CONTEXT = new MathContext(100);

  /** The BigDecimal representation of this BigNumber. */
  protected BigDecimal number;

  /**
   * Class constructor specifying a String number that is the basis for this BigNumber. The default
   * context is used to set precision to 100.
   *
   * @param number the string representation of the BigNumber
   * @throws NumberFormatException if the String parameter is not a valid representation of a
   *     BigNumber
   */
  public BigNumber(String number) throws NumberFormatException {
    this.number = new BigDecimal(number, DEFAULT_CONTEXT);
  }

  /**
   * Class constructor specifying a BigDecimal that is the basis for this BigNumber. The default
   * context is used to set precision to 100.
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

  @Override
  public Number divide(Number other) throws ArithmeticException {
    return new BigNumber(number.divide(((BigNumber) other).number, DEFAULT_CONTEXT));
  }

  @Override
  public Number square() {
    return new BigNumber(number.pow(2, DEFAULT_CONTEXT));
  }

  @Override
  public Number squareRoot() throws ArithmeticException {
    return new BigNumber(number.sqrt(DEFAULT_CONTEXT));
  }

  @Override
  public Number negate() {
    return new BigNumber(number.negate());
  }

  @Override
  public Number inverse() throws ArithmeticException {
    return (new BigNumber("1")).divide(this);
  }

  /**
   * Returns a string representation of this BigNumber.
   *
   * <p>Removes trailing zeroes and trailing decimals. Uses scientific notation per the BigDecimal
   * toString() method but limits total digits to 100.
   *
   * @return a String representation of this
   */
  @Override
  public String toString() {
    String numString = number.toString();

    // remove trailing zeroes and decimals
    String[] subStrings = numString.split("E");
    subStrings[0] =
        subStrings[0].contains(".")
            ? subStrings[0].replaceAll("0*$", "").replaceAll("\\.$", "")
            : subStrings[0];
    numString = String.join("E", subStrings);

    // limit length to 100-digits
    if (numString.length() > 100) {
      subStrings[0] =
          subStrings.length > 1
              ? subStrings[0].substring(0, 99 - subStrings[1].length())
              : subStrings[0].substring(0, 100);
      numString = String.join("E", subStrings);
    }
    return numString;
  }

  /**
   * Compares this BigNumber with the specified Object for equality in value and scale. For example,
   * 2.0 is not equal to 2.00 when compared by this method, unlike compareTo method.
   *
   * @param object Object to which this BigNumber is being compared to.
   * @return only if the Object is a BigNumber with same value and scale as this BigNumber's.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    BigNumber bigNumber = (BigNumber) object;
    return Objects.equals(number, bigNumber.number);
  }

  /**
   * Returns the hash code for this BigNumber. If two BigNumber objects are numerically equal but
   * differ in scale, for example 2.0 and 2.00, they will generally not have the same hash code.
   *
   * @return hash code for this BigNumber
   */
  @Override
  public int hashCode() {
    if (number != null) {
      return number.hashCode();
    }
    return 0;
  }

  /**
   * Compares this BigNumber with other given BigNumber. Two BigNumber objects that are equal in
   * value but have a different scale, for example 2.0 and 2.00, are treated as equal.
   *
   * @param other BigNumber that is compared to another BigNumber
   * @return -1 if less than, 1 if greater than, and 0 if equal to.
   */
  @Override
  public int compareTo(BigNumber other) {
    return number.compareTo(other.number);
  }
}
