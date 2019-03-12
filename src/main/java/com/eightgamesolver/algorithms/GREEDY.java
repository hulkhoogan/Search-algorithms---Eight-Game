package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.HeuristicNode;
import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.ManhattanDistanceCalculator;
import com.eightgamesolver.utils.NodesGenerator;

import java.util.Arrays;
import java.util.PriorityQueue;

class GREEDY implements NodesGenerator {

    private static PriorityQueue<HeuristicNode> heuristicNodePriorityQueue = new PriorityQueue<>();
    private String solutionPath;
    private String finalState;

    public String getSolutionPath(int[] initialState, int[] goalState) throws Exceptions.InvalidPath {
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        String finalState = Arrays.toString(goalState).replaceAll(arrayToStringRegex, "");
        setFinalState(finalState);
        Node root = new Node(0, "");
        int manhattanDistance = ManhattanDistanceCalculator.calculate(rootState, 0, finalState);
        heuristicNodePriorityQueue.add(new HeuristicNode(rootState, 0, manhattanDistance));
        visitedMap.put(rootState, root);

        while (!heuristicNodePriorityQueue.isEmpty()) {
            HeuristicNode heuristicNode = heuristicNodePriorityQueue.poll();
            if (heuristicNode != null) {
                String state = heuristicNode.getState();
                if (generateDescendents(state)) {
                    return getSolutionPath();
                }
            }
        }
        throw new Exceptions.InvalidPath();
    }

    public boolean validateState(String parentState, String childState, String move) {
        if (!visitedMap.containsKey(childState)) {
            Node parentNode = visitedMap.get(parentState);
            int parentDepth = parentNode.getDepth();
            String currentPath = parentNode.getPath();
            Node childNode = new Node((parentDepth + 1), (currentPath + move));
            visitedMap.put(childState, childNode);
            if (childState.equals(finalState)) {
                String path = childNode.getPath();
                solutionPath = path.replaceAll(NULL, "");
                return true;
            }
            int manhattanDistance = ManhattanDistanceCalculator.calculate(childState, 0, getFinalState());
            heuristicNodePriorityQueue.add(new HeuristicNode(childState, 0, manhattanDistance));
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


