package de.downgra.jitertools.utils;

public interface IFunctor<E, T> {
	public E call(final T object);
}
