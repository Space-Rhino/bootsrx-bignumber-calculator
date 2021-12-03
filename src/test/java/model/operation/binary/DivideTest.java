package model.operation.binary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DivideTest {

  @Test
  @DisplayName("Case# 1.071: Precedence equals 2")
  void getPrecedence_IsOne() {
    Divide divide = new Divide();
    int actual = divide.getPrecedence();
    int expected = 2;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.072: Operation is Binary")
  void isBinary_IsTrue() {
    Divide divide = new Divide();
    boolean actual = divide.isBinary();
    assertThat(actual).isTrue();
  }

  @Test
  @DisplayName("Case# 1.073: Integer two positive operands | x / y")
  void bigInteger_TwoPositiveOperands() {
    Divide divide = new Divide();
    BigNumber actual = new BigNumber(divide.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    BigNumber expected = TestInput.DIVIDE_IPX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.074: Integer two negative operands | -x / -y")
  void bigInteger_TwoNegativeOperands() {
    Divide divide = new Divide();
    BigNumber actual = new BigNumber(divide.executeBinary(TestInput.INX, TestInput.INY).toString());
    BigNumber expected = TestInput.DIVIDE_INX_INY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.075: Integer one negative & one positive operand | -x / y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    Divide divide = new Divide();
    BigNumber actual = new BigNumber(divide.executeBinary(TestInput.INX, TestInput.IPY).toString());
    BigNumber expected = TestInput.DIVIDE_INX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.076: Decimal two positive operands | x / y")
  void bigDecimal_TwoPositiveOperands() {
    Divide divide = new Divide();
    BigNumber actual = new BigNumber(divide.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    BigNumber expected = TestInput.DIVIDE_DPX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.077: Decimal two negative operands | -x / -y")
  void bigDecimal_TwoNegativeOperands() {
    Divide divide = new Divide();
    BigNumber actual = new BigNumber(divide.executeBinary(TestInput.DNX, TestInput.DNY).toString());
    BigNumber expected = TestInput.DIVIDE_DNX_DNY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.078: Decimal one negative & one positive operand | -x / y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    Divide divide = new Divide();
    BigNumber actual = new BigNumber(divide.executeBinary(TestInput.DNX, TestInput.DPY).toString());
    BigNumber expected = TestInput.DIVIDE_DNX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.079: Integer divide zero by positive operand | 0 / x && 0 / -x")
  void bigNumber_DivideZeroByPositiveInteger() {
    Divide divide = new Divide();
    BigNumber actual =
        new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.IPX).toString());
    BigNumber expected = TestInput.ZERO;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.079: Integer divide zero by negative operands | 0 / x && 0 / -x")
  void bigNumber_DivideZeroByNegativeInteger() {
    Divide divide = new Divide();
    BigNumber actual =
        new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.INX).toString());
    BigNumber expected = TestInput.ZERO;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.080: Decimal divide zero by positive | 0 / x && 0 / -x")
  void bigDecimal_DivideZeroByPositiveDecimal() {
    Divide divide = new Divide();
    BigNumber actual =
        new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.DPX).toString());
    BigNumber expected = new BigNumber("0");
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.080: Decimal divide zero by negative operands | 0 / x && 0 / -x")
  void bigDecimal_DivideZeroByNegativeDecimal() {
    Divide divide = new Divide();
    BigNumber actual =
        new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.DNX).toString());
    BigNumber expected = new BigNumber("0");
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.081: Integer positive operand divide by zero | throws ArithmeticException")
  void bigNumber_DividePositiveBigNumberByZero() {
    Divide divide = new Divide();
    assertThatExceptionOfType(ArithmeticException.class)
        .isThrownBy(() -> divide.executeBinary(TestInput.IPX, TestInput.ZERO));
  }

  @Test
  @DisplayName("Case# 1.081: Integer positive operand divide by zero | throws ArithmeticException")
  void bigNumber_DivideByPositiveInteger() {
    Divide divide = new Divide();
    assertThatExceptionOfType(ArithmeticException.class)
        .isThrownBy(() -> divide.executeBinary(TestInput.IPX, TestInput.ZERO));
  }

  @Test
  @DisplayName("Case# 1.082: Decimal negative operands divide by zero | throws ArithmeticException")
  void bigNumber_DivideByNegativeDecimal() {
    Divide divide = new Divide();
    assertThatExceptionOfType(ArithmeticException.class)
        .isThrownBy(() -> divide.executeBinary(TestInput.DNX, TestInput.ZERO));
  }
}
