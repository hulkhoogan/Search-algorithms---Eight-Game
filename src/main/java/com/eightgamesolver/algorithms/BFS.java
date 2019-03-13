package com.eightgamesolver.algorithms;

import com.eightgamesolver.utils.BaseSearchAlgorithm;

import java.util.Deque;
import java.util.LinkedList;

class BFS extends BaseSearchAlgorithm {
    private Deque<String> generatedStates = new LinkedList<>();

    BFS(int[] initialState, int[] goalState) {
        super(initialState, goalState);
    }

    @Override
    public Deque<String> getGeneratedStates() {
        return this.generatedStates;
    }

}


