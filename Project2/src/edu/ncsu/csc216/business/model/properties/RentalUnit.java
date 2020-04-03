package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * 
 * @author USER
 *
 */
public abstract class RentalUnit {

	private static final int MAX_FLOOR = 0;
	
	private static final int MIN_FLOOR = 0;
	
	private static final int MAX_ROOM = 0;
	
	private static final int MIN_ROOM = 0;
	
	private boolean inService;
	
	private int floor;
	
	private int room;
	
	private int capacity;
	
	private SortedLinkedListWithIterator<Lease> myLeases;
	
	/**
	 * 
	 * @param loc
	 * @param cap
	 * @throws
	 */
	public RentalUnit(String loc, int cap) {
		//not yet implemented
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getRoom() {
		return room;
	}
	
	public int compareTo(RentalUnit o) {
		return 0;
	}
	
	public void returnToService() {
		
	}
	
	public boolean isInService() {
		return false;
	}
	
	public void takeOutService() {
		
	}
	
	//throws
	public abstract Lease reserve(Client c, LocalDate start, int d, int o);
	
	public abstract Lease recordExistingLease(int co, Client c, LocalDate start, LocalDate end, int o);
	
	public void checkDates(LocalDate start, LocalDate end) {
		
	}
	
	protected void checkLeaseConditions(Client c, LocalDate start, int d, int o) {
		
	}
	
	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
		return null;
	}
	
	protected int cutoffIndex(LocalDate start) {
		return 0;
	}
	
	public Lease cancelLeaseByNumber(int c) {
		return null;
	}
	
	public void addLease(Lease l) {
		
	}
	
	public String[] listLeases() {
		return null;
	}
	
	public String getDescription() {
		return null;
	}
	
	public int hashCode() {
		return 0;
	}
	
	public boolean equals(Object o) {
		return false;
	}
}
