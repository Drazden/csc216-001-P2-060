package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
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
		if (cap > 25) {
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
	 */
	@Override
	public Lease reserve(Client cli, LocalDate start, int dur, int ocu) throws RentalCapacityException, RentalDateException{
		if (ocu > MAX_CAPACITY) {
			throw new RentalCapacityException();
		}
		if (dur > MAX_DURATION) {
			throw new RentalDateException();
		}
		RentalUnit me = this;
		LocalDate end = start.plusDays(dur);
		Lease lease = new Lease(cli, me, start, end, ocu);
		return lease;
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
	 * Removes this unit from service on specified date. Removes all leases after that date.
	 * Current leases will have end dates shifted up to closing date. Returns list of leases that were cancelled
	 * @param start start date
	 * @return list of cancelled leases
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
		SortedLinkedListWithIterator<Lease> cancel = new SortedLinkedListWithIterator<Lease>();
		for (int i = 0; i < myLeases.size(); i++) {
			if (myLeases.get(i).getStart().isEqual(start) || myLeases.get(i).getStart().isAfter(start)) {
				cancel.add(myLeases.get(i));
				myLeases.remove(i);
			} else if (myLeases.get(i).getEnd().isAfter(start)) {
				myLeases.get(i).setEndDateEarlier(start);
			}
		}
		return cancel;
	}
	
	/**
	 * Returns information about this unit
	 * @return floor, room, capacity, availability
	 */
	public String getDescription() {
		return null;
	}
}
