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
	private static final int RESIZE = 0;
	
	/** List of objects maintained by the ArrayList **/
	private Object[] list;
	
	/** Current number of elements in the list **/
	private int size;
	
	/**
	 * Construcsts a new SimpleArrayList
	 */
	SimpleArrayList() {
		//not yet implemented
	}
	
	/**
	 * Constructs a new SimpleArrayList with provided size
	 * @param size to set list to
	 */
	SimpleArrayList(int size) {
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
	 * Adds element e to the list
	 * @param e element to be added
	 */
	@Override
	public void add(int pos, E e) {
		//Not yet implemented
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
	 * Gets element from list
	 * @param pos location of element to get
	 * @return element found
	 */
	@Override
	public E get(int pos) {
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

}
