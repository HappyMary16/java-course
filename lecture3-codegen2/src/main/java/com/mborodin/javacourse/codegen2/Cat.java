package com.mborodin.javacourse.codegen2;

@Getter
public class Cat {

    private final String catName;
    private final String color;

    public Cat(String catName, String color) {
        this.catName = catName;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat {name: " + catName + ", color: " + color + "}";
    }
}
