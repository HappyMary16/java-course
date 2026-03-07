package com.mborodin.javacourse;

/**
 * Add break points to lines 20, 21, 25, 26
 * and see how a call stack looks like on each stop.
 */
public class StackAndRecursion {

    public static void main(String[] args) {
        int result = increaseTo(1, 5);
        System.out.println(result);
    }

    private static int increaseTo(int i, int increaseTo) {
        if (i > increaseTo) {
            System.out.println("i is already bigger than increaseTo");
            return i;
        }

        if (i == increaseTo) {
            return i;
        }

        System.out.println("i: " + i + ", increase to: " + increaseTo);
        int res = increaseTo(i + 1, increaseTo);
        return res;
    }
}