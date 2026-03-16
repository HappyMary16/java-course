package com.mborodin.javacourse.threadcreation;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadsExample {

    public static void main(String[] args) {
        // Створення “мільйона” потоків
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 1_000_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return i;
                });
            });
        }
        // Тут усі потоки завершаться автоматично,
        // бо ми використали try with resources
    }
}
