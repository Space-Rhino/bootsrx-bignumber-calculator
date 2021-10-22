package model.unaryoperations;

import model.TestNumbers;
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
class NegateTest {
  private final TestNumbers number = new TestNumbers();
  private final Negate negate = new Negate();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = negate.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  void isBinary_IsTrue() {
    boolean actual = negate.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("BigInteger: one pos operand | x")
  void bigInteger_OnePositiveOperand() {
    expected = new BigNumber("-40000000000000000000000000000000000000000000000000");
    actual = new BigNumber(negate.executeUnary(number.getIntPosX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: one neg operand | x")
  void bigInteger_OneNegativeOperand() {
    expected = new BigNumber("40000000000000000000000000000000000000000000000000");
    actual = new BigNumber(negate.executeUnary(number.getIntNegX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(5)
  @DisplayName("BigDecimal: one pos operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected = new BigNumber("-0.0000000000000000000000000000000000000000000000004");
    actual = new BigNumber(negate.executeUnary(number.getDecPosX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: one neg operand | x")
  void bigDecimal_OneNegativeOperand() {
    expected = new BigNumber("0.0000000000000000000000000000000000000000000000004");
    actual = new BigNumber(negate.executeUnary(number.getDecNegX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }
}
