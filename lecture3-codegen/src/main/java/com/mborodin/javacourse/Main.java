package com.mborodin.javacourse;

import com.mborodin.javacourse.codegen.CatBuilder;

public class Main {

    public static void main(String[] args) {
        CatBuilder catBuilder = CatBuilder.newBuilder();
        catBuilder.catName("Tom");
        catBuilder.color("Black");
        System.out.println(catBuilder.build());
    }
}
