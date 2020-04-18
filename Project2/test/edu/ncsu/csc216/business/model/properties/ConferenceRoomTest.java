package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.list_utils.SortedLinkedListWithIterator;
import edu.ncsu.csc216.business.list_utils.SortedList;
import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
/**
 * Tests ConferenceRoom class
 * @author Jacob Robinson
 *
 */
public class ConferenceRoomTest {

	/**
	 * Tests creating new ConferenceRoom
	 */
	@Test
	public void testConstruct() {
		ConferenceRoom room = new ConferenceRoom("10-10", 10);
		assertEquals(10, room.getCapacity());
		assertEquals(10, room.getFloor());
		assertEquals(10, room.getRoom());
		assertEquals(true, room.isInService());
		assertEquals("Conference Room: 10, 10 | 10", room.getDescription());
		assertTrue(room.equals(room));
		assertEquals(0, room.compareTo(room));
		
		RentalUnit unit2 = new ConferenceRoom("10-10", 10);
		assertTrue(room.equals(unit2));
		
		try {
			ConferenceRoom roomCap = new ConferenceRoom("10-10", 30);
			roomCap.takeOutOfService();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}
	
	
	/**
	 * Tests reserving room
	 */
	@Test
	public void testReserve() {
		RentalUnit unit = new ConferenceRoom("10-10", 10);
		
		
		Client client = new Client("Jacob", "Jac");
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 4, 17);
		Lease lease = new Lease(0, client, unit, start, end, 1);
		
		Lease reserve = null;
		try {
			reserve = unit.reserve(client, start, 7, 1);
		} catch (IllegalArgumentException e) {
			fail();
		} catch (RentalOutOfServiceException e) {
			fail();
		} catch (RentalDateException e) {
			fail();
		} catch (RentalCapacityException e) {
			fail();
		}
		
		assertEquals(lease.getClient(), reserve.getClient());
		assertEquals(lease.getNumOccupants(), reserve.getNumOccupants());
		assertEquals(lease.getStart(), reserve.getStart());
		assertEquals(lease.getEnd(), reserve.getEnd());
		
		SortedList<Lease> cancel = new SortedLinkedListWithIterator<Lease>();
		cancel.add(reserve);
		
		assertEquals(cancel.get(0), unit.removeFromServiceStarting(start).get(0));
		
		try {
			unit.recordExistingLease(0, client, start, end, 10);
		} catch (RentalDateException e) {
			fail();
		} catch (RentalCapacityException e) {
			fail();
		}
	}

}
