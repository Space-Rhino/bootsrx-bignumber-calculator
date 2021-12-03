package model.state;

import static org.assertj.core.api.Assertions.assertThat;

import model.app.Calculator;
import model.operation.binary.Add;
import model.operation.function.AllClear;
import model.operation.function.Clear;
import model.operation.function.Equals;
import model.operation.function.Pi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NextOperandStateTest {

  @Test
  @DisplayName("Case# 2.100: Verify calculator object associated with this instance of states")
  void testConstructor() {
    Calculator calculator = new Calculator();
    State state = calculator.ready;

    Calculator calculator1 = (new NextOperandState(calculator)).calculator;
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
  @DisplayName("Case# 2.101: Check the enterDigit state transition from the nextOperandState")
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    assertThat((new NextOperandState(calculator)).enterDigit("8"))
        .isSameAs(calculator.buildingOperand);
  }

  @Test
  @DisplayName("Case# 2.102: Check the enterOperation state transition from the nextOperandState")
  void testEnterOperation() {
    NextOperandState nextOperandState = new NextOperandState(new Calculator());
    assertThat(nextOperandState.enterOperation(new Add())).isSameAs(nextOperandState);

    nextOperandState = new NextOperandState(new Calculator());
    assertThat(nextOperandState.enterOperation(new Clear())).isSameAs(nextOperandState);

    Calculator calculator = new Calculator();
    nextOperandState = new NextOperandState(calculator);
    assertThat(nextOperandState.enterOperation(new AllClear())).isSameAs(calculator.ready);

    calculator = new Calculator();
    nextOperandState = new NextOperandState(calculator);
    assertThat(nextOperandState.enterOperation(new Equals())).isSameAs(calculator.ready);
  }

  @Test
  @DisplayName("Case# 2.103: Check the enterConstant state transition from the nextOperandState")
  void testEnterConstant() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertThat(nextOperandState.enterConstant(new AllClear())).isSameAs(calculator.nextOperation);

    calculator = new Calculator();
    nextOperandState = new NextOperandState(calculator);
    assertThat(nextOperandState.enterConstant(new Clear())).isSameAs(calculator.nextOperation);

    calculator = new Calculator();
    nextOperandState = new NextOperandState(calculator);
    assertThat(nextOperandState.enterConstant(new Pi())).isSameAs(calculator.nextOperation);

    calculator = new Calculator();
    nextOperandState = new NextOperandState(calculator);
    assertThat(nextOperandState.enterConstant(new Equals())).isSameAs(calculator.nextOperation);
  }
}
