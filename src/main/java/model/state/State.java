package model.state;

import model.app.Calculator;
import model.operation.function.Operation;

/**
 * This class provides a skeletal implementation of a generic calculator state.
 * To implement a state, extend this class and override the its methods.
 *
 * @author  Shawn Crahen
 * @version 1.0
 *
 */
public abstract class State {

	/**
	 * The calculator associated with this state.
	 */
	protected Calculator calculator;

	/**
	 * Class constructor specifying the calculator object associated with this
	 * state.
	 *
	 * @param calculator the calculator associated with this state
	 */
	public State(Calculator calculator) {
		this.calculator = calculator;
	}

	/**
	 * An abstract method representing the state transition "enter digit."
	 *
	 * @param  digit the digit to enter
	 * @return       the next state per the calculator state machine
	 */
	public abstract State enterDigit(String digit);

	/**
	 * An abstract method representing the state transition "enter operation."
	 *
	 * @param  op the operation to enter
	 * @return    the next state per the calculator state machine
	 */
	public abstract State enterOperation(Operation op);

	/**
	 * An abstract method representing the state transition "enter constant."
	 *
	 * @param  op the operation responsible for the constant entry
	 * @return    the next state per the calculator state machine
	 */
	public abstract State enterConstant(Operation op);
}
