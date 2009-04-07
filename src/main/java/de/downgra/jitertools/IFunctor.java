package de.downgra.jitertools;

public interface IFunctor<E, T> {
    public E call(final T object);
}
