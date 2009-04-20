package de.downgra.jitertools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JIterTools {

    private JIterTools() {
        /* don't mess with me */
    }

    /**
     * Return a iterable which generate an arithmetic progression of integers
     * from 0 to stop - 1 in steps of 1.
     * 
     * range(5) -> 0, 1, 2, 3, 4
     * 
     * @param stop stop value
     * @return iterable of integers
     */
    public static Iterable<Integer> range(final int stop) {
        return range(0, stop, 1);
    }

    /**
     * Return a iterable which generate an arithmetic progression of integers
     * from start to stop -1 in steps of 1.
     * 
     * range(7, 10) -> 7, 8, 9
     * 
     * @param start start value
     * @param stop stop value
     * @return iterable of integers
     */
    public static Iterable<Integer> range(final int start, final int stop) {
        return range(start, stop, 1);
    }

    /**
     * Return a iterable which generate an arithmetic progression of integers
     * from start to stop - 1 in steps of step.
     * 
     * step can be negative so a reversed list is returned.
     * 
     * range(4, 9, 2) -> 4, 6, 8 range(9, 0, -2) -> 9, 7, 5, 3, 1
     * 
     * @param start start value
     * @param stop stop value
     * @param step increment or decrement
     * @return list of integers
     */
    public static Iterable<Integer> range(final int start, final int stop, final int step) {
        assert step != 0;
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int _current = start;

                    @Override
                    public boolean hasNext() {
                        if (step < 0) {
                            return _current > stop;
                        } else {
                            return _current < stop;
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

    /**
     * Return the iterable where the function is applied to each element.
     * 
     * @param <E> type of the returned iterable
     * @param <T> type of the given iterable
     * @param function the function
     * @param iterable an iterable of type T
     * @return iterable of type E
     */
    public static <E, T> Iterable<E> map(final IFunctor<E, T> function, final Iterable<T> iterable) {
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    private final Iterator<T> _iterator = iterable.iterator();

                    @Override
                    public boolean hasNext() {
                        return _iterator.hasNext();
                    }

                    @Override
                    public E next() {
                        return function.call(_iterator.next());
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Convert a iterable to a List.
     * 
     * @param <T> the type
     * @param iterable of type T
     * @return List of type T
     */
    public static <T> List<T> list(final Iterable<T> iterable) {
        // any better way?
        ArrayList<T> r = new ArrayList<T>();
        for (T item : iterable) {
            r.add(item);
        }
        return r;
    }

    /**
     * Apply a function of two arguments cumulatively to the items of a sequence
     * from left to right.
     * 
     * @param <T> type
     * @param function a functor of return type T and parameter type Pair&lt;T,
     *            T&gt;
     * @param iterable an iterable of type T
     * @return the reduced value
     */
    public static <T> T reduce(final IFunctor<T, Pair<T, T>> function, final Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        T res = it.next();
        while (it.hasNext()) {
            res = function.call(Pair.create(res, it.next()));
        }
        return res;
    }

    /**
     * Apply a function of two arguments cumulatively to the items of a sequence
     * from left to right.
     * 
     * @param <T> type
     * @param function a functor of return type T and parameter type Pair&lt;T,
     *            T&gt;
     * @param iterable an iterable of type T
     * @param initial is placed before the items in iterable
     * @return the reduced value
     */
    public static <T> T reduce(final IFunctor<T, Pair<T, T>> function, final Iterable<T> iterable, final T initial) {
        T res = initial;
        for (T item : iterable) {
            res = function.call(Pair.create(res, item));
        }
        return res;
    }

    /**
     * Return an iterable which returns elements from the first iterable until
     * it is exhausted, then elements from the next iterable, until all of the
     * iterables are exhausted.
     * 
     * @param <T> type
     * @param iterables 1 to n Iterable&lt;T&gt; objects
     * @return one iterable of type T
     */
    public static <T> Iterable<T> chain(final Iterable<T>... iterables) {
        return chain(Arrays.asList(iterables));
    }

    /**
     * Return an iterable which returns elements from the first iterable until
     * it is exhausted, then elements from the next iterable, until all of the
     * iterables are exhausted.
     * 
     * @param <T> type
     * @param iterables iterable of Iterable&lt;T&gt; objects
     * @return one iterable of type T
     */
    public static <T> Iterable<T> chain(final Iterable<Iterable<T>> iterables) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Iterator<Iterable<T>> _outer = iterables.iterator();
                    private Iterator<T> _current = _outer.hasNext() ? _outer.next().iterator() : new ArrayList<T>().iterator();

                    private void consume() {
                        while (!_current.hasNext() && _outer.hasNext()) {
                            _current = _outer.next().iterator();
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        consume();
                        return _current.hasNext();
                    }

                    @Override
                    public T next() {
                        consume();
                        return _current.next();
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Return an iterable of consecutive integers starting from zero.
     * 
     * @return iterable of consecutive integers
     */
    public static Iterable<Integer> count() {
        return count(0);
    }

    /**
     * Return an iterable of consecutive integers starting from startval.
     * 
     * @param startval first value to begin counting
     * @return iterable of consecutive integers
     */
    public static Iterable<Integer> count(final int startval) {
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int _i = startval;

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

    /**
     * Return elements from the iterable until it is exhausted. Then repeat the
     * sequence indefinitely.
     * 
     * Note, this function may require significant auxiliary storage (depending
     * on the length of the iterable).
     * 
     * @param <T> type
     * @param iterable the iterable for cycling
     * @return indefinitely iterable of type T
     */
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

    /**
     * Drop items from the iterable while predicate(item) is true. Afterwards,
     * return every element until the iterable is exhausted.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable the iterable of type T
     * @return iterable of type T
     */
    public static <T> Iterable<T> dropwhile(final IPredicate<T> predicate, final Iterable<T> iterable) {
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

    /**
     * Return an iterable with those items of sequence for which predicate(item)
     * is true.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable the iterable of type T
     * @return iterable of type T
     */
    public static <T> Iterable<T> filter(final IPredicate<T> predicate, final Iterable<T> iterable) {
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

    /**
     * Return an iterable with those items of sequence for which predicate(item)
     * is false.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable the iterable of type T
     * @return iterable of type T
     */
    public static <T> Iterable<T> filterfalse(final IPredicate<T> predicate, final Iterable<T> iterable) {
        return filter(new IPredicate<T>() {
            @Override
            public Boolean call(T object) {
                return !predicate.call(object);
            }
        }, iterable);
    }

    /**
     * Return successive entries from an iterable as long as the predicate
     * evaluates to true for each entry.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable the iterable of type T
     * @return iterable of type T
     */
    public static <T> Iterable<T> takewhile(final IPredicate<T> predicate, final Iterable<T> iterable) {
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

    /**
     * Return an iterable that aggregates elements from each of the iterables.
     * Iteration continues until the shortest iterable is exhausted. All
     * trailing items of longer iterables are lost.
     * 
     * @param <T> type
     * @param iterables 1 to n Iterable&lt;T&gt; objects
     * @return iterable of lists of type T
     */
    public static <T> Iterable<List<T>> zip(final Iterable<T>... iterables) {
        return zip(Arrays.asList(iterables));
    }

    /**
     * Return an iterable that aggregates elements from each of the iterables.
     * Iteration continues until the shortest iterable is exhausted. All
     * trailing items of longer iterables are lost.
     * 
     * @param <T> type
     * @param iterables iterable of Iterable&lt;T&gt; objects
     * @return iterable of lists of type T
     */
    public static <T> Iterable<List<T>> zip(final Iterable<Iterable<T>> iterables) {
        return new Iterable<List<T>>() {
            @Override
            public Iterator<List<T>> iterator() {
                return new Iterator<List<T>>() {
                    private List<Iterator<T>> _outer = list(map(new IteratorFunctor<T>(), iterables));

                    @Override
                    public boolean hasNext() {
                        for (Iterator<T> inner : _outer) {
                            if (!inner.hasNext())
                                return false;
                        }
                        return true && _outer.size() != 0;
                    }

                    @Override
                    public List<T> next() {
                        ArrayList<T> ret = new ArrayList<T>(_outer.size());
                        for (Iterator<T> inner : _outer) {
                            ret.add(inner.next());
                        }
                        return ret;
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Return an iterable that aggregates elements from each of the iterables.
     * If the iterables are of uneven length, missing values are filled-in with
     * fillvalue. Iteration continues until the longest iterable is exhausted.
     * 
     * @param <T> type
     * @param fillvalue value to fill in if iterable is exhausted
     * @param iterables 1 to n Iterable&lt;T&gt; objects
     * @return iterable of lists of type T
     */
    public static <T> Iterable<List<T>> zip(final T fillvalue, final Iterable<T>... iterables) {
        return zip(fillvalue, Arrays.asList(iterables));
    }

    /**
     * Return an iterable that aggregates elements from each of the iterables.
     * If the iterables are of uneven length, missing values are filled-in with
     * fillvalue. Iteration continues until the longest iterable is exhausted.
     * 
     * @param <T> type
     * @param fillvalue value to fill in if iterable is exhausted
     * @param iterables iterable of Iterable&lt;T&gt; objects
     * @return iterable of lists of type T
     */
    public static <T> Iterable<List<T>> zip(final T fillvalue, final Iterable<Iterable<T>> iterables) {
        return new Iterable<List<T>>() {
            @Override
            public Iterator<List<T>> iterator() {
                return new Iterator<List<T>>() {
                    private List<Iterator<T>> _outer = list(map(new IteratorFunctor<T>(), iterables));

                    @Override
                    public boolean hasNext() {
                        for (Iterator<T> inner : _outer) {
                            if (inner.hasNext())
                                return true;
                        }
                        return false;
                    }

                    @Override
                    public List<T> next() {
                        ArrayList<T> ret = new ArrayList<T>(_outer.size());
                        for (Iterator<T> inner : _outer) {
                            if (inner.hasNext()) {
                                ret.add(inner.next());
                            } else {
                                ret.add(fillvalue);
                            }
                        }
                        return ret;
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Make an iterator that returns object over and over again. Runs
     * indefinitely.
     * 
     * @param <T> type
     * @param object the object to repeat
     * @return iterable of type T
     */
    public static <T> Iterable<T> repeat(final T object) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    @Override
                    public boolean hasNext() {
                        return true;
                    }

                    @Override
                    public T next() {
                        return object;
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Make an iterator that returns object over and over again. Runs
     * <b>times</b> times.
     * 
     * @param <T> type
     * @param object the object to repeat
     * @return iterable of type T
     */
    public static <T> Iterable<T> repeat(final T object, final int times) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private int _count = times;

                    @Override
                    public boolean hasNext() {
                        return _count > 0;
                    }

                    @Override
                    public T next() {
                        _count--;
                        return object;
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Return an iterable containing a pair which holds the count and value of
     * the original value from <b>iterable</b>.
     * 
     * @param <T> type
     * @param iterable iterable of type T
     * @return iterable of type Pair&lt;Integer, T&gt;
     */
    public static <T> Iterable<Pair<Integer, T>> enumerate(final Iterable<T> iterable) {
        return enumerate(iterable, 0);
    }

    /**
     * Return an iterable containing a pair which holds the count and value of
     * the original value from <b>iterable</b>.
     * 
     * @param <T> type
     * @param iterable iterable of type T
     * @param start enumeration start value
     * @return iterable of type Pair&lt;Integer, T&gt;
     */
    public static <T> Iterable<Pair<Integer, T>> enumerate(final Iterable<T> iterable, final int start) {
        return new Iterable<Pair<Integer, T>>() {
            @Override
            public Iterator<Pair<Integer, T>> iterator() {
                return new Iterator<Pair<Integer, T>>() {
                    private Iterator<T> _iterator = iterable.iterator();
                    private int _counter = start;

                    @Override
                    public boolean hasNext() {
                        return _iterator.hasNext();
                    }

                    @Override
                    public Pair<Integer, T> next() {
                        return Pair.create(_counter++, _iterator.next());
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Return true if predicate(x) is true for every element in iterable.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable iterable of type T
     * @return true if predicate(x) is true for every element in iterable
     */
    public static <T> boolean all(final IPredicate<T> predicate, final Iterable<T> iterable) {
        for (@SuppressWarnings("unused")
        final T item : filterfalse(predicate, iterable)) {
            return false;
        }
        return true;
    }

    /**
     * Return true if predicate(x) is true for at least one element in iterable.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable iterable of type T
     * @return true if predicate(x) is true for at least one element in iterable
     */
    public static <T> boolean any(final IPredicate<T> predicate, final Iterable<T> iterable) {
        for (@SuppressWarnings("unused")
        final T Item : filter(predicate, iterable)) {
            return true;
        }
        return false;
    }

    /**
     * Return true if predicate(x) is false for every element in iterable.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable iterable of type T
     * @return true if predicate(x) is false for every element in iterable
     */
    public static <T> boolean no(final IPredicate<T> predicate, final Iterable<T> iterable) {
        for (@SuppressWarnings("unused")
        final T item : filter(predicate, iterable)) {
            return false;
        }
        return true;
    }

    /**
     * Returns all elements in iterable and then returns padding object
     * indefinitely.
     * 
     * @param <T> type
     * @param iterable iterable of type T
     * @param padding the padding object
     * @return iterable of type T
     */
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> pad(final Iterable<T> iterable, final T padding) {
        return chain(iterable, repeat(padding));
    }

    /**
     * Count how many times the predicate is true in the iterable.
     * 
     * @param <T> type
     * @param predicate the predicate functor
     * @param iterable iterable of type T
     * @return number of items where predicate(item) is true
     */
    public static <T> int quantify(final IPredicate<T> predicate, final Iterable<T> iterable) {
        int count = 0;
        for (@SuppressWarnings("unused")
        final T Item : filter(predicate, iterable)) {
            count++;
        }
        return count;
    }

    /**
     * Returns the iterable n times.
     * 
     * @param <T> type
     * @param iterable the iterable to repeat
     * @param n repeat how many times
     * 
     * @return iterable of type T
     */
    public static <T> Iterable<T> ncycles(final Iterable<T> iterable, final int n) {
        return chain(repeat(iterable, n));
    }

    /**
     * Return an iterable that returns selected elements from <b>iterable</b>
     * which ranges from position 0 to <b>stop</b> and steps of 1.
     * 
     * slice(range(10), 5) -> 0, 1, 2, 3, 4
     * 
     * @param <T> type
     * @param iterable iterable of type T
     * @param stop the last element
     * @return iterable of type T
     */
    public static <T> Iterable<T> slice(final Iterable<T> iterable, final int stop) {
        return slice(iterable, 0, stop, 1);
    }

    /**
     * Return an iterable that returns selected elements from <b>iterable</b>
     * which ranges from position <b>start</b> to <b>stop</b> and steps of 1.
     * 
     * slice(range(10), 2, 5) -> 2, 3, 4
     * 
     * @param <T> type
     * @param iterable iterable of type T
     * @param start the first element
     * @param stop the last element
     * @return iterable of type T
     */
    public static <T> Iterable<T> slice(final Iterable<T> iterable, final int start, final int stop) {
        return slice(iterable, start, stop, 1);
    }

    /**
     * Return an iterable that returns selected elements from <b>iterable</b>
     * which ranges from position <b>start</b> to <b>stop</b> and steps of
     * <b>step</b>.
     * 
     * slice(range(10), 2, 5) -> 2, 3, 4
     * 
     * @param <T> type
     * @param iterable iterable of type T
     * @param start the first element
     * @param stop the last element
     * @return iterable of type T
     */
    public static <T> Iterable<T> slice(final Iterable<T> iterable, final int start, final int stop, final int step) {
        assert step > 0;
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    final Iterator<Integer> _counter = range(start, stop < 0 ? Integer.MAX_VALUE : stop, step).iterator();
                    final Iterator<Pair<Integer, T>> _iterator = enumerate(iterable).iterator();
                    Integer _current_counter = _counter.hasNext() ? _counter.next() : null;
                    T _last = null;

                    private final void consume() {
                        if (_last == null) {
                            while (_iterator.hasNext() && _current_counter != null) {
                                Pair<Integer, T> p = _iterator.next();
                                if (p.getFirst() == _current_counter) {
                                    _last = p.getSecond();
                                    _current_counter = _counter.hasNext() ? _counter.next() : null;
                                    return;
                                }
                            }
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        consume();
                        return _last != null;
                    }

                    @Override
                    public T next() {
                        consume();
                        T ret = _last;
                        _last = null;
                        return ret;
                    }

                    @Override
                    public void remove() {
                    }
                };
            }
        };
    }

    /**
     * Return function(0), function(1), ...
     * 
     * @param <T> type of the returned iterable
     * @param function the function
     * @return iterable of type T
     */
    public static <T> Iterable<T> tabulate(final IFunctor<T, Integer> function) {
        return tabulate(function, 0);
    }

    /**
     * Return function(count), function(count + 1), ...
     * 
     * @param <T> type of the returned iterable
     * @param function the function
     * @param start the value to begin counting
     * @return iterable of type T
     */
    public static <T> Iterable<T> tabulate(final IFunctor<T, Integer> function, final int start) {
        return map(function, count(start));
    }
}
