package de.downgra.jitertools;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import de.downgra.jitertools.utils.IFunctor;

public class TestDropwhile {
	class LT implements IFunctor<Integer> {
		private int i;

		public LT(final int i) {
			this.i = i;
		}

		@Override
		public boolean call(final Integer object) {
			return object < i;
		}
	};

	private Integer[] a = { 1, 2, 4, 8, 4, 2, 1 };

	@Test
	public void middle() {
		int validator = 8;
		for (Integer i : JIterTools.dropwhile(new LT(5), Arrays.asList(a))) {
			assertEquals(validator, i);
			validator /= 2;
		}
	}

	@Test
	public void first() {
		int counter = 0;
		for (Integer i : JIterTools.dropwhile(new LT(1), Arrays.asList(a))) {
			assertEquals(a[counter++], i);
		}
	}

	@Test
	public void last() {
		Integer a[] = { 1, 2, 3, 4, 5 };
		int validator = 5;
		int counter = 0;
		for (Integer i : JIterTools.dropwhile(new LT(validator), Arrays
				.asList(a))) {
			assertEquals(validator, i);
			counter++;
		}
		assertEquals(1, counter);
	}
}
