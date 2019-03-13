package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.HeuristicNode;
import com.eightgamesolver.common.Node;
import com.eightgamesolver.utils.HeuristicBaseSearchAlgorithm;
import com.eightgamesolver.utils.ManhattanDistanceCalculator;

import java.util.Map;
import java.util.PriorityQueue;

class ASTAR extends HeuristicBaseSearchAlgorithm {

    ASTAR(int[] initialState, int[] goalState) {
        super(initialState, goalState);
    }

    @Override
    public boolean validateState(String parentState, String childState, Character move) {
        Map<String, Node> visitedMap = getVisitedMap();
        if (!visitedMap.containsKey(childState)) {
            if (!processNode(parentState, childState, move)) {
                Node childNode = visitedMap.get(childState);
                String finalState = getFinalState();
                int depth = childNode.getDepth();
                int manhattanDistance = ManhattanDistanceCalculator.calculate(childState, depth, finalState);
                PriorityQueue<HeuristicNode> heuristicPriorityQueue = getHeuristicPriorityQueue();
                heuristicPriorityQueue.add(new HeuristicNode(childState, depth, manhattanDistance));
            } else {
                return true;
            }
        }
        return false;
    }

}


