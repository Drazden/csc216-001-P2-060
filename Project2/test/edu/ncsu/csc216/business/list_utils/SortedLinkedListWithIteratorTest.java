package edu.ncsu.csc216.business.list_utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests SortedLinkedListWithIterator class
 * 
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

		// New list should be empty
		assertTrue(list.isEmpty());
	}

	/**
	 * tests size method
	 */
	@Test
	public void testSize() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// New list should have size of 0
		assertEquals(0, list.size());

		// Adding should increase size
		list.add("A");
		assertEquals(1, list.size());

		// Removing should decrease size
		list.remove(0);
		assertEquals(0, list.size());
	}

	/**
	 * Tests isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// New list should be empty
		assertTrue(list.isEmpty());

		// Adding should signal its no longer empty
		list.add("A");
		assertFalse(list.isEmpty());

		// Removing all should return to empty
		list.remove(0);
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests contains method
	 */
	@Test
	public void testContains() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// New list should not contain anything
		assertFalse(list.contains("A"));

		// Should return true if element is in list
		list.add("A");
		list.add("B");
		assertTrue(list.contains("A"));
		assertTrue(list.contains("B"));

		// Should return false if element is not in list
		assertFalse(list.contains("C"));

	}

	/**
	 * Tests add method
	 */
	@Test
	public void testAdd() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// Empty so should go to index 0
		list.add("C");
		assertEquals("C", list.get(0));

		// A before C, A should be at index 0
		list.add("A");
		assertEquals("A", list.get(0));

		// B after A but before C, should be at index 1
		list.add("B");
		assertEquals("B", list.get(1));
		
		// D after all, should be at last index
		list.add("D");
		assertEquals("D", list.get(3));
	}

	/**
	 * Tests clear method
	 */
	@Test
	public void testClear() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		list.add("A");

		list.clear();

		// Cleared list should be empty
		assertTrue(list.isEmpty());
	}

	/**
	 * Tests get method
	 */
	@Test
	public void testGet() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// Should throw IOOBE for invalid index
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}

		try {
			list.get(1);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}

		// Should get element at index
		list.add("A");
		list.add("B");
		assertEquals("A", list.get(0));
		assertEquals("B", list.get(1));

	}

	/**
	 * Tests remove method
	 */
	@Test
	public void testRemove() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// Should throw IOOBE for invalid index
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}

		try {
			list.remove(1);
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}

		// Tests that elements are properly shifted when removed
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");

		assertEquals("A", list.remove(0));
		assertEquals("B", list.get(0));

		assertEquals("C", list.remove(1));
		assertEquals("D", list.get(1));
	}

	/**
	 * Tests to string method
	 */
	@Test
	public void testToString() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		// Empty list empty string
		assertEquals("[]", list.toString());

		list.add("A");
		list.add("B");
		assertEquals("[A, B]", list.toString());
	}

	/**
	 * Tests truncate method
	 */
	@Test
	public void testTruncate() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		assertEquals(0, list.size());
		
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		assertEquals(4, list.truncate(3).size());
		assertEquals(3, list.size());

	}

	/**
	 * Tests indexOf method
	 */
	@Test
	public void testIndexOf() {
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();

		// Should return -1 if list is empty
		assertEquals(-1, list.indexOf("A"));

		// Should return proper index
		list.add("A");
		list.add("B");
		assertEquals(0, list.indexOf("A"));
		assertEquals(1, list.indexOf("B"));

		// Returns -1 if not found
		assertEquals(-1, list.indexOf("C"));
	}

	/**
	 * Tests cursor
	 */
	@Test 
	public void testIterator() { 
		SortedLinkedListWithIterator<String> list = new SortedLinkedListWithIterator<String>();
		list.add("A"); 
		list.add("B");
		list.add("C");
		assertEquals("A", list.iterator().next());
	}
}
