package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BFS {
    private static final String NULL = "null";
    private static Map<String, Node> visitedMap = new HashMap<String, Node>();
    private static LinkedList<String> generatedStates = new LinkedList<String>();
    private String finalState;
    private String solutionPath;

    String BFS(int[] initialState, int[] finalState) {
        Node root = new Node(0, "");
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        this.finalState = Arrays.toString(finalState).replaceAll(arrayToStringRegex, "");

        generatedStates.add(rootState);
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates.remove();
            if (generateDescendents(state)) {
                return solutionPath;
            }
        }
        return "";
    }

    private boolean generateDescendents(String state) {
        int zeroPosition = 0;
        int boardSize = state.length();
        char[] parsedState = state.toCharArray();
        for (int i = 0; i < boardSize; i++) {
            if (state.charAt(i) == '0') {
                zeroPosition = i;
                break;
            }
        }

        //up
        if (zeroPosition > 2) {
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition - 3];
            parsedState[zeroPosition - 3] = temp;
            state = new String(parsedState);
            return validateState(state, "U");
        }
        //down
        if (zeroPosition < 6) {
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition + 3];
            parsedState[zeroPosition + 3] = temp;
            state = new String(parsedState);
            return validateState(state, "D");
        }
        //left
        if (zeroPosition != 0 && zeroPosition != 3 && zeroPosition != 6) {
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition - 1];
            parsedState[zeroPosition - 1] = temp;
            state = new String(parsedState);
            return validateState(state, "L");
        }
        //right
        if (zeroPosition != 2 && zeroPosition != 5 && zeroPosition != 8) {
            char temp = parsedState[zeroPosition];
            parsedState[zeroPosition] = parsedState[zeroPosition + 1];
            parsedState[zeroPosition + 1] = temp;
            state = new String(parsedState);
            return validateState(state, "R");
        }
        return false;
    }

    private boolean validateState(String state, String move) {
        if (!visitedMap.containsKey(state)) {
            visitedMap.put(state, new Node((visitedMap.get(state).getDepth() + 1), (visitedMap.get(state).getPath() + move)));
            if (state.equals(finalState)) {
                solutionPath = visitedMap.get(state).getPath().replaceAll(NULL, "");
                return true;
            }
            generatedStates.addLast(state);
        }
        return false;
    }
}


