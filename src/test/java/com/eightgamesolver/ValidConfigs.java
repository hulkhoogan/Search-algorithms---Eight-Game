package com.eightgamesolver;

public class ValidConfigs {
    private int[] initialState;
    private int[] finalState;

    private ValidConfigs(int[] initialState, int[] finalState) {
        this.initialState = initialState;
        this.finalState = finalState;
    }

    public static ValidConfigs getValidConfig1() {
        int[] startState = {3, 4, 2, 5, 1, 7, 6, 0, 8};
        int[] endState = {1, 2, 3, 8, 0, 4, 7, 6, 5};
        return new ValidConfigs(startState, endState);
    }

    public static ValidConfigs getValidConfig2() {
        int[] startState = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] endState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        return new ValidConfigs(startState, endState);
    }

    public int[] getInitialState() {
        return initialState;
    }

    public void setInitialState(int[] initialState) {
        this.initialState = initialState;
    }

    public int[] getFinalState() {
        return finalState;
    }

    public void setFinalState(int[] finalState) {
        this.finalState = finalState;
    }

}
