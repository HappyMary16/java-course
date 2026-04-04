package com.mborodin.javacourse;

import java.util.concurrent.Executors;

public class f_Multithreading {

    public static void main(String[] args) {
        var executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> executeSomething("Execute"));
        executorService.submit(() -> executeSomething("Submit"));
        executorService.close();

        System.out.println("Finished execution");
    }

    private static void executeSomething(String something) {
        System.out.println("executing method in " + something);
        for (int i = 0; i < 3; i++) {
            System.out.println("Iteration number: " + i);
            a_ThrowsExceptions.throwsUncheckedException("Exception when executing method in " + something);
        }
    }
}
