package model.operation.function;

import static org.assertj.core.api.Assertions.assertThat;

import driver.TestInput;
import model.app.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class PiTest {

  @Test
  @DisplayName("Case# 2.010: Precedence equals 4")
  void getPrecedence() {
    Pi pi = new Pi();
    int actual = pi.getPrecedence();
    int expected = 4;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Case# 2.011: Operation is NOT Binary")
  void isBinary() {
    Pi pi = new Pi();
    boolean actual = pi.isBinary();
    assertThat(actual).isFalse();
  }

  @Test
  @DisplayName("Case# 2.012: Calculator display = 3.14e-97")
  void execute() {
    Pi pi = new Pi();
    Calculator calculator = new Calculator();
    pi.execute(calculator);
    String actual = calculator.getDisplay().getValue();
    String expected = TestInput.PI.toString();
    assertThat(actual).isEqualTo(expected);
  }
}
