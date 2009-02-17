package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.chain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public final class TestChain {

	private void doTest(final Integer a[], final Integer b[], final Integer c[]) {
		ArrayList<Iterable<Integer>> outer = new ArrayList<Iterable<Integer>>();
		outer.add(Arrays.asList(a));
		outer.add(Arrays.asList(b));
		outer.add(Arrays.asList(c));

		int validator = 1;
		for (final Integer i : chain(outer)) {
			assertEquals(validator++, i);
		}
		assertEquals(a.length + b.length + c.length, validator - 1);
	}

	@Test
	public void empty() {
		ArrayList<Iterable<Integer>> outer = new ArrayList<Iterable<Integer>>();
		for (@SuppressWarnings("unused")
		final Integer i : chain(outer)) {
			fail();
		}
	}

	@Test
	public void normal() {
		Integer a[] = { 1, 2, 3, 4, 5 };
		Integer b[] = { 6, 7, 8, 9, 10, 11, 12, 13 };
		Integer c[] = { 14, 15, 16 };

		doTest(a, b, c);
	}

	@Test
	public void firstEmpty() {
		Integer a[] = {};
		Integer b[] = { 1, 2, 3, 4, 5, 6, 7 };
		Integer c[] = { 8, 9, 10, 11, 12 };

		doTest(a, b, c);
	}

	@Test
	public void lastEmpty() {
		Integer a[] = { 1, 2, 3, 4, 5 };
		Integer b[] = { 6, 7, 8, 9, 10, 11, 12, 13 };
		Integer c[] = {};

		doTest(a, b, c);
	}

	@Test
	public void innerEmpty() {
		Integer a[] = { 1, 2, 3, 4, 5 };
		Integer b[] = {};
		Integer c[] = { 6, 7, 8, 9, 10 };

		doTest(a, b, c);
	}

	@Test
	public void allEmpty() {
		Integer a[] = {};
		Integer b[] = {};
		Integer c[] = {};

		doTest(a, b, c);
	}
}
