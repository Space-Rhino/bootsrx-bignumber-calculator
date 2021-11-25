package model.operation.unary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import driver.TestInput;
import number.BigNumber;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class SquareRootTest {

  private final SquareRoot squareRoot = new SquareRoot();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.089: Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = squareRoot.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.090: Operation is not Binary | isBinary == false")
  void isBinary_IsTrue() {
    boolean actual = squareRoot.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.091: Integer one positive 50 digit operand | x")
  void bigInteger_OnePositiveOperand() {
    expected = TestInput.SQUARE_ROOT_IPX;
    actual = new BigNumber(squareRoot.executeUnary(TestInput.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.092: Integer one negative 50 digit operand | throws ArithmeticException")
  void bigInteger_OneNegativeOperand() {
    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(squareRoot.executeUnary(TestInput.INX).toString()));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.093: Decimal one positive 50 digit operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected = TestInput.SQUARE_ROOT_DPX;
    actual = new BigNumber(squareRoot.executeUnary(TestInput.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.094: Decimal one negative 50 digit operand | throws ArithmeticException")
  void bigDecimal_OneNegativeOperand() {
    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(squareRoot.executeUnary(TestInput.DNX).toString()));
  }
}
