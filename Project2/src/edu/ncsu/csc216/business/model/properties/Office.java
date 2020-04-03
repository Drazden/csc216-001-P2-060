package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

public class Office extends RentalUnit{

	/** Max capacity for office **/
	private static final int MAX_CAPACITY = 0;
	
	/** Number of calendar rows **/
	private static final int CAL_ROWS = 0;
	
	/** Number of calendar columns **/
	private static final int CAL_COLS = 0;
	
	/** Calendar for office **/
	private int[][] calendar;
	
	/**
	 * Creates a new office
	 * @param loc location
	 * @param cap capacity
	 * @throws IllegalArgumentException if capacity is too large
	 */
	public Office(String loc, int cap) {
		super(loc, cap);
	}

	/**
	 * Reserves this room for a lease
	 * @param cli client
	 * @param start start date
	 * @param dur duration
	 * @param ocu occupants
	 * @return new Lease
	 * @throws RentalDateException if start/end are not on first/last day of month
	 * @throws RentalCapacityException if capacity is breached
	 * @throws RentalOutOfServiceException if not in service
	 */
	@Override
	public Lease reserve(Client cli, LocalDate start, int dur, int ocu) throws RentalDateException, RentalCapacityException, RentalOutOfServiceException{
		return null;
	}

	/**
	 * Records an existing lease for this kind
	 * @param cli client
	 * @param start start date
	 * @param end end date
	 * @param ocu occupants
	 * @return new lease
	 */
	@Override
	public Lease recordExistingLease(int con, Client cli, LocalDate start, LocalDate end, int ocu) {
		return null;
	}
	
	/**
	 * Remaining capacity for date
	 * @param date to check
	 * @return remaining capacity
	 */
	protected int remainingCapacityFor(LocalDate date) {
		return 0;
		
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
	 * Gets duration in months
	 * @param start start date
	 * @param end end date
	 * @return number of months
	 */
	protected static int getMonthsDuration(LocalDate start, LocalDate end) {
		return 0;
	}
	
	/**
	 * Returns information about this unit
	 * @return floor, room, capacity, availability
	 */
	public String getDescription() {
		return null;
	}
	
	/**
	 * Checks if dates are correct. must be between 1/1/20 and 12/31/29, start before end
	 * @param start start date
	 * @param end end date
	 * @throws RentalDateException if the dates are invalid
	 */
	public void checkDates(LocalDate start, LocalDate end) throws RentalDateException {
		//not yet implemented
	}

}
