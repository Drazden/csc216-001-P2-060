package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;
import java.time.Period;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Represents a conference room kind of rental unit
 * @author Jacob Robinson
 *
 */
public class ConferenceRoom extends RentalUnit {

	/** max capacity of people for this kind **/
	public static final int MAX_CAPACITY = 25;
	
	/** max lease duration for this kind **/
	public static final int MAX_DURATION = 7;
	
	/**
	 * Creates a new conference room
	 * @param loc location
	 * @param cap capacity
	 * @throws IllegalArgumentException if capacity is too large
	 */
	public ConferenceRoom(String loc, int cap) {
		super(loc, cap);
		if (cap > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Reserves this room for a lease
	 * @param cli client
	 * @param start start date
	 * @param dur duration
	 * @param ocu occupants
	 * @return new Lease
	 * @throws RentalCapacityException for invalid capacity
	 * @throws RentalDateException for invalid date
	 * @throws RentalOutOfServiceException if unit not in service
	 */
	@Override
	public Lease reserve(Client cli, LocalDate start, int dur, int ocu) throws RentalCapacityException, RentalDateException, RentalOutOfServiceException{
		if (ocu > super.getCapacity()) {
			throw new RentalCapacityException();
		}
		if (dur > MAX_DURATION) {
			throw new RentalDateException();
		}
		
		super.checkLeaseConditions(cli, start, dur, ocu);
		
		
		RentalUnit me = this;
		LocalDate end = start.plusDays(dur - 1);
		super.checkDates(start, end);
		Lease lease = new Lease(cli, me, start, end, ocu);
		myLeases.add(lease);
		return lease;
	}

	/**
	 * Records an existing lease for this kind
	 * @param cli client
	 * @param start start date
	 * @param end end date
	 * @param ocu occupants
	 * @return new lease
	 * @throws RentalDateException if duration over 7 days
	 * @throws RentalOutOfServiceException if unit not in service
	 */
	@Override
	public Lease recordExistingLease(int con, Client cli, LocalDate start, LocalDate end, int ocu) throws RentalDateException, RentalOutOfServiceException {
		Lease lease = new Lease(con, cli, this, start, end, ocu);
		Period p = Period.between(start, end);
		p.plusDays(1);
		super.checkLeaseConditions(cli, start, p.getDays(), ocu);
		super.checkDates(start, end);
		myLeases.add(lease);
		return lease;
	}

	/**
	 * Removes this unit from service on specified date. Removes all leases after that date.
	 * Current leases will have end dates shifted up to closing date. Returns list of leases that were cancelled
	 * @param start start date
	 * @return list of cancelled leases
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
		return super.removeFromServiceStarting(start);
	}
	
	/**
	 * Returns information about this unit
	 * @return floor, room, capacity, availability
	 */
	public String getDescription() {
		String ret = "Conference Room: " + super.getDescription();
		return ret;
	}
}
