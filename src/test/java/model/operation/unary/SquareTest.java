package model.operation.unary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import driver.TestInput;
import number.BigNumber;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class SquareTest {

  private final Square square = new Square();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.083: Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = square.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.084: Operation is not Binary | isBinary == false")
  void isBinary_IsTrue() {
    boolean actual = square.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.085: Integer one positive 50 digit operand | x")
  void bigInteger_OnePositiveOperand() {
    expected = TestInput.SQUARE_IPX;
    actual = new BigNumber(square.executeUnary(TestInput.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.086: Integer one negative 50 digit operand | -x")
  void bigInteger_OneNegativeOperand() {
    expected = TestInput.SQUARE_INX;
    actual = new BigNumber(square.executeUnary(TestInput.INX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.087: Decimal one positive 50 digit operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected = TestInput.SQUARE_DPX;
    actual = new BigNumber(square.executeUnary(TestInput.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.088: Decimal one negative 50 digit operand | -x")
  void bigDecimal_OneNegativeOperand() {
    expected = TestInput.SQUARE_DNX;
    actual = new BigNumber(square.executeUnary(TestInput.DNX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }
}
