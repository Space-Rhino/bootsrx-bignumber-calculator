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
import org.junit.jupiter.api.Test;

class ReadyStateTest {
  
  @Test
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
  void testEnterDigit() {
    Calculator calculator = new Calculator();
    assertSame(calculator.buildingOperand, (new ReadyState(calculator)).enterDigit("4"));
  }
  
  @Test
  void testEnterDigit2() {
    Calculator calculator = new Calculator();
    assertSame(calculator.buildingOperand, (new ReadyState(calculator)).enterDigit("3"));
  }
  
  @Test
  void testEnterDigit3() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    assertSame(calculator.buildingOperand, (new ReadyState(calculator)).enterDigit("8"));
  }
  
  @Test
  void testEnterDigit4() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("6");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterDigit("2"));
  }
  
  @Test
  void testEnterDigit5() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("9");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterDigit("5"));
  }
  
  @Test
  void testEnterDigit6() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("2");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterDigit("1"));
  }
  
  @Test
  void testEnterDigit7() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("0");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterDigit("8"));
  }
  
  @Test
  void testEnterOperation() {
    Calculator calculator = new Calculator();
    ReadyState readyState = new ReadyState(calculator);
    assertSame(calculator.nextOperand, readyState.enterOperation(new Add()));
  }
  
  @Test
  void testEnterOperation2() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new AllClear()));
  }
  
  @Test
  void testEnterOperation3() {
    Calculator calculator = new Calculator();
    ReadyState readyState = new ReadyState(calculator);
    assertSame(calculator.nextOperand, readyState.enterOperation(new Clear()));
  }
  
  @Test
  void testEnterOperation4() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new Equals()));
  }
  
  @Test
  void testEnterOperation5() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new Pi()));
  }
  
  @Test
  void testEnterOperation6() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new Inverse()));
  }
  
  @Test
  void testEnterOperation7() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("42");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Inverse()));
  }
  
  @Test
  void testEnterOperation8() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("7");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Inverse()));
  }
  
  @Test
  void testEnterOperation9() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("3");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new AllClear()));
  }
  
  @Test
  void testEnterOperation10() {
    Calculator calculator = new Calculator();
    calculator.updateDisplay("4");
    ReadyState readyState = new ReadyState(calculator);
    assertSame(readyState, readyState.enterOperation(new Clear()));
  }
  
  @Test
  void testEnterOperation11() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new Negate()));
  }
  
  @Test
  void testEnterOperation12() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new Square()));
  }
  
  @Test
  void testEnterOperation13() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterOperation(new SquareRoot()));
  }
  
  @Test
  void testEnterConstant() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new AllClear()));
  }
  
  @Test
  void testEnterConstant2() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new Clear()));
  }
  
  @Test
  void testEnterConstant3() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new Equals()));
  }
  
  @Test
  void testEnterConstant4() {
    ReadyState readyState = new ReadyState(new Calculator());
    assertSame(readyState, readyState.enterConstant(new Pi()));
  }
  
}

