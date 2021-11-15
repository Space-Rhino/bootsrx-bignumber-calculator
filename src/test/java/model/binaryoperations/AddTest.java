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
class AddTest {

  private final Add add = new Add();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.047: Precedence equals 1")
  void getPrecedence_IsOne() {
    int actual = add.getPrecedence();
    int expected = 1;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.048: Operation is Binary | isBinary == true")
  void isBinary_IsTrue() {
    boolean actual = add.isBinary();
    assertTrue(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.049: Integer two positive 50 digit operands | x + y")
  void bigInteger_TwoPositiveOperands() {
    expected = InputTest.ADD_IPX_IPY;
    actual = new BigNumber(add.executeBinary(InputTest.IPX, InputTest.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.050: Integer two negative 50 digit operands | -x + -y")
  void bigInteger_TwoNegativeOperands() {
    expected = InputTest.ADD_INX_INY;
    actual = new BigNumber(add.executeBinary(InputTest.INX, InputTest.INY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.051: Integer one negative & one positive 50 digit operand | -x + y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    expected = InputTest.ADD_INX_IPY;
    actual = new BigNumber(add.executeBinary(InputTest.INX, InputTest.IPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.I_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.052: Decimal two positive 50 digit operands | x + y")
  void bigDecimal_TwoPositiveOperands() {
    expected = InputTest.ADD_DPX_DPY;
    actual = new BigNumber(add.executeBinary(InputTest.DPX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(7)
  @DisplayName("Case# 1.053: Decimal two negative 50 digit operands | -x + -y")
  void bigDecimal_TwoNegativeOperands() {
    expected = InputTest.ADD_DNX_DNY;
    actual = new BigNumber(add.executeBinary(InputTest.DNX, InputTest.DNY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }

  @Test
  @Order(8)
  @DisplayName("Case# 1.054: Decimal one negative & one positive 50 digit operand | -x + y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    expected = InputTest.ADD_DNX_DPY;
    actual = new BigNumber(add.executeBinary(InputTest.DNX, InputTest.DPY).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(InputTest.D_RESULT_WRONG));
  }
}
