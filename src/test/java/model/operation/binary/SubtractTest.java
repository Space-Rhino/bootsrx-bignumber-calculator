package model.operation.binary;

import static org.assertj.core.api.Assertions.assertThat;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubtractTest {

  @Test
  @DisplayName("Case# 1.055: Precedence equals 1")
  void getPrecedence_IsOne() {
    Subtract subtract = new Subtract();
    int actual = subtract.getPrecedence();
    int expected = 1;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.056: Operation is Binary")
  void isBinary_IsTrue() {
    Subtract subtract = new Subtract();
    boolean actual = subtract.isBinary();
    assertThat(actual).isTrue();
  }

  @Test
  @DisplayName("Case# 1.057: Integer two positive operands | x - y")
  void bigInteger_TwoPositiveOperands() {
    Subtract subtract = new Subtract();
    BigNumber actual =
        new BigNumber(subtract.executeBinary(TestInput.IPX, TestInput.IPY).toString());
    BigNumber expected = TestInput.SUBTRACT_IPX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.058: Integer two negative operands | -x - -y")
  void bigInteger_TwoNegativeOperands() {
    Subtract subtract = new Subtract();
    BigNumber actual =
        new BigNumber(subtract.executeBinary(TestInput.INX, TestInput.INY).toString());
    BigNumber expected = TestInput.SUBTRACT_INX_INY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.059: Integer one negative & one positive operand | -x - y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    Subtract subtract = new Subtract();
    BigNumber actual =
        new BigNumber(subtract.executeBinary(TestInput.INX, TestInput.IPY).toString());
    BigNumber expected = TestInput.SUBTRACT_INX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.060: Decimal two positive operands | x - y")
  void bigDecimal_TwoPositiveOperands() {
    Subtract subtract = new Subtract();
    BigNumber actual =
        new BigNumber(subtract.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    BigNumber expected = TestInput.SUBTRACT_DPX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.061: Decimal two negative operands | -x - -y")
  void bigDecimal_TwoNegativeOperands() {
    Subtract subtract = new Subtract();
    BigNumber actual =
        new BigNumber(subtract.executeBinary(TestInput.DNX, TestInput.DNY).toString());
    BigNumber expected = TestInput.SUBTRACT_DNX_DNY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.062: Decimal one negative & one positive operand | -x - y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    Subtract subtract = new Subtract();
    BigNumber actual =
        new BigNumber(subtract.executeBinary(TestInput.DNX, TestInput.DPY).toString());
    BigNumber expected = TestInput.SUBTRACT_DNX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }
}
