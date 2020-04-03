package edu.ncsu.csc216.business.model.properties;

/**
 * Class for exception thrown when the unit is out of service and use is attempted
 * @author Jacob Robinson
 *
 */
public class RentalUnitOutOfServiceException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs with a custom message
	 * @param message custom string
	 */
	public RentalUnitOutOfServiceException(String message) {
		super(message);
	}
	
	/**
	 * Constructs with default message
	 */
	public RentalUnitOutOfServiceException() {
		this("Unit out of service");
	}
	
}
