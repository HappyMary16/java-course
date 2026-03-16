package com.mborodin.javacourse.threadcreation;

public class ThreadExample {

    public static void main(String[] args) {
        new MyThread().start();
    }

    private static class MyThread extends Thread {
        public void run() {
            System.out.println("Привіт з потоку: " + Thread.currentThread().getName());
        }
    }
}
