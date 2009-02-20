package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.enumerate;
import static de.downgra.jitertools.JIterTools.list;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import de.downgra.jitertools.utils.Pair;

public class TestEnumerate {

    @Test
    public void normal() {
        assertArrayEquals(new Pair[] { Pair.create(0, 11), Pair.create(1, 22), Pair.create(2, 33), Pair.create(3, 44), Pair.create(4, 55) }, list(
                enumerate(Arrays.asList(11, 22, 33, 44, 55))).toArray());
    }

    @Test
    public void empty() {
        for (@SuppressWarnings("unused")
        Pair<Integer, Integer> pair : enumerate(new ArrayList<Integer>())) {
            fail();
        }
    }

    @Test
    public void withStartValue() {
        final int start = 42;
        assertArrayEquals(new Pair[] { Pair.create(start + 0, 11), Pair.create(start + 1, 22), Pair.create(start + 2, 33), Pair.create(start + 3, 44),
                Pair.create(start + 4, 55) }, list(enumerate(Arrays.asList(11, 22, 33, 44, 55), start)).toArray());
    }
}
