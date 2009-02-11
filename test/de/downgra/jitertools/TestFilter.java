package de.downgra.jitertools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import de.downgra.jitertools.utils.IFunctor;

public class TestFilter {

	class LT implements IFunctor<Integer> {
		private final int max;

		public LT(int max) {
			this.max = max;
		}

		@Override
		public boolean call(Integer object) {
			return object < max;
		}
	};

	Integer a[] = { 3, 5, 2, 4, 1, 5, 7, 8, 3, 2, 3 };

	@Test
	public void filter() {
		int count = 0;
		for (Integer i : JIterTools.filter(new LT(4), Arrays.asList(a))) {
			assertTrue(i < 4);
			count++;
		}
		assertEquals(6, count);
	}

	@Test
	public void filterfalse() {
		int count = 0;
		for (Integer i : JIterTools.filterfalse(new LT(4), Arrays.asList(a))) {
			assertTrue(i >= 4);
			count++;
		}
		assertEquals(5, count);
	}
}
