package com.eightgamesolver.utils;

import com.eightgamesolver.exceptions.Exceptions;

import java.util.Scanner;

class ProcessInput {
    private static final int SIZE_BOARD = 9;
    private int zeroPosition;
    private int[] initialState = new int[SIZE_BOARD];
    private int[] finalState = new int[SIZE_BOARD];

    ProcessInput() throws Exceptions.InvalidStates {
        Scanner inputFromSystem = new Scanner(System.in);
        for (int i = 0; i < SIZE_BOARD; i++) {
            initialState[i] = inputFromSystem.nextInt();
            if (initialState[i] == 0) {
                this.setZeroPosition(i);
            }
        }
        for (int i = 0; i < SIZE_BOARD; i++) {
            finalState[i] = inputFromSystem.nextInt();
        }
        if (isNotAValidConfig(initialState, finalState)) {
            throw new Exceptions.InvalidStates();
        }
    }

    ProcessInput(int[] initialStateParameter, int[] finalStateParameter) throws Exceptions.InvalidStates {
        this.setInitialState(initialStateParameter);
        this.setFinalState(finalStateParameter);
        for (int i = 0; i < SIZE_BOARD; i++) {
            if (initialStateParameter[i] == 0) {
                this.setZeroPosition(i);
            }
        }
        if (isNotAValidConfig(initialStateParameter, finalStateParameter)) {
            throw new Exceptions.InvalidStates();
        }
    }

    public static int getSizeBoard() {
        return SIZE_BOARD;
    }

    private boolean isNotAValidConfig(int[] stateInitial, int[] stateFinal) {
        int inversionsInitialState = countInversionsInState(stateInitial);
        int inversionsFinalState = countInversionsInState(stateFinal);

        return ((!isPair(inversionsInitialState) || !isPair(inversionsFinalState)) && (isPair(inversionsInitialState)
            || isPair(inversionsFinalState)));

    }

    private boolean isPair(int number) {
        return number % 2 == 0;
    }

    private int countInversionsInState(int[] state) {
        int inversions = 0;
        for (int i = 0; i < SIZE_BOARD; i++) {
            if (state[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < SIZE_BOARD; j++) {
                if (state[j] == 0) {
                    continue;
                }
                if (state[i] > state[j]) {
                    inversions++;
                }
            }
        }
        return inversions;
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

    private int getZeroPosition() {
        return this.zeroPosition;
    }

    private void setZeroPosition(int position) {
        this.zeroPosition = position;
    }
}
