package com.mborodin.javacourse.racecondition;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements Counter {

    AtomicInteger count = new AtomicInteger(0);

    @Override
    public void increment() {
        count.incrementAndGet();
    }

    @Override
    public int getCount() {
        return count.get();
    }
}
