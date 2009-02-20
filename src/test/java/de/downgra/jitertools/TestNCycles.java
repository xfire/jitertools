package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.list;
import static de.downgra.jitertools.JIterTools.ncycles;
import static de.downgra.jitertools.JIterTools.range;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestNCycles {

    @Test
    public void normal() {
        assertArrayEquals(new Integer[] { 1, 2, 3, 1, 2, 3, 1, 2, 3 }, list(ncycles(range(1, 4), 3)).toArray(new Integer[0]));
    }

    @Test
    public void empty() {
        assertArrayEquals(new Integer[] {}, list(ncycles(new ArrayList<Integer>(), 3)).toArray(new Integer[0]));
    }

    @Test
    public void zero() {
        assertArrayEquals(new Integer[] {}, list(ncycles(range(1, 4), 0)).toArray(new Integer[0]));
    }
}
