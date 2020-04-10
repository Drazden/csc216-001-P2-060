package edu.ncsu.csc216.business.list_utils;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests SimpleArrayList class
 * @author Jacob Robinson
 *
 */
public class SimpleArrayListTest {

	
	/**
	 * Tests construction of simple arraylist using both constructos
	 */
	@Test
	public void testConstructor() {
		//Any new list should be empty
		
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		assertTrue(list.isEmpty());
		
		
		SimpleArrayList<String> listC = new SimpleArrayList<String>(12);
		assertTrue(listC.isEmpty());
		
		//Cannot be constructed with negative capacity
		SimpleArrayList<String> listNeg = null;
		try {
			listNeg = new SimpleArrayList<String>(-1);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		assertNull(listNeg);
	}
	
	/**
	 * Tests size method
	 */
	@Test
	public void testSize() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		
		//New list should have size of 0
		assertEquals(0, list.size());
		
		//Adding an element should increase size
		list.add("String");
		assertEquals(1, list.size());
	}
	
	/**
	 * Tests isempty method
	 */
	@Test
	public void testIsEmpty() {
		SimpleArrayList<String> list = new SimpleArrayList<String>();
		
		//New list should be empty
		assertTrue(list.isEmpty());
		
		//Adding an element should mean its no longer empty
		list.add("String");
		assertFalse(list.isEmpty());
		
		//Removing element should mean its empty again
		list.remove(0);
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests both add methods
	 */
	@Test
	public void testAdd() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(4);

		//Null and empty E should throw NPE
		try {
			list.add(null);
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
		try {
			list.add("");
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
		
		//Adding with no idx should at lowest open idx 
		assertTrue(list.add("String"));
		assertEquals("String", list.get(0));
		assertEquals(0, list.indexOf("String"));
		assertTrue(list.contains("String"));
		
		//Duplicate item should not be added
		try {
			assertFalse(list.add("String"));
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		//Tests for adding at index
		//Adding at negative idx should throw IOOBE
		try {
			list.add(-1, "String1");
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		
		//Null and empty E should throw NPE
		try {
			list.add(0, null);
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
		try {
			list.add(0, "");
		} catch (NullPointerException e) {
			e.getMessage();
		}
		
		//Duplicate item should not be added
		try {
			list.add(0, "String");
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		
		//Adding before first element
		list.add(0, "String1");
		assertEquals("String1", list.get(0));
		assertEquals("String", list.get(1));
		
		//Adding between
		list.add(1, "String2");
		assertEquals("String1", list.get(0));
		assertEquals("String2", list.get(1));
		assertEquals("String", list.get(2));

		//Adding after
		list.add(3, "String3");
		assertEquals("String1", list.get(0));
		assertEquals("String2", list.get(1));
		assertEquals("String", list.get(2));
		assertEquals("String3", list.get(3));
		
		//Reaching capacity
		list.add("String4");
	}
	
	/**
	 * Tests remove method
	 */
	@Test
	public void testRemove() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(12);
		
		//Should not be able to remove on empty list
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		
		list.add("String");
		//Cant remove at idx < 0 or above size
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		
		try {
			list.remove(2);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		
		
		//Adds after to check shift
		list.add("String1");
		assertEquals("String", list.remove(0));
		assertEquals("String1", list.get(0));
	}
	
	/**
	 * Tests get method
	 */
	@Test
	public void testGet() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(12);
		
		//Cannot get at invalid index
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		
		try {
			list.get(2);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
		
		list.add("String");
		assertEquals("String", list.get(0));
	}
	
	/**
	 * Tests index of method
	 */
	@Test
	public void testIndexOf() {
		SimpleArrayList<String> list = new SimpleArrayList<String>(12);

		//Returns -1 if not found
		assertEquals(-1, list.indexOf("String"));
		
		list.add("String");
		assertEquals(0, list.indexOf("String"));
	}
}
