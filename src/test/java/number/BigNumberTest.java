package number;

import driver.TestInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BigNumberTest {

  @Nested
  class TestBigNumberObject {

    @Test
    @DisplayName("Case# 1.001: Integer invalid data type throws exception")
    void bigIntegerInvalidDataType_ThrowsNumberFormatException() {
      assertThatExceptionOfType(NumberFormatException.class)
          .isThrownBy(() -> new BigNumber(TestInput.I_INVALID));
    }

    @Test
    @DisplayName("Case# 1.002: Decimal invalid data type throws exception")
    void bigDecimalInvalidDataType_ThrowsNumberFormatException() {
      assertThatExceptionOfType(NumberFormatException.class)
          .isThrownBy(() -> new BigNumber(TestInput.D_INVALID));
    }

    @Test
    @DisplayName("Case# 1.200: object BigNumber is same value as other BigNumber")
    void testEquals() {
      BigNumber actual = new BigNumber("42");
      BigNumber expected = new BigNumber("42");
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.201: object BigNumber hashcode is same value as other BigNumber")
    void testHashCode_NumberIsNull() throws NullPointerException {
      BigNumber actual = new BigNumber("88");
      BigNumber expected = new BigNumber("88");
      assertThat(actual.hashCode()).hasSameHashCodeAs(expected.hashCode());
    }

    @Test
    @DisplayName("Case# 1.202: object BigNumber compareTo is LESS THAN other BigNumber")
    void testCompareTo_LessThan() {
      int actual = new BigNumber("44").compareTo(new BigNumber("88"));
      int expected = -1;
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Case# 1.203: object BigNumber compareTo is EQUAL TO other BigNumber")
    void testCompareTo_EqualTo() {
      int actual = new BigNumber("44").compareTo(new BigNumber("44"));
      int expected = 0;
      assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Case# 1.204: object BigNumber compareTo is GREATER THAN other BigNumber")
    void testCompareTo_GreaterThan() {
      int actual = new BigNumber("88").compareTo(new BigNumber("44"));
      int expected = 1;
      assertThat(actual).isEqualTo(expected);
    }
  }

  @Nested
  class TestBigNumberAdd {

    @Test
    @DisplayName("Case# 1.003: Integer two positive | x + y")
    void bigInteger_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.IPX.add(TestInput.IPY).toString());
      BigNumber expected = TestInput.ADD_IPX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.004: Integer two negative | -x + -y")
    void bigInteger_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.INX.add(TestInput.INY).toString());
      BigNumber expected = TestInput.ADD_INX_INY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.005: Integer one negative & one positive operand | -x + y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.add(TestInput.IPY).toString());
      BigNumber expected = TestInput.ADD_INX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.006: Decimal two positive | x + y")
    void bigDecimal_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.DPX.add(TestInput.DPY).toString());
      BigNumber expected = TestInput.ADD_DPX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.007: Decimal two negative | -x + -y")
    void bigDecimal_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.DNX.add(TestInput.DNY).toString());
      BigNumber expected = TestInput.ADD_DNX_DNY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.008: Decimal one negative & one positive operand | -x + y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.add(TestInput.DPY).toString());
      BigNumber expected = TestInput.ADD_DNX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }
  }

  @Nested
  class TestBigNumberSubtract {

    @Test
    @DisplayName("Case# 1.009: Integer two positive | x - y")
    void bigInteger_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.IPX.subtract(TestInput.IPY).toString());
      BigNumber expected = TestInput.SUBTRACT_IPX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.010: Integer two negative | -x - -y")
    void bigInteger_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.INX.subtract(TestInput.INY).toString());
      BigNumber expected = TestInput.SUBTRACT_INX_INY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.011: Integer one negative & one positive operand | -x - y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.subtract(TestInput.IPY).toString());
      BigNumber expected = TestInput.SUBTRACT_INX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.012: Integer two positive | x - y")
    void bigDecimal_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.DPX.subtract(TestInput.DPY).toString());
      BigNumber expected = TestInput.SUBTRACT_DPX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.013: Integer two negative | -x - -y")
    void bigDecimal_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.DNX.subtract(TestInput.DNY).toString());
      BigNumber expected = TestInput.SUBTRACT_DNX_DNY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.014: Integer one negative & one positive operand | -x - y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.subtract(TestInput.DPY).toString());
      BigNumber expected = TestInput.SUBTRACT_DNX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }
  }

  @Nested
  class TestBigNumberMultiply {

    @Test
    @Order(1)
    @DisplayName("Case# 1.015: Integer two positive | x * y")
    void bigInteger_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.IPX.multiply(TestInput.IPY).toString());
      BigNumber expected = TestInput.MULTIPLY_IPX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.016: Integer two negative | -x * -y")
    void bigInteger_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.INX.multiply(TestInput.INY).toString());
      BigNumber expected = TestInput.MULTIPLY_INX_INY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.017: Integer one negative & one positive operand | -x * y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.multiply(TestInput.IPY).toString());
      BigNumber expected = TestInput.MULTIPLY_INX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.018: Decimal two positive | x * y")
    void bigDecimal_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.DPX.multiply(TestInput.DPY).toString());
      BigNumber expected = TestInput.MULTIPLY_DPX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.019: Decimal two negative | -x * -y")
    void bigDecimal_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.DNX.multiply(TestInput.DNY).toString());
      BigNumber expected = TestInput.MULTIPLY_DNX_DNY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.020: Decimal one negative & one positive operand | -x * y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.multiply(TestInput.DPY).toString());
      BigNumber expected = TestInput.MULTIPLY_DNX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }
  }

  @Nested
  class TestBigNumberDivide {

    @Test
    @DisplayName("Case# 1.021: Integer two positive | x / y")
    void bigInteger_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.IPX.divide(TestInput.IPY).toString());
      BigNumber expected = TestInput.DIVIDE_IPX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.022: Integer two negative | -x / -y")
    void bigInteger_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.INX.divide(TestInput.INY).toString());
      BigNumber expected = TestInput.DIVIDE_INX_INY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.023: Integer one negative & one positive operand | -x / y")
    void bigInteger_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.divide(TestInput.IPY).toString());
      BigNumber expected = TestInput.DIVIDE_INX_IPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.024: Decimal two positive | x / y")
    void bigDecimal_TwoPositiveOperands() {
      BigNumber actual = new BigNumber(TestInput.DPX.divide(TestInput.DPY).toString());
      BigNumber expected = TestInput.DIVIDE_DPX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.025: Decimal two negative | -x / -y")
    void bigDecimal_TwoNegativeOperands() {
      BigNumber actual = new BigNumber(TestInput.DNX.divide(TestInput.DNY).toString());
      BigNumber expected = TestInput.DIVIDE_DNX_DNY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.026: Decimal one negative & one positive operand | -x / y")
    void bigDecimal_OneNegativeAndOnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.divide(TestInput.DPY).toString());
      BigNumber expected = TestInput.DIVIDE_DNX_DPY;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.027: Integer divide zero by positive | 0 / x && 0 / -x")
    void bigNumber_DivideZeroPositiveByBigNumber() {
      BigNumber actual = new BigNumber(TestInput.ZERO.divide(TestInput.IPX).toString());
      BigNumber expected = new BigNumber("0");
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.027: Integer divide zero by negative | 0 / x && 0 / -x")
    void bigNumber_DivideZeroByNegativeBigNumber() {
      BigNumber actual = new BigNumber(TestInput.ZERO.divide(TestInput.INX).toString());
      BigNumber expected = new BigNumber("0");
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.028: Decimal divide zero by positive | 0 / x && 0 / -x")
    void bigDecimal_DivideZeroByPositiveBigDecimal() {
      BigNumber actual = new BigNumber(TestInput.ZERO.divide(TestInput.DPX).toString());
      BigNumber expected = new BigNumber("0");
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.028: Decimal divide zero by negative | 0 / x && 0 / -x")
    void bigDecimal_DivideZeroByNegativeBigDecimal() {
      BigNumber actual = new BigNumber(TestInput.ZERO.divide(TestInput.DNX).toString());
      BigNumber expected = new BigNumber("0");
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.029: Integer divide by zero | throws ArithmeticException")
    void bigNumber_DivideByZero() {
      BigNumber zero = new BigNumber("0");
      assertThatExceptionOfType(ArithmeticException.class)
          .isThrownBy(() -> TestInput.IPX.divide(zero));
    }

    @Test
    @DisplayName("Case# 1.030: Decimal divide by zero | throws ArithmeticException")
    void bigDecimal_DivideByZero() {
      BigNumber zero = new BigNumber("0");
      assertThatExceptionOfType(ArithmeticException.class)
          .isThrownBy(() -> TestInput.DPX.divide(zero));
    }
  }

  @Nested
  class TestBigNumberSquare {

    @Test
    @DisplayName("Case# 1.031: Integer one positive operand | x")
    void bigInteger_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.IPX.square().toString());
      BigNumber expected = TestInput.SQUARE_IPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.032: Integer one negative operand | -x")
    void bigInteger_OneNegativeOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.square().toString());
      BigNumber expected = TestInput.SQUARE_INX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.033: Decimal one positive operand | x")
    void bigDecimal_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DPX.square().toString());
      BigNumber expected = TestInput.SQUARE_DPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.034: Decimal one negative operand | -x")
    void bigDecimal_OneNegativeOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.square().toString());
      BigNumber expected = TestInput.SQUARE_DNX;
      assertThat(actual).isEqualByComparingTo(expected);
    }
  }

  @Nested
  class TestBigNumberSquareRoot {

    @Test
    @DisplayName("Case# 1.035: Integer one positive operand | x")
    void bigInteger_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.IPX.squareRoot().toString());
      BigNumber expected = TestInput.SQUARE_ROOT_IPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.036: Integer one negative operand | throws ArithmeticException")
    void bigInteger_OneNegativeOperand() {
      assertThatExceptionOfType(ArithmeticException.class)
          .isThrownBy(
              () -> {
                BigNumber negativeNumber = TestInput.INX;
                negativeNumber.squareRoot();
              });
    }

    @Test
    @DisplayName("Case# 1.037: Decimal one positive operand | x")
    void bigDecimal_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DPX.squareRoot().toString());
      BigNumber expected = TestInput.SQUARE_ROOT_DPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.038: Decimal one negative operand | throws ArithmeticException")
    void bigDecimal_OneNegativeOperand() {
      assertThatExceptionOfType(ArithmeticException.class)
          .isThrownBy(
              () -> {
                BigNumber negativeNumber = TestInput.DNX;
                negativeNumber.squareRoot();
              });
    }
  }

  @Nested
  class TestBigNumberNegate {

    @Test
    @DisplayName("Case# 1.039: Integer one positive operand | x")
    void bigInteger_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.IPX.negate().toString());
      BigNumber expected = TestInput.NEGATE_IPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.040: Integer one negative operand | -x")
    void bigInteger_OneNegativeOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.negate().toString());
      BigNumber expected = TestInput.NEGATE_INX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.041: Decimal one positive operand | x")
    void bigDecimal_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DPX.negate().toString());
      BigNumber expected = TestInput.NEGATE_DPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.042: Decimal one negative operand | -x")
    void bigDecimal_OneNegativeOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.negate().toString());
      BigNumber expected = TestInput.NEGATE_DNX;
      assertThat(actual).isEqualByComparingTo(expected);
    }
  }

  @Nested
  class TestBigNumberInverse {

    @Test
    @DisplayName("Case# 1.043: Integer one positive operand | x")
    void bigInteger_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.IPX.inverse().toString());
      BigNumber expected = TestInput.INVERSE_IPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.044: Integer one negative operand | -x")
    void bigInteger_OneNegativeOperand() {
      BigNumber actual = new BigNumber(TestInput.INX.inverse().toString());
      BigNumber expected = TestInput.INVERSE_INX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.045: Decimal one positive operand | x")
    void bigDecimal_OnePositiveOperand() {
      BigNumber actual = new BigNumber(TestInput.DPX.inverse().toString());
      BigNumber expected = TestInput.INVERSE_DPX;
      assertThat(actual).isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("Case# 1.046: Decimal one negative operand | -x")
    void bigDecimal_OneNegativeOperand() {
      BigNumber actual = new BigNumber(TestInput.DNX.inverse().toString());
      BigNumber expected = TestInput.INVERSE_DNX;
      assertThat(actual).isEqualByComparingTo(expected);
    }
  }
}
