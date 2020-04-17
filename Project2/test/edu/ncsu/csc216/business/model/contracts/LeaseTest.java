package edu.ncsu.csc216.business.model.contracts;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;

/**
 * Tests Lease class
 * @author Jacob Robinson
 *
 */
public class LeaseTest {

	/**
	 * Tests construction of leases, getters, comparison
	 */
	@Test
	public void testConstruct() {
		Client client = new Client("Jacob", "Jac");
		RentalUnit unit = new ConferenceRoom("10-10", 10);
		LocalDate start = LocalDate.of(2020, 4, 10);
		LocalDate end = LocalDate.of(2020, 4, 17);
		
		Lease lease = new Lease(0, client, unit, start, end, 1);
		
		HotelSuite suite = new HotelSuite("10-10", 1);
		
		Lease leaseS = new Lease(1, client, suite, start, end, 1);
		String[] suiteData = leaseS.leaseData();
		assertEquals("Hotel Suite:10-10", suiteData[3]);
		
		assertEquals(0, lease.getConfirmationNumber());
		assertEquals(client, lease.getClient());
		assertEquals(unit, lease.getProperty());
		assertEquals(start, lease.getStart());
		assertEquals(end, lease.getEnd());
		assertEquals(1, lease.getNumOccupants());
		
		String[] data = lease.leaseData();
		assertEquals("000000", data[0]);
		
		Lease lease2 = new Lease(client, unit, start, end, 1);
		assertEquals(2, lease2.getConfirmationNumber());
		
		LocalDate endNew = LocalDate.of(2020, 4, 16);
		lease.setEndDateEarlier(endNew);
		assertEquals(endNew, lease.getEnd());
		
		
		assertEquals(-1, lease.compareTo(lease2));
		assertEquals(-1, lease.compareTo(leaseS));
	}

}
