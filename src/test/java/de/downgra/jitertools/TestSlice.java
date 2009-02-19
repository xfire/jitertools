package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.list;
import static de.downgra.jitertools.JIterTools.range;
import static de.downgra.jitertools.JIterTools.slice;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestSlice {

	@Test
	public void stop() {
		assertArrayEquals(new Integer[] { 0, 1, 2, 3, 4 }, list(
				slice(range(10), 5)).toArray());
		assertArrayEquals(new Integer[] {}, list(slice(range(10), 0)).toArray());
		assertArrayEquals(new Integer[] { 3, 4, 5 },
				list(slice(range(3, 6), 3)).toArray());
	}

	@Test
	public void startStop() {
		assertArrayEquals(new Integer[] { 2, 3, 4 }, list(
				slice(range(10), 2, 5)).toArray());
		assertArrayEquals(new Integer[] {}, list(slice(range(10), 0, 0))
				.toArray());
	}

	@Test
	public void startStopStep() {
		assertArrayEquals(new Integer[] { 2, 4, 6 }, list(
				slice(range(10), 2, 8, 2)).toArray());
	}

	@Test
	public void empty() {
		assertArrayEquals(new Integer[] {}, list(
				slice(new ArrayList<Integer>(), 2)).toArray());
	}

	@Test
	public void noStop() {
		assertArrayEquals(new Integer[] { 0, 33, 66, 99 }, list(
				slice(range(100), 0, -1, 33)).toArray());
	}
}
