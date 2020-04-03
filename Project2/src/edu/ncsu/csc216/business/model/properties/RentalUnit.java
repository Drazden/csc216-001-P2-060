package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Rental unit is an abstract class that represents rentable property in the building. It implementgs comparable
 * to compare against itself. It has a list of leases for itself using a SortedLInkListWithIterator
 * @author Jacob Robinson
 *
 */
public abstract class RentalUnit implements Comparable<RentalUnit> {

	/** Highest floor number for the rental unit **/
	private static final int MAX_FLOOR = 0;
	
	/** Lowest floor number for the rental unit **/
	private static final int MIN_FLOOR = 0;
	
	/** Highest room number for the rental unit **/
	private static final int MAX_ROOM = 0;
	
	/** Lowest room number for the rental unit **/
	private static final int MIN_ROOM = 0;
	
	/** Represents if room is in service, true if it is, otherwise false **/
	private boolean inService;
	
	/** floor where rental unit is **/
	private int floor;

	/** room where rental unit is **/
	private int room;
	
	/** maximum occupants in the unit **/
	private int capacity;
	
	/** A list of leases associated with the unit **/
	protected SortedLinkedListWithIterator<Lease> myLeases;
	
	/**
	 * Constructs a new RentalUnit at a specified location with specified capacity
	 * @param loc location in ff-rr where ff is floor and rr is room
	 * @param cap max occupants for room
	 * @throws IllegalArgumentException for invalid location or capacity is less than 1
	 */
	public RentalUnit(String loc, int cap) {
		//not yet implemented
	}
	
	/**
	 * Getter for capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Getter for floor
	 * @return floor
	 */
	public int getFloor() {
		return floor;
	}
	
	/**
	 * Getter for room
	 * @return room
	 */
	public int getRoom() {
		return room;
	}
	
	/**
	 * Compares this rental unit to a specified one
	 * @param o RentalUnit to compare to
	 * @return int
	 */
	public int compareTo(RentalUnit o) {
		return 0;
	}
	
	/**
	 * Return this unit to service
	 */
	public void returnToService() {
		//not yet implemented
	}
	
	/**
	 * Checks if this room is in service
	 * @return boolean for is in service
	 */
	public boolean isInService() {
		return false;
	}
	
	/**
	 * Take this unit out of service
	 */
	public void takeOutService() {
		
	}
	
	/**
	 * Reserves this rental unit for lease
	 * @param cli client
	 * @param start start date
 	 * @param dur duration 
	 * @param ocu occupants
	 * @return new Lease
	 * @throws IllegalArgumentException if null parameters or duration/occupants < 1
	 * @throws RentalUnitOutOfServiceException if unit is not in service
	 * @throws RentalDateException if dates are invalid (conflicting, improper for kind, out of range)
	 * @throws RentalCapacityException if occupants exceed capacity
	 */
	public abstract Lease reserve(Client cli, LocalDate start, int dur, int ocu) throws IllegalArgumentException, RentalUnitOutOfServiceException, RentalDateException, RentalCapacityException;
	
	/**
	 * Records an existing lease for reading from file
	 * @param con confirmation number
	 * @param cli client
	 * @param start start date
	 * @param end end date
	 * @param ocu occupants
	 * @return new lease
	 * @throws RentalDateException if dates are invalid (conflicting, improper for kind, out of range)
	 * @throws RentalCapacityException if occupants exceed capacity
	 */
	public abstract Lease recordExistingLease(int con, Client cli, LocalDate start, LocalDate end, int ocu) throws RentalDateException, RentalCapacityException;
	
	/**
	 * Checks if dates are correct. must be between 1/1/20 and 12/31/29, start before end
	 * @param start start date
	 * @param end end date
	 * @throws RentalDateException if the dates are invalid
	 */
	public void checkDates(LocalDate start, LocalDate end) {
		//not yet implemented
	}
	
	/**
	 * Checks all parameters for the lease and makes sure they are correct
	 * @param cli client
	 * @param start start date
	 * @param dur duration 
	 * @param ocu occupants
	 * @throws IllegalArgumentException if parameters are null, ocu/dur < 1
	 * @throws RentalUnitOutOfServiceException if unit is not in service
	 */
	protected void checkLeaseConditions(Client cli, LocalDate start, int dur, int ocu) {
		//Not yet implemented
	}
	
	/**
	 * Removes this unit from service on specified date. Removes all leases after that date.
	 * Current leases will have end dates shifted up to closing date. Returns list of leases that were cancelled
	 * @param start start date
	 * @return list of cancelled leases
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
		return null;
	}
	
	/**
	 * Checks for lease with date starting at specified date or -1 if none found
	 * @param start date to look for 
	 * @return index of found lease
	 */
	protected int cutoffIndex(LocalDate start) {
		return 0;
	}
	
	/**
	 * Cancels a lease using its confirmation number
	 * @param con confirmation number of lease
	 * @return cancelled lease
	 * @throws IllegalArgumentException if no such lease exists
	 */
	public Lease cancelLeaseByNumber(int con) {
		return null;
	}
	
	/**
	 * Adds a lease to this unit
	 * @param lease to add
	 * @throws IllegalArgumentException if lease is not for this unit
	 */
	public void addLease(Lease lease) {
		//not yet implemented
	}
	
	/**
	 * Lists leases for this unit
	 * @return string array with each string as a lease
	 */
	public String[] listLeases() {
		return null;
	}
	
	/**
	 * Returns information about this unit
	 * @return floor, room, capacity, availability
	 */
	public String getDescription() {
		return null;
	}
	
	/**
	 * Hashsed int
	 * @return hashed code
	 */
	public int hashCode() {
		return 0;
	}
	
	/**
	 * Checks if this unit equals another
	 * @param o unit to check against
	 * @return true if equals
	 */
	public boolean equals(Object o) {
		return false;
	}
}
