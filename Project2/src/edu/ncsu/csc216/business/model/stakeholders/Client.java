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
		if (name == null || name.isBlank() || id == null || id.equals("") || id.length() < 3) {
			throw new IllegalArgumentException();
		}
		this.name = name.trim();
		this.id = id.replaceAll("\\s", "");
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
			Lease l = myLeases.get(i);
			String confNum = "" + l.getConfirmationNumber();
			confNum = ("000000" + confNum).substring(confNum.length());
			String ocu = "" + l.getNumOccupants();
			if (ocu.length() == 1) {
				ocu = " " + ocu;
			}
			String propString = new String();
			propString = l.getProperty().getClass().getSimpleName() + ":" + l.getProperty().getRoom() + l.getProperty().getFloor();
			leases[i] = confNum + " | " + l.getStart() + " to " + l.getEnd() + " | " + ocu + " | " + propString;
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
