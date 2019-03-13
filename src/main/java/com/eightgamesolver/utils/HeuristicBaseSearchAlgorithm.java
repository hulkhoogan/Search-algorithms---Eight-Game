package com.eightgamesolver.utils;

import com.eightgamesolver.common.HeuristicNode;
import com.eightgamesolver.common.Node;
import com.eightgamesolver.exceptions.Exceptions;

import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class HeuristicBaseSearchAlgorithm extends BaseSearchAlgorithm {
    private PriorityQueue<HeuristicNode> heuristicPriorityQueue = new PriorityQueue<>();

    public HeuristicBaseSearchAlgorithm(int[] initialState, int[] goalState) {
        super(initialState, goalState);
    }

    protected PriorityQueue<HeuristicNode> getHeuristicPriorityQueue() {
        return heuristicPriorityQueue;
    }

    @Override
    public String getSolutionPath() throws Exceptions.InvalidPath {
        String rootState = getInitialState();
        String endState = getFinalState();
        Node root = new Node(0, "");
        int manhattanDistance = ManhattanDistanceCalculator.calculate(rootState, 0, endState);
        heuristicPriorityQueue.add(new HeuristicNode(rootState, 0, manhattanDistance));
        Map<String, Node> visitedMap = getVisitedMap();
        visitedMap.put(rootState, root);

        while (!heuristicPriorityQueue.isEmpty()) {
            HeuristicNode heuristicNode = heuristicPriorityQueue.poll();
            if (heuristicNode != null) {
                String state = heuristicNode.getState();
                if (generateDescendents(state)) {
                    return getFinalPath();
                }
            }
        }
        throw new Exceptions.InvalidPath();
    }

    @Override
    public Queue<String> getGeneratedStates() {
        return new LinkedList<>();
    }

}
