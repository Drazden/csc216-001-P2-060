package edu.ncsu.csc216.business.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

import edu.ncsu.csc216.business.model.properties.ConferenceRoom;
import edu.ncsu.csc216.business.model.properties.HotelSuite;
import edu.ncsu.csc216.business.model.properties.Office;
import edu.ncsu.csc216.business.model.properties.RentalUnit;
import edu.ncsu.csc216.business.model.stakeholders.Client;
import edu.ncsu.csc216.business.model.stakeholders.DuplicateClientException;
import edu.ncsu.csc216.business.model.stakeholders.DuplicateRoomException;
import edu.ncsu.csc216.business.model.stakeholders.PropertyManager;

/**
 * RentalReader reads data from text files. It has one static method, readRentalData. 
 * @author Jacob Robinson
 *
 */
public class RentalReader {

	
	/**
	 * readRentalData reads data from a text file. The parameter is the name of the file to read from.
	 * If any errors occur during reading, all data is cleared and an exception is thrown.
	 * This method is static to allow calling from other classes.
	 * @param filename String containing name of file to read
	 * @throws IllegalArgumentException exception thrown if any errors occur
	 */
	public static void readRentalData(String filename) {
		Scanner s = null;
		try {
			s = new Scanner(new FileInputStream(filename));
		
		while (s.hasNextLine()) {
			String nextline = s.nextLine();
			if (nextline.isEmpty() || nextline.charAt(0) == '#') {
				break;
			}
			
			try {
				processRoom(nextline);
			} catch (DuplicateRoomException e) {
				s.close();
				throw new IllegalArgumentException();
			}
		}
		
		while (s.hasNextLine()) {
			String next = s.nextLine();
			Client client = null;
			if (next.charAt(0) != '#') {
				processLease(client, next);
			} else {
				try {
					client = processClient(next);
				} catch (DuplicateClientException e) {
					s.close();
					throw new IllegalArgumentException();
				}
			}
				
		}
		
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
		s.close();
	}

	private static void processLease(Client client, String nextLine) {
		Scanner s = new Scanner(nextLine);
		int con = Integer.parseInt(s.next());
		s.next();
		LocalDate start = LocalDate.parse(s.next());
		s.next();
		LocalDate end = LocalDate.parse(s.next());
		s.next();
		int ocu = s.nextInt();
		s.next();
		RentalUnit r = null;
		String kind = s.next();
		if (kind.equals("Conference")) {
			s.next();
			String loc = s.next();
			int cap = 1;
			r = new ConferenceRoom(loc, cap);
		} else if (kind.equals("Hotel")) {
			s.next();
			String loc = s.next();
			int cap = 1;
			r = new HotelSuite(loc, cap);
		} else {
			String loc = s.next();

			int cap = 1;
			r = new Office(loc, cap);
		}
		
		PropertyManager.getInstance().addLeaseFromFile(client, con, r, start, end, ocu);
		s.close();
	}

	private static Client processClient(String nextLine) throws DuplicateClientException {
		Scanner s = new Scanner(nextLine);
		String name = "";
		name = s.next().replaceAll("#", "");
		String id = null;
		while (s.hasNext()) {
			String a = s.next();
			if (a.charAt(0) == '(') {
				id = a;
			} else {
				name += " " + a;
			}
		}
		
		id = id.substring(1, id.length());
		
		
		s.close();
		PropertyManager.getInstance().addNewClient(name, id);
		Client client = new Client(name, id);
		return client;
	}

	private static void processRoom(String nextLine) throws DuplicateRoomException {
		Scanner s = new Scanner(nextLine);
		String kind = s.next();
		if (kind.equals("Conference")) {
			s.next();
			String loc = s.next();
			s.next();
			int cap = s.nextInt();
			s.close();
			PropertyManager.getInstance().addNewUnit("C", loc, cap);
		} else if (kind.equals("Hotel")) {
			s.next();
			String loc = s.next();
			s.next();
			int cap = s.nextInt();
			s.close();
			PropertyManager.getInstance().addNewUnit("H", loc, cap);
		} else {
			String loc = s.next();
			s.next();
			int cap = s.nextInt();
			s.close();
			PropertyManager.getInstance().addNewUnit("O", loc, cap);
		}
	}

	
	
}
