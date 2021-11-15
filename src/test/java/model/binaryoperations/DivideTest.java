package model.binaryoperations;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class DivideTest {

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
    expected = InputTest.DIVIDE_IPX_IPY;
    actual = new BigNumber(divide.executeBinary(InputTest.DPX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: two neg operands | -x / -y")
  void bigInteger_TwoNegativeOperands() {
    expected = InputTest.DIVIDE_INX_INY;
    actual = new BigNumber(divide.executeBinary(InputTest.INX, InputTest.INY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("BigInteger: one neg & one pos operand | -x / y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = InputTest.DIVIDE_INX_IPY;
    actual = new BigNumber(divide.executeBinary(InputTest.INX, InputTest.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: two pos operands | x / y")
  void bigDecimal_TwoPositiveOperands() {
    expected = InputTest.DIVIDE_DPX_DPY;
    actual = new BigNumber(divide.executeBinary(InputTest.DPX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(7)
  @DisplayName("BigDecimal: two neg operands | -x / -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = InputTest.DIVIDE_DNX_DNY;
    actual = new BigNumber(divide.executeBinary(InputTest.DNX, InputTest.DNY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(8)
  @DisplayName("BigDecimal: one neg & one pos operand | -x / y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = InputTest.DIVIDE_DNX_DPY;
    actual = new BigNumber(divide.executeBinary(InputTest.DNX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(9)
  @DisplayName("BigInteger: divide zero by number | 0 / x && 0 / -x")
  void bigNumber_DivideZeroByBigNumber() {
    expected = InputTest.ZERO;

    actual = new BigNumber(divide.executeBinary(InputTest.ZERO, InputTest.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));

    actual = new BigNumber(divide.executeBinary(InputTest.ZERO, InputTest.INX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(10)
  @DisplayName("BigDecimal: divide zero by number | 0 / x && 0 / -x")
  void bigDecimal_DivideZeroByBigDecimal() {
    expected = new BigNumber("0");

    actual = new BigNumber(divide.executeBinary(InputTest.ZERO, InputTest.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));

    actual = new BigNumber(divide.executeBinary(InputTest.ZERO, InputTest.DNX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(11)
  @DisplayName("BigInteger: divide by zero | throws ArithmeticException")
  void bigNumber_DivideByZero() {
    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(divide.executeBinary(InputTest.IPX, InputTest.ZERO).toString()));

    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(divide.executeBinary(InputTest.INX, InputTest.ZERO).toString()));
  }

  @Test
  @Order(12)
  @DisplayName("BigDecimal: divide zero by number | throws ArithmeticException")
  void bigDecimal_DivideByZero() {
    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(divide.executeBinary(InputTest.DPX, InputTest.ZERO).toString()));

    assertThrows(
        ArithmeticException.class,
        () -> actual = new BigNumber(divide.executeBinary(InputTest.DNX, InputTest.ZERO).toString()));
  }
}
