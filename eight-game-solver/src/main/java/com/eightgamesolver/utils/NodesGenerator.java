package com.eightgamesolver.utils;

public interface NodesGenerator {

    default boolean generateDescendents(String parentState) {
        int zeroPosition = 0;
        int boardSize = parentState.length();
        char[] parsedState;
        for (int i = 0; i < boardSize; i++) {
            if (parentState.charAt(i) == '0') {
                zeroPosition = i;
                break;
            }
        }

        //up
        if (zeroPosition > 2) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition - 3];
            parsedState[zeroPosition - 3] = temp;
            String childState = new String(parsedState);
            if (validateState(parentState, childState, "U")) {
                return true;
            }
        }
        //down
        if (zeroPosition < 6) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition + 3];
            parsedState[zeroPosition + 3] = temp;
            String childState = new String(parsedState);
            if (validateState(parentState, childState, "D")) {
                return true;
            }
        }
        //left
        if (zeroPosition != 0 && zeroPosition != 3 && zeroPosition != 6) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition - 1];
            parsedState[zeroPosition - 1] = temp;
            String childState = new String(parsedState);
            if (validateState(parentState, childState, "L")) {
                return true;
            }
        }
        //right
        if (zeroPosition != 2 && zeroPosition != 5 && zeroPosition != 8) {
            parsedState = parentState.toCharArray();
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition + 1];
            parsedState[zeroPosition + 1] = temp;
            String childState = new String(parsedState);
            return validateState(parentState, childState, "R");
        }
        return false;
    }

    boolean validateState(String parentState, String childState, String move);
}