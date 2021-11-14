package model.operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
 * @version 1.0
 * @see     Number
 * @see     Operation
 */

public class Calculator {
	
	/**
	 * A stack to store operands of type Number.
	 */
	private Stack<Number> operandStack;
	
	/**
	 * A stack to store arithmetic operations.
	 */
	private Stack<Operation> operationStack;
	
	/**
	 * The display register for this Calculator.
	 */
	private Display display;
	
	/**
	 * The state of this calculator.
	 */
	private State state;
	
	/**
	 * A map to provide O(1) access to operations.
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
	
	/**
	 * Class constructor.
	 */
	public Calculator() {
		operandStack = new Stack<>();
		operationStack = new Stack<>();
		display = new Display("0");
		operationMap = new HashMap<>();
		initializeOperationMap();
		initializeStates();
		setState(ready);
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
	 * Returns the operation stack.
	 * 
	 * @return the operationStack
	 */
	public Stack<Operation> getOperationStack() {
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
	 * Enters an operation from the current state and sets state to the next state
	 * per the state machine.
	 * <p>
	 * Calls enterOperation(Operation) on the current state object.
	 * <p>
	 * The Pi operation calls enterConstant(Operation) on the current state object.
	 * 
	 * @param opString the String representation of the operation to enter
	 */
	public void enterOperation(String opString) {
		// only execute if opString is supported
		if (operationMap.containsKey(opString)) {
			Operation op = operationMap.get(opString);
			
			if (op instanceof model.operation.Pi) {
				setState(state.enterConstant(op));
			} else {
				setState(state.enterOperation(op));
			}
		}
	}
	
	/**
	 * Enters a digit from the current state and sets state to the next state per
	 * the state machine.
	 * <p>
	 * Supported digit Strings include: { "0", "1", "2", "3", "4", "5", "6", "7",
	 * "8", "9", ".", "BKSP" }
	 * <p>
	 * (supports requirements 3.1.3, 3.1.5, 3.1.7, 3.3.6)
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
	
	/**
	 * Resolves all pending operations on the operationStack.
	 */
	public void equals() {
		while (!operationStack.isEmpty()) {
			executeOperation(operationStack.pop());
		}
	}
	
	/**
	 * Executes the clear operation.
	 */
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
	
	/**
	 * Builds a map of supported operations to facilitate O(1) access to operations.
	 */
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