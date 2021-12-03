package model.operation.function;

import model.app.Calculator;
import model.operation.binary.Add;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(OrderAnnotation.class)
class AllClearTest {

  @Test
  @DisplayName("Case# 2.001: Precedence = 4")
  void getPrecedence() {
    AllClear allClear = new AllClear();
    int actual = allClear.getPrecedence();
    int expected = 4;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 2.002: isBinary = false")
  void isBinary() {
    AllClear allClear = new AllClear();
    boolean actual = allClear.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 2.003: calculator display = 0 | operandStack & operationStack isEmpty = true")
  void execute() {
    Calculator calculator = new Calculator();
    AllClear allClear = new AllClear();
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
    calculator.pushDisplayToOperandStack();

    // Check calculator values
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isNotEqualTo(expected);
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    // Execute allClear function to set values back to instantiated values
    allClear.execute(calculator);

    // Confirm values set from allClear function
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();
    assertThat(calculator.getOperationStack().isEmpty()).isTrue();
  }
}
