package com.mborodin.javacourse;

public class c_TryCatchUsage {

    public static void main(String[] args)  {

        for (int i = 0; i < 3; i++) {
            System.out.println("Iteration number: " + i);
            try {
                a_ThrowsExceptions.throwsCheckedException();
            } catch (CheckedException e) {
                System.out.println("Caught CheckedException");
            }
        }
    }
}