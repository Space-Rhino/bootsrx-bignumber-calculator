package model.operation.binary;

import driver.TestInput;
import number.BigNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
class AddTest {

  @Test
  @DisplayName("Case# 1.047: Precedence equals 1")
  void getPrecedence_IsOne() {
    Add add = new Add();
    int actual = add.getPrecedence();
    int expected = 1;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 1.048: Operation is Binary | isBinary == true")
  void isBinary_IsTrue() {
    Add add = new Add();
    boolean actual = add.isBinary();
    assertThat(actual).isTrue();
  }

  @Test
  @DisplayName("Case# 1.049: Integer two positive 50 digit operands | x + y")
  void bigInteger_TwoPositiveOperands() {
    Add add = new Add();
    BigNumber actual = new BigNumber(add.executeBinary(TestInput.IPX, TestInput.IPY).toString());
    BigNumber expected = TestInput.ADD_IPX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.050: Integer two negative 50 digit operands | -x + -y")
  void bigInteger_TwoNegativeOperands() {
    Add add = new Add();
    BigNumber actual = new BigNumber(add.executeBinary(TestInput.INX, TestInput.INY).toString());
    BigNumber expected = TestInput.ADD_INX_INY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.051: Integer one negative & one positive 50 digit operand | -x + y")
  void bigInteger_OneNegativeAndOnePositiveOperand() {
    Add add = new Add();
    BigNumber actual = new BigNumber(add.executeBinary(TestInput.INX, TestInput.IPY).toString());
    BigNumber expected = TestInput.ADD_INX_IPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.052: Decimal two positive 50 digit operands | x + y")
  void bigDecimal_TwoPositiveOperands() {
    Add add = new Add();
    BigNumber actual = new BigNumber(add.executeBinary(TestInput.DPX, TestInput.DPY).toString());
    BigNumber expected = TestInput.ADD_DPX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.053: Decimal two negative 50 digit operands | -x + -y")
  void bigDecimal_TwoNegativeOperands() {
    Add add = new Add();
    BigNumber actual = new BigNumber(add.executeBinary(TestInput.DNX, TestInput.DNY).toString());
    BigNumber expected = TestInput.ADD_DNX_DNY;
    assertThat(actual).isEqualByComparingTo(expected);
  }

  @Test
  @DisplayName("Case# 1.054: Decimal one negative & one positive 50 digit operand | -x + y")
  void bigDecimal_OneNegativeAndOnePositiveOperand() {
    Add add = new Add();
    BigNumber actual = new BigNumber(add.executeBinary(TestInput.DNX, TestInput.DPY).toString());
    BigNumber expected = TestInput.ADD_DNX_DPY;
    assertThat(actual).isEqualByComparingTo(expected);
  }
}
