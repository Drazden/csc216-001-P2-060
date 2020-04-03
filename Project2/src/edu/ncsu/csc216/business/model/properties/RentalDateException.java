package edu.ncsu.csc216.business.model.properties;

/**
 * Class for exception thrown when dates are invalid for this program
 * @author Jacob Robinson
 *
 */
public class RentalDateException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs with a custom message
	 * @param message custom string
	 */
	public RentalDateException(String message) {
		super(message);
	}
	
	/**
	 * Constructs with default message
	 */
	public RentalDateException() {
		this("Invalid date");
	}

}
