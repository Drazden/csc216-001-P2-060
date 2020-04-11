package edu.ncsu.csc216.business.model.contracts;

import java.time.LocalDate;

import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * This class represents a lease in the property. It has a confirmation number, startdate, enddate, owner, unit, and number of occupants.
 * @author Jacob Robinson
 *
 */
public class Lease implements Comparable<Lease> {

	/** Counter for assigning confirmation numbers **/
	private static int confirmationCounter = 0;
	
	/** Highest possible confirmation number **/
	private static final int MAX_CONF_NUM = 999999;
	
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
		this.confirmationNumber = c;
		resetConfirmationNumbering(c);
		confirmationCounter++;
		this.owner = cl;
		this.property = r;
		this.startDate = start;
		this.endDate = end;
		this.numOccupants = o;
	}
	
	/**
	 * Constructs a new lease
	 * @param cl client
	 * @param r unit
	 * @param start start date
	 * @param end end date 
	 * @param o occupants
	 */
	public Lease(Client cl, RentalUnit r, LocalDate start, LocalDate end, int o) {
		this.confirmationNumber = confirmationCounter;
		confirmationCounter++;
		this.owner = cl;
		this.property = r;
		this.startDate = start;
		this.endDate = end;
		this.numOccupants = o;
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
	 * used for setting end date to an earlier date if need
	 * @param end end date to set
	 * @throws IllegalArgumentException if end date would conflict with start
	 */
	public void setEndDateEarlier(LocalDate end) {
		if (end.isBefore(this.startDate)) {
			throw new IllegalArgumentException();
		}
		this.endDate = end;
	}
	
	/**
	 * returns data of lease as string
	 * @return formatted data as string array
	 */
	public String[] leaseData() {
		String[] data = new String[6];
		data[0] = "" + this.confirmationNumber;
		data[1] = this.startDate.toString() + " to " + this.endDate.toString();
		data[2] = "" + this.numOccupants;
		data[3] = this.property.getClass().toString() + ":" + this.property.getFloor() + "-" + this.property.getRoom();
		data[4] = this.owner.getName();
		data[5] = this.owner.getId();
		return data;
	}
	
	/**
	 * resets the counter
	 * @param num number to reset counter to
	 * @throws IllegalArgumentException if number is below 0 or above max
	 */
	public static void resetConfirmationNumbering(int num) {
		if (num < 0 || num > MAX_CONF_NUM) {
			throw new IllegalArgumentException();
		}
		
		confirmationCounter = num;
	}
	
	/**
	 * compares to another lease
	 * @param o lease to compare to
	 * @return int
	 */
	public int compareTo(Lease o) {
		if (this.startDate.compareTo(o.getStart()) < 0) {
			return -1;
		} else if (this.endDate.compareTo(o.getEnd()) < 0) {
			return -1;
		} else {
			if (this.confirmationNumber < o.confirmationNumber) {
				return -1;
			} else {
				return 1;
			}
		}
		
	}
}

