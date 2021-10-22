package model.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.binaryoperations.Add;
import model.binaryoperations.Divide;
import model.binaryoperations.Multiply;
import model.binaryoperations.Subtract;
import model.state.BuildingOperandState;
import model.state.NextOperandState;
import model.state.NextOperationState;
import model.state.ReadyState;
import model.state.State;
import model.unaryoperations.Inverse;
import model.unaryoperations.Negate;
import model.unaryoperations.Square;
import model.unaryoperations.SquareRoot;
import number.BigNumber;
import number.Number;

/**
 * This class is the main model class and contains the methods necessary to
 * complete calculator operations. Calculator implements a state machine which
 * determines which methods to call for each input type based on state.
 *
 * @author  Shawn Crahen
<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
 * @version 1.0
=======
 * @version 2.1
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Calculator.java
=======
 * @version 1.0
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Calculator.java
 * @see     Number
 * @see     Operation
 */
@SuppressWarnings("CommentedOutCode")
public class Calculator {

	/**
	 * A stack to store operands of type Number.
	 */
	@SuppressWarnings("FieldMayBeFinal")
	private Stack<Number> operandStack;

<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
   //	private Stack<Operation> operationStack;
=======
	/**
	 * A stack to store arithmetic operations.
	 */
	private Stack<Operation> operationStack;
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Calculator.java

	/**
	 * The display register for this Calculator.
	 */
	@SuppressWarnings("FieldMayBeFinal")
	private Display display;
	
  // private State state;
  //	private Map<String, Operation> operationMap;

<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
  // public State ready;
  // public State buildingOperand;
  // public State nextOperand;
  // public State nextOperation;
=======
	/**
	 * The state of this calculator.
	 */
	private State state;

	/**
	 * 
	 */
	private Map<String, Operation> operationMap;

	/**
	 * An instance of ReadyState.
	 */
	public State ready;

	/**
	 * An instance of BuildingOperandState.
	 */
	public State buildingOperand;

	/**
	 * An instance of NextOperandState.
	 */
	public State nextOperand;

	/**
	 * An instance of NextOperationState.
	 */
	public State nextOperation;
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Calculator.java

	/**
	 * Class constructor.
	 */
	@SuppressWarnings("CommentedOutCode")
	public Calculator() {
		operandStack = new Stack<>();
<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
		// operationStack = new Stack<>();
		display = new Display("0");
		// operationMap = new HashMap<>();
		// initializeOperationMap();
		// initializeStates();
		// setState(ready);
	}

	/*
	 * This is a utility method for test only. Delete when
	 * pushDisplayToOperandStack() is complete.
	 */
	public void pushToOperandStack(Number number) {
		operandStack.push(number);
=======
		operationStack = new Stack<>();
		display = new Display("0");
		operationMap = new HashMap<>();
		initializeOperationMap();
		initializeStates();
		setState(ready);
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Calculator.java
	}

	/**
<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
	 * Calls overloaded updateDisplay(String) with the String representation of the
	 * top number of the operand stack.
	 */
	public void updateDisplay() {
		updateDisplay(operandStack.peek().toString());
	}

	/**
	 * Calls setValue(String) on this.display with the String to display.
	 *
	 * @param value the string representation of the number to display
	 */
	public void updateDisplay(String value) {
		display.setValue(value);
	}

	/**
=======
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Calculator.java
	 * Returns the operand stack.
	 *
	 * @return the operandStack
	 */
	public Stack<Number> getOperandStack() {
		return operandStack;
	}

	public Stack<Operation> getOperationStack() {
		return operationStack;
	}

	public Display getDisplay() {
		return display;
	}	
	
	public void enterOperation(String opString) {
		Operation op = operationMap.get(opString);

		if (op instanceof model.operation.Pi) {
			setState(state.enterConstant(op));
		} else {
			setState(state.enterOperation(op));
		}
	}

	public void enterDigit(String digit) {
		setState(state.enterDigit(digit));
	}

	/**
	 * Pushes the value in the display to the operand stack.
	 * 
	 * @throws NumberFormatException if the display value is not a valid
	 *                               representation of a BigNumber
	 */
	public void pushDisplayToOperandStack() throws NumberFormatException {
		operandStack.push(new BigNumber(display.getValue()));
	}

	/**
	 * Pushes an operation to the calculator. If the last operation on the operation
	 * stack has higher precedence, calls executeOperation with that operation.
	 * Otherwise, if the operation is a binaryOperation, pushes the operation to the
	 * operation stack, or, if a unaryOperation, executes the operation immediately.
	 * 
	 * @param op the operation to push to the calculator
	 */
	public void pushOperation(Operation op) {
		// compare precedence etc.
		while (!operationStack.isEmpty() && operationStack.peek().getPrecedence() >= op.getPrecedence()) {
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
		if (!operationStack.isEmpty()) {
			// do not pop operation if operation is the Clear operation
			if (!(op instanceof model.operation.Clear)) {
				operationStack.pop();
			}
		}
		pushOperation(op);
	}
	
	public void equals() {
		while (!operationStack.isEmpty()) {
			executeOperation(operationStack.pop());
		}
	}

	public void clear() {
		display.clear();
	}
	
	/**
	 * Resets the operand and operation stacks and clears the display.
	 */
	public void allClear() {
		operandStack.clear();
		operationStack.clear();
		display.clear();
	}

	/**
	 * Resets the calculator.
	 */
	public void resetCalculator() {
		allClear();
	}

	/**
	 * Sends the next digit to the display.
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
	 * Calls overloaded updateDisplay(String) with the String representation of the
	 * top number of the operand stack.
	 */
	public void updateDisplay() {
		updateDisplay(operandStack.peek().toString());
	}

	/**
	 * Calls setValue(String) on this.display with the String to display.
	 * 
	 * @param value the string representation of the number to display
	 */
	public void updateDisplay(String value) {
		display.setValue(value);
	}

	/**
	 * Resets the display register.
	 */
	public void resetDisplay() {
		display.reset();
	}

	/**
	 * Creates instances of each state in the state hierarchy.
	 */
	private void initializeStates() {
		ready = new ReadyState(this);
		nextOperand = new NextOperandState(this);
		nextOperation = new NextOperationState(this);
		buildingOperand = new BuildingOperandState(this);
	}

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
