package model.operation;

<<<<<<< HEAD:src/main/java/model/operation/Display.java
import java.beans.PropertyChangeEvent;
=======
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Display.java

/**
 * This class represents the display register for this Calculator. It maintains
 * the display value and fires a PropertyChangeEvent when the value changes.
 *
 * @author  Shawn Crahen
<<<<<<< HEAD:src/main/java/model/operation/Display.java
<<<<<<< HEAD:src/main/java/model/operation/Display.java
 * @version 1.0
=======
 * @version 2.1
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Display.java
 * @see     PropertyChangeEvent
 */
public class Display {

<<<<<<< HEAD:src/main/java/model/operation/Display.java
  // private static final int MAX_DIGITS = 100;
=======
=======
 * @version 1.0
 * @see     PropertyChangeEvent
 */
public class Display {
	
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Display.java
	/**
	 * The maximum number of digits allowed in the display register.
	 */
	private static final int MAX_DIGITS = 100;
<<<<<<< HEAD:src/main/java/model/operation/Display.java
>>>>>>> b3c742d (dev-2.1 complete):src/main/java/model/Display.java

=======
	
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Display.java
	/**
	 * The display value.
	 */
	private String value;
<<<<<<< HEAD:src/main/java/model/operation/Display.java

  // private PropertyChangeSupport support;

=======
	
	/**
	 * Enables binding to a PropertyChangeListener.
	 */
	private PropertyChangeSupport support;
	
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Display.java
	/**
	 * Class constructor.
	 *
	 * @param initialValue the value upon instantiation
	 */
	public Display(String initialValue) {
		value = initialValue;
<<<<<<< HEAD:src/main/java/model/operation/Display.java
  // support = new PropertyChangeSupport(this);
=======
		support = new PropertyChangeSupport(this);
>>>>>>> 855449a (reorganize package structure in preparation for rebase on dev-1):src/main/java/model/Display.java
	}
	
	/**
	 * Sets the value of the display register to the given String and fires a
	 * PropertyChangeEvent to listeners.
	 * 
	 * @param value the new value
	 */
	public void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		support.firePropertyChange("display", oldValue, this.value);
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value in the display register
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Appends the next digit to the display register. Enforces maximum length,
	 * prevents subsequent decimal entries.
	 * 
	 * @param digit the digit to append
	 */
	public void addDigit(String digit) {
		// prevent length > maximum length
		if (value.length() >= MAX_DIGITS)
			return;
		
		// prevent more than one decimal
		if (digit.equals(".") && value.contains("."))
			return;
		
		// delete leading 0s
		if (value.equals("0") && !(digit.equals("."))) {
			value = "";
		}
		setValue(value + digit);
	}
	
	/**
	 * Deletes the least significant digit from the display register.
	 */
	public void deleteDigit() {
		if (value.length() == 1) {
			setValue("0");
		} else {
			setValue(value.substring(0, value.length() - 1));
		}
	}
	
	/**
	 * Sets the value of the display back to "0"
	 */
	public void clear() {
		// call setValue to trigger PropertyChangeEvent
		setValue("0");
	}
	
	/**
	 * Sets the display register to "0" without triggering a PropertyChangeEvent
	 */
	public void reset() {
		// set directly; no change event
		value = "0";
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}
}
