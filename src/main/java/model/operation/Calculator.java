package model.operation;

import java.util.Stack;

import number.Number;

/**
 * This class is the main model class and contains the methods necessary to
 * complete calculator operations. Calculator implements a state machine which
 * determines which methods to call for each input type based on state.
 *
 * @author  Shawn Crahen
 * @version 1.0
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

   //	private Stack<Operation> operationStack;

	/**
	 * The display register for this Calculator.
	 */
	@SuppressWarnings("FieldMayBeFinal")
	private Display display;
	
  // private State state;
  //	private Map<String, Operation> operationMap;

  // public State ready;
  // public State buildingOperand;
  // public State nextOperand;
  // public State nextOperation;

	/**
	 * Class constructor.
	 */
	@SuppressWarnings("CommentedOutCode")
	public Calculator() {
		operandStack = new Stack<>();
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

}
