package number;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BigNumberTest {
  private static final BigNumber ZERO = new BigNumber("0");
  private static BigNumber expected;
  private static BigNumber actual;

  private final BigNumber intPosX =
      new BigNumber("40000000000000000000000000000000000000000000000000");
  private final BigNumber intPosY =
      new BigNumber("20000000000000000000000000000000000000000000000000");
  private final BigNumber intNegX =
      new BigNumber("-40000000000000000000000000000000000000000000000000");
  private final BigNumber intNegY =
      new BigNumber("-20000000000000000000000000000000000000000000000000");
  private final BigNumber wrongIntResult = new BigNumber("123456789");

  private final BigNumber decPosX =
      new BigNumber("0.0000000000000000000000000000000000000000000000004");
  private final BigNumber decPosY =
      new BigNumber("0.0000000000000000000000000000000000000000000000002");
  private final BigNumber decNegX =
      new BigNumber("-0.0000000000000000000000000000000000000000000000004");
  private final BigNumber decNegY =
      new BigNumber("-0.0000000000000000000000000000000000000000000000002");
  private final BigNumber wrongDecResult = new BigNumber("123.456789");

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumber {
    BigNumber bigNumber;

    @Test
    @Order(1)
    @DisplayName("BigInteger: invalid data type")
    void bigIntegerInvalidDataType_ThrowsNumberFormatException() {
      String invalidIntegerType = "123abc456789";
      assertThrows(
          NumberFormatException.class, () -> bigNumber = new BigNumber(invalidIntegerType));
    }

    @Test
    @Order(2)
    @DisplayName("BigDecimal: invalid data type")
    void bigDecimalInvalidDataType_ThrowsNumberFormatException() {
      String invalidDecimalType = "12.3abc456789";
      Assertions.assertThrows(
          NumberFormatException.class, () -> bigNumber = new BigNumber(invalidDecimalType));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestAdd {

    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x + y")
    void bigInteger_TwoPositiveOperands() {
      expected = new BigNumber("60000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intPosX.add(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x + -y")
    void bigInteger_TwoNegativeOperands() {
      expected = new BigNumber("-60000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.add(intNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x + y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-20000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.add(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x + y")
    void bigDecimal_TwoPositiveOperands() {
      expected = new BigNumber("0.0000000000000000000000000000000000000000000000006");
      actual = new BigNumber(decPosX.add(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x + -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000006");
      actual = new BigNumber(decNegX.add(decNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x + y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000002");
      actual = new BigNumber(decNegX.add(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestSubtract {

    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x - y")
    void bigInteger_TwoPositiveOperands() {
      expected = new BigNumber("20000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intPosX.subtract(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x - -y")
    void bigInteger_TwoNegativeOperands() {
      expected = new BigNumber("-20000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.subtract(intNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x - y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-60000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.subtract(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x - y")
    void bigDecimal_TwoPositiveOperands() {
      expected = new BigNumber("0.0000000000000000000000000000000000000000000000002");
      actual = new BigNumber(decPosX.subtract(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x - -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000002");
      actual = new BigNumber(decNegX.subtract(decNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x - y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000006");
      actual = new BigNumber(decNegX.subtract(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestMultiply {

    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x * y")
    void bigInteger_TwoPositiveOperands() {
      expected =
          new BigNumber(
              "800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intPosX.multiply(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x * -y")
    void bigInteger_TwoNegativeOperands() {
      expected =
          new BigNumber(
              "800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.multiply(intNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x * y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected =
          new BigNumber(
              "-800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.multiply(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x * y")
    void bigDecimal_TwoPositiveOperands() {
      expected =
          new BigNumber(
              "0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008");
      actual = new BigNumber(decPosX.multiply(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x * -y")
    void bigDecimal_TwoNegativeOperands() {
      expected =
          new BigNumber(
              "0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008");
      actual = new BigNumber(decNegX.multiply(decNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x * y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected =
          new BigNumber(
              "-0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008");
      actual = new BigNumber(decNegX.multiply(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestDivide {

    @Test
    @Order(1)
    @DisplayName("BigInteger: two pos operands | x / y")
    void bigInteger_TwoPositiveOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(intPosX.divide(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: two neg operands | -x / -y")
    void bigInteger_TwoNegativeOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(intNegX.divide(intNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigInteger: one neg & one pos operand | -x / y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-2");
      actual = new BigNumber(intNegX.divide(intPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: two pos operands | x / y")
    void bigDecimal_TwoPositiveOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(decPosX.divide(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(5)
    @DisplayName("BigDecimal: two neg operands | -x / -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = new BigNumber("2");
      actual = new BigNumber(decNegX.divide(decNegY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(6)
    @DisplayName("BigDecimal: one neg & one pos operand | -x / y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = new BigNumber("-2");
      actual = new BigNumber(decNegX.divide(decPosY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(7)
    @DisplayName("BigInteger: divide zero by number | 0 / x && 0 / -x")
    void bigNumber_DivideZeroByBigNumber() {
      expected = new BigNumber("0");
      actual = new BigNumber(ZERO.divide(intPosX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));

      actual = new BigNumber(ZERO.divide(intNegX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(8)
    @DisplayName("BigDecimal: divide zero by number | 0 / x && 0 / -x")
    void bigDecimal_DivideZeroByBigDecimal() {
      expected = new BigNumber("0");
      actual = new BigNumber(ZERO.divide(decPosX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));

      actual = new BigNumber(ZERO.divide(decNegX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(9)
    @DisplayName("BigInteger: divide by zero | throws ArithmeticException")
    void bigNumber_DivideByZero() {
      assertThrows(
          ArithmeticException.class, () -> actual = new BigNumber(intPosX.divide(ZERO).toString()));

      assertThrows(
          ArithmeticException.class, () -> actual = new BigNumber(intNegX.divide(ZERO).toString()));
    }

    @Test
    @Order(10)
    @DisplayName("BigDecimal: divide zero by number | throws ArithmeticException")
    void bigDecimal_DivideByZero() {
      assertThrows(
          ArithmeticException.class, () -> actual = new BigNumber(decPosX.divide(ZERO).toString()));

      assertThrows(
          ArithmeticException.class, () -> actual = new BigNumber(decNegX.divide(ZERO).toString()));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestSquare {

    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected =
          new BigNumber(
              "1600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intPosX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      expected =
          new BigNumber(
              "1600000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected =
          new BigNumber(
              "0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000016");
      actual = new BigNumber(decPosX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      expected =
          new BigNumber(
              "0.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000016");
      actual = new BigNumber(decNegX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestSquareRoot {

    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected =
          new BigNumber(
              "6324555320336758663997787.08886543706743911027865043365371500970558518887727847644268849621675860059");
      actual = new BigNumber(intPosX.squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class, () -> actual = new BigNumber(intNegX.squareRoot().toString()));
    }

    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected =
          new BigNumber(
              "6.3245553203367586639977870888654370674391102786504336537150097055851888772784764426884962167586E-25");
      actual = new BigNumber(decPosX.squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class, () -> actual = new BigNumber(decNegX.squareRoot().toString()));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestNegate {

    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = new BigNumber("-40000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intPosX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      expected = new BigNumber("40000000000000000000000000000000000000000000000000");
      actual = new BigNumber(intNegX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = new BigNumber("-0.0000000000000000000000000000000000000000000000004");
      actual = new BigNumber(decPosX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      expected = new BigNumber("0.0000000000000000000000000000000000000000000000004");
      actual = new BigNumber(decNegX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }
  }

  @Nested
  @TestMethodOrder(OrderAnnotation.class)
  class TestInverse {

    @Test
    @Order(1)
    @DisplayName("BigInteger: one pos operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = new BigNumber("0.000000000000000000000000000000000000000000000000025");
      actual = new BigNumber(intPosX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(2)
    @DisplayName("BigInteger: one neg operand | x")
    void bigInteger_OneNegativeOperand() {
      expected = new BigNumber("-0.000000000000000000000000000000000000000000000000025");
      actual = new BigNumber(intNegX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongIntResult));
    }

    @Test
    @Order(3)
    @DisplayName("BigDecimal: one pos operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = new BigNumber("2500000000000000000000000000000000000000000000000");
      actual = new BigNumber(decPosX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }

    @Test
    @Order(4)
    @DisplayName("BigDecimal: one neg operand | x")
    void bigDecimal_OneNegativeOperand() {
      expected = new BigNumber("-2500000000000000000000000000000000000000000000000");
      actual = new BigNumber(decNegX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(wrongDecResult));
    }
  }
}
