package com.mborodin.javacourse.racecondition;

public class SynchronizedCounter implements Counter {

    int count = 0;

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
