package com.mborodin.javacourse;

import com.mborodin.javacourse.codegen2.Cat;

public class CodeGen2Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Tom", "Black");

        System.out.println("Getting cat name using generated code. Name: " + cat.getCatName());
        System.out.println("Getting cat color using generated code. Color: " + cat.getColor());

        System.out.println("All field from Cat model were printed.");
    }
}
