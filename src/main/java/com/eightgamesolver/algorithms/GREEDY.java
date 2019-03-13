package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.HeuristicNode;
import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.BaseSearchAlgorithm;
import com.eightgamesolver.utils.ManhattanDistanceCalculator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class GREEDY extends BaseSearchAlgorithm {
    private Queue<String> generatedStates = new LinkedList<>();
    private PriorityQueue<HeuristicNode> heuristicNodePriorityQueue = new PriorityQueue<>();

    @Override
    public String getSolutionPath(int[] initialState, int[] goalState) throws Exceptions.InvalidPath {
        String arrayToStringRegex = "\\[|]|,|\\s";
        String rootState = Arrays.toString(initialState).replaceAll(arrayToStringRegex, "");
        String endState = Arrays.toString(goalState).replaceAll(arrayToStringRegex, "");
        setFinalState(endState);
        Node root = new Node(0, "");
        int manhattanDistance = ManhattanDistanceCalculator.calculate(rootState, 0, endState);
        heuristicNodePriorityQueue.add(new HeuristicNode(rootState, 0, manhattanDistance));
        Map<String, Node> visitedMap = getVisitedMap();
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
        Map<String, Node> visitedMap = getVisitedMap();
        if (!visitedMap.containsKey(childState)) {
            Node parentNode = visitedMap.get(parentState);
            int parentDepth = parentNode.getDepth();
            String currentPath = parentNode.getPath();
            Node childNode = new Node((parentDepth + 1), (currentPath + move));
            visitedMap.put(childState, childNode);
            String finalState = getFinalState();
            if (childState.equals(finalState)) {
                String path = childNode.getPath();
                this.setSolutionPath(path.replaceAll("null", ""));
                return true;
            }
            int manhattanDistance = ManhattanDistanceCalculator.calculate(childState, 0, finalState);
            heuristicNodePriorityQueue.add(new HeuristicNode(childState, 0, manhattanDistance));
        }
        return false;
    }

    @Override
    public Queue<String> getGeneratedStates() {
        return this.generatedStates;
    }
}


