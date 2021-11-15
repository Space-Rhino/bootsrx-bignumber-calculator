package model.operation;

import model.binaryoperations.Add;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(OrderAnnotation.class)
class AllClearTest {

  Calculator calculator = new Calculator();
  AllClear allClear = new AllClear();
  Add add = new Add();

  @Test
  @Order(1)
  @DisplayName("Precedence = 4")
  void getPrecedence() {
    int actual = allClear.getPrecedence();
    int expected = 4;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("isBinary = false")
  void isBinary() {
    boolean actual = allClear.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("calculator display = 0 | operandStack & operationStack isEmpty = true")
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
    // operandStack isEmpty == true
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertTrue(operandStack_IsEmpty);
    // operationStack isEmpty == true
    operationStack_IsEmpty = calculator.getOperationStack().isEmpty();
    assertTrue(operationStack_IsEmpty);

    // Push values from calculator display to operandStack & operationStack
    calculator.updateDisplay("111");
    calculator.pushDisplayToOperandStack();
    calculator.updateDisplay("+");
    calculator.pushOperation(add);
    calculator.updateDisplay("222");
    calculator.pushDisplayToOperandStack();

    /* Check calculator values */
    // display != 0
    actualDisplay = calculator.getDisplay().getValue();
    assertNotEquals(expected, actualDisplay);
    // operandStack isEmpty = false
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertFalse(operandStack_IsEmpty);
    // operationStack isEmpty = false
    operationStack_IsEmpty = calculator.getOperationStack().isEmpty();
    assertFalse(operationStack_IsEmpty);

    // Confirm operandStack size == 2
    assertEquals(2, calculator.getOperandStack().size());
    // Confirm operationStack size == 1
    assertEquals(1, calculator.getOperationStack().size());

    // Execute allClear function to set values back to instantiated values
    allClear.execute(calculator);

    /* Confirm values set from allClear function */
    // display == 0
    actualDisplay = calculator.getDisplay().getValue();
    assertEquals(expected, actualDisplay);
    // operandStack isEmpty == true
    operandStack_IsEmpty = calculator.getOperandStack().isEmpty();
    assertTrue(operandStack_IsEmpty);
    // operationStack isEmpty == true
    operationStack_IsEmpty = calculator.getOperationStack().isEmpty();
    assertTrue(operationStack_IsEmpty);
  }
}
