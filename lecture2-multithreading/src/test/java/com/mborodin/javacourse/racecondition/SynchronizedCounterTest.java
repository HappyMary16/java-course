package com.mborodin.javacourse.racecondition;

class SynchronizedCounterTest extends AbstractCounterTest {

    SynchronizedCounter counter = new SynchronizedCounter();

    @Override
    Counter getCounter() {
        return counter;
    }
}