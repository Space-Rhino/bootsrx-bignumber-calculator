package model;

/**
 * This class represents the display register for this Calculator. It maintains
 * the display value and fires a PropertyChangeEvent when the value changes.
 * 
 * @author  Shawn Crahen
 * @version 1.1
 * @see     PropertyChangeEvent
 */
public class Display {

//	private static final int MAX_DIGITS = 100;

	/**
	 * The display value.
	 */
	private String value;

//	private PropertyChangeSupport support;

	/**
	 * Class constructor.
	 * 
	 * @param initialValue the value upon instantiation
	 */
	public Display(String initialValue) {
		value = initialValue;
//		support = new PropertyChangeSupport(this);
	}

	/*
	 * This method facilitates testing the operation hierarchy. Rewrite with
	 * production method.
	 */
	public void setValue(String value) {
		// Stub: for test only
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value in the display register
	 */
	public String getValue() {
		return value;
	}
}
