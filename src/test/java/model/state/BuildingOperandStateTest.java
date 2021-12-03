package model.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

class BuildingOperandStateTest {

  @Test
  @DisplayName("Case# 2.120: Verify calculator object associated with this instance of states")
  void testConstructor() {
    Calculator calculator = new Calculator();
    State state = calculator.ready;

    Calculator calculator1 = (new BuildingOperandState(calculator)).calculator;
    State state1 = calculator1.ready;
    assertSame(state, state1);
    assertTrue(state1 instanceof ReadyState);
    assertTrue(calculator1.getOperandStack().isEmpty());
    assertSame(state, state1);

    State state2 = calculator.nextOperation;
    State state3 = calculator1.nextOperation;
    assertSame(state2, state3);
    assertTrue(state3 instanceof NextOperationState);

    State state4 = calculator.buildingOperand;
    State state5 = calculator1.buildingOperand;
    assertSame(state4, state5);
    assertTrue(state5 instanceof BuildingOperandState);
    assertSame(state2, state3);
    assertSame(state4, state5);

    State state6 = calculator.nextOperand;
    State state7 = calculator1.nextOperand;
    assertSame(state6, state7);
    assertTrue(state7 instanceof NextOperandState);
    assertTrue(calculator1.getOperationStack().isEmpty());
    assertSame(state6, state7);
    assertSame(calculator1, (state1).calculator);
    assertSame(calculator1, (state7).calculator);
    assertEquals("0", calculator1.getDisplay().getValue());
    assertSame(calculator1, (state5).calculator);
    assertSame(calculator1, (state3).calculator);
    assertSame(calculator1, calculator);
  }

  @Test
  @DisplayName("Case# 2.121: Check the enterDigit state transition from the buildingOperandState")
  void testEnterDigit() {
    BuildingOperandState buildingOperandState = new BuildingOperandState(new Calculator());
    assertSame(buildingOperandState, buildingOperandState.enterDigit("8"));

    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(buildingOperandState, buildingOperandState.enterDigit("8"));
  }

  @Test
  @DisplayName("Case# 2.122: Check enterOperation state transition from the buildingOperandState")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    BuildingOperandState buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.nextOperand, buildingOperandState.enterOperation(new Add()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.nextOperand, buildingOperandState.enterOperation(new Clear()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new AllClear()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Equals()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Pi()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Inverse()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Inverse()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Inverse()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new AllClear()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Negate()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new Square()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.ready, buildingOperandState.enterOperation(new SquareRoot()));
  }

  @Test
  @DisplayName("Case# 2.123: Check enterConstant state transition from the buildingOperandState")
  void testEnterConstant() {
    Calculator calculator = new Calculator();
    BuildingOperandState buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.nextOperation, buildingOperandState.enterConstant(new AllClear()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.nextOperation, buildingOperandState.enterConstant(new Clear()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.nextOperation, buildingOperandState.enterConstant(new Equals()));

    calculator = new Calculator();
    buildingOperandState = new BuildingOperandState(calculator);
    assertSame(calculator.nextOperation, buildingOperandState.enterConstant(new Pi()));
  }
}
