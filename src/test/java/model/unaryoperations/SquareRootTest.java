package model.unaryoperations;

import number.BigNumber;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import testutilities.TestNumbers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(OrderAnnotation.class)
class SquareRootTest {
  private final TestNumbers number = new TestNumbers();
  private final SquareRoot squareRoot = new SquareRoot();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = squareRoot.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  void isBinary_IsTrue() {
    boolean actual = squareRoot.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("BigInteger: one pos operand | x")
  void bigInteger_OnePositiveOperand() {
    expected =
        new BigNumber(
            "6324555320336758663997787"
                + ".08886543706743911027865043365371500970558518887727847644268849621675860059");
    actual = new BigNumber(squareRoot.executeUnary(number.getIntPosX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: one neg operand | x")
  void bigInteger_OneNegativeOperand() {
    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(squareRoot.executeUnary(number.getIntNegX()).toString()));
  }

  @Test
  @Order(5)
  @DisplayName("BigDecimal: one pos operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected =
        new BigNumber(
            "6.32455532033675866399778708886543706743911027865"
                + "04336537150097055851888772784764426884962167586E-25");
    actual = new BigNumber(squareRoot.executeUnary(number.getDecPosX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: one neg operand | x")
  void bigDecimal_OneNegativeOperand() {
    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(squareRoot.executeUnary(number.getDecNegX()).toString()));
  }
}
