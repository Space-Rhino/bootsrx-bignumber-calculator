package model.operation.function;

import static org.assertj.core.api.Assertions.assertThat;

import model.app.Calculator;
import model.operation.binary.Add;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClearTest {

  @Test
  @DisplayName("Case# 2.004: Precedence equals 4")
  void getPrecedence() {
    Clear clear = new Clear();
    int actual = clear.getPrecedence();
    int expected = 4;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 2.005: Operation is NOT Binary")
  void isBinary() {
    Clear clear = new Clear();
    boolean actual = clear.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 2.006: Display equals 0 | operandStack & operationStack is NOT empty.")
  void execute() {
    Calculator calculator = new Calculator();
    Clear clear = new Clear();
    Add add = new Add();

    // Confirm instantiated calculator values
    String actual = calculator.getDisplay().getValue();
    String expected = "0";
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();
    assertThat(calculator.getOperationStack().isEmpty()).isTrue();

    // Push values from calculator display to operandStack & operationStack
    calculator.updateDisplay("111");
    calculator.pushDisplayToOperandStack();
    calculator.updateDisplay("+");
    calculator.pushOperation(add);
    calculator.updateDisplay("222");

    // Check calculator values
    actual = calculator.getDisplay().getValue();
    expected = "222";
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    // Execute clear function to remove last un-pushed operand from display only
    clear.execute(calculator);

    // Confirm display value == 0
    actual = calculator.getDisplay().getValue();
    expected = "0";
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);
  }
}
