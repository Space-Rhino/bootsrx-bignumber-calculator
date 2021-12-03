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

class NextOperationStateTest {

  @Test
  @DisplayName("Case# 2.110: verify calculator object associated with this instance of states")
  void testConstructor() {
    Calculator calculator = new Calculator();
    State state = calculator.ready;

    Calculator calculator1 = (new NextOperationState(calculator)).calculator;
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
  @DisplayName("Case# 2.111: check the enterDigit state transition from the nextOperationSate")
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    assertThat((new NextOperationState(calculator)).enterDigit("4"))
        .isSameAs(calculator.buildingOperand);
  }

  @Test
  @DisplayName("Case# 2.112: check the enterOperation state transition from the nextOperandState")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    NextOperationState nextOperationState = new NextOperationState(calculator);
    assertThat(nextOperationState.enterOperation(new Add())).isSameAs(calculator.nextOperand);

    calculator = new Calculator();
    nextOperationState = new NextOperationState(calculator);
    assertThat(nextOperationState.enterOperation(new Clear())).isSameAs(calculator.nextOperand);

    calculator = new Calculator();
    nextOperationState = new NextOperationState(calculator);
    assertThat(nextOperationState.enterOperation(new AllClear())).isSameAs(calculator.ready);

    calculator = new Calculator();
    nextOperationState = new NextOperationState(calculator);
    assertThat(nextOperationState.enterOperation(new Equals())).isSameAs(calculator.ready);
  }

  @Test
  @DisplayName("Case# 2.113: check the enterConstant state transition from the nextOperandState")
  void testEnterConstant() {
    NextOperationState nextOperationState = new NextOperationState(new Calculator());
    assertThat(nextOperationState.enterConstant(new AllClear())).isSameAs(nextOperationState);

    nextOperationState = new NextOperationState(new Calculator());
    assertThat(nextOperationState.enterConstant(new Clear())).isSameAs(nextOperationState);

    nextOperationState = new NextOperationState(new Calculator());
    assertThat(nextOperationState.enterConstant(new Equals())).isSameAs(nextOperationState);

    nextOperationState = new NextOperationState(new Calculator());
    assertThat(nextOperationState.enterConstant(new Pi())).isSameAs(nextOperationState);
  }
}
