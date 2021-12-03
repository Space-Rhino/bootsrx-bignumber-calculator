package model.state;

import static org.assertj.core.api.Assertions.assertThat;

import model.app.Calculator;
import model.operation.binary.Add;
import model.operation.function.AllClear;
import model.operation.function.Clear;
import model.operation.function.Equals;
import model.operation.function.Pi;
import model.operation.unary.Inverse;
import model.operation.unary.Negate;
import model.operation.unary.Square;
import model.operation.unary.SquareRoot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyStateTest {

  @Test
  @DisplayName("Case# 2.130: Verify calculator object associated with this instance of states")
  void testConstructor() {
    Calculator calculator = new Calculator();
    State state = calculator.ready;

    Calculator calculator1 = (new BuildingOperandState(calculator)).calculator;
    State state1 = calculator1.ready;
    assertThat(state1).isInstanceOf(ReadyState.class).isSameAs(state);

    State state2 = calculator.nextOperation;
    State state3 = calculator1.nextOperation;
    assertThat(state3).isInstanceOf(NextOperationState.class).isSameAs(state2);

    State state4 = calculator.buildingOperand;
    State state5 = calculator1.buildingOperand;
    assertThat(state5).isInstanceOf(BuildingOperandState.class).isSameAs(state4);

    State state6 = calculator.nextOperand;
    State state7 = calculator1.nextOperand;
    assertThat(state7).isInstanceOf(NextOperandState.class).isSameAs(state6);
  }

  @Test
  @DisplayName("Case# 2.131: Check the enterDigit state transition from the readyState")
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    assertThat((new ReadyState(calculator)).enterDigit("4")).isSameAs(calculator.buildingOperand);

    calculator = new Calculator();
    assertThat((new ReadyState(calculator)).enterDigit("3")).isSameAs(calculator.buildingOperand);

    calculator = new Calculator();
    calculator.updateDisplay("42");
    assertThat((new ReadyState(calculator)).enterDigit("8")).isSameAs(calculator.buildingOperand);
  }

  @Test
  @DisplayName("Case# 2.132: Check enterOperation state transition from the readyState")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    ReadyState readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Add())).isSameAs(calculator.nextOperand);

    readyState = new ReadyState(new Calculator());
    assertThat(readyState.enterOperation(new AllClear())).isSameAs(readyState);

    calculator = new Calculator();
    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Clear())).isSameAs(calculator.nextOperand);

    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Equals())).isSameAs(readyState);

    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Pi())).isSameAs(readyState);

    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Inverse())).isSameAs(readyState);

    calculator = new Calculator();
    calculator.updateDisplay("42");
    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Inverse())).isSameAs(readyState);

    calculator = new Calculator();
    calculator.updateDisplay("7");
    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Inverse())).isSameAs(readyState);

    calculator = new Calculator();
    calculator.updateDisplay("3");
    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new AllClear())).isSameAs(readyState);

    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Negate())).isSameAs(readyState);

    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new Square())).isSameAs(readyState);

    readyState = new ReadyState(calculator);
    assertThat(readyState.enterOperation(new SquareRoot())).isSameAs(readyState);
  }

  @Test
  @DisplayName("Case# 2.133: Check enterConstant state transition from the readyState")
  void testEnterConstant() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertThat(readyState.enterConstant(new AllClear())).isSameAs(readyState);

    readyState = new ReadyState(new Calculator());
    assertThat(readyState.enterConstant(new Clear())).isSameAs(readyState);

    readyState = new ReadyState(new Calculator());
    assertThat(readyState.enterConstant(new Equals())).isSameAs(readyState);

    readyState = new ReadyState(new Calculator());
    assertThat(readyState.enterConstant(new Pi())).isSameAs(readyState);
  }
}
