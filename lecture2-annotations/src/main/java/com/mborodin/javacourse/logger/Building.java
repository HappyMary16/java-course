package com.mborodin.javacourse.logger;

public record Building(long id, String name) {

    public Building(String name) {
        this(System.nanoTime(), name);
    }
}
