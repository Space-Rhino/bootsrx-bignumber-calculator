package model.unaryoperations;

import driver.InputTest;
import number.BigNumber;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
class SquareTest {

  private final Square square = new Square();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = square.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  void isBinary_IsTrue() {
    boolean actual = square.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("BigInteger: one pos operand | x")
  void bigInteger_OnePositiveOperand() {
    expected = InputTest.SQUARE_IPX;
    actual = new BigNumber(square.executeUnary(InputTest.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: one neg operand | x")
  void bigInteger_OneNegativeOperand() {
    expected = InputTest.SQUARE_INX;
    actual = new BigNumber(square.executeUnary(InputTest.INX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("BigDecimal: one pos operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected = InputTest.SQUARE_DPX;
    actual = new BigNumber(square.executeUnary(InputTest.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: one neg operand | x")
  void bigDecimal_OneNegativeOperand() {
    expected = InputTest.SQUARE_DNX;
    actual = new BigNumber(square.executeUnary(InputTest.DNX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }
}
