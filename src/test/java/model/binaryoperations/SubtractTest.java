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
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class SubtractTest {

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
    expected = InputTest.SUBTRACT_IPX_IPY;
    actual = new BigNumber(subtract.executeBinary(InputTest.IPX, InputTest.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("BigInteger: two neg operands | -x - -y")
  void bigInteger_TwoNegativeOperands() {
    expected = InputTest.SUBTRACT_INX_INY;
    actual = new BigNumber(subtract.executeBinary(InputTest.INX, InputTest.INY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("BigInteger: one neg & one pos operand | -x - y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = InputTest.SUBTRACT_INX_IPY;
    actual = new BigNumber(subtract.executeBinary(InputTest.INX, InputTest.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("BigDecimal: two pos operands | x - y")
  void bigDecimal_TwoPositiveOperands() {
    expected = InputTest.SUBTRACT_DPX_DPY;
    actual = new BigNumber(subtract.executeBinary(InputTest.DPX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(7)
  @DisplayName("BigDecimal: two neg operands | -x - -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = InputTest.SUBTRACT_DNX_DNY;
    actual = new BigNumber(subtract.executeBinary(InputTest.DNX, InputTest.DNY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(8)
  @DisplayName("BigDecimal: one neg & one pos operand | -x - y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = InputTest.SUBTRACT_DNX_DPY;
    actual = new BigNumber(subtract.executeBinary(InputTest.DNX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }
}
