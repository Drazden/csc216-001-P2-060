package edu.ncsu.csc216.business.model.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;
/**
 * Tests RentalReader class
 * @author Jacob Robinson
 *
 */
public class RentalReaderTest {

	/**
	 * Default test
	 */
	@Test
	public void testRead() {
		RentalReader reader = new RentalReader();
		assertNotEquals(null, reader);
		try {
			RentalReader.readRentalData("test-files/sample.txt");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
