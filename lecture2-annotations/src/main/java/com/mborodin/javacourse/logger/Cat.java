package com.mborodin.javacourse.logger;

@PII
public class Cat {

    private final long id;
    @PII
    private final String name;

    public Cat(String name) {
        this.id = System.nanoTime();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
