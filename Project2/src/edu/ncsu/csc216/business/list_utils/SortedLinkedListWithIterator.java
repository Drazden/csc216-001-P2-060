package edu.ncsu.csc216.business.list_utils;

public class SortedLinkedListWithIterator<E extends Comparable<E>> implements SortedList<E> {
	
	private Node head;
	
	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(E e) {
		return false;
	}

	@Override
	public boolean add(E e) {
		return false;
	}

	@Override
	public void clear() {
		//not yet implemented
	}

	@Override
	public E get(int index) {
		return null;
	}

	@Override
	public E remove(int index) {
		return null;
	}

	@Override
	public SortedList<E> truncate(int start) {
		return null;
	}

	@Override
	public int indexOf(E e) {
		return 0;
	}

	
	private class Node<E> {
		private Cursor traveler;
	}
	
	private class Cursor {
		
	}
}
