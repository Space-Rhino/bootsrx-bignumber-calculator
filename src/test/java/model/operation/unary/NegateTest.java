package model.operation.unary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import driver.TestInput;
import number.BigNumber;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class NegateTest {

  private final Negate negate = new Negate();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.095: Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = negate.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.096: Operation is not Binary | isBinary == false")
  void isBinary_IsTrue() {
    boolean actual = negate.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.097: Integer one positive 50 digit operand | x")
  void bigInteger_OnePositiveOperand() {
    expected = TestInput.NEGATE_IPX;
    actual = new BigNumber(negate.executeUnary(TestInput.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.098: Integer one negative 50 digit operand | -x")
  void bigInteger_OneNegativeOperand() {
    expected = TestInput.NEGATE_INX;
    actual = new BigNumber(negate.executeUnary(TestInput.INX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.099: Decimal one positive 50 digit operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected = TestInput.NEGATE_DPX;
    actual = new BigNumber(negate.executeUnary(TestInput.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.100: Decimal one negative 50 digit operand | -x")
  void bigDecimal_OneNegativeOperand() {
    expected = TestInput.NEGATE_DNX;
    actual = new BigNumber(negate.executeUnary(TestInput.DNX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }
}
