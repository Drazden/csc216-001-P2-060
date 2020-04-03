package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Represents a hotel suite kind of rental unit
 * @author Jacob Robinson
 *
 */
public class HotelSuite extends RentalUnit {

	/** max capacity of people for this kind **/
	private static final int MAX_CAPACITY = 0;
	
	/**
	 * Creates a new hotel suite
	 * @param loc location
	 * @param cap capacity
	 * @throws IllegalArgumentException if capacity is over 2
	 */
	public HotelSuite(String loc, int cap) {
		super(loc, cap);
	}

	/**
	 * Reserves this room for a lease
	 * @param cli client
	 * @param start start date
	 * @param dur duration
	 * @param ocu occupants
	 * @return new Lease
	 * @throws RentalDateException if start/end dates are not sundays or conflict
	 */
	@Override
	public Lease reserve(Client cli, LocalDate start, int dur, int ocu) {
		return null;
	}

	/**
	 * Records an existing lease for this kind
	 * @param con confirmation number
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
	 * Removes this unit from service on specified date. Removes all leases after that date.
	 * Current leases will have end dates shifted up to closing date. Returns list of leases that were cancelled
	 * @param start start date
	 * @return list of cancelled leases
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
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
	 * Checks if dates are correct. must be between 1/1/20 and 12/31/29, start before end
	 * @param start start date
	 * @param end end date
	 * @throws RentalDateException if the dates are invalid
	 */
	public void checkDates(LocalDate start, LocalDate end) {
		//not yet implemented
	}
}
