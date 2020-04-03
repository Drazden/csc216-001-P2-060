package edu.ncsu.csc216.business.model.contracts;

import java.time.LocalDate;

import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * This class represents a lease in the property. It has a confirmation number, startdate, enddate, owner, unit, and number of occupants.
 * @author Jacob Robinson
 *
 */
public class Lease {

	/** Counter for assigning confirmation numbers **/
	private static int confirmationCounter = 0;
	
	/** Highest possible confirmation number **/
	private static final int MAX_CONF_NUM = 0;
	
	/** this leases confirmation number **/
	private int confirmationNumber;
	
	/** start date for this lease **/
	private LocalDate startDate;
	
	/** end date for this lease **/
	private LocalDate endDate;
	
	/** number of occupants in this lease **/
	private int numOccupants;
	
	/** client for this lease **/
	private Client owner;
	
	/** rental unit for this lease **/
	private RentalUnit property;
	
	/**
	 * Constructs a new lease
	 * @param c confirmation number
	 * @param cl client
	 * @param r unit
	 * @param start start date
	 * @param end end date
	 * @param o occupants
	 */
	public Lease(int c, Client cl, RentalUnit r, LocalDate start, LocalDate end, int o) {
		//not yet implemented
	}
	
	/**
	 * Constructs a new lease
	 * @param cl client
	 * @param r unit
	 * @param start start date
	 * @param end end date 
	 * @param o occupants
	 */
	public Lease(Client c, RentalUnit r, LocalDate start, LocalDate end, int o) {
		//not yet implented
	}
	
	/**
	 * getter for confirmation number
	 * @return confirmation number
	 */
	public int getConfirmationNumber() {
		return confirmationNumber;
	}
	
	/**
	 * getter for client
	 * @return client
	 */
	public Client getClient() {
		return owner;
	}
	
	/**
	 * get for rentalunit
	 * @return property
	 */
	public RentalUnit getProperty() {
		return property;
	}
	
	/**
	 * getter for start date
	 * @return start date
	 */
	public LocalDate getStart() {
		return startDate;
	}
	
	/**
	 * getter for end date
	 * @return end date
	 */
	public LocalDate getEnd() {
		return endDate;
	}
	
	/** 
	 * getter for occupants
	 * @return occupants
	 */
	public int getNumOccupants() {
		return numOccupants;
	}
	
	/**
	 * setter for end date
	 * @param end end date to set
	 */
	public void setEndDateEarlier(LocalDate end) {
		//not yet implemented
	}
	
	/**
	 * returns data of lease as string
	 * @return formatted data as string array
	 */
	public String[] leaseData() {
		return null;
	}
	
	/**
	 * resets the counter
	 * @param num number to reset counter to
	 */
	public static void resetConfirmationNumbering(int num) {
		//not yet implemented
	}
	
	/**
	 * compares to another lease
	 * @param o lease to compare to
	 * @return int
	 */
	public int compareTo(Lease o) {
		return 0;
	}
}

