package model.operation.binary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import driver.TestInput;
import number.BigNumber;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class DivideTest {

  private final Divide divide = new Divide();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.071: Precedence equals 2")
  void getPrecedence_IsOne() {
    int actual = divide.getPrecedence();
    int expected = 2;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.072: Operation is Binary | isBinary == true")
  void isBinary_IsTrue() {
    boolean actual = divide.isBinary();
    assertTrue(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.073: Integer two positive 50 digit operands | x / y")
  void bigInteger_TwoPositiveOperands() {
    expected = TestInput.DIVIDE_IPX_IPY;
    actual = new BigNumber(divide.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.074: Integer two negative 50 digit operands | -x / -y")
  void bigInteger_TwoNegativeOperands() {
    expected = TestInput.DIVIDE_INX_INY;
    actual = new BigNumber(divide.executeBinary(TestInput.INX, TestInput.INY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.075: Integer one negative & one positive 50 digit operand | -x / y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = TestInput.DIVIDE_INX_IPY;
    actual = new BigNumber(divide.executeBinary(TestInput.INX, TestInput.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.076: Decimal two positive 50 digit operands | x / y")
  void bigDecimal_TwoPositiveOperands() {
    expected = TestInput.DIVIDE_DPX_DPY;
    actual = new BigNumber(divide.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(7)
  @DisplayName("Case# 1.077: Decimal two negative 50 digit operands | -x / -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = TestInput.DIVIDE_DNX_DNY;
    actual = new BigNumber(divide.executeBinary(TestInput.DNX, TestInput.DNY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(8)
  @DisplayName("Case# 1.078: Decimal one negative & one positive 50 digit operand | -x / y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = TestInput.DIVIDE_DNX_DPY;
    actual = new BigNumber(divide.executeBinary(TestInput.DNX, TestInput.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(9)
  @DisplayName("Case# 1.079: Integer divide zero by pos & neg 50 digit operands | 0 / x && 0 / -x")
  void bigNumber_DivideZeroByBigNumber() {
    expected = TestInput.ZERO;

    actual = new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));

    actual = new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.INX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(10)
  @DisplayName("Case# 1.080: Decimal divide zero by pos & neg 50 digit operands | 0 / x && 0 / -x")
  void bigDecimal_DivideZeroByBigDecimal() {
    expected = new BigNumber("0");

    actual = new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));

    actual = new BigNumber(divide.executeBinary(TestInput.ZERO, TestInput.DNX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(11)
  @DisplayName(
      "Case# 1.081: Integer pos & neg 50 digit operands divide by zero | throws ArithmeticException")
  void bigNumber_DivideByZero() {
    assertThrows(
        ArithmeticException.class,
        () ->
            actual = new BigNumber(divide.executeBinary(TestInput.IPX, TestInput.ZERO).toString()));
    assertThrows(
        ArithmeticException.class,
        () ->
            actual = new BigNumber(divide.executeBinary(TestInput.INX, TestInput.ZERO).toString()));
  }

  @Test
  @Order(12)
  @DisplayName(
      "Case# 1.082: Decimal pos & neg 50 digit operands divide by zero\n | throws ArithmeticException")
  void bigDecimal_DivideByZero() {
    assertThrows(
        ArithmeticException.class,
        () ->
            actual = new BigNumber(divide.executeBinary(TestInput.DPX, TestInput.ZERO).toString()));
    assertThrows(
        ArithmeticException.class,
        () ->
            actual = new BigNumber(divide.executeBinary(TestInput.DNX, TestInput.ZERO).toString()));
  }
}
