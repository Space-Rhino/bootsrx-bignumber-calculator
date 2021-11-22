package model.operation.function;

import model.app.Calculator;
import model.operation.binary.Add;
import model.operation.function.Clear;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClearTest {

  Calculator calculator = new Calculator();
  Clear clear = new Clear();
  Add add = new Add();

  @Test
  @Order(1)
  @DisplayName("Case# 2.004: Precedence = 4")
  void getPrecedence() {
    int actual = clear.getPrecedence();
    int expected = 4;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 2.005: isBinary = false")
  void isBinary() {
    boolean actual = clear.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName(
      "Case# 2.006: calculator display = 0 | operandStack & operationStack isEmpty = false")
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
    operationStack_IsEmpty = calculator.getOperationStack().isEmpty();
    assertTrue(operationStack_IsEmpty);

    // Push values from calculator display to operandStack & operationStack
    calculator.updateDisplay("111");
    calculator.pushDisplayToOperandStack();
    calculator.updateDisplay("+");
    calculator.pushOperation(add);
    calculator.updateDisplay("222");

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

    // Confirm operandStack size == 1
    assertEquals(1, calculator.getOperandStack().size());
    // Confirm operationStack size == 1
    assertEquals(1, calculator.getOperationStack().size());

    // Execute clear function to remove last un-pushed operand from display only
    clear.execute(calculator);

    // Confirm display value == 0
    actualDisplay = calculator.getDisplay().getValue();
    expected = "0";
    assertEquals(expected, actualDisplay);

    /* Confirm values set from clear function are correct */
    // operandStack isEmpty == false
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertFalse(operandStack_IsEmpty);
    // operationStack isEmpty = false
    operationStack_IsEmpty = calculator.getOperationStack().isEmpty();
    assertFalse(operationStack_IsEmpty);
    // Confirm operandStack size == 1
    assertEquals(1, calculator.getOperandStack().size());
    // Confirm operationStack size == 1
    assertEquals(1, calculator.getOperationStack().size());
  }
}
