package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.range;
import static de.downgra.jitertools.JIterTools.reduce;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class TestReduce {

    class Sum implements IFunctor<Integer, Pair<Integer, Integer>> {
        @Override
        public Integer call(Pair<Integer, Integer> object) {
            return object.getFirst() + object.getSecond();
        }
    };

    class Mul implements IFunctor<Integer, Pair<Integer, Integer>> {
        @Override
        public Integer call(Pair<Integer, Integer> object) {
            return object.getFirst() * object.getSecond();
        }
    };

    @Test
    public void withoutInital() {
        assertEquals(4950, reduce(new Sum(), range(100)));
        assertEquals(10 + 10 + 10 + 10 + 10, reduce(new Sum(), Arrays.asList(new Integer[] { 10, 10, 10, 10, 10 })));
        assertEquals(1 * 2 * 3 * 4 * 5 * 6, reduce(new Mul(), range(1, 7)));
        assertEquals(5 * 5 * 5 * 5 * 5, reduce(new Mul(), Arrays.asList(new Integer[] { 5, 5, 5, 5, 5 })));
    }

    @Test
    public void withInital() {
        assertEquals(4992, reduce(new Sum(), range(100), 42));
        assertEquals(10 + 10 + 10 + 10 + 10 + 23, reduce(new Sum(), Arrays.asList(new Integer[] { 10, 10, 10, 10, 10 }), 23));
        assertEquals(1 * 2 * 3 * 4 * 5 * 6 * 42, reduce(new Mul(), range(1, 7), 42));
        assertEquals(5 * 5 * 5 * 5 * 5 * 23, reduce(new Mul(), Arrays.asList(new Integer[] { 5, 5, 5, 5, 5 }), 23));
    }

    @Test
    public void empty() {
        assertEquals(42, reduce(new Sum(), new ArrayList<Integer>(), 42));
        assertEquals(23, reduce(new Mul(), new ArrayList<Integer>(), 23));
    }
}
