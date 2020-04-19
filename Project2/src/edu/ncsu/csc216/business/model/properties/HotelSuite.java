package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;
import java.time.Period;

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
	public static final int MAX_CAPACITY = 2;
	
	/**
	 * Creates a new hotel suite
	 * @param loc location
	 * @throws IllegalArgumentException if capacity is over 2
	 */
	public HotelSuite(String loc) {
		super(loc, 1);
		
	}
	
	/**
	 * Creates a new hotel suite
	 * @param loc location
	 * @param cap capacity
	 * @throws IllegalArgumentException if capacity is over 2
	 */
	public HotelSuite(String loc, int cap) {
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
	 * @throws RentalDateException if start/end dates are not sundays or conflict
	 * @throws RentalCapacityException if exceeds capacity
	 * @throws RentalOutOfServiceException if unit not in service
	 */
	@Override
	public Lease reserve(Client cli, LocalDate start, int dur, int ocu) throws RentalDateException, RentalCapacityException, RentalOutOfServiceException{
		if (ocu > super.getCapacity()) {
			throw new RentalCapacityException();
		}
		
		super.checkLeaseConditions(cli, start, dur, ocu);
		
		RentalUnit me = this;
		LocalDate end = start.plusWeeks(dur);
		checkDates(start, end);
		Lease lease = new Lease(cli, me, start, end, ocu);
		
		for (int i = 0; i < myLeases.size(); i++) {
			LocalDate nS = lease.getStart();
			LocalDate nE = lease.getEnd();
			LocalDate s = myLeases.get(i).getStart();
			LocalDate e = myLeases.get(i).getEnd();
			
			if (nS.equals(s) || s.equals(nS)) {
				throw new RentalDateException();
			}
			
			if (nE.equals(e) || e.equals(nE)) {
				throw new RentalDateException();
			}
			
//			if (nS.equals(e) || e.equals(nS)) {
//				throw new RentalDateException();
//			}
//			
//			if (nE.equals(s) || s.equals(nE)) {
//				throw new RentalDateException();
//			}
			
			if (nS.isBefore(s) && nE.isAfter(s)) {
				throw new RentalDateException();
			}
			
			if (nS.isBefore(e) && nE.isAfter(e)) {
				throw new RentalDateException();
			}
			
			if (nS.isAfter(s) && nE.isBefore(e)) {
				throw new RentalDateException();
			}
			
		}
		
		
		myLeases.add(lease);
		return lease;
	}

	/**
	 * Records an existing lease for this kind
	 * @param con confirmation number
	 * @param cli client
	 * @param start start date
	 * @param end end date
	 * @param ocu occupants
	 * @return new lease
	 * @throws RentalOutOfServiceException if unit not in service
	 * @throws RentalDateException if date is invalid
	 */
	@Override
	public Lease recordExistingLease(int con, Client cli, LocalDate start, LocalDate end, int ocu) throws RentalOutOfServiceException, RentalDateException {
		Lease lease = new Lease(con, cli, this, start, end, ocu);
		Period p = Period.between(start, end);
		super.checkLeaseConditions(cli, start, p.getDays() + 1, ocu);
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
		LocalDate sun = LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth() + 7 - start.getDayOfWeek().getValue());
		return super.removeFromServiceStarting(sun);
	}
	
	/**
	 * Returns information about this unit
	 * @return floor, room, capacity, availability
	 */
	public String getDescription() {
		String ret = "Hotel Suite: " + super.getDescription();
		return ret;
	}
	
	/**
	 * Checks if dates are correct. must be between 1/1/20 and 12/31/29, start before end
	 * @param start start date
	 * @param end end date
	 * @throws RentalDateException if the dates are invalid
	 */
	public void checkDates(LocalDate start, LocalDate end) throws RentalDateException {
		super.checkDates(start, end);
		if (start.getDayOfWeek().getValue() != 7 || end.getDayOfWeek().getValue() != 7) {
			throw new RentalDateException();
		}
	}
}
