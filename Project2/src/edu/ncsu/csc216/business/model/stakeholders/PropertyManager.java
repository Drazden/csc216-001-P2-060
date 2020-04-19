package edu.ncsu.csc216.business.model.stakeholders;

import java.time.LocalDate;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalCapacityException;
import edu.ncsu.csc216.business.model.properties.RentalDateException;
import edu.ncsu.csc216.business.model.properties.RentalOutOfServiceException;
import edu.ncsu.csc216.business.model.properties.RentalUnit;

/**
 * Class that manages the property. Has a list of clients and rooms available to lease.
 * Can add or remove clients or units, filter by type.
 * @author Jacob Robinson
 */
public class PropertyManager implements Landlord {

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
		inServiceFilter = false;
		kindFilter = null;
		Lease.resetConfirmationNumbering(0);
	}

	
	/**
	 * Adds a new client
	 * @param name name of client
	 * @param id unique id
	 * @return new client
	 * @throws DuplicateClientException if client already exists
	 */
	public Client addNewClient(String name, String id) throws DuplicateClientException{
		Client client = new Client(name, id);
		
		for (int i = 0; i < customerBase.size(); i++) {
			if (customerBase.get(i).equals(client)) {
				throw new DuplicateClientException();
			}
		}
		
		customerBase.add(client);
		return client;
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
		RentalUnit unit;
		if (kind.equals("O")) {
			unit = new Office(loc, cap);
		} else if (kind.equals("H")) {
			unit = new HotelSuite(loc, cap);
		} else {
			unit = new ConferenceRoom(loc, cap);
		}
		
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).equals(unit)) {
				throw new DuplicateRoomException();
			}
		}
		
		rooms.add(unit);
		return unit;
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
		Boolean c = false;
		int client = -1;
		for (int i = 0; i < customerBase.size(); i++) {
			if (customerBase.get(i).equals(cli)) {
				client = i;
				c = true;
			}
		}
		
		Boolean u = false;
		int room = -1;
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).equals(r)) {
				room = i;
				u = true;
			}
		}
		
		if (!c || !u) {
			throw new IllegalArgumentException();
		}
		
		Lease l = new Lease(con, cli, r, start, end, ocu);
		rooms.get(room).addLease(l);
		customerBase.get(client).addNewLease(l);
		
	}
	
	/**
	 * Cancel lease for a client
	 * @param idx index of lease
	 * @param con confirmation number of lease
	 */
	public void cancelClientsLease(int idx, int con) {
		customerBase.get(idx).cancelLeaseWithNumber(con);
	}
	
	/**
	 * Returns a unit to service
	 * @param idx index of room to return to service
	 */
	public void returnToService(int idx) {
		rooms.get(idx).returnToService();
	}
	
	/**
	 * Removes a unit from serivce
	 * @param idx index of unit
	 * @param start start date
	 * @return closed unit
	 */
	public RentalUnit removeFromService(int idx, LocalDate start) {
		rooms.get(idx).removeFromServiceStarting(start);
		return rooms.get(idx);
	}
	
	/**
	 * Permanently closes a unit
	 * @param idx index of unit
	 */
	public void closeRentalUnit(int idx) {
		rooms.remove(idx);
	}
	
	/**
	 * Creates a new lease
	 * @param r idx of rental unnit
	 * @param cli client
	 * @param start start date
	 * @param dur duration
	 * @param ocu occupants
	 * @return new lease
	 */
	public Lease createLease(int r, int cli, LocalDate start, int dur, int ocu) {
		Lease lease;
		try {
			lease = rooms.get(r).reserve(customerBase.get(cli), start, dur, ocu);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		customerBase.get(cli).addNewLease(lease);
		return lease;
	}
	
	/**
	 * returns list of clients
	 * @return string array of clients
	 */
	public String[] listClients() {
		String[] clients = new String[customerBase.size()];
		for (int i = 0; i < customerBase.size(); i++) {
			clients[i] = customerBase.get(i).toString();
		}
		return clients;
	}
	
	/**
	 * returns list of leases
	 * @param idx index of client
	 * @return string array of leases
	 */
	public String[] listClientLeases(int idx) {
		return customerBase.get(idx).listLeases();
	}
	
	/**
	 * returns list of units
	 * @return string array of units
	 */
	public String[] listRentalUnits() {
		String[] units = new String[rooms.size()];
		for (int i = 0; i < rooms.size(); i++) {
			String unit = rooms.get(i).getDescription();
			
			if (inServiceFilter && rooms.get(i).isInService()) {
				units[i] = unit;
			}
			
			if (kindFilter != null && !kindFilter.isEmpty()) {
				if (kindFilter.toUpperCase().equals("H") && unit.contains("Hotel")) {
					units[i] = unit;
				}
				
				if (kindFilter.toUpperCase().equals("C") && unit.contains("Conference")) {
					units[i] = unit;
				}
				
				if (kindFilter.toUpperCase().equals("O") && unit.contains("Office")) {
					units[i] = unit;
				}
			}
			
			if (!inServiceFilter) {
				units[i] = unit;
			}
			
		}
		return units;
	}
	
	/**
	 * returns list of leases for unit
	 * @param idx index of client
	 * @return string array of leases
	 */
	public String[] listLeasesForRentalUnit(int idx) {
		return rooms.get(idx).listLeases();
	}
	
	/**
	 * gets a unit from specifed location
	 * @param loc location of unit
	 * @return found unit
	 */
	public RentalUnit getUnitAtLocation(String loc) {
		for (int i = 0; i < rooms.size(); i++) {
			String location = rooms.get(i).getFloor() + "-" + rooms.get(i).getRoom();
			if (location.equals(loc)) {
				return rooms.get(i);
			}
		}
		
		throw new IllegalArgumentException();
	}
	
	/**
	 * Filters units by kind or availability
	 * @param kind type of unit
	 * @param avail if room is in service
	 */
	public void filterRentalUnits(String kind, boolean avail) {
		kindFilter = kind;
		inServiceFilter = avail;
	}
	
	/**
	 * Clears all data
	 */
	public void flushAllData() { 
		instance = null;
		this.rooms = null;
		this.customerBase = null;
		inServiceFilter = false;
		kindFilter = null;
		Lease.resetConfirmationNumbering(0);
	}
	
}
