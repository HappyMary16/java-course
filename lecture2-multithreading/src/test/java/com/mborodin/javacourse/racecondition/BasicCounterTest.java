package com.mborodin.javacourse.racecondition;

class BasicCounterTest extends AbstractCounterTest {

    BasicCounter counter = new BasicCounter();

    @Override
    Counter getCounter() {
        return counter;
    }
}