package model.operation.binary;

import driver.TestInput;
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
class SubtractTest {

  private final Subtract subtract = new Subtract();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.055: Precedence equals 1")
  void getPrecedence_IsOne() {
    int actual = subtract.getPrecedence();
    int expected = 1;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.056: Operation is Binary | isBinary == true")
  void isBinary_IsTrue() {
    boolean actual = subtract.isBinary();
    assertTrue(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.057: Integer two positive 50 digit operands | x - y")
  void bigInteger_TwoPositiveOperands() {
    expected = TestInput.SUBTRACT_IPX_IPY;
    actual = new BigNumber(subtract.executeBinary(TestInput.IPX, TestInput.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.058: Integer two negative 50 digit operands | -x - -y")
  void bigInteger_TwoNegativeOperands() {
    expected = TestInput.SUBTRACT_INX_INY;
    actual = new BigNumber(subtract.executeBinary(TestInput.INX, TestInput.INY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.059: Integer one negative & one positive 50 digit operand | -x - y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = TestInput.SUBTRACT_INX_IPY;
    actual = new BigNumber(subtract.executeBinary(TestInput.INX, TestInput.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.060: Decimal two positive 50 digit operands | x - y")
  void bigDecimal_TwoPositiveOperands() {
    expected = TestInput.SUBTRACT_DPX_DPY;
    actual = new BigNumber(subtract.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(7)
  @DisplayName("Case# 1.061: Decimal two negative 50 digit operands | -x - -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = TestInput.SUBTRACT_DNX_DNY;
    actual = new BigNumber(subtract.executeBinary(TestInput.DNX, TestInput.DNY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(8)
  @DisplayName("Case# 1.062: Decimal one negative & one positive 50 digit operand | -x - y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = TestInput.SUBTRACT_DNX_DPY;
    actual = new BigNumber(subtract.executeBinary(TestInput.DNX, TestInput.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }
}
