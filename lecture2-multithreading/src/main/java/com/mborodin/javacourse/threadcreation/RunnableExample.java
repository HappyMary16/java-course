package com.mborodin.javacourse.threadcreation;


public class RunnableExample {

    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Привіт з потоку: " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
