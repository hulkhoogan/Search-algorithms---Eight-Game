package com.eightgamesolver.exceptions;

public class Exceptions extends RuntimeException {
    private static final String
        INVALID_START_CONFIG
        = "With the given start an final state is impossible to solve this puzzle.";
    private static final String
        INVALID_PATH_FOUND
        = "Invalid path found";

    private Exceptions(String message) {
        super(message);
    }

    public static class InvalidStates extends Throwable {
        public InvalidStates() {
            throw new Exceptions(INVALID_START_CONFIG);
        }
    }


    public static class InvalidPath extends Throwable {
        public InvalidPath() {
            throw new Exceptions(INVALID_PATH_FOUND);
        }
    }
}
