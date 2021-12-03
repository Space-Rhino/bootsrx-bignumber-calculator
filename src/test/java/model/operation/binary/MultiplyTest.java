package model.operation.binary;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultiplyTest {

  @Test
  @DisplayName("Case# 1.063: Precedence equals 2")
  void getPrecedence_IsOne() {
    Multiply multiply = new Multiply();
    int actual = multiply.getPrecedence();
    int expected = 2;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.064: Operation is Binary | isBinary == true")
  void isBinary_IsTrue() {
    Multiply multiply = new Multiply();
    boolean actual = multiply.isBinary();
    assertThat(actual).isTrue();
  }

  @Test
  @DisplayName("Case# 1.065: Integer two positive 50 digit operands | x * y")
  void bigInteger_TwoPositiveOperands() {
    Multiply multiply = new Multiply();
    BigNumber actual =
        new BigNumber(multiply.executeBinary(TestInput.IPX, TestInput.IPY).toString());
    BigNumber expected = TestInput.MULTIPLY_IPX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.066: Integer two negative 50 digit operands | -x * -y")
  void bigInteger_TwoNegativeOperands() {
    Multiply multiply = new Multiply();
    BigNumber actual =
        new BigNumber(multiply.executeBinary(TestInput.INX, TestInput.INY).toString());
    BigNumber expected = TestInput.MULTIPLY_INX_INY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.067: Integer one negative & one positive 50 digit operand | -x * y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    Multiply multiply = new Multiply();
    BigNumber actual =
        new BigNumber(multiply.executeBinary(TestInput.INX, TestInput.IPY).toString());
    BigNumber expected = TestInput.MULTIPLY_INX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.068: Decimal two positive 50 digit operands | x * y")
  void bigDecimal_TwoPositiveOperands() {
    Multiply multiply = new Multiply();
    BigNumber actual =
        new BigNumber(multiply.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    BigNumber expected = TestInput.MULTIPLY_DPX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.069: Decimal two negative 50 digit operands | -x * -y")
  void bigDecimal_TwoNegativeOperands() {
    Multiply multiply = new Multiply();
    BigNumber actual =
        new BigNumber(multiply.executeBinary(TestInput.DNX, TestInput.DNY).toString());
    BigNumber expected = TestInput.MULTIPLY_DNX_DNY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.070: Decimal one negative & one positive 50 digit operand | -x * y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    Multiply multiply = new Multiply();
    BigNumber actual =
        new BigNumber(multiply.executeBinary(TestInput.DNX, TestInput.DPY).toString());
    BigNumber expected = TestInput.MULTIPLY_DNX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }
}
