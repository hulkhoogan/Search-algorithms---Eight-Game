package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.BaseSearchAlgorithm;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

class IDFS extends BaseSearchAlgorithm {
    private Deque<String> generatedStates = new LinkedList<>();

    String getSolutionPath(int[] initialState, int[] goalState, int maxDepth) throws Exceptions.InvalidPath {
        Node root = new Node(0, "");
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        setFinalState(Arrays.toString(goalState).replaceAll(arrayToStringRegex, ""));

        generatedStates.add(rootState);
        Map<String, Node> visitedMap = getVisitedMap();
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates.remove();
            Node currentState = visitedMap.get(state);
            if (currentState.getDepth() + 1 <= maxDepth && generateDescendents(state)) {
                return getSolutionPath();
            }
        }
        throw new Exceptions.InvalidPath();
    }

    public boolean validateState(String parentState, String childState, String move) {
        Map<String, Node> visitedMap = getVisitedMap();
        if (!visitedMap.containsKey(childState)) {
            visitedMap.put(childState, new Node((visitedMap.get(parentState).getDepth() + 1), (visitedMap.get(parentState).getPath() + move)));
            String finalState = getFinalState();
            if (childState.equals(finalState)) {
                setSolutionPath(visitedMap.get(childState).getPath().replaceAll("null", ""));
                return true;
            }
            generatedStates.add(childState);
        }
        return false;
    }

    @Override
    public Deque<String> getGeneratedStates() {
        return this.generatedStates;
    }

}


