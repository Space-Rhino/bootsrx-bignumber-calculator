package model.binaryoperations;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class MultiplyTest {
  private final TestNumbers number = new TestNumbers();
  private final Multiply multiply = new Multiply();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Precedence equals 2")
  void getPrecedence_IsOne() {
    int actual = multiply.getPrecedence();
    int expected = 2;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  void isBinary_IsTrue() {
    boolean actual = multiply.isBinary();
    assertTrue(actual);
  }

  @Test
  @Order(3)
  @DisplayName("BigInteger: two pos operands | x * y")
  void bigInteger_TwoPositiveOperands() {
    expected =
        new BigNumber(
            "800000000000000000000000000000000000000000000000000"
                + "000000000000000000000000000000000000000000000000");
    actual =
        new BigNumber(multiply.executeBinary(number.getIntPosX(), number.getIntPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: two neg operands | -x * -y")
  void bigInteger_TwoNegativeOperands() {
    expected =
        new BigNumber(
            "800000000000000000000000000000000000000000000000000"
                + "000000000000000000000000000000000000000000000000");
    actual =
        new BigNumber(multiply.executeBinary(number.getIntNegX(), number.getIntNegY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(5)
  @DisplayName("BigInteger: one neg & one pos operand | -x * y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected =
        new BigNumber(
            "-80000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000000");
    actual =
        new BigNumber(multiply.executeBinary(number.getIntNegX(), number.getIntPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: two pos operands | x * y")
  void bigDecimal_TwoPositiveOperands() {
    expected =
        new BigNumber(
            "0.0000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000008");
    actual =
        new BigNumber(multiply.executeBinary(number.getDecPosX(), number.getDecPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(7)
  @DisplayName("BigDecimal: two neg operands | -x * -y")
  void bigDecimal_TwoNegativeOperands() {
    expected =
        new BigNumber(
            "0.0000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000008");
    actual =
        new BigNumber(multiply.executeBinary(number.getDecNegX(), number.getDecNegY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(8)
  @DisplayName("BigDecimal: one neg & one pos operand | -x * y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected =
        new BigNumber(
            "-0.000000000000000000000000000000000000000000000000"
                + "00000000000000000000000000000000000000000000000008");
    actual =
        new BigNumber(multiply.executeBinary(number.getDecNegX(), number.getDecPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }
}
