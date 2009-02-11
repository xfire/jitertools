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
}
