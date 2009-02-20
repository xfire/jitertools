package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.list;
import static de.downgra.jitertools.JIterTools.repeat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRepeat {

    @Test
    public void repeatInfinite() {
        Integer value = new Integer(23);
        int _max = 1000;
        for (final Integer item : repeat(value)) {
            assertEquals(item, value);
            if (_max-- <= 0)
                break;
        }
    }

    @Test
    public void repeatTimes() {
        assertArrayEquals(new Integer[] { 42, 42, 42, 42, 42 }, list(repeat(new Integer(42), 5)).toArray(new Integer[0]));
    }

}
