package model.binaryoperations;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class SubtractTest {
  private final TestNumbers number = new TestNumbers();
  private final Subtract subtract = new Subtract();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Precedence equals 1")
  void getPrecedence_IsOne() {
    int actual = subtract.getPrecedence();
    int expected = 1;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  void isBinary_IsTrue() {
    boolean actual = subtract.isBinary();
    assertTrue(actual);
  }

  @Test
  @Order(3)
  @DisplayName("BigInteger: two pos operands | x - y")
  void bigInteger_TwoPositiveOperands() {
    expected = new BigNumber("20000000000000000000000000000000000000000000000000");
    actual =
        new BigNumber(subtract.executeBinary(number.getIntPosX(), number.getIntPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: two neg operands | -x - -y")
  void bigInteger_TwoNegativeOperands() {
    expected = new BigNumber("-20000000000000000000000000000000000000000000000000");
    actual =
        new BigNumber(subtract.executeBinary(number.getIntNegX(), number.getIntNegY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(5)
  @DisplayName("BigInteger: one neg & one pos operand | -x - y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = new BigNumber("-60000000000000000000000000000000000000000000000000");
    actual =
        new BigNumber(subtract.executeBinary(number.getIntNegX(), number.getIntPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: two pos operands | x - y")
  void bigDecimal_TwoPositiveOperands() {
    expected = new BigNumber("0.0000000000000000000000000000000000000000000000002");
    actual =
        new BigNumber(subtract.executeBinary(number.getDecPosX(), number.getDecPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(7)
  @DisplayName("BigDecimal: two neg operands | -x - -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = new BigNumber("-0.0000000000000000000000000000000000000000000000002");
    actual =
        new BigNumber(subtract.executeBinary(number.getDecNegX(), number.getDecNegY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(8)
  @DisplayName("BigDecimal: one neg & one pos operand | -x - y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = new BigNumber("-0.0000000000000000000000000000000000000000000000006");
    actual =
        new BigNumber(subtract.executeBinary(number.getDecNegX(), number.getDecPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }
}
