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
		//not yet implemented
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
	 * Hashs clients data
	 * @return hashed int
	 */
	public int hashCode() {
		return 0;
	}
	
	/**
	 * Checks if one client is equal to another
	 * @param o client to check against this client
	 * @return true if equal, false if not
	 */
	public boolean equals(Object o) {
		return false;
	}
	
	/**
	 * Adds a lease to this clients list of leases
	 * @param l lease to be added
	 * @throws IllegalArgumentException if lease is not this clients lease
	 */
	public void addNewLease(Lease l) {
		//not yet implemented
	}
	
	/**
	 * Returns this clients list of leases as a string array
	 * @return this clients leases list
	 */
	public String[] listLeases() {
		return null;
	}
	
	/**
	 * Cancels lease at index 
	 * @param idx index to cancel at
	 * @return cancelled lease
	 * @throws IllegalArgumentException if index is invalid
	 */
	public Lease cancelLeaseAt(int idx) {
		return null;
	}
	
	/**
	 * Cancels lease using confirmation number
	 * @param c confirmation number of lease
	 * @return cancelled lease
	 * @throws IllegalArgumentException if no lease with number found
	 */
	public Lease cancelLeaseWithNumber(int c) {
		return null;
	}
}
