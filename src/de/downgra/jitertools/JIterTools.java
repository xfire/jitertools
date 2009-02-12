package de.downgra.jitertools;

import java.util.ArrayList;
import java.util.Iterator;

import de.downgra.jitertools.utils.IBooleanFunctor;
import de.downgra.jitertools.utils.IFunctor;

public class JIterTools {

	public static Iterable<Integer> range(final int end) {
		return range(0, end, 1);
	}

	public static Iterable<Integer> range(final int start, final int end) {
		return range(start, end, 1);
	}

	public static Iterable<Integer> range(final int start, final int end,
			final int step) {
		assert step != 0;
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					private int _current = start;

					@Override
					public boolean hasNext() {
						if (step < 0) {
							return _current > end;
						} else {
							return _current < end;
						}
					}

					@Override
					public Integer next() {
						int tmp = _current;
						_current += step;
						return tmp;
					}

					@Override
					public void remove() {
					}
				};
			}

		};
	}

	public static <T> Iterable<T> chain(final Iterable<Iterable<T>> iterables) {
		if (!iterables.iterator().hasNext()) {
			return new ArrayList<T>();
		} else {
			return new Iterable<T>() {
				@Override
				public Iterator<T> iterator() {
					return new Iterator<T>() {
						private Iterator<Iterable<T>> _outer = iterables
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

	public static <T> Iterable<T> cycle(final Iterable<T> iterable) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private Iterator<T> _iterator = iterable.iterator();
					private ArrayList<T> _saved = new ArrayList<T>();
					private boolean _firstCycle = true;

					@Override
					public boolean hasNext() {
						return _iterator.hasNext();
					}

					@Override
					public T next() {
						if (_iterator.hasNext() && _firstCycle) {
							T item = _iterator.next();
							_saved.add(item);
							return item;
						}
						_firstCycle = false;
						if (!_iterator.hasNext()) {
							_iterator = _saved.iterator();
						}
						return _iterator.next();
					}

					@Override
					public void remove() {
					}
				};
			}
		};

	}

	public static <T> Iterable<T> dropwhile(final IBooleanFunctor<T> predicate,
			final Iterable<T> iterable) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private Iterator<T> _iterator = iterable.iterator();
					private T _first = null;
					private boolean _onFirst = false;

					private boolean consume() {
						if (_first == null) {
							while (_iterator.hasNext()) {
								_first = _iterator.next();
								if (!predicate.call(_first)) {
									_onFirst = true;
									return true;
								}
							}
						}
						return false;
					}

					@Override
					public boolean hasNext() {
						return consume() || _iterator.hasNext();
					}

					@Override
					public T next() {
						consume();
						if (_onFirst) {
							_onFirst = false;
							return _first;
						}
						return _iterator.next();
					}

					@Override
					public void remove() {
					}
				};
			}
		};
	}

	public static <T> Iterable<T> filter(final IBooleanFunctor<T> predicate,
			final Iterable<T> iterable) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private final Iterator<T> _iterator = iterable.iterator();
					private T _last = null;

					private boolean consume() {
						if (_last == null) {
							while (_iterator.hasNext()) {
								_last = _iterator.next();
								if (predicate.call(_last)) {
									return true;
								}
							}
							_last = null;
						}
						return false;
					}

					@Override
					public boolean hasNext() {
						return consume() || _iterator.hasNext();
					}

					@Override
					public T next() {
						consume();
						T tmp = _last;
						_last = null;
						return tmp;
					}

					@Override
					public void remove() {
					}
				};
			}

		};
	}

	public static <T> Iterable<T> filterfalse(
			final IBooleanFunctor<T> predicate, final Iterable<T> iterable) {
		return filter(new IBooleanFunctor<T>() {
			@Override
			public Boolean call(T object) {
				return !predicate.call(object);
			}
		}, iterable);
	}

	public static <T> Iterable<T> takewhile(final IBooleanFunctor<T> predicate,
			final Iterable<T> iterable) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private final Iterator<T> _iterator = iterable.iterator();
					private T _last = null;

					private boolean consume() {
						if (_last == null) {
							if (_iterator.hasNext()) {
								_last = _iterator.next();
								if (predicate.call(_last)) {
									return true;
								}
							}
						}
						return false;
					}

					@Override
					public boolean hasNext() {
						return consume();
					}

					@Override
					public T next() {
						consume();
						T tmp = _last;
						_last = null;
						return tmp;
					}

					@Override
					public void remove() {
					}
				};
			}
		};
	}
}
