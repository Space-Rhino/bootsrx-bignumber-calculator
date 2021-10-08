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
	private static final String PI = "3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117068";

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
