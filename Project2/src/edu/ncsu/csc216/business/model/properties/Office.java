package edu.ncsu.csc216.business.model.properties;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;

public class Office extends RentalUnit{

	private static final int MAX_CAPACITY = 0;
	
	private static final int CAL_ROWS = 0;
	
	private static final int CAL_COLS = 0;
	
	private int[][] calendar;
	
	
	
	public Office(String loc, int cap) {
		super(loc, cap);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Lease reserve(Client c, LocalDate start, int d, int o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lease recordExistingLease(int co, Client c, LocalDate start, LocalDate end, int o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected int remainingCapacityFor(LocalDate start) {
		return 0;
		
	}
	

	public SortedList<Lease> removeFromServiceStarting(LocalDate start) {
		return null;
	}
	
	protected static int getMonthsDuration(LocalDate start, LocalDate end) {
		return 0;
	}
	
	public String getDescription() {
		return null;
	}
	
	public void checkDates(LocalDate start, LocalDate end) {
		//not yet implemented
	}

}
