package edu.ncsu.csc216.business.model.stakeholders;

import edu.ncsu.csc216.business.list_utils.SimpleArrayList;
import edu.ncsu.csc216.business.model.contracts.Lease;

/**
 * The client class represents a client of the property. They have a name and unique id. 
 * They also have a list of current leases.
 * @author Jacob Robinson
 *
 */
public class Client {

	/** name of client **/
	private String name;
	
	/** unique client id **/
	private String id;
	
	/** clients current leases as a lsit **/
	private SimpleArrayList<Lease> myLeases;
	
	/**
	 * Constructs a new client
	 * @param name string of clients name 
	 * @param id string of clients id
	 * @throws IllegalArgumentException if either parameter is invalid
	 */
	public Client(String name, String id) {
		if (name == null || name.equals("") || id == null || id.equals("") || id.length() < 3) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.id = id;
		myLeases = new SimpleArrayList<Lease>();
	}
	
	/**
	 * Getter for clients name
	 * @return name string
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for clients id
	 * @return id string
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Hashs clients data using id
	 * @return hashed int
	 */
	public int hashCode() {
		return id.hashCode();
	}
	
	/**
	 * Checks if one client is equal to another
	 * @param o client to check against this client
	 * @return true if equal, false if not
	 */
	public boolean equals(Object o) {
		return ((Client) o).getId().equals(this.id);
	}
	
	/**
	 * Adds a lease to this clients list of leases
	 * @param lease to be added
	 * @throws IllegalArgumentException if lease is not this clients lease
	 */
	public void addNewLease(Lease lease) {
		if (lease.getClient() != this) {
			throw new IllegalArgumentException();
		}
		myLeases.add(lease);
	}
	
	/**
	 * Returns this clients list of leases as a string array
	 * @return this clients leases list
	 */
	public String[] listLeases() {
		String[] leases = new String[myLeases.size()];
		for (int i = 0; i < myLeases.size(); i++) {
			leases[i] = myLeases.get(i).toString();
		}
		return leases;
	}
	
	/**
	 * Cancels lease at index 
	 * @param idx index to cancel at
	 * @return cancelled lease
	 * @throws IllegalArgumentException if index is invalid
	 */
	public Lease cancelLeaseAt(int idx) {
		if (idx < 0 || idx >= myLeases.size()) {
			throw new IllegalArgumentException();
		}
		Lease cancel = myLeases.get(idx);
		myLeases.remove(idx);
		return cancel;
	}
	
	/**
	 * Cancels lease using confirmation number
	 * @param c confirmation number of lease
	 * @return cancelled lease
	 * @throws IllegalArgumentException if no lease with number found
	 */
	public Lease cancelLeaseWithNumber(int c) {
		Lease cancel = null;
		for (int i = 0; i < myLeases.size(); i++) {
			if (myLeases.get(i).getConfirmationNumber() == c) {
				cancel = myLeases.get(i);
				myLeases.remove(i);
			}
		}
		if (cancel == null) {
			throw new IllegalArgumentException();
		} else {
			return cancel;
		}
	}
}
