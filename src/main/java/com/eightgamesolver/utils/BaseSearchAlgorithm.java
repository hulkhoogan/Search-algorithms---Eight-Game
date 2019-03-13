package com.eightgamesolver.utils;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSearchAlgorithm {
    private Map<String, Node> visitedMap = new HashMap<>();
    private String finalPath;
    private String finalState;
    private String initialState;
    private int maxDepth;

    public BaseSearchAlgorithm(int[] initialState, int[] goalState) {
        setInitialAndFinalState(initialState, goalState);
    }

    public BaseSearchAlgorithm(int[] initialState, int[] goalState, int maxDepth) {
        setInitialAndFinalState(initialState, goalState);
        this.setMaxDepth(maxDepth);
    }

    private void setInitialAndFinalState(int[] initialState, int[] goalState) {
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        String endState = Arrays.toString(goalState).replaceAll(arrayToStringRegex, "");
        this.setInitialState(rootState);
        this.setFinalState(endState);
    }

    public String getSolutionPath() throws Exceptions.InvalidPath {
        Node root = new Node(0, "");

        Collection<String> generatedStates = getGeneratedStates();
        String rootState = getInitialState();
        generatedStates.add(rootState);
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates
                .stream().findFirst()
                .orElse("");
            generatedStates.remove(state);
            if (generateDescendents(state)) {
                return getFinalPath();
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
            if (validateState(parentState, childState, 'U')) {
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
            if (validateState(parentState, childState, 'D')) {
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
            if (validateState(parentState, childState, 'L')) {
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
            return validateState(parentState, childState, 'R');
        }
        return false;
    }

    public boolean validateState(String parentState, String childState, Character move) {
        if (!visitedMap.containsKey(childState)) {
            if (!processNode(parentState, childState, move)) {
                Collection<String> generatedStates = getGeneratedStates();
                generatedStates.add(childState);
            } else {
                return true;
            }
        }
        return false;
    }

    protected boolean processNode(String parentState, String childState, Character move) {
        Node parentNode = visitedMap.get(parentState);
        int parentDepth = parentNode.getDepth();
        String currentPath = parentNode.getPath();
        Node childNode = new Node((parentDepth + 1), (currentPath + move));
        visitedMap.put(childState, childNode);
        if (childState.equals(finalState)) {
            String path = childNode.getPath();
            this.setFinalPath(path.replaceAll("null", ""));
            return true;
        }
        return false;
    }

    protected String getFinalPath() {
        return finalPath;
    }

    private void setFinalPath(String path) {
        this.finalPath = path;
    }

    public String getFinalState() {
        return finalState;
    }

    public void setFinalState(String finalState) {
        this.finalState = finalState;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public abstract Collection<String> getGeneratedStates();

    protected Map<String, Node> getVisitedMap() {
        return this.visitedMap;
    }

    protected int getMaxDepth() {
        return maxDepth;
    }

    private void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
}