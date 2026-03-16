package com.mborodin.javacourse.racecondition;

class AtomicIntegerCounterTest extends AbstractCounterTest {

    AtomicIntegerCounter counter = new AtomicIntegerCounter();

    @Override
    Counter getCounter() {
        return counter;
    }
}