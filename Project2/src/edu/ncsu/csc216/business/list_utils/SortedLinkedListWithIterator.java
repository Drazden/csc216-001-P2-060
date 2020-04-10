package edu.ncsu.csc216.business.list_utils;

/**
 * This class is a linkedlist that uses the sortedlist interface and the simplelistiterator.
 * It contains methods for manipulation of data in the list and two inner classes, node and cursor.
 * @author Jacob Robinson
 * @param <E>
 */
public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {
	
	/** First node in the list **/
	private Node<E> head;
	
	/**
	 * Constructs a new list
	 */
	public SortedLinkedListWithIterator() {
		head = null;
	}
	
	/**
	 * Gets the current number of elements in the list
	 * @return integer representing size
	 */
	@Override
	public int size() {
		int size = 0;
		
		if (head == null) {
			return size;
		}
		
		
		Node<E> trav = head;
		size++;
		
		while(trav.next != null) {
			trav = trav.next;
			size++;
		}
		return size;
	}

	/**
	 * Checks if the list is currently empty
	 * @return true if list is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return !(size() > 0);
	}

	/**
	 * Checks if the list contains a certain element e
	 * @param e element to check list for
	 * @return true if list contains e, false if it doesnt
	 */
	@Override
	public boolean contains(E e) {
		if (head == null) {
			return false;
		}
		
		Node<E> trav = head;
		
		if (trav.value == e) {
			return true;
		}
		
		while(trav.next != null) {
			if (trav.value == e) {
				return true;
			} else {
				trav = trav.next;
			}
		}
		
		if (trav.value == e) {
			return true;
		}
		
		return false;
	}

	/**
	 * Adds element e to the list
	 * @param e element to be added
	 * @return true if added, false if not
	 */
	@Override
	public boolean add(E e) {
		Node<E> newNode = new Node<E>(e, null);
		if (head == null) {
			head = newNode;
			return true;
		}
		
		Node<E> trav = head;
		while (trav.next != null) {
			trav = trav.next;
		}
		trav.next = newNode;
		return true;
		
	}

	/**
	 * Clears data from the list
	 */
	@Override
	public void clear() {
		head = null;
	}

	/**
	 * Gets element from list
	 * @param index location of element to get
	 * @return element found
	 */
	@Override
	public E get(int index) {
		Node<E> trav = head;
		
		for (int i = 0; i < index; i++) {
			trav = trav.next;
		}
		
		return trav.value;
		
	}

	/**
	 * Removes element from list
	 * @param index location of element to be removed
	 * @return element removed
	 */
	@Override
	public E remove(int index) {
		if (index == 0) {
			Node<E> ret = head;
			if(head.next != null) {
				head = head.next;
			} else {
				head = null;
			}
			return ret.value;
		}
		
		
		Node<E> trav = head;
		
		for (int i = 0; i < index - 1; i++) {
			trav = trav.next;
		}
		
		Node<E> remove = trav.next;
		
		
		if (trav.next.next == null) {
			trav.next = null;
		} else {
			trav.next = trav.next.next;
		}
		
		return remove.value;
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
		Node<E> trav = head;
		int counter = 0;
		
		while (trav.next != null) {
			if (trav.value == e) {
				return counter;
			} else {
				trav = trav.next;
				counter++;
			}
		}
		
		return -1;
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
		private Node<E> next;
		
		/**
		 * constructs a new node
		 * @param value value in node
		 * @param next next node in lsit
		 */
		Node(E value, Node<E> next) {
			traveler = new Cursor();
			this.value = value;
			this.next = next;
		}
		
	}
	
	/**
	 * cursor
	 * @author Jacob Robinson
	 *
	 */
	private class Cursor implements SimpleListIterator<E> {
		
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
		public E next() {
			return null;
		}
	}
}
