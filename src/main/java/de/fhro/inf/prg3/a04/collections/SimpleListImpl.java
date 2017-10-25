package de.fhro.inf.prg3.a04.collections;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl<T> implements SimpleList<T> {

	private ListElement head;
	private int size;
	private Class<T> clazz;
	private T defautlElement;

	public SimpleListImpl() {
		head = null;
	}

	public SimpleListImpl(Class<T> clazz){this.clazz = clazz;}

	/**
	 * Add an object to the end of the list
	 * @param item item to add
	 */
	public void add(T item){
		/* special case empty list */
		if(head == null){
			head = new ListElement(item);
		}else {
			/* any other list length */
			ListElement current = head;
			while (current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(new ListElement(item));
		}
		size++;
	}

	public void addDefault() throws InstantiationException,IllegalAccessException{
		defautlElement = clazz.newInstance();
		this.add(defautlElement);
	}

	/**
	 * @return size of the list
	 */
	public int size() {
		return size;
	}


	/**
	 * Get a new SimpleList instance with all items of this list which match the given filter
	 * @param filter SimpleFilter instance
	 * @return new SimpleList instance
	 */

	/**
	 * @inheritDoc
	 */
	@Override
	public Iterator<T> iterator() {
		return new SimpleIterator();
	}

	/**
	 * Helper class which implements the Iterator<T> interface
	 * Has to be non static because otherwise it could not access the head of the list
	 */
	private class SimpleIterator<T> implements Iterator<T> {

		private ListElement<T> current = head;

		/**
		 * @inheritDoc
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * @inheritDoc
		 */
		@Override
		public T next() {
			T tmp = current.getItem();
			current = current.getNext();
			return tmp;
		}
	}

	/**
	 * Helper class for the linked list
	 * can be static because the ListElement does not need to access the SimpleList instance
	 */
	private static class ListElement<T> {
		private T item;
		private ListElement next;

		ListElement(T item) {
			this.item = item;
			this.next = null;
		}

		/**
		 * @return get object in the element
		 */
		public T getItem() {
			return item;
		}

		/**
		 * @return successor of the ListElement - may be NULL
		 */
		public ListElement getNext() {
			return next;
		}

		/**
		 * Sets the successor of the ListElement
		 * @param next ListElement
		 */
		public void setNext(ListElement next) {
			this.next = next;
		}
	}

}
