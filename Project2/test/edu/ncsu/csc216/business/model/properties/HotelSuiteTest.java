package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
/**
 * Tests HotelSuite class
 * @author Jacob Robinson
 *
 */
public class HotelSuiteTest {

	/**
	 * Tests creating new HotelSuite
	 */
	@Test
	public void testConstruct() {	
		HotelSuite room = new HotelSuite("10-10", 2);
		assertEquals(2, room.getCapacity());
		assertEquals(10, room.getFloor());
		assertEquals(10, room.getRoom());
		assertEquals(true, room.isInService());
		assertEquals("Hotel Suite:     10-10 | 2", room.getDescription());
		assertTrue(room.equals(room));
		assertEquals(0, room.compareTo(room));
		
		RentalUnit unit2 = new HotelSuite("10-10", 2);
		assertTrue(room.equals(unit2));
		
		try {
			HotelSuite roomCap = new HotelSuite("10-10", 10);
			roomCap.takeOutOfService();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		HotelSuite suite = new HotelSuite("11-11");
		assertEquals(1, suite.getCapacity());
	}

	
	
	/**
	 * Tests reserving room
	 */
	@Test
	public void testReserve() {
		RentalUnit unit = new HotelSuite("10-10", 2);
		
		
		Client client = new Client("Jacob", "Jac");
		LocalDate start = LocalDate.of(2020, 4, 19);
		LocalDate end = LocalDate.of(2020, 4, 26);
		Lease lease = new Lease(0, client, unit, start, end, 1);
		
		Lease reserve = null;
		try {
			reserve = unit.reserve(client, start, 1, 1);
		} catch (IllegalArgumentException e) {
			fail();
		} catch (RentalOutOfServiceException e) {
			fail();
		} catch (RentalDateException e) {
			fail();
		} catch (RentalCapacityException e) {
			fail();
		}
		
		try {
			unit.reserve(client, start, 1, 1);
		} catch (IllegalArgumentException e1) {
			fail();
		} catch (RentalOutOfServiceException e1) {
			fail();
		} catch (RentalDateException e1) {
			e1.getMessage();
		} catch (RentalCapacityException e1) {
			fail();
		}
		
		try {
			unit.reserve(client, start, 6, 1);
		} catch (IllegalArgumentException e1) {
			fail();
		} catch (RentalOutOfServiceException e1) {
			fail();
		} catch (RentalDateException e1) {
			e1.getMessage();
		} catch (RentalCapacityException e1) {
			fail();
		}
		
		try {
			unit.reserve(client, start.plusDays(1), 6, 1);
		} catch (IllegalArgumentException e1) {
			fail();
		} catch (RentalOutOfServiceException e1) {
			fail();
		} catch (RentalDateException e1) {
			e1.getMessage();
		} catch (RentalCapacityException e1) {
			fail();
		}
		
		try {
			unit.reserve(client, start.minusDays(1), 6, 1);
		} catch (IllegalArgumentException e1) {
			fail();
		} catch (RentalOutOfServiceException e1) {
			fail();
		} catch (RentalDateException e1) {
			e1.getMessage();
		} catch (RentalCapacityException e1) {
			fail();
		}
		
		
		try {
			unit.reserve(client, start.minusWeeks(1), 2, 1);
		} catch (IllegalArgumentException e1) {
			fail();
		} catch (RentalOutOfServiceException e1) {
			fail();
		} catch (RentalDateException e1) {
			e1.getMessage();
		} catch (RentalCapacityException e1) {
			fail();
		}
		
		
		assertEquals(lease.getClient(), reserve.getClient());
		assertEquals(lease.getNumOccupants(), reserve.getNumOccupants());
		assertEquals(lease.getStart(), reserve.getStart());
		assertEquals(lease.getEnd(), reserve.getEnd());
		
		SortedList<Lease> cancel = new SortedLinkedListWithIterator<Lease>();
		cancel.add(reserve);
		
		assertEquals(cancel.get(0), unit.removeFromServiceStarting(start).get(0));
		
		unit.returnToService();
		
		try {
			unit.recordExistingLease(0, client, start, end, 2); 
		} catch (RentalDateException e) {
			fail();
		} catch (RentalCapacityException e) {
			fail();
		} catch (RentalOutOfServiceException e) {
			fail();
		}

		try {
			unit.recordExistingLease(0, client, start, end, 2); 
		} catch (RentalDateException e) {
			e.getMessage();
		} catch (RentalCapacityException e) {
			fail();
		} catch (RentalOutOfServiceException e) {
			fail();
		}
		
		try {
			unit.recordExistingLease(0, client, start.minusWeeks(1), end, 2); 
		} catch (RentalDateException e) {
			e.getMessage();
		} catch (RentalCapacityException e) {
			fail();
		} catch (RentalOutOfServiceException e) {
			fail();
		}
	}
}
