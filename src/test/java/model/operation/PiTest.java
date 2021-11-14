package model.operation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import testutilities.TestNumbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
class PiTest {
  Calculator calculator = new Calculator();
  TestNumbers testNumber = new TestNumbers();
  Pi pi = new Pi();

  @Test
  @Order(1)
  @DisplayName("Precedence = 4")
  void getPrecedence() {
    int actual = pi.getPrecedence();
    int expected = 4;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("isBinary = false")
  void isBinary() {
    boolean actual = pi.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("calculator display = 3.14e-97")
  void execute() {
    pi.execute(calculator);
    String actual = calculator.getDisplay().getValue();
    String expected = testNumber.getPI().toString();
    assertEquals(expected, actual);
  }
}
