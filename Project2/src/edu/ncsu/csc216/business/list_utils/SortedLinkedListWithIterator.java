package edu.ncsu.csc216.business.list_utils;

/**
 * This class is a linkedlist that uses the sortedlist interface and the simplelistiterator.
 * It contains methods for manipulation of data in the list and two inner classes, node and cursor.
 * @author Jacob Robinson
 * @param <E>
 */
public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {
	
	/** First node in the list **/
	private Node head;
	
	/**
	 * Constructs a new list
	 */
	SortedLinkedListWithIterator() {
		//not yet implemented
	}
	
	/**
	 * Gets the current number of elements in the list
	 * @return integer representing size
	 */
	@Override
	public int size() {
		return 0;
	}

	/**
	 * Checks if the list is currently empty
	 * @return true if list is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Checks if the list contains a certain element e
	 * @param e element to check list for
	 * @return true if list contains e, false if it doesnt
	 */
	@Override
	public boolean contains(E e) {
		return false;
	}

	/**
	 * Adds element e to the list
	 * @param e element to be added
	 * @return true if added, false if not
	 */
	@Override
	public boolean add(E e) {
		return false;
	}

	/**
	 * Clears data from the lsit
	 */
	@Override
	public void clear() {
		//not yet implemented
	}

	/**
	 * Gets element from list
	 * @param index location of element to get
	 * @return element found
	 */
	@Override
	public E get(int index) {
		return null;
	}

	/**
	 * Removes element from list
	 * @param index location of element to be removed
	 * @return element removed
	 */
	@Override
	public E remove(int index) {
		return null;
	}

	/**
	 * Truncates two lists together
	 * @param start of list
	 * @return truncated lists
	 */
	@Override
	public SortedList<E> truncate(int start) {
		return null;
	}

	/**
	 * Gets index of element
	 * @param e element to search for
	 * @return integer index of element
	 */
	@Override
	public int indexOf(E e) {
		return 0;
	}
	
	/**
	 * Iterates the list
	 * @return iterator
	 */
	public SimpleListIterator<E> iterator() {
		return null;
	}

	/**
	 * Represents each node of the list
	 * @author Jacob Robinson
	 * @param <E> data type of node
	 */
	private class Node<E> {
		
		/** nodes instance of cursor **/
		private Cursor traveler;
		
		/** value in the node **/
		private E value;
		
		/** next node in list **/
		private Node next;
		
		/**
		 * constructs a new node
		 * @param value value in node
		 * @param next next node in lsit
		 */
		Node(E value, Node<E> next) {
			//Not yet implented
		}
		
	}
	
	/**
	 * cursor
	 * @author Jacob Robinson
	 *
	 */
	private class Cursor {
		
		/**
		 * checks if there is next value
		 * @return true if yes, false if no
		 */
		public boolean hasNext() {
			return false;
			
		}
		
		/**
		 * goes to next value
		 */
		public void next() {
			//not yet implemented
		}
	}
}
