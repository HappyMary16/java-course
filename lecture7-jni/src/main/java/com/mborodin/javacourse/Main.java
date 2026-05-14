package com.mborodin.javacourse;

public class Main {

    public static void main(String[] args) {
        // Library should be located in folder where you start an app
        System.loadLibrary("lecture7_jni");
        sayHello(); // Method from library
    }

    // Defining method from library using keyword "native"
    private static native void sayHello();
}
