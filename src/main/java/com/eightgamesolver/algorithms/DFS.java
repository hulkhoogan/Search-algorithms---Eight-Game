package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.utils.BaseSearchAlgorithm;

import java.util.LinkedList;
import java.util.Map;

class DFS extends BaseSearchAlgorithm {
    private LinkedList<String> generatedStates = new LinkedList<>();

    DFS(int[] initialState, int[] goalState) {
        super(initialState, goalState);
    }

    @Override
    public boolean validateState(String parentState, String childState, Character move) {
        Map<String, Node> visitedMap = getVisitedMap();
        if (!visitedMap.containsKey(childState)) {
            if (!processNode(parentState, childState, move)) {
                generatedStates.addFirst(childState);
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public LinkedList<String> getGeneratedStates() {
        return this.generatedStates;
    }
}


