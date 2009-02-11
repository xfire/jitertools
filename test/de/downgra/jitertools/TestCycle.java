package de.downgra.jitertools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestCycle {

	@Test
	public void normal() {
		Integer a[] = { 1, 2, 3, 4, 5 };
		Iterable<Integer> iterable = Arrays.asList(a);

		int counter = 0;
		int validator = 0;
		for (Integer i : JIterTools.cycle(iterable)) {
			assertEquals(validator + 1, i);
			validator = ((validator + 1) % a.length);
			if (counter++ > (a.length * 5 + 2))
				break;
		}
	}
	
	@Test
	public void empty() {
		Iterable<Integer> iterable = new ArrayList<Integer>();

		for (@SuppressWarnings("unused") final Integer i : JIterTools.cycle(iterable)) {
			fail();
		}
	}
}
