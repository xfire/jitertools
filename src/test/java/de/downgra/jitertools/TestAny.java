package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.any;
import static de.downgra.jitertools.JIterTools.range;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import de.downgra.jitertools.utils.IPredicate;

public class TestAny {
	class LT implements IPredicate<Integer> {
		private final int comparator;

		public LT(int comparator) {
			this.comparator = comparator;
		}

		@Override
		public Boolean call(Integer object) {
			return object < this.comparator;
		}
	};

	@Test
	public void isFalse() {
		assertFalse(any(new LT(5), range(23, 42)));
	}

	@Test
	public void isTrue() {
		assertTrue(any(new LT(10), range(23)));
		assertTrue(any(new LT(10), range(5)));
	}

	@Test
	public void empty() {
		assertFalse(any(new LT(5), new ArrayList<Integer>()));
	}
}
