package model.operation;

public class Equals extends Operation {

	/**
	 * 
	 */
	public Equals() {
		precedence = 4;
		isBinary = false;
	}

	/**
	 *
	 */
	public void execute(Calculator calculator) {
		calculator.equals();
	}
}
