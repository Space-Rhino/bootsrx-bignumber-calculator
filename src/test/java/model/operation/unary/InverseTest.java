package model.operation.unary;

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
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
class InverseTest {

  private final Inverse inverse = new Inverse();
  private BigNumber expected;
  private BigNumber actual;

  @Test
  @Order(1)
  @DisplayName("Case# 1.101: Precedence equals 3")
  void getPrecedence_IsOne() {
    int actual = inverse.getPrecedence();
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 1.102: Operation is not Binary | isBinary == false")
  void isBinary_IsTrue() {
    boolean actual = inverse.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 1.103: Integer one positive 50 digit operand | x")
  void bigInteger_OnePositiveOperand() {
    expected = TestInput.INVERSE_IPX;
    actual = new BigNumber(inverse.executeUnary(TestInput.IPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(4)
  @DisplayName("Case# 1.104: Integer one negative 50 digit operand\n | -x")
  void bigInteger_OneNegativeOperand() {
    expected = TestInput.INVERSE_INX;
    actual = new BigNumber(inverse.executeUnary(TestInput.INX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.I_RESULT_WRONG));
  }

  @Test
  @Order(5)
  @DisplayName("Case# 1.105: Decimal one positive 50 digit operand | x")
  void bigDecimal_OnePositiveOperand() {
    expected = TestInput.INVERSE_DPX;
    actual = new BigNumber(inverse.executeUnary(TestInput.DPX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }

  @Test
  @Order(6)
  @DisplayName("Case# 1.106: Decimal one negative 50 digit operand | -x")
  void bigDecimal_OneNegativeOperand() {
    expected = TestInput.INVERSE_DNX;
    actual = new BigNumber(inverse.executeUnary(TestInput.DNX).toString());
    assertThat(expected, Matchers.comparesEqualTo(actual));
    assertThat(expected, Matchers.not(TestInput.D_RESULT_WRONG));
  }
}
