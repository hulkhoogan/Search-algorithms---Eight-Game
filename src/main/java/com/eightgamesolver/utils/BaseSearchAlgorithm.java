package com.eightgamesolver.utils;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSearchAlgorithm {
    private Map<String, Node> visitedMap = new HashMap<>();
    private String solutionPath;
    private String finalState;

    public String getSolutionPath(int[] initialState, int[] goalState) throws Exceptions.InvalidPath {
        Node root = new Node(0, "");
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        setFinalState(Arrays.toString(goalState).replaceAll(arrayToStringRegex, ""));

        Collection<String> generatedStates = getGeneratedStates();
        generatedStates.add(rootState);
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates
                .stream().findFirst()
                .orElse("");
            generatedStates.remove(state);
            if (generateDescendents(state)) {
                return getSolutionPath();
            }
        }
        throw new Exceptions.InvalidPath();
    }

    protected boolean generateDescendents(String parentState) {
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

    public abstract boolean validateState(String parentState, String childState, String move);

    protected String getSolutionPath() {
        return solutionPath;
    }

    protected void setSolutionPath(String path) {
        this.solutionPath = path;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public abstract Collection<String> getGeneratedStates();

    protected Map<String, Node> getVisitedMap() {
        return this.visitedMap;
    }

}