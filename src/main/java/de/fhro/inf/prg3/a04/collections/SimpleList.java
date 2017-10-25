package de.fhro.inf.prg3.a04.collections;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable<T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	default SimpleList<T> filter(SimpleFilter<T> filter){
		SimpleList result = new SimpleListImpl();
		for(T o : this){
			if(filter.include(o)){
				result.add(o);
			}
		}
		return result;
	}

	void addDefault () throws InstantiationException,IllegalAccessException;

	default <R> SimpleList<R> map(Function<T,R> transform){
		SimpleList<R> result = new SimpleListImpl<>();
		for (T item : this){
			result.add(transform.apply(item));
		}
		return result;
	}

}
