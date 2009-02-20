package de.downgra.jitertools.utils;

import java.util.Iterator;

public class IteratorFunctor<T> implements IFunctor<Iterator<T>, Iterable<T>> {
    @Override
    public Iterator<T> call(Iterable<T> object) {
        return object.iterator();
    }

}
