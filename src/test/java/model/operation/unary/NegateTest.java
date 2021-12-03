package model.operation.unary;

import static org.assertj.core.api.Assertions.assertThat;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NegateTest {

  @Test
  @DisplayName("Case# 1.095: Precedence equals 3")
  void getPrecedence_IsOne() {
    Negate negate = new Negate();
    int actual = negate.getPrecedence();
    int expected = 3;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.096: Operation is NOT Binary")
  void isBinary_IsTrue() {
    Negate negate = new Negate();
    boolean actual = negate.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 1.097: Integer one positive 50 digit operand | x")
  void bigInteger_OnePositiveOperand() {
    Negate negate = new Negate();
    BigNumber actual = new BigNumber(negate.executeUnary(TestInput.IPX).toString());
    BigNumber expected = TestInput.NEGATE_IPX;
    assertThat(expected).isEqualByComparingTo(actual);
  }

  @Test
  @DisplayName("Case# 1.098: Integer one negative 50 digit operand | -x")
  void bigInteger_OneNegativeOperand() {
    Negate negate = new Negate();
    BigNumber actual = new BigNumber(negate.executeUnary(TestInput.INX).toString());
    BigNumber expected = TestInput.NEGATE_INX;
    assertThat(expected).isEqualByComparingTo(actual);
  }

  @Test
  @DisplayName("Case# 1.099: Decimal one positive 50 digit operand | x")
  void bigDecimal_OnePositiveOperand() {
    Negate negate = new Negate();
    BigNumber actual = new BigNumber(negate.executeUnary(TestInput.DPX).toString());
    BigNumber expected = TestInput.NEGATE_DPX;
    assertThat(expected).isEqualByComparingTo(actual);
  }

  @Test
  @DisplayName("Case# 1.100: Decimal one negative 50 digit operand | -x")
  void bigDecimal_OneNegativeOperand() {
    Negate negate = new Negate();
    BigNumber actual = new BigNumber(negate.executeUnary(TestInput.DNX).toString());
    BigNumber expected = TestInput.NEGATE_DNX;
    assertThat(expected).isEqualByComparingTo(actual);
  }
}
