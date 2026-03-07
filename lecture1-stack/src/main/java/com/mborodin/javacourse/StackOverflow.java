package com.mborodin.javacourse;

/**
 * Add break points to lines 17, 18, 22, 23
 * and see how a call stack looks like on each stop.
 * <p>
 * Why we never do return from our recursive call?
 */
public class StackOverflow {

    public static void main(String[] args) {
        int result = increaseTo(0, -5);
        System.out.println(result);
    }

    private static int increaseTo(int i, int increaseTo) {
        if (i == increaseTo) {
            return i;
        }

        System.out.println("i: " + i + ", increase to: " + increaseTo);
        int res = increaseTo(i + 1, increaseTo);
        return res;
    }
}
