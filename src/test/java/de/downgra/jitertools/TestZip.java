package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.zip;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestZip {

	@SuppressWarnings("unchecked")
	@Test
	public void two() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(Arrays.asList(1, 2, 3), Arrays.asList(4,
				5, 6))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(new int[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } }, result
				.toArray());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void twoFiller() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(42, Arrays.asList(1, 2, 3), Arrays
				.asList(4, 5, 6))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(new int[][] { { 1, 4 }, { 2, 5 }, { 3, 6 } }, result
				.toArray());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void three() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(Arrays.asList(1, 2, 3), Arrays.asList(4,
				5, 6), Arrays.asList(7, 8, 9))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(
				new int[][] { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } }, result
						.toArray());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void threeFiller() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(42, Arrays.asList(1, 2, 3), Arrays
				.asList(4, 5, 6), Arrays.asList(7, 8, 9))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(
				new int[][] { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } }, result
						.toArray());

	}

	@Test
	public void empty() {
		for (@SuppressWarnings("unused")
		List<Integer> part : zip(new ArrayList<Iterable<Integer>>())) {
			fail();
		}
	}

	@Test
	public void emptyFiller() {
		for (@SuppressWarnings("unused")
		List<Integer> part : zip(42, new ArrayList<Iterable<Integer>>())) {
			fail();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void irregularTwo() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(Arrays.asList(1, 2, 3), Arrays.asList(4,
				5))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(new int[][] { { 1, 4 }, { 2, 5 } }, result.toArray());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void irregularTwoFiller() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(42, Arrays.asList(1, 2, 3), Arrays
				.asList(4, 5))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(new int[][] { { 1, 4 }, { 2, 5 }, { 3, 42 } }, result
				.toArray());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void irregularThree() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(Arrays.asList(1, 2), Arrays.asList(4, 5,
				6), Arrays.asList(7))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(new int[][] { { 1, 4, 7 } }, result.toArray());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void irregularThreeFiller() {
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		for (List<Integer> part : zip(42, Arrays.asList(1, 2), Arrays.asList(4,
				5, 6), Arrays.asList(7))) {
			result.add(part.toArray(new Integer[0]));
		}
		assertArrayEquals(new int[][] { { 1, 4, 7 }, { 2, 5, 42 },
				{ 42, 6, 42 } }, result.toArray());
	}
}
