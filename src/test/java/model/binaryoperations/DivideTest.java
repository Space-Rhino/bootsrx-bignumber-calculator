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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class DivideTest {
  private final TestNumbers number = new TestNumbers();
  private final Divide divide = new Divide();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Precedence equals 2")
  void getPrecedence_IsOne() {
    int actual = divide.getPrecedence();
    int expected = 2;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  void isBinary_IsTrue() {
    boolean actual = divide.isBinary();
    assertTrue(actual);
  }

  @Test
  @Order(3)
  @DisplayName("BigInteger: two pos operands | x / y")
  void bigInteger_TwoPositiveOperands() {
    expected = new BigNumber("2");
    actual =
        new BigNumber(divide.executeBinary(number.getIntPosX(), number.getIntPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: two neg operands | -x / -y")
  void bigInteger_TwoNegativeOperands() {
    expected = new BigNumber("2");
    actual =
        new BigNumber(divide.executeBinary(number.getIntNegX(), number.getIntNegY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(5)
  @DisplayName("BigInteger: one neg & one pos operand | -x / y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = new BigNumber("-2");
    actual =
        new BigNumber(divide.executeBinary(number.getIntNegX(), number.getIntPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: two pos operands | x / y")
  void bigDecimal_TwoPositiveOperands() {
    expected = new BigNumber("2");
    actual =
        new BigNumber(divide.executeBinary(number.getDecPosX(), number.getDecPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(7)
  @DisplayName("BigDecimal: two neg operands | -x / -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = new BigNumber("2");
    actual =
        new BigNumber(divide.executeBinary(number.getDecNegX(), number.getDecNegY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(8)
  @DisplayName("BigDecimal: one neg & one pos operand | -x / y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = new BigNumber("-2");
    actual =
        new BigNumber(divide.executeBinary(number.getDecNegX(), number.getDecPosY()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(9)
  @DisplayName("BigInteger: divide zero by number | 0 / x && 0 / -x")
  void bigNumber_DivideZeroByBigNumber() {
    expected = new BigNumber("0");
    actual = new BigNumber(divide.executeBinary(number.getZERO(), number.getIntPosX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));

    actual = new BigNumber(divide.executeBinary(number.getZERO(), number.getIntNegX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongIntResult()));
  }

  @Test
  @Order(10)
  @DisplayName("BigDecimal: divide zero by number | 0 / x && 0 / -x")
  void bigDecimal_DivideZeroByBigDecimal() {
    expected = new BigNumber("0");
    actual = new BigNumber(divide.executeBinary(number.getZERO(), number.getDecPosX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));

    actual = new BigNumber(divide.executeBinary(number.getZERO(), number.getDecNegX()).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(number.getWrongDecResult()));
  }

  @Test
  @Order(11)
  @DisplayName("BigInteger: divide by zero | throws ArithmeticException")
  void bigNumber_DivideByZero() {
    assertThrows(
        ArithmeticException.class,
        () ->
            actual =
                new BigNumber(
                    divide.executeBinary(number.getIntPosX(), number.getZERO()).toString()));

    assertThrows(
        ArithmeticException.class,
        () ->
            actual =
                new BigNumber(
                    divide.executeBinary(number.getIntNegX(), number.getZERO()).toString()));
  }

  @Test
  @Order(12)
  @DisplayName("BigDecimal: divide zero by number | throws ArithmeticException")
  void bigDecimal_DivideByZero() {
    assertThrows(
        ArithmeticException.class,
        () ->
            actual =
                new BigNumber(
                    divide.executeBinary(number.getDecPosX(), number.getZERO()).toString()));

    assertThrows(
        ArithmeticException.class,
        () ->
            actual =
                new BigNumber(
                    divide.executeBinary(number.getDecNegX(), number.getZERO()).toString()));
  }
}
