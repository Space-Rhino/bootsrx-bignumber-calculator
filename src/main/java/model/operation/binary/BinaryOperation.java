package model.operation.binary;

import java.util.EmptyStackException;

import model.app.Calculator;
import model.operation.function.Operation;
import number.Number;

/**
 * This class provides a skeletal implementation of a generic binary operation.
 * To implement a binary operation, extend this class and override the
 * executeBinary(Number, Number) method.
 *
 * @author  Shawn Crahen
 * @version 1.0
 * @see     Operation
 * @see     Number
 */
public abstract class BinaryOperation extends Operation {

	/**
	 * Class constructor.
	 */
	protected BinaryOperation() {
		isBinary = true;
	}

	/**
	 * Executes this operation.
	 * <p>
	 * Calls executeBinary(Number, Number), pushes the result back to the operand
	 * stack, then updates the display.
	 * <p>
	 * If executeBinary(Number, Number) throws and ArithmeticException, updates
	 * display indicating invalid attempt to divide by zero.
	 *
	 * @throws EmptyStackException if stack is empty
	 */
	@Override
	public void execute(Calculator calculator) throws EmptyStackException {
		stack = calculator.getOperandStack();
		Number number2 = stack.pop();
		Number number1 = stack.pop();
		try {
			stack.push(executeBinary(number1, number2));
			calculator.updateDisplay();
		} catch (ArithmeticException e) { // divide by zero
			calculator.updateDisplay("Cannot divide by zero");
		}
	}

	/**
	 * Executes this binary operation.
	 *
	 * @param  number1 the first operand
	 * @param  number2 the second operand
	 * @return         the result of this binary operation
	 */
	public abstract Number executeBinary(Number number1, Number number2);

}
