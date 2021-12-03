package model.operation.unary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareRootTest {

  @Test
  @DisplayName("Case# 1.089: Precedence equals 3")
  void getPrecedence_IsOne() {
    SquareRoot squareRoot = new SquareRoot();
    int actual = squareRoot.getPrecedence();
    int expected = 3;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.090: Operation is NOT Binary")
  void isBinary_IsTrue() {
    SquareRoot squareRoot = new SquareRoot();
    boolean actual = squareRoot.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 1.091: Integer one positive operand | x")
  void bigInteger_OnePositiveOperand() {
    SquareRoot squareRoot = new SquareRoot();
    BigNumber actual = new BigNumber(squareRoot.executeUnary(TestInput.IPX).toString());
    BigNumber expected = TestInput.SQUARE_ROOT_IPX;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.092: Integer one negative operand | throws ArithmeticException")
  void bigInteger_OneNegativeOperand() {
    SquareRoot squareRoot = new SquareRoot();
    assertThatExceptionOfType(ArithmeticException.class)
        .isThrownBy(() -> squareRoot.executeUnary(TestInput.INX));
  }

  @Test
  @DisplayName("Case# 1.093: Decimal one positive operand | x")
  void bigDecimal_OnePositiveOperand() {
    SquareRoot squareRoot = new SquareRoot();
    BigNumber actual = new BigNumber(squareRoot.executeUnary(TestInput.DPX).toString());
    BigNumber expected = TestInput.SQUARE_ROOT_DPX;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.094: Decimal one negative operand | throws ArithmeticException")
  void bigDecimal_OneNegativeOperand() {
    SquareRoot squareRoot = new SquareRoot();
    assertThatExceptionOfType(ArithmeticException.class)
        .isThrownBy(() -> squareRoot.executeUnary(TestInput.DNX));
  }
}
