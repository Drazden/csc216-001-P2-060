package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;
import java.time.Period;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Represents a office kind of rental unit
 * @author Jacob Robinson
 *
 */
public class Office extends RentalUnit {

	/** Max capacity for office **/
	private static final int MAX_CAPACITY = 150;
	
	/** Number of calendar rows **/
	private static final int CAL_ROWS = 13;
	
	/** Number of calendar columns **/
	private static final int CAL_COLS = 32;
	
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
		if (cap > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
		calendar = new int[CAL_ROWS][CAL_COLS];
		for (int i = 0; i < CAL_ROWS; i++) {
			for (int j = 0; j < CAL_COLS; j++) {
				calendar[i][j] = cap;
			}
			
		}
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
		if (ocu > super.getCapacity()) {
			throw new RentalCapacityException();
		}
		
		
		
		super.checkLeaseConditions(cli, start, dur, ocu);		
		RentalUnit me = this;
		LocalDate end = start.plusMonths(dur).minusDays(1);
		
		checkDates(start, end);
		
		if (getMonthsDuration(start, end) < 1) {
			throw new RentalDateException();
		}
		
		Lease lease = new Lease(cli, me, start, end, ocu);
		
		LocalDate current = start;
		while (!current.equals(end.plusDays(1))) {
			if (ocu > remainingCapacityFor(current)) {
				throw new RentalCapacityException();
			}
			current = current.plusDays(1);
		}
		
		myLeases.add(lease);
		
		current = start;
		while (!current.equals(end.plusDays(1))) {
			calendar[current.getMonthValue()][current.getDayOfMonth()] -= ocu;
			current = current.plusDays(1);
		}
		
		
		
		return lease;
	}

	/**
	 * Records an existing lease for this kind
	 * @param cli client
	 * @param start start date
	 * @param end end date
	 * @param ocu occupants
	 * @return new lease
	 * @throws RentalOutOfServiceException if unit not in service
	 * @throws RentalDateException if date is invalid
	 * @throws RentalCapacityException if capacity is breached
	 */
	@Override
	public Lease recordExistingLease(int con, Client cli, LocalDate start, LocalDate end, int ocu) throws RentalOutOfServiceException, RentalDateException, RentalCapacityException {
		Lease lease = new Lease(con, cli, this, start, end, ocu);
		Period p = Period.between(start, end);
		super.checkLeaseConditions(cli, start, p.getDays() + 1, ocu);
		checkDates(start, end);
		
		LocalDate current = start;
		while (!current.equals(end.plusDays(1))) {
			if (ocu > remainingCapacityFor(current)) {
				throw new RentalCapacityException();
			}
			current = current.plusDays(1);
		}
		
		current = start;
		while (!current.equals(end.plusDays(1))) {
			calendar[current.getMonthValue()][current.getDayOfMonth()] -= ocu;
			current = current.plusDays(1);
		}
		
		myLeases.add(lease);
		return lease;
	}
	
	/**
	 * Remaining capacity for date
	 * @param date to check
	 * @return remaining capacity
	 */
	protected int remainingCapacityFor(LocalDate date) {
		return calendar[date.getMonthValue()][date.getDayOfMonth()];
	}
	
	/**
	 * Removes this unit from service on specified date. Removes all leases after that date.
	 * Current leases will have end dates shifted up to closing date. Returns list of leases that were cancelled
	 * @param start start date
	 * @return list of cancelled leases
	 */
	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
		LocalDate d = LocalDate.of(start.getYear(), start.getMonth(), 1);
		return super.removeFromServiceStarting(d);
	}
	
	/**
	 * Gets duration in months
	 * @param start start date
	 * @param end end date
	 * @return number of months
	 */
	protected static int getMonthsDuration(LocalDate start, LocalDate end) {
		Period p = Period.between(start, end);
		return (p.getYears() * 12) + p.getMonths() + 1;
	}
	
	/**
	 * Returns information about this unit
	 * @return floor, room, capacity, availability
	 */
	public String getDescription() {
		String ret = "Office:          " + super.getDescription();
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
		
		if (start.getDayOfMonth() != 1) {
			throw new RentalDateException();
		}
			
		
		if (end.getMonthValue() == 2) {
			if (end.isLeapYear()) {
				if (end.getDayOfMonth() != 29) {
					throw new RentalDateException();
				} 
			} else {
				if (end.getDayOfMonth() != 28) {
					throw new RentalDateException();
				}
			}
		} else if (end.getDayOfMonth() != end.getMonth().maxLength()) {
			throw new RentalDateException();
		} 
		
	}

}
