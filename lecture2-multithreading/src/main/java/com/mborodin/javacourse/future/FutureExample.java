package com.mborodin.javacourse.future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        // Створюємо ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Створюємо Callable (як Runnable тільки є можливість повернути щось з потоку)
        Callable<String> task = () -> {
            Thread.sleep(2000); // імітація роботи
            return "Дані з бази отримано!";
        };

        // submit() повертає обʼєкт класу Future за допомогою
        // якого ми можемо почекати на результат і отримати його
        System.out.println("Запит відправлено...");
        Future<String> future = executor.submit(task);

        // Отримуємо результат
        try {
            // Чекаємо на результат
            String result = future.get();
            System.out.println("Результат: " + result);
        } catch (InterruptedException | ExecutionException ignored) {
        }

        // Зупиняємо всі потоки в нашому executor
        executor.close();
    }
}
