package com.mborodin.javacourse.racecondition;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractCounterTest {

    abstract Counter getCounter();

    @Test
    void increment_oneThread() {
        // WHEN
        getCounter().increment();

        // THEN
        assertEquals(1, getCounter().getCount());
    }

    @Test
    void increment_multipleThreads() {
        // WHEN
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 1_000_000).forEach(i -> {
                executor.submit(() -> {
                    getCounter().increment();
                });
            });
        }

        // THEN
        assertEquals(1_000_000, getCounter().getCount());
    }
}
