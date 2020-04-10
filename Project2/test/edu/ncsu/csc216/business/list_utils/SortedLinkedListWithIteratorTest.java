package edu.ncsu.csc216.business.list_utils;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests SortedLinkedListWithIterator class
 * @author Jacob Robinson
 *
 */
public class SortedLinkedListWithIteratorTest {

	/**
	 * tests construction of list
	 */
	@Test
	public void testConstruct() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		//New list should be empty
		assertTrue(list.isEmpty());
	}
	
	/**
	 * tests size method
	 */
	@Test
	public void testSize() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		//New list should have size of 0
		assertEquals(0, list.size());
		
		list.add("String");
		assertEquals(1, list.size());
		
		
		list.add("String1");
		assertEquals(2, list.size());
		
		
		list.remove(0);
		assertEquals(1, list.size());
		
		list.remove(0);
		assertEquals(0, list.size());
		
	}
	
	/**
	 * Tests isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		//New list should be empty
		assertTrue(list.isEmpty());
		
		list.add("String");
		assertFalse(list.isEmpty());
		
		list.remove(0);
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests contains method
	 */
	@Test
	public void testContains() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		//New list should not contain anything
		assertEquals(false, list.contains("String"));
		
		list.add("String");
		
		assertEquals(true, list.contains("String"));
		
		assertEquals(false, list.contains("String1"));
		
		list.add("String1");
		
		assertEquals(true, list.contains("String1"));
	}
	
	/**
	 * Tests add method
	 */
	@Test
	public void testAdd() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		list.add("String");
		assertEquals("String", list.get(0));
		
		list.add("String1");
		assertEquals("String1", list.get(1));
		
		list.remove(0);
		list.add("String2");
		assertEquals("String2", list.get(1));
	}
	
	/**
	 * Tests clear method
	 */
	@Test
	public void testClear() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		list.add("String");
		
		list.clear();
		
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Tests get method
	 */
	@Test
	public void testGet() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		list.add("String");
		
		assertEquals("String", list.get(0));
		
		list.add("String1");
		
		assertEquals("String1", list.get(1));
	}
	
	/**
	 * Tests remove method
	 */
	@Test
	public void testRemove() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		
		list.add("String");
		
		list.add("String2");
		
		list.add("String3");
		
		assertEquals("String2", list.remove(1));
		assertEquals("String", list.get(0));
		assertEquals("String3", list.get(1));
		
		assertEquals("String", list.remove(0));
		assertEquals("String3", list.get(0));
	}
	
	//TODO truncate
	
	/**
	 * Tests indexOf method
	 */
	@Test
	public void testIndexOf() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		assertEquals(-1, list.indexOf("String"));
		
		list.add("String");
		assertEquals(0, list.indexOf("String"));
		
		assertEquals(-1, list.indexOf("String1"));
		
		list.add("String1");
		assertEquals(1, list.indexOf("String1"));
	}
	

}
