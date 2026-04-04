package com.mborodin.javacourse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class g_MultithreadingAndUncaughtExceptionHandler {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(g_MultithreadingAndUncaughtExceptionHandler::exceptionHandler);

        //  Exception handler працює
        var thread = new Thread(() -> executeSomething("new thread"));
        thread.start();

        var executorService = Executors.newSingleThreadExecutor();

        //  Exception handler працює
        executorService.execute(() -> executeSomething("Execute"));

        //  Exception handler не працює для помилки яка стається в submit()
        // submit() ловить помилку і повертає її під час виклику future.get();
        Future<?> future = executorService.submit(() -> executeSomething("Submit"));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            //  Ця помилка вже буде опрацьована за допомогою exception handler, який ми створили
            throw new RuntimeException(e);
        }

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

    private static void exceptionHandler(Thread t, Throwable e) {
        System.out.println("Inside of default uncaught exception handler. Message: " + e.getMessage());
        e.printStackTrace(System.out);
    }
}
