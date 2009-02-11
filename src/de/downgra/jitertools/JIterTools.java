package de.downgra.jitertools;

import java.util.ArrayList;
import java.util.Iterator;

public class JIterTools {

	public static <T> Iterable<T> chain(final Iterable<Iterable<T>> iterators) {
		if (!iterators.iterator().hasNext()) {
			return new ArrayList<T>();
		} else {
			return new Iterable<T>() {
				@Override
				public Iterator<T> iterator() {
					return new Iterator<T>() {
						private Iterator<Iterable<T>> _outer = iterators
								.iterator();
						private Iterator<T> _current = _outer.next().iterator();

						@Override
						public boolean hasNext() {
							if (!_current.hasNext() && _outer.hasNext()) {
								_current = _outer.next().iterator();
								return _current.hasNext();
							}
							return _current.hasNext();
						}

						@Override
						public T next() {
							if (!_current.hasNext() && _outer.hasNext()) {
								_current = _outer.next().iterator();
								return _current.next();
							}
							return _current.next();
						}

						@Override
						public void remove() {
						}
					};
				}
			};
		}
	}
	
	public static Iterable<Integer> count() {
		return count(0);
	}
	public static Iterable<Integer> count(final int n) {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					private int _i = n;
					
					@Override
					public boolean hasNext() {
						return true;
					}

					@Override
					public Integer next() {
						return _i++;
					}

					@Override
					public void remove() {
					}
				};
			}
		};
	}
}
