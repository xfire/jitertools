package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.list;
import static de.downgra.jitertools.JIterTools.slice;
import static de.downgra.jitertools.JIterTools.tabulate;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestTabulate {
    class Mul implements IFunctor<Integer, Integer> {
        @Override
        public Integer call(Integer object) {
            return object.intValue() * 11;
        }
    };

    @Test
    public void start_0() {
        ArrayList<Integer> result = new ArrayList<Integer>(list(slice(tabulate(new Mul()), 8)));
        assertArrayEquals(new Integer[] { 0, 11, 22, 33, 44, 55, 66, 77 }, result.toArray());
    }

    @Test
    public void start_100() {
        ArrayList<Integer> result = new ArrayList<Integer>(list(slice(tabulate(new Mul(), 100), 5)));
        assertArrayEquals(new Integer[] { 1100, 1111, 1122, 1133, 1144 }, result.toArray());
    }
}
