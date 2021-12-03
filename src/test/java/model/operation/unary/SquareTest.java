package model.operation.unary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

  @Test
  @DisplayName("Case# 1.083: Precedence equals 3")
  void getPrecedence_IsOne() {
    Square square = new Square();
    int actual = square.getPrecedence();
    int expected = 3;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.084: Operation is not Binary")
  void isBinary_IsTrue() {
    Square square = new Square();
    boolean actual = square.isBinary();
    assertFalse(actual);
  }

  @Test
  @DisplayName("Case# 1.085: Integer one positive operand | x")
  void bigInteger_OnePositiveOperand() {
    Square square = new Square();
    BigNumber expected = TestInput.SQUARE_IPX;
    BigNumber actual = new BigNumber(square.executeUnary(TestInput.IPX).toString());
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.086: Integer one negative operand | -x")
  void bigInteger_OneNegativeOperand() {
    Square square = new Square();
    BigNumber expected = TestInput.SQUARE_INX;
    BigNumber actual = new BigNumber(square.executeUnary(TestInput.INX).toString());
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.087: Decimal one positive operand | x")
  void bigDecimal_OnePositiveOperand() {
    Square square = new Square();
    BigNumber expected = TestInput.SQUARE_DPX;
    BigNumber actual = new BigNumber(square.executeUnary(TestInput.DPX).toString());
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.088: Decimal one negative operand | -x")
  void bigDecimal_OneNegativeOperand() {
    Square square = new Square();
    BigNumber expected = TestInput.SQUARE_DNX;
    BigNumber actual = new BigNumber(square.executeUnary(TestInput.DNX).toString());
    assertThat(actual).isEqualByComparingTo(expected);
  }
}
