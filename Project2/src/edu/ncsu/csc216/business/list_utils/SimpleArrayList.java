package edu.ncsu.csc216.business.list_utils;

/**
 * SimpleArrayList implements the interface SimpleList using the array data structure. 
 * It is used by the PropertyManager and Client class for keeping lists of customers and leases respectively.
 * The class contains methods for manipulating information in the list
 * @author Jacob Robinson
 * @param <E> generic element, SimpleArrayList can be instantiated to any data type
 */
public class SimpleArrayList<E> implements SimpleList<E> {
	
	/** Integer used to resize list if needed **/
	private static final int RESIZE = 12;
	
	/** List of objects maintained by the ArrayList **/
	private Object[] list;
	
	/** Current number of elements in the list **/
	private int size;
	
	/**
	 * Construcsts a new SimpleArrayList
	 */
	public SimpleArrayList() {
		list = new Object[RESIZE];
		size = 0;
		
	}
	
	/**
	 * Constructs a new SimpleArrayList with provided capacity
	 * @param capacity to set list to
	 * @throws IllegalArgumentException if capacity is less than 0
	 */
	SimpleArrayList(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		}
		
		list = new Object[capacity];
		
	}
	
	/**
	 * Gets the current number of elements in the list
	 * @return integer representing size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the list is currently empty
	 * @return true if list is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return !(size > 0);
	}

	/**
	 * Checks if the list contains a certain element e
	 * @param e element to check list for
	 * @return true if list contains e, false if it doesnt
	 */
	@Override
	public boolean contains(E e) {
		return indexOf(e) != -1;
	}

	/**
	 * Adds element e to the list
	 * @param e element to be added
	 * @return true if added, false if not
	 * @throws IllegalArgumentException if e is null or empty
	 */
	@Override
	public boolean add(E e) {
		//if (e == null || e.equals("")) {
		//	throw new IllegalArgumentException();
		//}
		
		if (contains(e)) {
			return false;
		}
		
		if (size + 1 > list.length) {
			grow();
		}
		
		list[size] = e;
		size++;
		
		return true;
	}

	/**
	 * Adds element e to the list
	 * @param idx index for element to be added
	 * @param e element to be added
	 * @throws IllegalArgumentException if e is null or empty, or if list contains e
	 * @throws IndexOutOfBoundsException if idx is less than 0
	 */
	@Override
	public void add(int idx, E e) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
		//if (e == null || e.equals("")) {
		//	throw new IllegalArgumentException();
		//}
		
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		
		if (size + 1 > list.length) {
			grow();
		}
		
		for(int i = size - 1; i >= idx; i--) {
			list[i + 1] = list[i];
		}
		
		
		list[idx] = e;
		size++;
	}

	/**
	 * Removes element from list
	 * @param idx location of element to be removed
	 * @return element removed
	 * @throws IndexOutOfBoundsException if index is less than 0 or above size or list is empty
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int idx) {
		if (size == 0 || idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		
		Object removed = list[idx];
		list[idx] = null;
		
		for (int i = idx; i < size; i++) {
			list[i] = list[i + 1];
		}
		
		size--;
		return (E) removed;
	}

	/**
	 * Gets element from list
	 * @param idx location of element to get
	 * @return element found
	 * @throws IndexOutOfBoundsException if index is less than 0 or over size
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int idx) {
		if (idx < 0 || idx > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return (E) list[idx];
	}

	/**
	 * Gets index of element
	 * @param e element to search for
	 * @return integer index of element
	 */
	@Override
	public int indexOf(E e) {
		int idx = -1;
		
		for (int i = 0; i < size; i++) {
			if (list[i] == e) {
				idx = i;
			}
		}
		
		return idx;
	}
	
	/**
	 * Grows list to lenght + resize if needed
	 */
	private void grow() {
		Object[] newList = new Object[list.length + RESIZE];
		
		for (int i = 0; i < size; i++) {
			newList[i] = list[i];
		}
		
		list = newList;
	}

}
