package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.count;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCount {

	@Test
	public void normal() {
		int validator = 0;
		for (final Integer i : count()) {
			assertEquals(validator++, i);
			if (i > 10)
				break;
		}
	}

	@Test
	public void startValue() {
		int validator = 1234;
		for (final Integer i : count(validator)) {
			assertEquals(validator++, i);
			if (i > 1234 + 10)
				break;
		}
	}
}
