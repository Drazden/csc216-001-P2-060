package edu.ncsu.csc216.business.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * RentalWriter writes data to text files. It has one static method, writeentalData. 
 * @author Jacob Robinson
 *
 */
public class RentalWriter {

	/**
	 * writeRentalData writes data to a text file. The parameter is the name of the file to write to.
	 * If any errors occur during writing, an exception is thrown.
	 * This method is static to allow calling from other classes.
	 * @param filename String containing name of file to write
	 * @throws IllegalArgumentException exception thrown if any errors occur
	 */
	public static void writeRentalFile(String filename) {
		try {
			PrintStream print = new PrintStream(new File(filename));
			
			String[] units = PropertyManager.getInstance().listRentalUnits();
			if (units != null && units.length > 0) {
				for (int i = 0; i < units.length; i++) {
				print.println(units[i]);
				}
				print.println();
			}
			
			String[] clients = PropertyManager.getInstance().listClients();
			String[] leases = null;
			
			if (clients != null && clients.length > 0) {
				for (int i = 0; i < clients.length; i++) {
					print.println(clients[i]);
					leases = PropertyManager.getInstance().listClientLeases(i);
					if (leases != null && leases.length > 0) {
						for (int j = 0; j < leases.length; j++) {
							print.println("   " + leases[i]);
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		} 
		
	}
	
}
