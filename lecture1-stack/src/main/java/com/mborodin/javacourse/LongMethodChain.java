package com.mborodin.javacourse;

/**
 * Add break points to lines 12, 13, 20, 21, 28, 29, 36, 37, 44
 * and see how a call stack looks like on each stop.
 * Try to switch between different methods in a call stack
 * and see how much info is stored there.
 */
public class LongMethodChain {

    public static void main(String[] args) {
        int result = modifyByAddition(0);
        System.out.println(result);
    }

    private static int modifyByAddition(int i) {
        int increaseBy = (int) (Math.random() * 10);
        System.out.println("i: " + i + ", increase by: " + increaseBy);

        int res = modifyByMultiplication(i + increaseBy);
        return res;
    }

    private static int modifyByMultiplication(int i) {
        int multiplyBy = (int) (Math.random() * 10);
        System.out.println("i: " + i + ", multiply by: " + multiplyBy);

        int res = modifyBySubtraction(i * multiplyBy);
        return res;
    }

    private static int modifyBySubtraction(int i) {
        int subtractBy = (int) (Math.random() * 10);
        System.out.println("i: " + i + ", subtract by: " + subtractBy);

        int res = modifyByDivision(i - subtractBy);
        return res;
    }

    private static int modifyByDivision(int i) {
        int divideBy = (int) (Math.random() * 10) + 1;
        System.out.println("i: " + i + ", divide by: " + divideBy);

        return i / divideBy;
    }
}
