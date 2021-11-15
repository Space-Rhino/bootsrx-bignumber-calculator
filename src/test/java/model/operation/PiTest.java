package model.operation;

import driver.InputTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(OrderAnnotation.class)
class PiTest {

  Calculator calculator = new Calculator();
  Pi pi = new Pi();

  @Test
  @Order(1)
  @DisplayName("Case# 2.010: Precedence = 4")
  void getPrecedence() {
    int actual = pi.getPrecedence();
    int expected = 4;
    assertEquals(expected, actual);
  }

  @Test
  @Order(2)
  @DisplayName("Case# 2.011: isBinary = false")
  void isBinary() {
    boolean actual = pi.isBinary();
    assertFalse(actual);
  }

  @Test
  @Order(3)
  @DisplayName("Case# 2.012: calculator display = 3.14e-97")
  void execute() {
    pi.execute(calculator);
    String actual = calculator.getDisplay().getValue();
    String expected = InputTest.PI.toString();
    assertEquals(expected, actual);
  }
}
