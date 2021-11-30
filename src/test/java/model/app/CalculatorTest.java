package model.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import java.util.Objects;
import model.operation.binary.Add;
import model.operation.binary.Divide;
import model.operation.binary.Multiply;
import model.operation.function.AllClear;
import model.operation.function.Pi;
import number.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  private static final String PI_100_DIGITS =
      "3.141592653589793238462643383279502884197169399375105"
          + "82097494459230781640628620899862803482534211707";
  private static String expected;
  private static String actual;

  @Test
  @DisplayName("case# 2.300: check constructor initialized correctly")
  void testConstructor() {
    Calculator calculator = new Calculator();

    assertTrue(calculator.getOperandStack().isEmpty());
    assertNotNull(calculator.getOperandStack());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertNotNull(calculator.getOperationStack());
    assertEquals("0", calculator.getDisplay().getValue());
    // operationMap is private with no accessor methods
    assertTrue(calculator.ready instanceof model.state.ReadyState);
    assertTrue(calculator.nextOperand instanceof model.state.NextOperandState);
    assertTrue(calculator.nextOperation instanceof model.state.NextOperationState);
    assertTrue(calculator.buildingOperand instanceof model.state.BuildingOperandState);
    assertEquals(calculator.getState(), calculator.ready);
  }

  @Test
  @DisplayName("case# 2.301: operation contains key and is instance of PI")
  void testEnterOperation() {
    Calculator calculator = new Calculator();

    calculator.enterOperation("PI");
    assertEquals(1, calculator.getOperandStack().size());
    assertTrue(calculator.getOperationStack().isEmpty());

    expected = PI_100_DIGITS;
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);

    expected = PI_100_DIGITS;
    actual = calculator.getOperandStack().getFirst().toString();
    assertEquals(expected, actual);

    calculator.enterOperation("+");
    expected = "model.operation.binary.Add";
    actual = Objects.requireNonNull(calculator.getOperationStack().peek()).toString();
    assertThat(actual).containsSequence(expected);
    assertTrue(Objects.requireNonNull(calculator.getOperationStack().peek()).isBinary());

    int expectedPrecedence = 1;
    int actualPrecedence =
        Objects.requireNonNull(calculator.getOperationStack().peek()).getPrecedence();
    assertEquals(expectedPrecedence, actualPrecedence);
  }

  @Test
  @DisplayName("case# 2.302: valid digit entered and backspace removes last digit")
  void testEnterDigit() {
    Calculator calculator = new Calculator();

    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());

    calculator.enterDigit("0");
    expected = "0";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);

    calculator = new Calculator();
    calculator.enterDigit("4");
    calculator.enterDigit("2");
    expected = "42";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);

    calculator.enterDigit("BKSP");
    expected = "4";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("case# 2.303: display value is sent to operandStack and trows NumberFormatException")
  void testPushDisplayToOperandStack() throws NumberFormatException {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("888");
    calculator.pushDisplayToOperandStack();
    Deque<Number> operandStack = calculator.getOperandStack();

    expected = "888";
    actual = operandStack.getFirst().toString();
    assertEquals(1, operandStack.size());
    assertEquals(expected, actual);

    calculator = new Calculator();
    calculator.updateDisplay("8b");
    Throwable thrown = catchThrowable(calculator::pushDisplayToOperandStack);
    assertThat(thrown).isInstanceOf(NumberFormatException.class);
  }

  @Test
  @DisplayName("case# 2.304: execute higher precedence of operation in correct order")
  void testPushOperation() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("1");
    calculator.pushDisplayToOperandStack();
    assertEquals(1, calculator.getOperandStack().size());

    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertEquals(2, calculator.getOperandStack().size());

    calculator.pushOperation(new Multiply());
    assertEquals(2, calculator.getOperationStack().size());

    calculator.updateDisplay("3");
    calculator.pushDisplayToOperandStack();
    assertEquals(3, calculator.getOperandStack().size());
  }

  @Test
  @DisplayName("case# 2.305: execute this operation of allClear and Pi")
  void testExecuteOperation() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("1");
    calculator.pushDisplayToOperandStack();
    assertEquals(1, calculator.getOperandStack().size());

    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertEquals(2, calculator.getOperandStack().size());

    calculator.executeOperation(new AllClear());
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());

    calculator.executeOperation(new Pi());
    expected = PI_100_DIGITS;
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("case# 2.306: replace current operation on operationStack with different operation")
  void testReplaceOperation() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("4");
    calculator.pushDisplayToOperandStack();
    assertEquals(1, calculator.getOperandStack().size());

    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());
    expected = "model.operation.binary.Add";
    actual = Objects.requireNonNull(calculator.getOperationStack().peek()).toString();
    assertThat(actual).containsSequence(expected);

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertEquals(2, calculator.getOperandStack().size());

    calculator.replaceOperation(new Divide());
    assertEquals(1, calculator.getOperationStack().size());
    expected = "model.operation.binary.Divide";
    actual = Objects.requireNonNull(calculator.getOperationStack().peek()).toString();
    assertThat(actual).containsSequence(expected);

    calculator.equals();
    calculator.updateDisplay();
    expected = "2";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("case# 2.307: calculator recovers from isErrorState and does executeOperation")
  void testEquals() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("4");
    calculator.pushDisplayToOperandStack();
    assertEquals(1, calculator.getOperandStack().size());

    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());
    expected = "model.operation.binary.Add";
    actual = Objects.requireNonNull(calculator.getOperationStack().peek()).toString();
    assertThat(actual).containsSequence(expected);

    calculator.updateDisplay("Invalid operation");
    calculator.equals();
    assertEquals(calculator.getState(), calculator.ready);

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertEquals(2, calculator.getOperandStack().size());

    calculator.equals();
    calculator.updateDisplay();
    expected = "6";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("case# 2.308: sets display to zero with PropertyChangeEvent")
  void testClear() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("6");
    calculator.pushDisplayToOperandStack();
    assertEquals(1, calculator.getOperandStack().size());

    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());
    expected = "model.operation.binary.Add";
    actual = Objects.requireNonNull(calculator.getOperationStack().peek()).toString();
    assertThat(actual).containsSequence(expected);

    calculator.updateDisplay("2");
    calculator.clear();
    calculator.updateDisplay("4");
    calculator.pushDisplayToOperandStack();
    assertEquals(2, calculator.getOperandStack().size());

    calculator.equals();
    calculator.updateDisplay();
    expected = "10";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("case# 2.309: clears display, clears operandStack, and clears operationStack")
  void testAllClear() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("1");
    calculator.pushDisplayToOperandStack();
    assertEquals(1, calculator.getOperandStack().size());

    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());

    calculator.updateDisplay("2");
    calculator.pushDisplayToOperandStack();
    assertEquals(2, calculator.getOperandStack().size());

    calculator.pushOperation(new Multiply());
    assertEquals(2, calculator.getOperationStack().size());

    calculator.updateDisplay("3");
    calculator.pushDisplayToOperandStack();
    assertEquals(3, calculator.getOperandStack().size());

    calculator.allClear();
    calculator.updateDisplay();
    expected = "0";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
  }

  @Test
  @DisplayName("case# 2.310: calls allClear then clears display, operandStack, and operationStack")
  void testResetCalculator() {
    testAllClear();
  }

  @Test
  @DisplayName("case# 2.311: sends digit to display and removes last digit with BKSP operation")
  void testSendDigitToDisplay() {
    Calculator calculator = new Calculator();

    calculator.sendDigitToDisplay("8");
    assertEquals("8", calculator.getDisplay().getValue());

    calculator = new Calculator();
    calculator.updateDisplay("42");
    calculator.sendDigitToDisplay("BKSP");
    assertEquals("4", calculator.getDisplay().getValue());

    calculator = new Calculator();
    calculator.sendDigitToDisplay(".");
    assertEquals("0.", calculator.getDisplay().getValue());

    calculator = new Calculator();
    calculator.updateDisplay("42");
    calculator.sendDigitToDisplay("9");
    assertEquals("429", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.312: updates display from operandStack")
  void testUpdateDisplay_CallsOverloadedUpdateDisplay() {
    Calculator calculator = new Calculator();

    calculator.sendDigitToDisplay("888");
    calculator.pushDisplayToOperandStack();

    calculator.sendDigitToDisplay("444");
    expected = "888";
    actual = Objects.requireNonNull(calculator.getOperandStack().peek()).toString();
    assertEquals(expected, actual);

    calculator.updateDisplay();
    expected = "888";
    actual = calculator.getDisplay().getValue();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("case# 2.313: updates display with set value of string value")
  void testUpdateDisplay_CallsSetValue() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("42");
    assertEquals("42", calculator.getDisplay().getValue());

    calculator.updateDisplay("84");
    assertEquals("84", calculator.getDisplay().getValue());

    calculator.updateDisplay("0");
    assertEquals("0", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName(
      "case# 2.314: sets the display register to 0 without triggering a PropertyChangeEvent")
  void testResetDisplay() {
    Calculator calculator = new Calculator();

    calculator.updateDisplay("42");
    assertEquals("42", calculator.getDisplay().getValue());

    calculator.resetDisplay();
    assertEquals("0", calculator.getDisplay().getValue());
  }
}
