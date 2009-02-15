package de.downgra.jitertools;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestRange {

	@Test
	public void end() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.range(9)) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }, result
				.toArray());
	}

	@Test
	public void startEnd() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.range(4, 9)) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 4, 5, 6, 7, 8 }, result.toArray());
	}

	@Test
	public void startEndStep() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.range(4, 9, 2)) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 4, 6, 8 }, result.toArray());
	}

	@Test
	public void negativeStepLowerStart() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.range(4, 9, -2)) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] {}, result.toArray());
	}

	@Test
	public void negativeStep() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.range(9, 0, -1)) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 }, result
				.toArray());
	}

	@Test
	public void negativeStep2() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Integer i : JIterTools.range(9, 0, -2)) {
			result.add(i);
		}
		assertArrayEquals(new Integer[] { 9, 7, 5, 3, 1 }, result.toArray());
	}

}
