package com.mborodin.javacourse.racecondition;

public class BasicCounter implements Counter {

    private int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}
