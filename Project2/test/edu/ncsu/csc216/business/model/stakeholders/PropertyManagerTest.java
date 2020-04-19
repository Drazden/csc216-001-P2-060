package edu.ncsu.csc216.business.model.stakeholders;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.RentalCapacityException;
import edu.ncsu.csc216.business.model.properties.RentalDateException;
import edu.ncsu.csc216.business.model.properties.RentalOutOfServiceException;
import edu.ncsu.csc216.business.model.properties.RentalUnit;
/**
 * Tests PropertyManager class
 * @author Jacob Robinson
 *
 */
public class PropertyManagerTest {

	/**
	 * Tests a new instance
	 */
	@Test
	public void testInstance() {
		PropertyManager mgr = PropertyManager.getInstance();
		assertEquals(0, mgr.listClients().length);
		assertEquals(0, mgr.listRentalUnits().length);
		mgr.flushAllData();
	}
	
	/**
	 * Tests adding of client
	 */
	@Test
	public void testAddClient() {
		PropertyManager mgr = PropertyManager.getInstance();
		try {
			mgr.addNewClient("Jacob", "Jac");
		} catch (DuplicateClientException e) {
			fail();
		}
		
		try {
			mgr.addNewClient("Jacob", "Jac");
			fail();
		} catch (DuplicateClientException e) {
			e.getMessage();
		}
		
		
		assertEquals(1, mgr.listClients().length);
		
		mgr.flushAllData();
		
	}
	
	/**
	 * Tests adding of unit
	 */
	@Test
	public void testAddUnit() {
		PropertyManager mgr = PropertyManager.getInstance();
		
		try {
			mgr.addNewUnit("O", "10-10", 10);
		} catch (DuplicateRoomException e) {
			fail();
		}
		
		try {
			mgr.addNewUnit("H", "10-11", 1);
		} catch (DuplicateRoomException e) {
			fail();
		}
		
		try {
			mgr.addNewUnit("C", "10-12", 10);
		} catch (DuplicateRoomException e) {
			fail();
		}
		
		try {
			mgr.addNewUnit("O", "10-10", 10);
			fail();
		} catch (DuplicateRoomException e) {
			e.getMessage();
		}
		
		assertEquals(3, mgr.listRentalUnits().length);
		
		RentalUnit h = new HotelSuite("10-11", 1);
		assertEquals(h, mgr.getUnitAtLocation("10-11"));
		
		
		mgr.flushAllData();
	}
	
	/**
	 * Tests adding new lease
	 */
	@Test
	public void testAddLease() {
		PropertyManager mgr = PropertyManager.getInstance();
		
		try {
			mgr.addNewUnit("H", "10-10", 1);
			
		} catch (DuplicateRoomException e) {
			fail();
		}	
		
		try {
			mgr.addNewClient("Jacob", "Jac");
			
		} catch (DuplicateClientException e) {
			fail();
		}
		
		RentalUnit hotel = new HotelSuite("10-10", 1);
		Client client = new Client("Jacob", "Jac");
		
		LocalDate start = LocalDate.of(2020, 4, 19);
		LocalDate end = LocalDate.of(2020, 4, 26);
		
		try {
			hotel.reserve(client, start, 1, 1);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.getMessage();
		}
		
		mgr.addLeaseFromFile(client, 0, hotel, start, end, 1);
		assertEquals(1, mgr.listClientLeases(0).length);
		
		Client badClient = new Client("John", "John");
		RentalUnit badRoom = new HotelSuite("11-11", 1);
		
		try {
			mgr.addLeaseFromFile(badClient, 0, hotel, start, end, 1);
			fail();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		try {
			mgr.addLeaseFromFile(client, 0, badRoom, start, end, 1);
			fail();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		
		mgr.createLease(0, 0, start.plusWeeks(1), 1, 1);
		assertEquals(2, mgr.listClientLeases(0).length);
		assertEquals(2, mgr.listLeasesForRentalUnit(0).length);
		
		mgr.flushAllData();
	}
	
	/**
	 * Tests cancel lease
	 */
	@Test
	public void testCancel() {
		PropertyManager mgr = PropertyManager.getInstance();
		
		try {
			mgr.addNewUnit("H", "10-10", 1);
			
		} catch (DuplicateRoomException e) {
			fail();
		}	
		
		try {
			mgr.addNewClient("Jacob", "Jac");
			
		} catch (DuplicateClientException e) {
			fail();
		}
		
		RentalUnit hotel = new HotelSuite("10-10", 1);
		Client client = new Client("Jacob", "Jac");
		
		LocalDate start = LocalDate.of(2020, 4, 19);
		LocalDate end = LocalDate.of(2020, 4, 26);
		
		try {
			hotel.reserve(client, start, 1, 1);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.getMessage();
		}
		
		mgr.addLeaseFromFile(client, 0, hotel, start, end, 1);
		assertEquals(1, mgr.listClientLeases(0).length);

		mgr.cancelClientsLease(0, 0);
		assertEquals(0, mgr.listClientLeases(0).length);

		
		mgr.flushAllData();
	}
	
	/**
	 * Tests list, service, and filter methods
	 */
	@Test
	public void testListAndFilter() {
		PropertyManager mgr = PropertyManager.getInstance();
		RentalUnit h = new HotelSuite("10-10", 1);
		
		try {
			mgr.addNewUnit("H", "10-10", 1);
		} catch (DuplicateRoomException e) {
			fail();
		}
		
		LocalDate start = LocalDate.of(2020, 4, 20);
		assertEquals(h, mgr.removeFromService(0, start));
		
	
		
		mgr.filterRentalUnits("H", false);
		assertEquals(1, mgr.listRentalUnits().length);
		
		mgr.returnToService(0);
		
		mgr.flushAllData();
	}
}
