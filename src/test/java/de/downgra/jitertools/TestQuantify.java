package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.quantify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import de.downgra.jitertools.utils.IPredicate;

public class TestQuantify {

	class EQ implements IPredicate<String> {
		private String comparator;

		public EQ(String comparator) {
			this.comparator = comparator;
		}

		@Override
		public Boolean call(String object) {
			return this.comparator.equals(object);
		}
	};

	@Test
	public void normal() {
		assertEquals(3, quantify(new EQ("x"), Arrays.asList("a", "x", "f", "1",
				"x", "j", "x")));
		assertEquals(1, quantify(new EQ("x"), Arrays.asList("x")));
		assertEquals(0, quantify(new EQ("x"), Arrays.asList("a", "f", "1", "j",
				"i")));
	}

	@Test
	public void empty() {
		assertEquals(0, quantify(new EQ("x"), new ArrayList<String>()));
	}
}
