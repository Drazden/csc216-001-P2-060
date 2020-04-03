package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

public class PropertyManager {

	public static final LocalDate EARLIEST_DATE = null;
	
	public static final LocalDate LATEST_DATE = null;
	
	private String kindFilter;
	
	private boolean inServiceFilter;
	
	private SimpleArrayList<Client> customerBase;
	
	private SortedLinkedListWithIterator<RentalUnit> rooms;
	
	private static PropertyManager instance;
	
	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}
	
	PropertyManager() {
		customerBase = new SimpleArrayList<Client>();	
		rooms = new SortedLinkedListWithIterator<RentalUnit>();
	}

	
	public Client addNewClient(String name, String id) {
		return null;
	}
	
	public RentalUnit addNewUnit(String floor, String room, int cap) {
		return null;
	}
	
	public void addLeaseFromFile(Client c, int i, RentalUnit r, LocalDate start, LocalDate end, int e) {
		
	}
	
	public void cancelClientsLease(int a, int b) {
		
	}
	
	public void returnToService(int a) {
		
	}
	
	public RentalUnit removeFromService(int a, LocalDate start) {
		return null;
	}
	
	public void closeRentalUnit(int a) {
		
	}
	
	public Lease createLease(int a, int b, LocalDate start, int c, int d) {
		return null;
	}
	
	public String[] listClients() {
		return null;
	}
	
	public String[] listClientLeases(int a) {
		return null;
	}
	
	public String[] listRentalUnits() {
		return null;
	}
	
	public String[] listLeasesForRentalUnits(int a) {
		return null;
	}
	
	public RentalUnit getUnitAtLocation(String a) {
		return null;
	}
	
	public void filterRentalUnits(String a, boolean b) {
		
	}
	
	public void flushAllData() { 
		
	}
	
}
