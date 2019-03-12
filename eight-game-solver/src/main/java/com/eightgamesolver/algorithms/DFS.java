package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.NodesGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class DFS implements NodesGenerator {
    private static final String NULL = "null";
    private static Map<String, Node> visitedMap = new HashMap<String, Node>();
    private static LinkedList<String> generatedStates = new LinkedList<String>();
    private static String finalState;
    private static String solutionPath;

    String getSolutionPath(int[] initialState, int[] finalState) throws Exceptions.InvalidPath {
        Node root = new Node(0, "");
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        DFS.finalState = Arrays.toString(finalState).replaceAll(arrayToStringRegex, "");

        generatedStates.add(rootState);
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates.remove();
            if (generateDescendents(state)) {
                return solutionPath;
            }
        }
        throw new Exceptions.InvalidPath();
    }

    public boolean validateState(String parentState, String childState, String move) {
        if (!visitedMap.containsKey(childState)) {
            visitedMap.put(childState, new Node((visitedMap.get(parentState).getDepth() + 1), (visitedMap.get(parentState).getPath() + move)));
            if (childState.equals(finalState)) {
                solutionPath = visitedMap.get(childState).getPath().replaceAll(NULL, "");
                return true;
            }
            generatedStates.addFirst(childState);
        }
        return false;
    }
}


