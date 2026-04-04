package com.mborodin.javacourse;

public class d_UnhandledUncheckedException {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Iteration number: " + i);
            a_ThrowsExceptions.throwsUncheckedException();
        }
    }
}