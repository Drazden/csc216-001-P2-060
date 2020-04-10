package edu.ncsu.csc216.business.list_utils;

import java.util.NoSuchElementException;

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
	 * @throws IllegalArgumentException for duplicat element
	 */
	@Override
	public boolean add(E e) {
		Node<E> add = new Node<E>(e, null);
		if (head == null) {
			head = add;
			return true;
		} else if (contains(e)) {
			throw new IllegalArgumentException();
		} else if (add.value.compareTo(head.value) < 0) {
			add.next = head;
			head = add;
			return true;
		} else if (head.next == null) {
			head.next = add;
			return true;
		} else {
			Cursor cursor = new Cursor();
			Node<E> trav = head;
			Node<E> prev = null;
			while(cursor.hasNext()) {
				if (trav.next == null) {
					trav.next = add;
					return true;
				}
				
				prev = trav;
				trav = trav.next;
				
				
				if (add.value.compareTo(trav.value) < 0) {
					add.next = trav;
					if (prev != null) {
						prev.next = add;
					}
					return true;
				}
				
			}
			return false;
		}
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
	 * @throws IndexOutOfBoundsException if index is less than 0 or above size
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
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
	 * @throws IndexOutOfBoundsException if index is less than 0 or above size
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
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
	 * Returns list as string
	 * @return string representation
	 */
	@Override
	public String toString() {
		String ret = new String();
		ret = "[";
		if (head != null) {
			Node<E> trav = head;
			while(trav.next != null) {
				ret += trav.value.toString() + ", ";
				trav = trav.next;
			}
			ret += trav.value.toString();
		}
		ret += "]";
		return ret;
		
	}

	/**
	 * Truncates the list
	 * @param start where to truncate
	 * @return truncated list
	 */
	@Override
	public SortedList<E> truncate(int start) {
		
		Node<E> trav = head;
		
		for(int i = 0; i < start; i++) {
			trav = trav.next;
		}
		
		return this;
		
		
	}

	/**
	 * Gets index of element
	 * @param e element to search for
	 * @return integer index of element
	 */
	@Override
	public int indexOf(E e) {
		if (head == null) {
			return -1;
		}
		
		
		Node<E> trav = head;
		
		if (trav.value == e) {
			return 0;
		}
		
		int counter = 0;
		
		while (trav.next != null) {
			if (trav.value == e) {
				return counter;
			} else {
				trav = trav.next;
				counter++;
			}
		}
		
		if (trav.value == e) {
			return counter;
		}
		
		return -1;
	}
	
	/**
	 * Gets a new iterator
	 * @return new cursor
	 */
	public SimpleListIterator<E> iterator() {		
		Cursor cursor = new Cursor();
		return cursor;
	}

	/**
	 * Represents each node of the list
	 * @author Jacob Robinson
	 * @param <E> data type of node
	 */
	private class Node<E> {
		
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
		
		private Node<E> traveller;
		
		Cursor() {
			traveller = head;
		}
		
		/**
		 * Checks if there is a next value to go to
		 * @return true if yes, false if no
		 */
		public boolean hasNext() {
			if (traveller == null) {
				return false;
			}
			return traveller.next != null;
		}
		
		/**
		 * Goes to next value and returns it
		 * @return E next value in list
		 * @throws NoSuchElementException if there is no next value
		 */
		public E next() {
			if (hasNext()) {
				E val = traveller.value;
				traveller = traveller.next;
				return val;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
