package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.consume;
import static de.downgra.jitertools.JIterTools.list;
import static de.downgra.jitertools.JIterTools.range;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestConsume {

    @Test
    public void none() {
        assertArrayEquals(new Integer[] { 0, 1, 2, 3, 4 }, list(consume(range(5), 0)).toArray());
    }

    @Test
    public void all() {
        assertTrue(list(consume(range(10))).size() == 0);
        assertTrue(list(consume(range(10), 10)).size() == 0);
    }

    @Test
    public void more() {
        assertArrayEquals(new Integer[] {}, list(consume(range(10), 1000)).toArray());
    }

    @Test
    public void normal() {
        assertArrayEquals(new Integer[] { 7, 8, 9 }, list(consume(range(5, 10), 2)).toArray());
    }
}
