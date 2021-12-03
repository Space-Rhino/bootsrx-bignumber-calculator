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

class BuildingOperandStateTest {

  @Test
  @DisplayName("Case# 2.120: Verify calculator object associated with this instance of states")
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
  @DisplayName("Case# 2.121: Check the enterDigit state transition from the buildingOperandState")
  void testEnterDigit() {
    BuildingOperandState buildingOperandState = new BuildingOperandState(new Calculator());
    assertThat(buildingOperandState.enterDigit("8")).isSameAs(buildingOperandState);
  }

  @Test
  @DisplayName("Case# 2.122: Check enterOperation state transition from the buildingOperandState")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    BuildingOperandState buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterOperation(new Add())).isSameAs(calculator.nextOperand);

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterOperation(new Clear())).isSameAs(calculator.nextOperand);

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterOperation(new AllClear())).isSameAs(calculator.ready);

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterOperation(new Equals())).isSameAs(calculator.ready);
  }

  @Test
  @DisplayName("Case# 2.123: Check enterConstant state transition from the buildingOperandState")
  void testEnterConstant() {
    Calculator calculator = new Calculator();
    BuildingOperandState buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterConstant(new AllClear()))
        .isSameAs(calculator.nextOperation);

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterConstant(new Clear())).isSameAs(calculator.nextOperation);

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterConstant(new Equals())).isSameAs(calculator.nextOperation);

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertThat(buildingOperandState.enterConstant(new Pi())).isSameAs(calculator.nextOperation);
  }
}
