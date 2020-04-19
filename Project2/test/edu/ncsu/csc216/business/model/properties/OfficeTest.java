package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
/**
 * Tests Office class
 * @author Jacob Robinson
 *
 */
public class OfficeTest {

	/**
	 * Tests creating new Office
	 */
	@Test
	public void testConstruct() {
		Office room = new Office("10-10", 10);
		assertEquals(10, room.getCapacity());
		assertEquals(10, room.getFloor());
		assertEquals(10, room.getRoom());
		assertEquals(true, room.isInService());
		assertEquals("Office:          10-10 | 10", room.getDescription());
		assertTrue(room.equals(room));
		assertEquals(0, room.compareTo(room));
		
		RentalUnit unit2 = new Office("10-10", 10);
		assertTrue(room.equals(unit2));
		
		try {
			Office roomCap = new Office("10-10", 160);
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
		RentalUnit unit = new Office("10-10", 10);
		
		
		Client client = new Client("Jacob", "Jac");
		LocalDate start = LocalDate.of(2020, 4, 1);
		LocalDate end = LocalDate.of(2020, 4, 30);
		Lease lease = new Lease(0, client, unit, start, end, 1);
		
		Lease reserve = null;
		
		try {
			reserve = unit.reserve(client, start.plusDays(1), 1, 160);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.getMessage();
		}
		
		
		try {
			reserve = unit.reserve(client, start.plusDays(1), 1, 1);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.getMessage();
		}
		
		try {
			reserve = unit.reserve(client, start, 0, 1);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.getMessage();
		}
		
		
		
		
		try {
			reserve = unit.reserve(client, start, 1, 1);
		} catch (IllegalArgumentException | RentalOutOfServiceException | RentalDateException
				| RentalCapacityException e) {
			e.getMessage();
		}
		
		assertEquals(lease.getStart(), reserve.getStart());
		
		try {
			unit.recordExistingLease(0, client, start, end, 1);
		} catch (RentalDateException | RentalCapacityException | RentalOutOfServiceException e) {
			e.getMessage();
		}
		
		unit.removeFromServiceStarting(start);

	}
	

}
