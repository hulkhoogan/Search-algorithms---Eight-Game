package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.BaseSearchAlgorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

class IDFS extends BaseSearchAlgorithm {
    private Deque<String> generatedStates = new LinkedList<>();

    IDFS(int[] initialState, int[] goalState, int maxDepth) {
        super(initialState, goalState, maxDepth);
    }

    @Override
    public String getSolutionPath() throws Exceptions.InvalidPath {
        Node root = new Node(0, "");
        String rootState = getInitialState();

        generatedStates.add(rootState);
        Map<String, Node> visitedMap = getVisitedMap();
        visitedMap.put(rootState, root);
        while (!generatedStates.isEmpty()) {
            String state = generatedStates.remove();
            Node currentState = visitedMap.get(state);
            int maxDepth = getMaxDepth();
            if (currentState.getDepth() + 1 <= maxDepth && generateDescendents(state)) {
                return getFinalPath();
            }
        }
        throw new Exceptions.InvalidPath();
    }

    @Override
    public Deque<String> getGeneratedStates() {
        return this.generatedStates;
    }

}


