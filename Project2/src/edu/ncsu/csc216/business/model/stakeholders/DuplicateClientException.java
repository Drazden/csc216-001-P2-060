package edu.ncsu.csc216.business.model.stakeholders;

/**
 * Class for exception thrown when client already exists
 * @author Jacob Robinson
 *
 */
public class DuplicateClientException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs with a custom message
	 * @param message custom string
	 */
	public DuplicateClientException(String message) {
		super(message);
	}
	
	/**
	 * Constructs with default message
	 */
	public DuplicateClientException() {
		this("Client already exists");
	}
}
