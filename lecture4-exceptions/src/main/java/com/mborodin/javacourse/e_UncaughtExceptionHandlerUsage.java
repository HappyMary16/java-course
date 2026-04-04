package com.mborodin.javacourse;

public class e_UncaughtExceptionHandlerUsage {

    public static void main(String[] args) throws CheckedException {

        Thread.setDefaultUncaughtExceptionHandler(e_UncaughtExceptionHandlerUsage::exceptionHandler);

        for (int i = 0; i < 3; i++) {
            System.out.println("Iteration number: " + i);
            a_ThrowsExceptions.throwsCheckedException();
//            a_ThrowsExceptions.throwsUncheckedException();
        }
    }

    private static void exceptionHandler(Thread t, Throwable e) {
        System.out.println("Inside of default uncaught exception handler");
        e.printStackTrace(System.out);
    }
}