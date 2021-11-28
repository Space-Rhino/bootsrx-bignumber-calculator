package model.app;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.operation.binary.Add;
import model.operation.binary.Divide;
import model.operation.binary.Multiply;
import model.operation.binary.Subtract;
import model.operation.function.AllClear;
import model.operation.function.Clear;
import model.operation.function.Equals;
import model.operation.function.Operation;
import model.operation.function.Pi;
import model.operation.unary.Inverse;
import model.operation.unary.Negate;
import model.operation.unary.Square;
import model.operation.unary.SquareRoot;
import model.state.BuildingOperandState;
import model.state.NextOperandState;
import model.state.NextOperationState;
import model.state.ReadyState;
import model.state.State;
import number.BigNumber;
import number.Number;

/**
 * This class is the main model class and contains the methods necessary to complete calculator
 * operations. Calculator implements a state machine which determines which methods to call for each
 * input type based on state.
 *
 * @author Shawn Crahen
 * @version 1.0
 * @see Number
 * @see Operation
 */
public class Calculator {

  /** An instance of ReadyState. */
  public final State ready;

  /** An instance of BuildingOperandState. */
  public final State buildingOperand;

  /** An instance of NextOperandState. */
  public final State nextOperand;

  /** An instance of NextOperationState. */
  public final State nextOperation;

  /** A stack to store operands of type Number. */
  private final Deque<Number> operandStack;

  /** A stack to store arithmetic operations. */
  private final Deque<Operation> operationStack;

  /** The display register for this Calculator. */
  private final Display display;

  /** A map to provide O(1) access to operations. */
  private final Map<String, Operation> operationMap;

  /** The state of this calculator. */
  private State state;

  /** Class constructor. */
  public Calculator() {
    operandStack = new ArrayDeque<>();
    operationStack = new ArrayDeque<>();
    display = new Display("0");
    operationMap = new HashMap<>();
    initializeOperationMap();
    ready = new ReadyState(this);
    nextOperand = new NextOperandState(this);
    nextOperation = new NextOperationState(this);
    buildingOperand = new BuildingOperandState(this);
    setState(ready);
  }

  /**
   * Returns the operand stack.
   *
   * @return the operandStack
   */
  public Deque<Number> getOperandStack() {
    return operandStack;
  }

  /**
   * Returns the operation stack.
   *
   * @return the operationStack
   */
  public Deque<Operation> getOperationStack() {
    return operationStack;
  }

  /**
   * Returns the display register.
   *
   * @return the display
   */
  public Display getDisplay() {
    return display;
  }

  /**
   * Enters an operation from the current state and sets state to the next state per the state
   * machine.
   *
   * <p>Calls enterOperation(Operation) on the current state object.
   *
   * <p>The Pi operation calls enterConstant(Operation) on the current state object.
   *
   * @param opString the String representation of the operation to enter
   */
  public void enterOperation(String opString) {
    // only execute if opString is supported
    if (operationMap.containsKey(opString)) {
      Operation op = operationMap.get(opString);

      if (op instanceof Pi) {
        setState(state.enterConstant(op));
      } else {
        setState(state.enterOperation(op));
      }
    }
  }

  /**
   * Enters a digit from the current state and sets state to the next state per the state machine.
   *
   * <p>Supported digit Strings include: { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", *
   * "BKSP" }
   *
   * <p>(supports requirements 3.1.3, 3.1.5, 3.1.7, 3.3.6)
   *
   * @param digit the digit to enter
   */
  public void enterDigit(String digit) {
    Set<String> validDigits = Set.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "BKSP");

    // only execute if digit is valid
    if (validDigits.contains(digit)) {
      setState(state.enterDigit(digit));
    }
  }

  /**
   * Pushes the value in the display to the operand stack.
   *
   * @throws NumberFormatException if the display value is not a valid representation of a BigNumber
   */
  public void pushDisplayToOperandStack() throws NumberFormatException {
    operandStack.push(new BigNumber(display.getValue()));
  }

  /**
   * Pushes an operation to the calculator. If the last operation on the operation stack has higher
   * precedence, calls executeOperation with that operation. Otherwise, if the operation is a
   * binaryOperation, pushes the operation to the operation stack, or, if a unaryOperation, executes
   * the operation immediately.
   *
   * @param op the operation to push to the calculator
   */
  public void pushOperation(Operation op) {
    // compare precedence etc.
    while (!operationStack.isEmpty()
        && operationStack.peek().getPrecedence() >= op.getPrecedence()) {
      executeOperation(operationStack.pop()); // execute higher precedence operation
    }
    // execute unaryOperations immediately
    if (op.isBinary()) {
      operationStack.push(op);
    } else {
      executeOperation(op);
    }
  }

  /**
   * Executes an operation.
   *
   * @param op the operation to execute
   */
  public void executeOperation(Operation op) {
    op.execute(this);
  }

  /**
   * Replaces the last operation with the new operation.
   *
   * @param op the new operation
   */
  public void replaceOperation(Operation op) {
    // do not pop operation if operation is the Clear operation
    if (!operationStack.isEmpty() && !(op instanceof Clear)) {
      operationStack.pop();
    }
    pushOperation(op);
  }

  /** Resolves all pending operations on the operationStack. */
  public void equals() {
    while (!operationStack.isEmpty()) {
      boolean isErrorState = (getDisplay().getValue()).matches(".*[a-z].*");
      if (isErrorState) {
        setState(ready);
        return;
      } else {
        executeOperation(operationStack.pop());
      }
    }
  }

  /** Executes the clear operation. */
  public void clear() {
    display.clear();
  }

  /** Resets the operand and operation stacks and clears the display. */
  public void allClear() {
    operandStack.clear();
    operationStack.clear();
    display.clear();
  }

  /** Resets the calculator. */
  public void resetCalculator() {
    allClear();
  }

  /**
   * Sends the next digit to the display or deletes last digit.
   *
   * @param digit the digit to send to the display
   */
  public void sendDigitToDisplay(String digit) {
    if (digit.equals("BKSP")) {
      display.deleteDigit();
    } else {
      display.addDigit(digit);
    }
  }

  /**
   * Calls overloaded updateDisplay(String) with the String representation of the top number of the
   * operand stack.
   */
  public void updateDisplay() {
    if (operandStack.peek() != null) {
      updateDisplay(operandStack.peek().toString());
    }
  }

  /**
   * Calls setValue(String) on this.display with the String to display.
   *
   * @param value the string representation of the number to display
   */
  public void updateDisplay(String value) {
    display.setValue(value);
  }

  /** Resets the display register. */
  public void resetDisplay() {
    display.reset();
  }

  /** Builds a map of supported operations to facilitate O(1) access to operations. */
  private void initializeOperationMap() {
    operationMap.put("+", new Add());
    operationMap.put("-", new Subtract());
    operationMap.put("*", new Multiply());
    operationMap.put("/", new Divide());
    operationMap.put("SQR", new Square());
    operationMap.put("SQRT", new SquareRoot());
    operationMap.put("NEG", new Negate());
    operationMap.put("INV", new Inverse());
    operationMap.put("PI", new Pi());
    operationMap.put("AC", new AllClear());
    operationMap.put("C", new Clear());
    operationMap.put("=", new Equals());
  }

  /**
   * Sets the state of this calculator.
   *
   * @param newState the new state
   */
  private void setState(State newState) {
    state = newState;
  }
}
