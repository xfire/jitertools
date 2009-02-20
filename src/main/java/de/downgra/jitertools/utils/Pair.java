package de.downgra.jitertools.utils;

public class Pair<F, S> {
    private final F first;
    private final S second;

    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> create(A first, B second) {
        return new Pair<A, B>(first, second);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public final boolean equals(Object o) {
        if (!(o instanceof Pair))
            return false;

        final Pair<?, ?> other = (Pair<?, ?>) o;
        return equal(getFirst(), other.getFirst()) && equal(getSecond(), other.getSecond());
    }

    public static final boolean equal(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    public int hashCode() {
        int hFirst = getFirst() == null ? 0 : getFirst().hashCode();
        int hSecond = getSecond() == null ? 0 : getSecond().hashCode();

        return hFirst + (57 * hSecond);
    }

}
