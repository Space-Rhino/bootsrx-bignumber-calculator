package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import presenter.Presenter;

/**
 * This class is the main view class and configures the layout and functionality
 * of the main window frame. This class passes events to the Presenter.
 * 
 * @author  Shawn Crahen
 * @version 1.0
 * @see     JFrame
 */
public class Gui extends JFrame {
	
	/**
	 * The display panel for this gui. (requirement 3.1.2)
	 */
	private final DisplayPanel display;
	
	/**
	 * The button panel for this gui.
	 */
	private final ButtonPanel buttonPanel;
	
	/**
	 * The presenter.
	 */
	private final Presenter presenter;
	
	/**
	 * Class constructor.
	 * 
	 * @param presenter the presenter associated with this gui
	 */
	public Gui(Presenter presenter) {
		super("BigCalculator");
		this.presenter = presenter;
		
		// set aesthetic
		getContentPane().setBackground(new Color(60, 60, 60));
		setPreferredSize(new Dimension(945, 470));
		getContentPane().setLayout(new GridBagLayout());
		addKeyListener(new KeyHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // (requirement 3.1.1)
		
		// ensure frame loads in the center of the display
		Toolkit tk = getToolkit();
		Dimension screen = tk.getScreenSize();
		setLocation((screen.width - 995) / 3, (screen.height - 470) / 3);
		
		// build DisplayPanel
		display = new DisplayPanel();
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0);
		getContentPane().add(display, gbc);
		
		// build buttonPanel
		buttonPanel = new ButtonPanel(this);
		gbc = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(0, 10, 10, 10), 0, 0);
		getContentPane().add(buttonPanel, gbc);
		
		pack();
	}
	
	/**
	 * Passes a digit to the presenter.
	 * 
	 * @param digit the digit to pass
	 */
	public void enterDigit(String digit) {
		presenter.enterDigit(digit);
	}
	
	/**
	 * Passes an operation to the presenter.
	 * 
	 * @param opString the operation to pass
	 */
	public void enterOperation(String opString) {
		presenter.enterOperation(opString);
	}
	
	/**
	 * Updates the display.
	 * 
	 * @param text the display text
	 */
	public void updateDisplay(String text) {
		display.setDisplay(text);
	}
	
	/**
	 * This class handles KeyEvents enabling users to enter input with keyboard
	 * keys. (requirement 3.2.2)
	 * 
	 * @author  Shawn Crahen
	 * @version 1.0
	 * @see     KeyAdapter
	 * @see     KeyEvent
	 */
	class KeyHandler extends KeyAdapter {

    /**
		 * Handles keyTyped events.
		 */
    @Override
    public void keyTyped(KeyEvent e) {
			char key = e.getKeyChar();
			switch (key) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '.':
					// pass digit
					buttonPanel.setLastClickedButton(buttonPanel.getButton(String.valueOf(key)));
					presenter.enterDigit(String.valueOf(key));
					break;
				case '+':
				case '-':
				case '*':
				case '/':
					// handle operation button aesthetic
					JButton operationButton = buttonPanel.getActiveOperationButton();
					if (operationButton != null)
						operationButton.setBackground(Color.gray);
					buttonPanel.setActiveOperationButton(buttonPanel.getButton(String.valueOf(key)));
					buttonPanel.getActiveOperationButton().setBackground(Color.orange);
					buttonPanel.setLastClickedButton(buttonPanel.getButton(String.valueOf(key)));
					// pass operation
					presenter.enterOperation(String.valueOf(key));
					break;
					
				default:
					break;
			}
		}

    /**
		 * Handles keyPressed events.
		 */
    @Override
    public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			// handle enter key press
			if (key == KeyEvent.VK_ENTER) {
				JButton operationButton = buttonPanel.getActiveOperationButton();
				if (operationButton != null)
					operationButton.setBackground(Color.gray);
				presenter.enterOperation("=");
			}
			// handle delete key press
			if (key == KeyEvent.VK_DELETE)
				presenter.enterOperation("C");
			// handle backspace key press
			if (key == KeyEvent.VK_BACK_SPACE)
				presenter.enterDigit("BKSP");
		}
	}
	
}
