package edu.ncsu.csc216.business.model.stakeholders;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
/**
 * Tests Client class
 * @author Jacob Robinson
 *
 */
public class ClientTest {

	/**
	 * Tests client construction
	 */
	@Test
	public void testConstruct() {
		Client client;
		
		//Tests invalid params
		try {
			client = new Client(null, null);
		} catch(IllegalArgumentException e) {
			e.getMessage();
		}
		
		try {
			client = new Client("", null);
		} catch(IllegalArgumentException e) {
			e.getMessage();
		}
		
		try {
			client = new Client("Jacob", null);
		} catch(IllegalArgumentException e) {
			e.getMessage();
		}
		
		try {
			client = new Client("Jacob", "");
		} catch(IllegalArgumentException e) {
			e.getMessage();
		}
		
		try {
			client = new Client("Jacob", "Ja");
		} catch(IllegalArgumentException e) {
			e.getMessage();
		}
		
		client = new Client("Jacob", "Jac");
		
		//also tests getters
		assertEquals("Jacob", client.getName());
		assertEquals("Jac", client.getId());
		
	}
	
	/**
	 * Tests equals method
	 */
	@Test
	public void testEquals() {
		Client client = new Client("Jacob", "Jac");
		Client client2 = new Client("Jacob", "Jac");
		Client client3 = new Client("Jack", "Jac");
		Client client4 = new Client("Jack", "id#");
		
		//Indentical so should be equal
		assertTrue(client.equals(client2));
		
		//Same id considered equal
		assertTrue(client.equals(client3));
		
		//Name should not be relevant if ids are not equal
		assertFalse(client3.equals(client4));
	}
	
	/**
	 * Tests addNewLease method
	 */
	@Test
	public void testAddNewLease() {
		Client client = new Client("Jacob", "Jac");
		Client client2 = new Client("Jack", "Jack");
		ConferenceRoom room = new ConferenceRoom("01-01", 10);
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 5, 10);
		Lease lease = new Lease(0, client, room, start, end, 1);
		
		//Adds clients lease to client
		client.addNewLease(lease);
		assertEquals(lease.toString(), client.listLeases()[0]);
		
		//Trys to add someone elses lease to client
		Lease lease2 = new Lease(0, client2, room, start, end, 1);
		try {
			client.addNewLease(lease2);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Tests cancelling lease at index
	 */
	@Test
	public void testCancelLeaseAt() {
		Client client = new Client("Jacob", "Jac");
		ConferenceRoom room = new ConferenceRoom("01-01", 10);
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 5, 10);
		Lease lease = new Lease(0, client, room, start, end, 1);
		client.addNewLease(lease);
		
		//Tests if lease is properly cancelled
		assertEquals(lease, client.cancelLeaseAt(0));
		
		//trys to cancel at invalid indexs
		try {
			client.cancelLeaseAt(-1);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		try {
			client.cancelLeaseAt(100);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}
	
	/**
	 * tests cancelling using confirmation number
	 */
	@Test
	public void testCancelLeaseWithNumber() {
		Client client = new Client("Jacob", "Jac");
		ConferenceRoom room = new ConferenceRoom("01-01", 10);
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 5, 10);
		Lease lease = new Lease(0, client, room, start, end, 1);
		client.addNewLease(lease);
		
		//Cancels the lease with 0 conf number
		assertEquals(lease, client.cancelLeaseWithNumber(0));
		
		//Trys to cancel lease with a non existant number
		try {
			client.cancelLeaseWithNumber(1);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}

}
