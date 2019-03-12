package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.NodesGenerator;

import java.util.Arrays;

class IDFS implements NodesGenerator {

    private String solutionPath;
    private String finalState;

    String getSolutionPath(int[] initialState, int[] goalState, int maxDepth) throws Exceptions.InvalidPath {
        Node root = new Node(0, "");
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        setFinalState(Arrays.toString(goalState).replaceAll(arrayToStringRegex, ""));

        generatedStates.add(rootState);
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates.remove();
            Node currentState = visitedMap.get(state);
            if (currentState.getDepth() + 1 <= maxDepth) {
                if (generateDescendents(state)) {
                    return getSolutionPath();
                }
            }
        }
        throw new Exceptions.InvalidPath();
    }

    public boolean validateState(String parentState, String childState, String move) {
        if (!visitedMap.containsKey(childState)) {
            visitedMap.put(childState, new Node((visitedMap.get(parentState).getDepth() + 1), (visitedMap.get(parentState).getPath() + move)));
            if (childState.equals(finalState)) {
                setSolutionPath(visitedMap.get(childState).getPath().replaceAll(NULL, ""));
                return true;
            }
            generatedStates.addLast(childState);
        }
        return false;
    }

    @Override
    public String getSolutionPath() {
        return solutionPath;
    }

    @Override
    public void setSolutionPath(String path) {
        this.solutionPath = path;
    }

    @Override
    public String getFinalState() {
        return finalState;
    }

    @Override
    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }
}


