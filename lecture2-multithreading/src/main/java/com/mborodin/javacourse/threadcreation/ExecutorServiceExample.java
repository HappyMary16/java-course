package com.mborodin.javacourse.threadcreation;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        // Створюємо пул з 5 "робочих" потоків
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Віддаємо завдання на виконання
        executor.submit(() -> {
            System.out.println("Завдання виконується в пулі. Потік: " + Thread.currentThread().threadId());
        });

        // Обов'язково закриваємо пул в кінці
        executor.shutdown();
        executor.close();
    }
}
