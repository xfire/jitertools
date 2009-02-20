package de.downgra.jitertools;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import de.downgra.jitertools.utils.IPredicate;

public class TestTakewhile {
    class LT implements IPredicate<Integer> {
        private int i;

        public LT(final int i) {
            this.i = i;
        }

        @Override
        public Boolean call(final Integer object) {
            return object < i;
        }
    };

    private Integer[] a = { 1, 2, 4, 8, 4, 2, 1 };

    @Test
    public void middle() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer i : JIterTools.takewhile(new LT(5), Arrays.asList(a))) {
            result.add(i);
        }
        assertArrayEquals(new Integer[] { 1, 2, 4 }, result.toArray());
    }

    @Test
    public void first() {
        for (@SuppressWarnings("unused")
        Integer i : JIterTools.takewhile(new LT(1), Arrays.asList(a))) {
            fail();
        }
    }

    @Test
    public void last() {
        Integer a[] = { 1, 2, 3, 4, 5 };
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer i : JIterTools.takewhile(new LT(6), Arrays.asList(a))) {
            result.add(i);
        }
        assertArrayEquals(a, result.toArray());
    }
}
