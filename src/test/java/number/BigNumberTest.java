package number;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import driver.TestInput;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class BigNumberTest {

  private static BigNumber expected;
  private static BigNumber actual;

  @Nested
  @Order(1)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberInstantiation {

    @Test
    @Order(1)
    @DisplayName("Case# 1.001: Integer invalid data type throws exception")
    void bigIntegerInvalidDataType_ThrowsNumberFormatException() {
      assertThrows(NumberFormatException.class, () -> actual = new BigNumber(TestInput.I_INVALID));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.002: Decimal invalid data type throws exception")
    void bigDecimalInvalidDataType_ThrowsNumberFormatException() {
      Assertions.assertThrows(
          NumberFormatException.class, () -> actual = new BigNumber(TestInput.D_INVALID));
    }
  }

  @Nested
  @Order(2)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberAdd {

    @Test
    @Order(1)
    @DisplayName("Case# 1.003: Integer two positive 50 digit operands | x + y")
    void bigInteger_TwoPositiveOperands() {
      expected = TestInput.ADD_IPX_IPY;
      actual = new BigNumber(TestInput.IPX.add(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.004: Integer two negative 50 digit operands | -x + -y")
    void bigInteger_TwoNegativeOperands() {
      expected = TestInput.ADD_INX_INY;
      actual = new BigNumber(TestInput.INX.add(TestInput.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.005: Integer one negative & one positive 50 digit operand | -x + y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.ADD_INX_IPY;
      actual = new BigNumber(TestInput.INX.add(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.006: Decimal two positive 50 digit operands | x + y")
    void bigDecimal_TwoPositiveOperands() {
      expected = TestInput.ADD_DPX_DPY;
      actual = new BigNumber(TestInput.DPX.add(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.007: Decimal two negative 50 digit operands | -x + -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = TestInput.ADD_DNX_DNY;
      actual = new BigNumber(TestInput.DNX.add(TestInput.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.008: Decimal one negative & one positive 50 digit operand | -x + y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.ADD_DNX_DPY;
      actual = new BigNumber(TestInput.DNX.add(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }
  }

  @Nested
  @Order(3)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberSubtract {

    @Test
    @Order(1)
    @DisplayName("Case# 1.009: Integer two positive 50 digit operands | x - y")
    void bigInteger_TwoPositiveOperands() {
      expected = TestInput.SUBTRACT_IPX_IPY;
      actual = new BigNumber(TestInput.IPX.subtract(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.010: Integer two negative 50 digit operands | -x - -y")
    void bigInteger_TwoNegativeOperands() {
      expected = TestInput.SUBTRACT_INX_INY;
      actual = new BigNumber(TestInput.INX.subtract(TestInput.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.011: Integer one negative & one positive 50 digit operand | -x - y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.SUBTRACT_INX_IPY;
      actual = new BigNumber(TestInput.INX.subtract(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.012: Integer two positive 50 digit operands | x - y")
    void bigDecimal_TwoPositiveOperands() {
      expected = TestInput.SUBTRACT_DPX_DPY;
      actual = new BigNumber(TestInput.DPX.subtract(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.013: Integer two negative 50 digit operands | -x - -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = TestInput.SUBTRACT_DNX_DNY;
      actual = new BigNumber(TestInput.DNX.subtract(TestInput.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.014: Integer one negative & one positive 50 digit operand | -x - y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.SUBTRACT_DNX_DPY;
      actual = new BigNumber(TestInput.DNX.subtract(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }
  }

  @Nested
  @Order(4)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberMultiply {

    @Test
    @Order(1)
    @DisplayName("Case# 1.015: Integer two positive 50 digit operands | x * y")
    void bigInteger_TwoPositiveOperands() {
      expected = TestInput.MULTIPLY_IPX_IPY;
      actual = new BigNumber(TestInput.IPX.multiply(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.016: Integer two negative 50 digit operands | -x * -y")
    void bigInteger_TwoNegativeOperands() {
      expected = TestInput.MULTIPLY_INX_INY;
      actual = new BigNumber(TestInput.INX.multiply(TestInput.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.017: Integer one negative & one positive 50 digit operand | -x * y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.MULTIPLY_INX_IPY;
      actual = new BigNumber(TestInput.INX.multiply(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.018: Decimal two positive 50 digit operands | x * y")
    void bigDecimal_TwoPositiveOperands() {
      expected = TestInput.MULTIPLY_DPX_DPY;
      actual = new BigNumber(TestInput.DPX.multiply(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.019: Decimal two negative 50 digit operands | -x * -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = TestInput.MULTIPLY_DNX_DNY;
      actual = new BigNumber(TestInput.DNX.multiply(TestInput.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.020: Decimal one negative & one positive 50 digit operand | -x * y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.MULTIPLY_DNX_DPY;
      actual = new BigNumber(TestInput.DNX.multiply(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }
  }

  @Nested
  @Order(5)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberDivide {

    @Test
    @Order(1)
    @DisplayName("Case# 1.021: Integer two positive 50 digit operands | x / y")
    void bigInteger_TwoPositiveOperands() {
      expected = TestInput.DIVIDE_IPX_IPY;
      actual = new BigNumber(TestInput.IPX.divide(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.022: Integer two negative 50 digit operands | -x / -y")
    void bigInteger_TwoNegativeOperands() {
      expected = TestInput.DIVIDE_INX_INY;
      actual = new BigNumber(TestInput.INX.divide(TestInput.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.023: Integer one negative & one positive 50 digit operand | -x / y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.DIVIDE_INX_IPY;
      actual = new BigNumber(TestInput.INX.divide(TestInput.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.024: Decimal two positive 50 digit operands | x / y")
    void bigDecimal_TwoPositiveOperands() {
      expected = TestInput.DIVIDE_DPX_DPY;
      actual = new BigNumber(TestInput.DPX.divide(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.025: Decimal two negative 50 digit operands | -x / -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = TestInput.DIVIDE_DNX_DNY;
      actual = new BigNumber(TestInput.DNX.divide(TestInput.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.026: Decimal one negative & one positive 50 digit operand | -x / y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = TestInput.DIVIDE_DNX_DPY;
      actual = new BigNumber(TestInput.DNX.divide(TestInput.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(7)
    @DisplayName(
        "Case# 1.027: Integer divide zero by pos & neg 50 digit operands | 0 / x && 0 / -x")
    void bigNumber_DivideZeroByBigNumber() {
      expected = TestInput.ZERO;
      actual = new BigNumber(TestInput.ZERO.divide(TestInput.IPX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));

      actual = new BigNumber(TestInput.ZERO.divide(TestInput.INX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(8)
    @DisplayName(
        "Case# 1.028: Decimal divide zero by pos & neg 50 digit operands | 0 / x && 0 / -x")
    void bigDecimal_DivideZeroByBigDecimal() {
      expected = TestInput.ZERO;
      actual = new BigNumber(TestInput.ZERO.divide(TestInput.DPX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));

      actual = new BigNumber(TestInput.ZERO.divide(TestInput.DNX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(9)
    @DisplayName(
        "Case# 1.029: Integer pos & neg 50 digit operands divide by zero | throws ArithmeticException")
    void bigNumber_DivideByZero() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(TestInput.IPX.divide(TestInput.ZERO).toString()));

      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(TestInput.INX.divide(TestInput.ZERO).toString()));
    }

    @Test
    @Order(10)
    @DisplayName(
        "Case# 1.030: Decimal pos & neg 50 digit operands divide by zero | throws ArithmeticException")
    void bigDecimal_DivideByZero() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(TestInput.DPX.divide(TestInput.ZERO).toString()));

      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(TestInput.DNX.divide(TestInput.ZERO).toString()));
    }
  }

  @Nested
  @Order(6)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberSquare {

    @Test
    @Order(1)
    @DisplayName("Case# 1.031: Integer one positive 50 digit operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = TestInput.SQUARE_IPX;
      actual = new BigNumber(TestInput.IPX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.032: Integer one negative 50 digit operand | -x")
    void bigInteger_OneNegativeOperand() {
      expected = TestInput.SQUARE_INX;
      actual = new BigNumber(TestInput.INX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.033: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = TestInput.SQUARE_DPX;
      actual = new BigNumber(TestInput.DPX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.034: Decimal one negative 50 digit operand | -x")
    void bigDecimal_OneNegativeOperand() {
      expected = TestInput.SQUARE_DNX;
      actual = new BigNumber(TestInput.DNX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }
  }

  @Nested
  @Order(7)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberSquareRoot {

    @Test
    @Order(1)
    @DisplayName("Case# 1.035: Integer one positive 50 digit operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = TestInput.SQUARE_ROOT_IPX;
      actual = new BigNumber(TestInput.IPX.squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.036: Integer one negative 50 digit operand | throws ArithmeticException")
    void bigInteger_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(TestInput.INX.squareRoot().toString()));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.037: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = TestInput.SQUARE_ROOT_DPX;
      actual = new BigNumber(TestInput.DPX.squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.038: Decimal one negative 50 digit operand | throws ArithmeticException")
    void bigDecimal_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(TestInput.DNX.squareRoot().toString()));
    }
  }

  @Nested
  @Order(8)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberNegate {

    @Test
    @Order(1)
    @DisplayName("Case# 1.039: Integer one positive 50 digit operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = TestInput.NEGATE_IPX;
      actual = new BigNumber(TestInput.IPX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.040: Integer one negative 50 digit operand | -x")
    void bigInteger_OneNegativeOperand() {
      expected = TestInput.NEGATE_INX;
      actual = new BigNumber(TestInput.INX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.041: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = TestInput.NEGATE_DPX;
      actual = new BigNumber(TestInput.DPX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.042: Decimal one negative 50 digit operand | -x")
    void bigDecimal_OneNegativeOperand() {
      expected = TestInput.NEGATE_DNX;
      actual = new BigNumber(TestInput.DNX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }
  }

  @Nested
  @Order(9)
  @TestMethodOrder(OrderAnnotation.class)
  class TestBigNumberInverse {

    @Test
    @Order(1)
    @DisplayName("Case# 1.043: Integer one positive 50 digit operand | x")
    void bigInteger_OnePositiveOperand() {
      expected = TestInput.INVERSE_IPX;
      actual = new BigNumber(TestInput.IPX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.044: Integer one negative 50 digit operand | -x")
    void bigInteger_OneNegativeOperand() {
      expected = TestInput.INVERSE_INX;
      actual = new BigNumber(TestInput.INX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.045: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = TestInput.INVERSE_DPX;
      actual = new BigNumber(TestInput.DPX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.046: Decimal one negative 50 digit operand | -x")
    void bigDecimal_OneNegativeOperand() {
      expected = TestInput.INVERSE_DNX;
      actual = new BigNumber(TestInput.DNX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
    }
  }
}
