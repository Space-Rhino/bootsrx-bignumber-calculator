package model.operation.function;

import model.app.Calculator;
import model.operation.binary.Add;
import model.operation.function.Equals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EqualsTest {

  Calculator calculator = new Calculator();
  Equals equals = new Equals();
  Add add = new Add();

  @Test
  @Order(1)
  @DisplayName("Case# 2.007: Precedence = 4")
  void getPrecedence() {
    int actual = equals.getPrecedence();
    int expected = 4;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 2.008: isBinary = false")
  void isBinary() {
    boolean actual = equals.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 2.009: calculator display equals = expected result")
  void execute() {
    boolean operandStack_IsEmpty;
    boolean operationStack_IsEmpty;
    String actualDisplay;
    String expected;

    /* Confirm instantiated calculator values */
    // display = 0
    actualDisplay = calculator.getDisplay().getValue();
    expected = "0";
    assertEquals(expected, actualDisplay);
    // operandStack isEmpty = true
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertTrue(operandStack_IsEmpty);
    // operationStack isEmpty = true
    operationStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertTrue(operationStack_IsEmpty);

    // Push values from calculator display to operandStack & operationStack
    calculator.updateDisplay("111");
    calculator.pushDisplayToOperandStack();
    calculator.updateDisplay("+");
    calculator.pushOperation(add);
    calculator.updateDisplay("222");
    calculator.pushDisplayToOperandStack();

    /* Check calculator values */
    // display == 222
    actualDisplay = calculator.getDisplay().getValue();
    expected = "222";
    assertEquals(expected, actualDisplay);
    // operandStack isEmpty = false
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertFalse(operandStack_IsEmpty);
    // operationStack isEmpty = false
    operationStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertFalse(operationStack_IsEmpty);

    // Confirm operandStack size == 2
    assertEquals(2, calculator.getOperandStack().size());
    // Confirm operationStack size == 1
    assertEquals(1, calculator.getOperationStack().size());

    // Execute equals function to get expected result to calculator display
    equals.execute(calculator);

    // Confirm expected result set from equals function is displayed
    actualDisplay = calculator.getDisplay().getValue();
    expected = "333";
    assertEquals(expected, actualDisplay);

    // operandStack isEmpty = false
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertFalse(operandStack_IsEmpty);
    // operationStack isEmpty = false
    operationStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertFalse(operationStack_IsEmpty);
  }
}
