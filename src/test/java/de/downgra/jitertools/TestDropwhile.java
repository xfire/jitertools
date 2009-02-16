package de.downgra.jitertools;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import de.downgra.jitertools.utils.IPredicate;

public class TestDropwhile {
	class LT implements IPredicate<Integer> {
		private int i;

		public LT(final int i) {
			this.i = i;
		}

		@Override
		public Boolean call(final Integer object) {
			return object < i;
		}
	};

	private Integer[] a = { 1, 2, 4, 8, 4, 2, 1 };

	@Test
	public void middle() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.dropwhile(new LT(5), Arrays.asList(a))) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 8, 4, 2, 1 }, result.toArray());
	}

	@Test
	public void first() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.dropwhile(new LT(1), Arrays.asList(a))) {
			result.add(i);
		}
		assertArrayEquals(a, result.toArray());
	}

	@Test
	public void last() {
		Integer a[] = { 1, 2, 3, 4, 5 };
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.dropwhile(new LT(5), Arrays.asList(a))) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 5 }, result.toArray());
	}
}
