package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.utils.BaseSearchAlgorithm;

import java.util.LinkedList;
import java.util.Map;

class DFS extends BaseSearchAlgorithm {
    private LinkedList<String> generatedStates = new LinkedList<>();

    public boolean validateState(String parentState, String childState, String move) {
        Map<String, Node> visitedMap = getVisitedMap();
        if (!visitedMap.containsKey(childState)) {
            visitedMap.put(childState, new Node((visitedMap.get(parentState).getDepth() + 1), (visitedMap.get(parentState).getPath() + move)));
            String finalState = getFinalState();
            if (childState.equals(finalState)) {
                setSolutionPath(visitedMap.get(childState).getPath().replaceAll("null", ""));
                return true;
            }
            generatedStates.addFirst(childState);
        }
        return false;
    }

    @Override
    public LinkedList<String> getGeneratedStates() {
        return this.generatedStates;
    }
}


