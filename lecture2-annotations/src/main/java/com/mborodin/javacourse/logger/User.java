package com.mborodin.javacourse.logger;

import java.util.Objects;

@PII
public record User(long id, @PII String firstName, @PII String lastName, String favouriteColor) {

    public User {
        Objects.requireNonNull(firstName, "First name cannot be null");
        Objects.requireNonNull(firstName, "Last name cannot be null");
        id = System.currentTimeMillis(); // генеруємо новий id навіть якщо значення було передано

        // Всі поля будуть проініціалізовані переданими значеннями автоматично
        // За необхідності можна тут додати ініціалізація значеннями за замовчуванням
        // Приклад:
        if(favouriteColor == null) favouriteColor = "none";
    }

    public User(String firstName, String lastName, String favouriteColor) {
        this(System.nanoTime(), firstName, lastName, favouriteColor);
    }
}
