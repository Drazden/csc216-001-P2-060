package edu.ncsu.csc216.business.model.stakeholders;

/**
 * Class for exception thrown when room already exists
 * @author Jacob Robinson
 *
 */
public class DuplicateRoomException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs with a custom message
	 * @param message custom string
	 */
	public DuplicateRoomException(String message) {
		super(message);
	}
	
	/**
	 * Constructs with default message
	 */
	public DuplicateRoomException() {
		this("Room already exists");
	}
}
