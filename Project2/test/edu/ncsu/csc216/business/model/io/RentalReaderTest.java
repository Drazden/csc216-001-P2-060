package edu.ncsu.csc216.business.model.io;

import static org.junit.Assert.*;


import org.junit.Test;
/**
 * Tests RentalReader class
 * @author Jacob Robinson
 *
 */
public class RentalReaderTest {

	/**
	 * Tests rental reader
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
		
		try {
			RentalReader.readRentalData("test-files/empty.txt");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		
		try {
			RentalReader.readRentalData("test-files/clientsonly.txt");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		try {
			RentalReader.readRentalData("test-files/unitsonly.txt");
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		
		
		
	}

}
