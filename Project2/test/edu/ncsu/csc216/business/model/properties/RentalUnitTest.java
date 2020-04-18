package edu.ncsu.csc216.business.model.properties;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.contracts.Lease;
import edu.ncsu.csc216.business.model.stakeholders.Client;
/**
 * Tests RentalUnit class
 * @author Jacob Robinson
 *
 */
public class RentalUnitTest {

	/**
	 * tests constructing new rental unit
	 */
	@Test
	public void testConstruct() {
		RentalUnit unit = new ConferenceRoom("10-10", 10);
		assertEquals(10, unit.getCapacity());
		assertEquals(10, unit.getFloor());
		assertEquals(10, unit.getRoom());
		assertEquals(true, unit.isInService());
		//assertEquals("", unit.getDescription());
		assertTrue(unit.equals(unit));
		assertEquals(0, unit.compareTo(unit));
		
		RentalUnit unit2 = new ConferenceRoom("10-10", 10);
		assertTrue(unit.equals(unit2));
		
		
	}
	
	/**
	 * tests adding and manipulation of leases
	 */
	@Test
	public void testLeases() {
		RentalUnit unit = new ConferenceRoom("10-10", 10);
		Client client = new Client("Jacob", "Jac");
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 4, 17);
		Lease lease = new Lease(999999, client, unit, start, end, 1);
		
		unit.addLease(lease);
		
		String leases[] = unit.listLeases();
		assertEquals("999999 | 2020-04-10 to 2020-04-17 |  1 | Jacob (Jac)", leases[0]);
		
		assertEquals(lease, unit.cancelLeaseByNumber(999999));
		
	}
	
	/**
	 * Tests methods relating to unit being in service
	 */
	@Test
	public void testService() {
		RentalUnit unit = new ConferenceRoom("10-10", 10);
		unit.takeOutOfService();
		assertEquals(false, unit.isInService());
		
		Client client = new Client("Jacob", "Jac");
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 4, 17);
		Lease lease = new Lease(999999, client, unit, start, end, 1);
		
		try {
			unit.addLease(lease);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		LocalDate newEnd = LocalDate.of(2020, 4, 16);
		
		unit.returnToService();
		unit.addLease(lease);
		unit.removeFromServiceStarting(newEnd);
		assertEquals(newEnd, lease.getEnd());
	}

}
