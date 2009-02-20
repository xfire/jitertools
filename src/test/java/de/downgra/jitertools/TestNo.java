package de.downgra.jitertools;

import static de.downgra.jitertools.JIterTools.no;
import static de.downgra.jitertools.JIterTools.range;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import de.downgra.jitertools.utils.IPredicate;

public class TestNo {
    class LT implements IPredicate<Integer> {
        private final int comparator;

        public LT(int comparator) {
            this.comparator = comparator;
        }

        @Override
        public Boolean call(Integer object) {
            return object < this.comparator;
        }
    };

    @Test
    public void isTrue() {
        assertTrue(no(new LT(5), range(23, 42)));
    }

    @Test
    public void isFalse() {
        assertFalse(no(new LT(10), range(23)));
    }

    @Test
    public void empty() {
        assertTrue(no(new LT(5), new ArrayList<Integer>()));
    }
}