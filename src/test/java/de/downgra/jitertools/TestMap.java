package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.list;
import static de.downgra.jitertools.JIterTools.map;
import static de.downgra.jitertools.JIterTools.range;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.downgra.jitertools.utils.IFunctor;

public class TestMap {
    class DoSomeMath implements IFunctor<Integer, Integer> {
        @Override
        public Integer call(Integer object) {
            return object.intValue() * 10 + 5;
        }
    };

    class ToString implements IFunctor<String, Integer> {
        @Override
        public String call(Integer object) {
            return object.toString();
        }
    };

    @Test
    public void math() {
        ArrayList<Integer> result = new ArrayList<Integer>(list(map(new DoSomeMath(), range(10))));
        assertArrayEquals(new Integer[] { 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 }, result.toArray());
    }

    @Test
    public void stringify() {
        ArrayList<String> result = new ArrayList<String>(list(map(new ToString(), range(10, 16))));
        assertArrayEquals(new String[] { "10", "11", "12", "13", "14", "15" }, result.toArray());
    }
}
