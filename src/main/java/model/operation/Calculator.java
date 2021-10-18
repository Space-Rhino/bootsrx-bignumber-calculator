package model.operation;

import java.util.Stack;

import number.BigNumber;
import number.Number;

/**
 * This class is the main model class and contains the methods necessary to
 * complete calculator operations. Calculator implements a state machine which
 * determines which methods to call for each input type based on state.
 *
 * @author  Shawn Crahen
<<<<<<< HEAD:src/main/java/model/operation/Calculator.java
 * @version 1.0
=======
 * @version 2.1
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Calculator.java
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
	@SuppressWarnings("unused") // temporary suppression during development
	private State state;

//	private Map<String, Operation> operationMap;

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
//		operationMap = new HashMap<>();
//		initializeOperationMap();
		initializeStates();
		setState(ready);
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Calculator.java
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
	 * Returns the operand stack.
	 *
	 * @return the operandStack
	 */
	public Stack<Number> getOperandStack() {
		return operandStack;
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
	 * Replaces the last operation with the new operation.
	 * 
	 * @param op the new operation
	 */
	public void replaceOperation(Operation op) {
		if (!operationStack.isEmpty()) {
			// do not pop operation if operation is the Clear operation
			if (!(op instanceof model.Clear))
				operationStack.pop();
		}
		pushOperation(op);
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
		if (digit.equals("BKSP"))
			display.deleteDigit();
		else
			display.addDigit(digit);
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
	 * Creates instances of each state in the state hierarchy.
	 */
	private void initializeStates() {
		ready = new ReadyState(this);
		nextOperand = new NextOperandState(this);
		nextOperation = new NextOperationState(this);
		buildingOperand = new BuildingOperandState(this);
	}

	/**
	 * Sets the state of this calculator.
	 * 
	 * @param newState the new state
	 */
	private void setState(State newState) {
		state = newState;
		// test only
		// System.out.println("state changed to " + state);
	}

	/**
	 * Resets the display register.
	 */
	public void resetDisplay() {
		display.reset();
	}

}
