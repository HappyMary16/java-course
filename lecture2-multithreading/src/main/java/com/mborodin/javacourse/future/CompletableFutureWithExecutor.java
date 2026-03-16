package com.mborodin.javacourse.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class CompletableFutureWithExecutor {

    public static void main(String[] args) {

        var executor = Executors.newSingleThreadExecutor();

        CompletableFuture.supplyAsync(() -> {
                             // 1. Довгий запит до бази даних, API або просто щось довго робимо
                             System.out.println("Завантаження даних...");
                             try {
                                 Thread.sleep(100);
                             } catch (InterruptedException e) {
                                 throw new RuntimeException(e);
                             }
                             return "Сирі дані";
                         }, executor)
                         .thenApply(data -> {
                             // 2. Трансформуємо дані
                             return data + " -> Оброблені дані";
                         })
                         .thenAccept(result -> {
                             // 3. Виводимо фінальний результат
                             System.out.println("Результат: " + result);
                         })
                         .exceptionally(ex -> {
                             // 4. Обробка помилок у будь-якій ланці
                             System.err.println("Помилка: " + ex.getMessage());
                             return null;
                         });

        // Чекаємо поки все виконається і тільки потім завершуємо main.
        System.out.println("Чекаємо на виконання всіх завдань в executor");
        executor.close();
    }
}
