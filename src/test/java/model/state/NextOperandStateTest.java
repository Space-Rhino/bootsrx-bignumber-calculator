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
import org.junit.jupiter.api.Test;

class NextOperandStateTest {

  @Test
  void testConstructor() {
    Calculator calculator = new Calculator();
    State state = calculator.ready;

    Calculator calculator1 = (new NextOperandState(calculator)).calculator;
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
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    assertSame(calculator.buildingOperand, (new NextOperandState(calculator)).enterDigit("8"));
  }

  @Test
  void testEnterOperation() {
    NextOperandState nextOperandState = new NextOperandState(new Calculator());
    assertSame(nextOperandState, nextOperandState.enterOperation(new Add()));
  }

  @Test
  void testEnterOperation2() {
    NextOperandState nextOperandState = new NextOperandState(new Calculator());
    assertSame(nextOperandState, nextOperandState.enterOperation(new Clear()));
  }

  @Test
  void testEnterOperation3() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.ready, nextOperandState.enterOperation(new AllClear()));
  }

  @Test
  void testEnterOperation4() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.ready, nextOperandState.enterOperation(new Equals()));
  }

  @Test
  void testEnterOperation5() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.ready, nextOperandState.enterOperation(new Pi()));
  }

  @Test
  void testEnterConstant() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.nextOperation, nextOperandState.enterConstant(new AllClear()));
  }

  @Test
  void testEnterConstant2() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.nextOperation, nextOperandState.enterConstant(new Clear()));
  }

  @Test
  void testEnterConstant3() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.nextOperation, nextOperandState.enterConstant(new Equals()));
  }

  @Test
  void testEnterConstant4() {
    Calculator calculator = new Calculator();
    NextOperandState nextOperandState = new NextOperandState(calculator);
    assertSame(calculator.nextOperation, nextOperandState.enterConstant(new Pi()));
  }
}
