package com.mborodin.javacourse.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Void> future =
                CompletableFuture.supplyAsync(() -> {
                                     // 1. Довгий запит до бази даних, API або просто щось довго робимо
                                     System.out.println("Завантаження даних...");
                                     try {
                                         Thread.sleep(100);
                                     } catch (InterruptedException e) {
                                         throw new RuntimeException(e);
                                     }
                                     return "Сирі дані";
                                 })
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

        // Чекаємо на результат
        System.out.println("Чекаємо на виконання одного future");
        future.get();
    }
}
