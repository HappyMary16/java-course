package com.mborodin.javacourse;

public class UncheckedException extends RuntimeException {

    public UncheckedException() {
        super("Unchecked Exception");
    }

    public UncheckedException(String message) {
        super(message);
    }
}
