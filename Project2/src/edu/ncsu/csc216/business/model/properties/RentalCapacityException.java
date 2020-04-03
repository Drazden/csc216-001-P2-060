package edu.ncsu.csc216.business.model.properties;

/**
 * Class for exception thrown when capacity is exceeded
 * @author Jacob Robinson
 *
 */
public class RentalCapacityException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs with a custom message
	 * @param message custom string
	 */
	public RentalCapacityException(String message) {
		super(message);
	}
	
	/**
	 * Constructs with default message
	 */
	public RentalCapacityException() {
		this("Over capacity");
	}
}
