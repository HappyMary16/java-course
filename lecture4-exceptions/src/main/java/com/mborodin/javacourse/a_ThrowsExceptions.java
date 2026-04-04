package com.mborodin.javacourse;

public class a_ThrowsExceptions {

    public static void throwsCheckedException() throws CheckedException {
        throw new CheckedException();
    }

    public static void throwsUncheckedException() {
        throw new UncheckedException();
    }

    public static void throwsUncheckedException(String message) {
        throw new UncheckedException(message);
    }
}
