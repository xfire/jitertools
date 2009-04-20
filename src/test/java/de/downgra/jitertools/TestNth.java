package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.nth;
import static de.downgra.jitertools.JIterTools.range;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestNth {

    @Test
    public void normal() {
        Iterable<Integer> i = range(10);
        assertEquals(0, nth(i, 0));
        assertEquals(1, nth(i, 1));
        assertEquals(5, nth(i, 5));
        assertEquals(9, nth(i, 9));
    }

    @Test
    public void noDefaultValue() {
        Iterable<Integer> i = range(10);
        assertEquals(null, nth(i, 42));
    }

    @Test
    public void defaultValue() {
        Iterable<Integer> i = range(10);
        assertEquals(23, nth(i, 42, 23));
        assertEquals(null, nth(i, 42, null));
    }

    @Test
    public void empty() {
        assertEquals(42, nth(range(0, 0), 23, 42));
        assertEquals(42, nth(new ArrayList<Integer>(), 23, 42));
    }
}
