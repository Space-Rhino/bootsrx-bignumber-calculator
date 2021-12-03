package model.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Objects;
import model.operation.binary.Add;
import model.operation.binary.Divide;
import model.operation.binary.Multiply;
import model.operation.function.AllClear;
import model.state.BuildingOperandState;
import model.state.NextOperandState;
import model.state.NextOperationState;
import model.state.ReadyState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  private static String expected;
  private static String actual;

  @Test
  @DisplayName("case# 2.300: Check constructor initialized correctly")
  void testConstructor() {
    Calculator calculator = new Calculator();
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();
    assertThat(calculator.getOperandStack()).isNotNull();
    assertThat(calculator.getOperationStack().isEmpty()).isTrue();
    assertThat(calculator.getOperationStack()).isNotNull();
    assertThat(calculator.getDisplay().getValue()).isEqualTo("0");
    assertThat(calculator.ready).isInstanceOf(ReadyState.class);
    assertThat(calculator.nextOperand).isInstanceOf(NextOperandState.class);
    assertThat(calculator.nextOperation).isInstanceOf(NextOperationState.class);
    assertThat(calculator.buildingOperand).isInstanceOf(BuildingOperandState.class);
    assertThat(calculator.ready).isEqualTo(calculator.getState());
  }

  @Test
  @DisplayName("case# 2.301: Operation contains key and is instance of PI")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    String PI_100_DIGITS =
        "3.141592653589793238462643383279502884197169399375105"
            + "82097494459230781640628620899862803482534211707";
    calculator.enterOperation("PI");
    expected = PI_100_DIGITS;
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.302: Valid digit entered")
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    calculator.enterDigit("4");
    calculator.enterDigit("2");

    expected = "42";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);

    calculator.enterDigit("BKSP");
    expected = "4";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.302: Backspace removes last digit")
  void testEnterDigit_BackspaceRemovesLastDigit() {
    Calculator calculator = new Calculator();
    calculator.enterDigit("4");
    calculator.enterDigit("2");
    actual = calculator.getDisplay().getValue();

    calculator.enterDigit("BKSP");
    expected = "4";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.303: Display value is sent to operandStack")
  void testPushDisplayToOperandStack() throws NumberFormatException {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("888");
    calculator.pushDisplayToOperandStack();

    expected = "888";
    actual = calculator.getOperandStack().getFirst().toString();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.303: Display value is sent to operandStack and trows NumberFormatException")
  void testPushDisplayToOperandStack_ThrowsException() throws NumberFormatException {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("8b");

    assertThatExceptionOfType(NumberFormatException.class)
        .isThrownBy(calculator::pushDisplayToOperandStack);
  }

  @Test
  @DisplayName("case# 2.304: Execute higher precedence of operation in correct order")
  void testPushOperation() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("1");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);

    calculator.pushOperation(new Add());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);

    calculator.pushOperation(new Multiply());
    assertThat(calculator.getOperationStack().size()).isEqualTo(2);

    calculator.updateDisplay("3");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(3);
  }

  @Test
  @DisplayName("case# 2.305: Execute operation of AllClear ")
  void testExecuteOperation() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("1");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);

    calculator.pushOperation(new Add());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);

    calculator.executeOperation(new AllClear());
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();
    assertThat(calculator.getOperationStack().isEmpty()).isTrue();
    assertThat(calculator.getDisplay().getValue()).isEqualTo("0");
  }

  @Test
  @DisplayName("case# 2.306: Replace current operation on operationStack with different operation")
  void testReplaceOperation() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("4");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);

    calculator.pushOperation(new Add());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);

    calculator.replaceOperation(new Divide());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.equals();
    calculator.updateDisplay();
    expected = "2";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.307: Calculator recovers from isErrorState and does executeOperation")
  void testEquals() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("4");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);

    calculator.pushOperation(new Add());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.updateDisplay("Invalid operation");
    calculator.equals();
    assertThat(calculator.ready).isEqualTo(calculator.getState());

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);

    calculator.equals();
    calculator.updateDisplay();
    expected = "6";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.308: Sets display to zero with PropertyChangeEvent")
  void testClear() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("6");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);

    calculator.pushOperation(new Add());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.updateDisplay("2");
    calculator.clear();
    calculator.updateDisplay("4");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);

    calculator.equals();
    calculator.updateDisplay();
    expected = "10";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.309: AllClear clears display, operandStack, and operationStack")
  void testAllClear() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("1");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(1);

    calculator.pushOperation(new Add());
    assertThat(calculator.getOperationStack().size()).isEqualTo(1);

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(2);

    calculator.pushOperation(new Multiply());
    assertThat(calculator.getOperationStack().size()).isEqualTo(2);

    calculator.updateDisplay("3");
    calculator.pushDisplayToOperandStack();
    assertThat(calculator.getOperandStack().size()).isEqualTo(3);

    calculator.allClear();
    calculator.updateDisplay();
    expected = "0";
    actual = calculator.getDisplay().getValue();
    assertThat(actual).isEqualTo(expected);
    assertThat(calculator.getOperandStack().isEmpty()).isTrue();
    assertThat(calculator.getOperationStack().isEmpty()).isTrue();
  }

  @Test
  @DisplayName("case# 2.310: Calls AllClear then clears display, operandStack, and operationStack")
  void testResetCalculator() {
    testAllClear();
  }

  @Test
  @DisplayName("case# 2.311: Sends digit to display")
  void testSendDigitToDisplay() {
    Calculator calculator = new Calculator();
    calculator.sendDigitToDisplay("8");
    assertThat(calculator.getDisplay().getValue()).isEqualTo("8");
  }

  @Test
  @DisplayName("case# 2.312: Updates display from operandStack")
  void testUpdateDisplay_CallsOverloadedUpdateDisplay() {
    Calculator calculator = new Calculator();
    calculator.sendDigitToDisplay("888");
    calculator.pushDisplayToOperandStack();
    calculator.sendDigitToDisplay("444");

    expected = "888";
    actual = Objects.requireNonNull(calculator.getOperandStack().peek()).toString();
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("case# 2.313: Updates display with set value of string value")
  void testUpdateDisplay_CallsSetValue() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    assertThat(calculator.getDisplay().getValue()).isEqualTo("42");
  }

  @Test
  @DisplayName(
      "case# 2.314: Sets the display register to 0 without triggering a PropertyChangeEvent")
  void testResetDisplay() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    assertThat(calculator.getDisplay().getValue()).isEqualTo("42");

    calculator.resetDisplay();
    assertThat(calculator.getDisplay().getValue()).isEqualTo("0");
  }
}
