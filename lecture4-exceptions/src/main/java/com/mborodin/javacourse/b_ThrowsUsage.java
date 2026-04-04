package com.mborodin.javacourse;

public class b_ThrowsUsage {

    public static void main(String[] args) throws CheckedException {
        for (int i = 0; i < 3; i++) {
            System.out.println("Iteration number: " + i);
            a_ThrowsExceptions.throwsCheckedException();
        }
    }
}