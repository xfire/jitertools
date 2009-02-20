package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.pad;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestPad {

    @Test
    public void normal() {
        int count = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (final Integer i : pad(Arrays.asList(1, 2, 3, 4), 42)) {
            result.add(i);
            if (++count >= 9)
                break;
        }
        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 42, 42, 42, 42, 42 }, result.toArray(new Integer[0]));
    }

    @Test
    public void empty() {
        int count = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (final Integer i : pad(new ArrayList<Integer>(), 23)) {
            result.add(i);
            if (++count >= 8)
                break;
        }
        assertArrayEquals(new Integer[] { 23, 23, 23, 23, 23, 23, 23, 23 }, result.toArray(new Integer[0]));
    }

}
