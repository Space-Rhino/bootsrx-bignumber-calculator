package model.operation;

/**
 * This class implements the pi operation. (requirement 3.3.9)
 *
 * @author  Shawn Crahen
 * @version 1.0
 */
public class Pi extends Operation {
	
	/**
	 * String representation of pi with 100-digit precision.
	 */
	private static final String PI = "3.1415926535897932384626433832795028841971693993751"
			+ "0582097494459230781640628620899862803482534211707";
	
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
