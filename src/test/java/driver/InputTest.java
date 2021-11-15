package driver;

import number.BigNumber;

public class InputTest {

  public static final BigNumber ZERO = new BigNumber("0");
  public static final BigNumber PI =
      new BigNumber(
          "3.1415926535897932384626433832795028841971693993751"
              + "0582097494459230781640628620899862803482534211707");

  public static final String I_INVALID = "123abc456789";
  public static final String D_INVALID = "12.3abc456789";

  /* Number Acronym Definition
   * I = integer | D = decimal
   * P = positive | N = negative
   * X = operand 1 | Y = operand 2
   */
  public static final BigNumber IPX =
      new BigNumber("40000000000000000000000000000000000000000000000000");
  public static final BigNumber IPY =
      new BigNumber("20000000000000000000000000000000000000000000000000");
  public static final BigNumber INX =
      new BigNumber("-40000000000000000000000000000000000000000000000000");
  public static final BigNumber INY =
      new BigNumber("-20000000000000000000000000000000000000000000000000");
  public static final BigNumber I_RESULT_WRONG = new BigNumber("123456789");

  public static final BigNumber DPX =
      new BigNumber("0.0000000000000000000000000000000000000000000000004");
  public static final BigNumber DPY =
      new BigNumber("0.0000000000000000000000000000000000000000000000002");
  public static final BigNumber DNX =
      new BigNumber("-0.0000000000000000000000000000000000000000000000004");
  public static final BigNumber DNY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000002");
  public static final BigNumber D_RESULT_WRONG = new BigNumber("123.456789");

  // Add test results for operands
  public static final BigNumber ADD_IPX_IPY =
      new BigNumber("60000000000000000000000000000000000000000000000000");
  public static final BigNumber ADD_INX_INY =
      new BigNumber("-60000000000000000000000000000000000000000000000000");
  public static final BigNumber ADD_INX_IPY =
      new BigNumber("-20000000000000000000000000000000000000000000000000");
  public static final BigNumber ADD_DPX_DPY =
      new BigNumber("0.0000000000000000000000000000000000000000000000006");
  public static final BigNumber ADD_DNX_DNY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000006");
  public static final BigNumber ADD_DNX_DPY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000002");

  // Subtract test results for operands
  public static final BigNumber SUBTRACT_IPX_IPY =
      new BigNumber("20000000000000000000000000000000000000000000000000");
  public static final BigNumber SUBTRACT_INX_INY =
      new BigNumber("-20000000000000000000000000000000000000000000000000");
  public static final BigNumber SUBTRACT_INX_IPY =
      new BigNumber("-60000000000000000000000000000000000000000000000000");
  public static final BigNumber SUBTRACT_DPX_DPY =
      new BigNumber("0.0000000000000000000000000000000000000000000000002");
  public static final BigNumber SUBTRACT_DNX_DNY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000002");
  public static final BigNumber SUBTRACT_DNX_DPY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000006");

  // Multiply test results for operands
  public static final BigNumber MULTIPLY_IPX_IPY =
      new BigNumber(
          "8000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000000");
  public static final BigNumber MULTIPLY_INX_INY =
      new BigNumber(
          "8000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000000");
  public static final BigNumber MULTIPLY_INX_IPY =
      new BigNumber(
          "-8000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000000");
  public static final BigNumber MULTIPLY_DPX_DPY =
      new BigNumber(
          "0.0000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000008");
  public static final BigNumber MULTIPLY_DNX_DNY =
      new BigNumber(
          "0.0000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000008");
  public static final BigNumber MULTIPLY_DNX_DPY =
      new BigNumber(
          "-0.0000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000008");

  // Divide test results for operands
  public static final BigNumber DIVIDE_IPX_IPY = new BigNumber("2");
  public static final BigNumber DIVIDE_INX_INY = new BigNumber("2");
  public static final BigNumber DIVIDE_INX_IPY = new BigNumber("-2");
  public static final BigNumber DIVIDE_DPX_DPY = new BigNumber("2");
  public static final BigNumber DIVIDE_DNX_DNY = new BigNumber("2");
  public static final BigNumber DIVIDE_DNX_DPY = new BigNumber("-2");

  // Square test results for operand
  public static final BigNumber SQUARE_IPX =
      new BigNumber(
          "160000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000000");
  public static final BigNumber SQUARE_INX =
      new BigNumber(
          "160000000000000000000000000000000000000000000000000"
              + "0000000000000000000000000000000000000000000000000");
  public static final BigNumber SQUARE_DPX =
      new BigNumber(
          "0.000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000016");
  public static final BigNumber SQUARE_DNX =
      new BigNumber(
          "0.000000000000000000000000000000000000000000000000"
              + "00000000000000000000000000000000000000000000000016");

  // Square test results for operand
  public static final BigNumber SQUARE_ROOT_IPX =
      new BigNumber(
          "6324555320336758663997787"
              + ".08886543706743911027865043365371500970558518887727847644268849621675860059");
  public static final BigNumber SQUARE_ROOT_DPX =
      new BigNumber(
          "6.32455532033675866399778708886543706743911027865"
              + "04336537150097055851888772784764426884962167586E-25");

  // Negate test results for operand
  public static final BigNumber NEGATE_IPX =
      new BigNumber("-40000000000000000000000000000000000000000000000000");
  public static final BigNumber NEGATE_INX =
      new BigNumber("40000000000000000000000000000000000000000000000000");
  public static final BigNumber NEGATE_DPX =
      new BigNumber("-0.0000000000000000000000000000000000000000000000004");
  public static final BigNumber NEGATE_DNX =
      new BigNumber("0.0000000000000000000000000000000000000000000000004");

  // Inverse test results for operand
  public static final BigNumber INVERSE_IPX =
      new BigNumber("0.000000000000000000000000000000000000000000000000025");
  public static final BigNumber INVERSE_INX =
      new BigNumber("-0.000000000000000000000000000000000000000000000000025");
  public static final BigNumber INVERSE_DPX =
      new BigNumber("2500000000000000000000000000000000000000000000000");
  public static final BigNumber INVERSE_DNX =
      new BigNumber("-2500000000000000000000000000000000000000000000000");
}
