package com.mborodin.javacourse;

import com.mborodin.javacourse.logger.Building;
import com.mborodin.javacourse.logger.Cat;
import com.mborodin.javacourse.logger.Logger;
import com.mborodin.javacourse.logger.User;

import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        User user = new User("John", "Doe", "Red");
        Cat cat = new Cat("Oscar");
        Building building = new Building("Home");

        System.out.println("Printing entities without PII.");
        try (var executorService = Executors.newSingleThreadExecutor()) {
            Stream.of(user, cat, building)
                  .forEach(entity -> executorService.execute(() -> log(entity)));
        }
    }

    private static void log(Object entity) {
        String format = entity.getClass().getSimpleName() + ": %s";
        Logger.logWithoutPii(format, entity);
    }
}
