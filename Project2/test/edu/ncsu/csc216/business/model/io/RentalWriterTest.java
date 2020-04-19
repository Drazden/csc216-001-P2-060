package edu.ncsu.csc216.business.model.io;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests RentalWriter class
 * @author Jacob Robinson
 *
 */
public class RentalWriterTest {

	/**
	 * Tests rental writer
	 */
	@Test
	public void test() {
		RentalWriter writer = new RentalWriter();
		assertNotEquals(null, writer);
		
		RentalWriter.writeRentalFile("test-files/writeroutput");
		
	}

}
