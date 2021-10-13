package model;

/**
 * This class implements the pi operation. (requirement 3.3.9)
 * 
 * @author  Shawn Crahen
 * @version 1.1
 * 
 */
public class Pi extends Operation {

	/**
	 * String representation of pi with 100-digit precision.
	 */
	private static final String PI = "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211707";

	/**
	 * Class constructor.
	 */
	public Pi() {
		precedence = 4;
		isBinary = false;
	}

	/**
	 * Updates the calculator display with pi.
	 */
	@Override
	public void execute(Calculator calculator) {
		calculator.updateDisplay(PI);
	}

}
