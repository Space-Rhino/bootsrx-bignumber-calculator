package model.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;
import model.operation.binary.Add;
import model.operation.function.AllClear;
import model.operation.function.Clear;
import model.operation.function.Pi;
import number.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  @Test
  @DisplayName("case# 2.300: ")
  void testConstructor() {
    Calculator actualCalculator = new Calculator();
    assertTrue(actualCalculator.ready instanceof model.state.ReadyState);
    assertTrue(actualCalculator.getOperandStack().isEmpty());
    assertTrue(actualCalculator.nextOperation instanceof model.state.NextOperationState);
    assertTrue(actualCalculator.nextOperand instanceof model.state.NextOperandState);
    assertTrue(actualCalculator.buildingOperand instanceof model.state.BuildingOperandState);
    assertTrue(actualCalculator.getOperationStack().isEmpty());
    assertEquals("0", actualCalculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.301: ")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    calculator.enterOperation("Op String");
    assertTrue(calculator.ready instanceof model.state.ReadyState);
    assertTrue(calculator.nextOperation instanceof model.state.NextOperationState);
    assertTrue(calculator.nextOperand instanceof model.state.NextOperandState);
    assertTrue(calculator.buildingOperand instanceof model.state.BuildingOperandState);

    calculator = new Calculator();
    calculator.enterOperation("PI");
    Deque<Number> operandStack = calculator.getOperandStack();
    assertEquals(1, operandStack.size());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals(
        "3.141592653589793238462643383279502884197169399375105"
            + "82097494459230781640628620899862803482534211707",
        calculator.getDisplay().getValue());
    assertEquals(
        "3.141592653589793238462643383279502884197169399375105"
            + "82097494459230781640628620899862803482534211707",
        operandStack.getFirst().toString());
  }

  @Test
  @DisplayName("case# 2.302: ")
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    calculator.enterDigit("0");
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());

    calculator = new Calculator();
    calculator.updateDisplay("42");
    calculator.enterDigit("BKSP");
    assertEquals("4", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.303: ")
  void testPushDisplayToOperandStack() throws NumberFormatException {
    Calculator calculator = new Calculator();
    calculator.pushDisplayToOperandStack();
    Deque<Number> operandStack = calculator.getOperandStack();
    assertEquals(1, operandStack.size());
    assertEquals("0", operandStack.getFirst().toString());
  }

  @Test
  @DisplayName("case# 2.304: ")
  void testPushOperation() {
    Calculator calculator = new Calculator();
    calculator.pushOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());

    calculator = new Calculator();
    calculator.pushOperation(new AllClear());
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.305: ")
  void testExecuteOperation() {
    Calculator calculator = new Calculator();
    calculator.executeOperation(new AllClear());
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());

    calculator.executeOperation(new Pi());
    assertEquals(
        "3.141592653589793238462643383279502884197169399375105"
            + "82097494459230781640628620899862803482534211707",
        calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.306: ")
  void testReplaceOperation() {
    Calculator calculator = new Calculator();
    calculator.replaceOperation(new Add());
    assertEquals(1, calculator.getOperationStack().size());

    calculator = new Calculator();
    calculator.replaceOperation(new Clear());
    assertEquals("0", calculator.getDisplay().getValue());

    calculator = new Calculator();
    calculator.replaceOperation(new AllClear());
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.307: ")
  void testEquals() {
    Calculator calculator = new Calculator();
    calculator.equals();
    assertTrue(calculator.ready instanceof model.state.ReadyState);
    assertTrue(calculator.nextOperation instanceof model.state.NextOperationState);
    assertTrue(calculator.nextOperand instanceof model.state.NextOperandState);
    assertTrue(calculator.buildingOperand instanceof model.state.BuildingOperandState);
  }

  @Test
  @DisplayName("case# 2.308: ")
  void testClear() {
    Calculator calculator = new Calculator();
    calculator.clear();
    assertEquals("0", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.309: ")
  void testAllClear() {
    Calculator calculator = new Calculator();
    calculator.allClear();
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.310: ")
  void testResetCalculator() {
    Calculator calculator = new Calculator();
    calculator.resetCalculator();
    assertTrue(calculator.getOperandStack().isEmpty());
    assertTrue(calculator.getOperationStack().isEmpty());
    assertEquals("0", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.311: ")
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
  @DisplayName("case# 2.312: ")
  void testUpdateDisplay_CallsOverloadedUpdateDisplay() {
    // change to call operand stack
    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    assertEquals("42", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.313: ")
  void testUpdateDisplay_CallsSetValue() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    assertEquals("42", calculator.getDisplay().getValue());
  }

  @Test
  @DisplayName("case# 2.314: ")
  void testResetDisplay() {
    Calculator calculator = new Calculator();
    calculator.resetDisplay();
    assertEquals("0", calculator.getDisplay().getValue());
  }
}
