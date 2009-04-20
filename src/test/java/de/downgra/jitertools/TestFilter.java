package de.downgra.jitertools;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestFilter {

    class LT implements IPredicate<Integer> {
        private final int max;

        public LT(int max) {
            this.max = max;
        }

        @Override
        public Boolean call(Integer object) {
            return object < max;
        }
    };

    Integer a[] = { 3, 5, 2, 4, 1, 5, 7, 8, 3, 2, 3 };

    @Test
    public void filter() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer i : JIterTools.filter(new LT(4), Arrays.asList(a))) {
            result.add(i);
        }
        assertArrayEquals(new Integer[] { 3, 2, 1, 3, 2, 3 }, result.toArray());
    }

    @Test
    public void filterfalse() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer i : JIterTools.filterfalse(new LT(4), Arrays.asList(a))) {
            result.add(i);
        }
        assertArrayEquals(new Integer[] { 5, 4, 5, 7, 8 }, result.toArray());
    }

    @Test
    public void empty() {
        for (@SuppressWarnings("unused")
        Integer i : JIterTools.filter(new LT(4), new ArrayList<Integer>())) {
            fail();
        }
        for (@SuppressWarnings("unused")
        Integer i : JIterTools.filterfalse(new LT(4), new ArrayList<Integer>())) {
            fail();
        }
    }
}
