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

class ReadyStateTest {

  @Test
  @DisplayName("Case# 2.130: Verify calculator object associated with this instance of states")
  void testConstructor() {
    Calculator calculator = new Calculator();
    State state = calculator.ready;

    Calculator calculator1 = (new ReadyState(calculator)).calculator;
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
  @DisplayName("Case# 2.131: Check the enterDigit state transition from the readyState")
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    assertSame(calculator.buildingOperand, (new ReadyState(calculator)).enterDigit("4"));

    calculator = new Calculator();
    assertSame(calculator.buildingOperand, (new ReadyState(calculator)).enterDigit("3"));

    calculator = new Calculator();
    calculator.updateDisplay("42");
    assertSame(calculator.buildingOperand, (new ReadyState(calculator)).enterDigit("8"));
  }

  @Test
  @DisplayName("Case# 2.132: Check enterOperation state transition from the readyState")
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    ReadyState readyState = new ReadyState(calculator);
    assertSame(calculator.nextOperand, readyState.enterOperation(new Add()));

    readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new AllClear()));

    calculator = new Calculator();
    readyState = new ReadyState(calculator);
    assertSame(calculator.nextOperand, readyState.enterOperation(new Clear()));

    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Equals()));

    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Pi()));

    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Inverse()));

    calculator = new Calculator();
    calculator.updateDisplay("42");
    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Inverse()));

    calculator = new Calculator();
    calculator.updateDisplay("7");
    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Inverse()));

    calculator = new Calculator();
    calculator.updateDisplay("3");
    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new AllClear()));

    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Negate()));

    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Square()));

    readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new SquareRoot()));
  }

  @Test
  @DisplayName("Case# 2.133: Check enterConstant state transition from the readyState")
  void testEnterConstant() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new AllClear()));

    readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new Clear()));

    readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new Equals()));

    readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new Pi()));
  }
}
