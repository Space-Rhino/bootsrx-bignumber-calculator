package number;

import driver.InputTest;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
      assertThrows(NumberFormatException.class, () -> actual = new BigNumber(InputTest.I_INVALID));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.002: Decimal invalid data type throws exception")
    void bigDecimalInvalidDataType_ThrowsNumberFormatException() {
      Assertions.assertThrows(
          NumberFormatException.class, () -> actual = new BigNumber(InputTest.D_INVALID));
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
      expected = InputTest.ADD_IPX_IPY;
      actual = new BigNumber(InputTest.IPX.add(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.004: Integer two negative 50 digit operands | -x + -y")
    void bigInteger_TwoNegativeOperands() {
      expected = InputTest.ADD_INX_INY;
      actual = new BigNumber(InputTest.INX.add(InputTest.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.005: Integer one negative & one positive 50 digit operand | -x + y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.ADD_INX_IPY;
      actual = new BigNumber(InputTest.INX.add(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.006: Decimal two positive 50 digit operands | x + y")
    void bigDecimal_TwoPositiveOperands() {
      expected = InputTest.ADD_DPX_DPY;
      actual = new BigNumber(InputTest.DPX.add(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.007: Decimal two negative 50 digit operands | -x + -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = InputTest.ADD_DNX_DNY;
      actual = new BigNumber(InputTest.DNX.add(InputTest.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.008: Decimal one negative & one positive 50 digit operand | -x + y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.ADD_DNX_DPY;
      actual = new BigNumber(InputTest.DNX.add(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
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
      expected = InputTest.SUBTRACT_IPX_IPY;
      actual = new BigNumber(InputTest.IPX.subtract(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.010: Integer two negative 50 digit operands | -x - -y")
    void bigInteger_TwoNegativeOperands() {
      expected = InputTest.SUBTRACT_INX_INY;
      actual = new BigNumber(InputTest.INX.subtract(InputTest.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.011: Integer one negative & one positive 50 digit operand | -x - y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.SUBTRACT_INX_IPY;
      actual = new BigNumber(InputTest.INX.subtract(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.012: Integer two positive 50 digit operands | x - y")
    void bigDecimal_TwoPositiveOperands() {
      expected = InputTest.SUBTRACT_DPX_DPY;
      actual = new BigNumber(InputTest.DPX.subtract(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.013: Integer two negative 50 digit operands | -x - -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = InputTest.SUBTRACT_DNX_DNY;
      actual = new BigNumber(InputTest.DNX.subtract(InputTest.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.014: Integer one negative & one positive 50 digit operand | -x - y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.SUBTRACT_DNX_DPY;
      actual = new BigNumber(InputTest.DNX.subtract(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
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
      expected = InputTest.MULTIPLY_IPX_IPY;
      actual = new BigNumber(InputTest.IPX.multiply(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.016: Integer two negative 50 digit operands | -x * -y")
    void bigInteger_TwoNegativeOperands() {
      expected = InputTest.MULTIPLY_INX_INY;
      actual = new BigNumber(InputTest.INX.multiply(InputTest.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.017: Integer one negative & one positive 50 digit operand | -x * y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.MULTIPLY_INX_IPY;
      actual = new BigNumber(InputTest.INX.multiply(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.018: Decimal two positive 50 digit operands | x * y")
    void bigDecimal_TwoPositiveOperands() {
      expected = InputTest.MULTIPLY_DPX_DPY;
      actual = new BigNumber(InputTest.DPX.multiply(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.019: Decimal two negative 50 digit operands | -x * -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = InputTest.MULTIPLY_DNX_DNY;
      actual = new BigNumber(InputTest.DNX.multiply(InputTest.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.020: Decimal one negative & one positive 50 digit operand | -x * y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.MULTIPLY_DNX_DPY;
      actual = new BigNumber(InputTest.DNX.multiply(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
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
      expected = InputTest.DIVIDE_IPX_IPY;
      actual = new BigNumber(InputTest.IPX.divide(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.022: Integer two negative 50 digit operands | -x / -y")
    void bigInteger_TwoNegativeOperands() {
      expected = InputTest.DIVIDE_INX_INY;
      actual = new BigNumber(InputTest.INX.divide(InputTest.INY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.023: Integer one negative & one positive 50 digit operand | -x / y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.DIVIDE_INX_IPY;
      actual = new BigNumber(InputTest.INX.divide(InputTest.IPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.024: Decimal two positive 50 digit operands | x / y")
    void bigDecimal_TwoPositiveOperands() {
      expected = InputTest.DIVIDE_DPX_DPY;
      actual = new BigNumber(InputTest.DPX.divide(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(5)
    @DisplayName("Case# 1.025: Decimal two negative 50 digit operands | -x / -y")
    void bigDecimal_TwoNegativeOperands() {
      expected = InputTest.DIVIDE_DNX_DNY;
      actual = new BigNumber(InputTest.DNX.divide(InputTest.DNY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(6)
    @DisplayName("Case# 1.026: Decimal one negative & one positive 50 digit operand | -x / y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      expected = InputTest.DIVIDE_DNX_DPY;
      actual = new BigNumber(InputTest.DNX.divide(InputTest.DPY).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(7)
    @DisplayName(
        "Case# 1.027: Integer divide zero by pos & neg 50 digit operands | 0 / x && 0 / -x")
    void bigNumber_DivideZeroByBigNumber() {
      expected = InputTest.ZERO;
      actual = new BigNumber(InputTest.ZERO.divide(InputTest.IPX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));

      actual = new BigNumber(InputTest.ZERO.divide(InputTest.INX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(8)
    @DisplayName(
        "Case# 1.028: Decimal divide zero by pos & neg 50 digit operands | 0 / x && 0 / -x")
    void bigDecimal_DivideZeroByBigDecimal() {
      expected = InputTest.ZERO;
      actual = new BigNumber(InputTest.ZERO.divide(InputTest.DPX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));

      actual = new BigNumber(InputTest.ZERO.divide(InputTest.DNX).toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(9)
    @DisplayName(
        "Case# 1.029: Integer pos & neg 50 digit operands divide by zero | throws ArithmeticException")
    void bigNumber_DivideByZero() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(InputTest.IPX.divide(InputTest.ZERO).toString()));

      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(InputTest.INX.divide(InputTest.ZERO).toString()));
    }

    @Test
    @Order(10)
    @DisplayName(
        "Case# 1.030: Decimal pos & neg 50 digit operands divide by zero | throws ArithmeticException")
    void bigDecimal_DivideByZero() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(InputTest.DPX.divide(InputTest.ZERO).toString()));

      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(InputTest.DNX.divide(InputTest.ZERO).toString()));
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
      expected = InputTest.SQUARE_IPX;
      actual = new BigNumber(InputTest.IPX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.032: Integer one negative 50 digit operand | -x")
    void bigInteger_OneNegativeOperand() {
      expected = InputTest.SQUARE_INX;
      actual = new BigNumber(InputTest.INX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.033: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = InputTest.SQUARE_DPX;
      actual = new BigNumber(InputTest.DPX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.034: Decimal one negative 50 digit operand | -x")
    void bigDecimal_OneNegativeOperand() {
      expected = InputTest.SQUARE_DNX;
      actual = new BigNumber(InputTest.DNX.square().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
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
      expected = InputTest.SQUARE_ROOT_IPX;
      actual = new BigNumber(InputTest.IPX.squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.036: Integer one negative 50 digit operand | throws ArithmeticException")
    void bigInteger_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(InputTest.INX.squareRoot().toString()));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.037: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = InputTest.SQUARE_ROOT_DPX;
      actual = new BigNumber(InputTest.DPX.squareRoot().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.038: Decimal one negative 50 digit operand | throws ArithmeticException")
    void bigDecimal_OneNegativeOperand() {
      assertThrows(
          ArithmeticException.class,
          () -> actual = new BigNumber(InputTest.DNX.squareRoot().toString()));
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
      expected = InputTest.NEGATE_IPX;
      actual = new BigNumber(InputTest.IPX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.040: Integer one negative 50 digit operand | -x")
    void bigInteger_OneNegativeOperand() {
      expected = InputTest.NEGATE_INX;
      actual = new BigNumber(InputTest.INX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.041: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = InputTest.NEGATE_DPX;
      actual = new BigNumber(InputTest.DPX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.042: Decimal one negative 50 digit operand | -x")
    void bigDecimal_OneNegativeOperand() {
      expected = InputTest.NEGATE_DNX;
      actual = new BigNumber(InputTest.DNX.negate().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
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
      expected = InputTest.INVERSE_IPX;
      actual = new BigNumber(InputTest.IPX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(2)
    @DisplayName("Case# 1.044: Integer one negative 50 digit operand | -x")
    void bigInteger_OneNegativeOperand() {
      expected = InputTest.INVERSE_INX;
      actual = new BigNumber(InputTest.INX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
    }

    @Test
    @Order(3)
    @DisplayName("Case# 1.045: Decimal one positive 50 digit operand | x")
    void bigDecimal_OnePositiveOperand() {
      expected = InputTest.INVERSE_DPX;
      actual = new BigNumber(InputTest.DPX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }

    @Test
    @Order(4)
    @DisplayName("Case# 1.046: Decimal one negative 50 digit operand | -x")
    void bigDecimal_OneNegativeOperand() {
      expected = InputTest.INVERSE_DNX;
      actual = new BigNumber(InputTest.DNX.inverse().toString());
      assertThat(expected, Matchers.comparesEqualTo(actual));
      assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
    }
  }
}
