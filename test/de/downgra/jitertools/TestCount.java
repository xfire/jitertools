package de.downgra.jitertools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.downgra.jitertools.JIterTools;

public class TestCount {

	@Test
	public void normal() {
		int validator = 0;
		for (final Integer i : JIterTools.count()) {
			assertEquals(validator++, i);
			if (i > 10)
				break;
		}
	}

	@Test
	public void startValue() {
		int validator = 1234;
		for (final Integer i : JIterTools.count(validator)) {
			assertEquals(validator++, i);
			if (i > 1234 + 10)
				break;
		}
	}
}
