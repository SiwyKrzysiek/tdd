package util;

import java.util.Iterator;

public class InfiniteCounter implements Iterator<Integer> {
    private int value;

    public InfiniteCounter() {
        this(0);
    }

    public InfiniteCounter(int initialValue) {
        value = initialValue;
    }

    @Override
    public boolean hasNext() {
        return value != Integer.MAX_VALUE;
    }

    @Override
    public Integer next() {
        return value++;
    }
}
