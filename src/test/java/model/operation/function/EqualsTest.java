package model.operation.function;

import static org.assertj.core.api.Assertions.assertThat;

import model.app.Calculator;
import model.operation.binary.Add;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EqualsTest {

  @Test
  @DisplayName("Case# 2.007: Precedence equals 4")
  void getPrecedence() {
    Equals equals = new Equals();
    int actual = equals.getPrecedence();
    int expected = 4;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 2.008: Operation is NOT Binary")
  void isBinary() {
    Equals equals = new Equals();
    boolean actual = equals.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 2.009: Calculator display equals expected result")
  void execute() {
    Calculator calculator = new Calculator();
    Equals equals = new Equals();
    Add add = new Add();

    // Confirm instantiated calculator values
    String actual = calculator.getDisplay().getValue();
    String expected = "0";
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();

    // Push values from calculator display to operandStack & operationStack
    calculator.updateDisplay("111");
    calculator.pushDisplayToOperandStack();
    calculator.updateDisplay("+");
    calculator.pushOperation(add);
    calculator.updateDisplay("222");
    calculator.pushDisplayToOperandStack();

    // Check calculator values
    actual = calculator.getDisplay().getValue();
    expected = "222";
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    // Execute equals function
    equals.execute(calculator);

    // Confirm expected result set from equals function is displayed
    actual = calculator.getDisplay().getValue();
    expected = "333";
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().isEmpty()).isFalse();
    assertThat(calculator.getOperandStack().isEmpty()).isFalse();
  }
}
