package model.operation.unary;

import static org.assertj.core.api.Assertions.assertThat;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InverseTest {

  @Test
  @DisplayName("Case# 1.101: Precedence equals 3")
  void getPrecedence_IsOne() {
    Inverse inverse = new Inverse();
    int actual = inverse.getPrecedence();
    int expected = 3;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.102: Operation is NOT Binary")
  void isBinary_IsTrue() {
    Inverse inverse = new Inverse();
    boolean actual = inverse.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 1.103: Integer one positive operand | x")
  void bigInteger_OnePositiveOperand() {
    Inverse inverse = new Inverse();
    BigNumber actual = new BigNumber(inverse.executeUnary(TestInput.IPX).toString());
    BigNumber expected = TestInput.INVERSE_IPX;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.104: Integer one negative operand | -x")
  void bigInteger_OneNegativeOperand() {
    Inverse inverse = new Inverse();
    BigNumber actual = new BigNumber(inverse.executeUnary(TestInput.INX).toString());
    BigNumber expected = TestInput.INVERSE_INX;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.105: Decimal one positive operand | x")
  void bigDecimal_OnePositiveOperand() {
    Inverse inverse = new Inverse();
    BigNumber actual = new BigNumber(inverse.executeUnary(TestInput.DPX).toString());
    BigNumber expected = TestInput.INVERSE_DPX;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.106: Decimal one negative operand | -x")
  void bigDecimal_OneNegativeOperand() {
    Inverse inverse = new Inverse();
    BigNumber actual = new BigNumber(inverse.executeUnary(TestInput.DNX).toString());
    BigNumber expected = TestInput.INVERSE_DNX;
    assertThat(actual).isEqualByComparingTo(expected);
  }
}
