package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * Class that manages the property. Has a list of clients and rooms available to lease.
 * Can add or remove clients or units, filter by type.
 * @author Jacob Robinson
 */
public class PropertyManager {

	/** Earliest date for a lease **/
	public static final LocalDate EARLIEST_DATE = null;
	
	/** Latest date for a lease **/
	public static final LocalDate LATEST_DATE = null;
	
	/** Filters by kind of unit **/
	private String kindFilter;
	
	/** Filters by unit availability **/
	private boolean inServiceFilter;
	
	/** Array list of clients **/
	private SimpleArrayList<Client> customerBase;
	
	/** LinkedList of rooms **/
	private SortedLinkedListWithIterator<RentalUnit> rooms;
	
	/** Singleton instance of manager **/
	private static PropertyManager instance;
	
	/**
	 * Gets the instance
	 * @return instance
	 */
	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}
	
	/**
	 * Constructs a new manager
	 * customerBase and rooms are constructed as empty lists
	 */
	PropertyManager() {
		customerBase = new SimpleArrayList<Client>();	
		rooms = new SortedLinkedListWithIterator<RentalUnit>();
	}

	
	/**
	 * Adds a new client
	 * @param name name of client
	 * @param id unique id
	 * @return new client
	 * @throws DuplicateClientException if client already exists
	 */
	public Client addNewClient(String name, String id) throws DuplicateClientException{
		return null;
	}
	
	/**
	 * Adds a new unit
	 * @param kind type of unit
	 * @param loc location of unit
	 * @param cap capacity
	 * @return new unit
	 * @throws DuplicateRoomException if unit already exists
	 */
	public RentalUnit addNewUnit(String kind, String loc, int cap) throws DuplicateRoomException{
		return null;
	}
	
	/**
	 * Adds a lease from file 
	 * @param cli client
	 * @param con confirmation number
	 * @param r rentalunit
	 * @param start start date
	 * @param end end date
	 * @param ocu occupants
	 * @throws IllegalArgumentException for any errors
	 */
	public void addLeaseFromFile(Client cli, int con, RentalUnit r, LocalDate start, LocalDate end, int ocu) {
		//not yet implemented
	}
	
	/**
	 * Cancel lease for a client
	 * @param idx index of lease
	 * @param con confirmation number of lease
	 */
	public void cancelClientsLease(int idx, int con) {
		//not yet implemented
	}
	
	/**
	 * Returns a unit to service
	 * @param loc location of unit
	 */
	public void returnToService(int loc) {
		//not yet implemented
	}
	
	/**
	 * Removes a unit from serivce
	 * @param loc location of unit
	 * @param start start date
	 * @return closed unit
	 */
	public RentalUnit removeFromService(int loc, LocalDate start) {
		return null;
	}
	
	/**
	 * Permanently closes a unit
	 * @param loc location of unit
	 */
	public void closeRentalUnit(int loc) {
		//not yet implemented
	}
	
	/**
	 * Creates a new lease
	 * @param con confirmation number
	 * @param cli client
	 * @param start start date
	 * @param dur duration
	 * @param ocu occupants
	 * @return new lease
	 */
	public Lease createLease(int con, int cli, LocalDate start, int dur, int ocu) {
		return null;
	}
	
	/**
	 * returns list of clients
	 * @return string array of clients
	 */
	public String[] listClients() {
		return null;
	}
	
	/**
	 * returns list of leases
	 * @param idx index of client
	 * @return string array of leases
	 */
	public String[] listClientLeases(int idx) {
		return null;
	}
	
	/**
	 * returns list of units
	 * @return string array of units
	 */
	public String[] listRentalUnits() {
		return null;
	}
	
	/**
	 * returns list of leases for unit
	 * @param idx index of client
	 * @return string array of leases
	 */
	public String[] listLeasesForRentalUnit(int idx) {
		return null;
	}
	
	/**
	 * gets a unit from specifed location
	 * @param loc location of unit
	 * @return found unit
	 */
	public RentalUnit getUnitAtLocation(String loc) {
		return null;
	}
	
	/**
	 * Filters units by kind or availability
	 * @param kind type of unit
	 * @param avail if room is in service
	 */
	public void filterRentalUnits(String kind, boolean avail) {
		//not yet implemented
	}
	
	/**
	 * Clears all data
	 */
	public void flushAllData() { 
		//not yet implemented
	}
	
}
