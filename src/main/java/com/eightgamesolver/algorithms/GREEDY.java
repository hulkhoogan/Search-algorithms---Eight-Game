package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.HeuristicNode;
import com.eightgamesolver.common.Node;
import com.eightgamesolver.utils.HeuristicBaseSearchAlgorithm;
import com.eightgamesolver.utils.ManhattanDistanceCalculator;

import java.util.Map;

class GREEDY extends HeuristicBaseSearchAlgorithm {

    GREEDY(int[] initialState, int[] goalState) {
        super(initialState, goalState);
    }

    @Override
    public boolean validateState(String parentState, String childState, Character move) {
        Map<String, Node> visitedMap = getVisitedMap();
        if (!visitedMap.containsKey(childState)) {
            if (!processNode(parentState, childState, move)) {
                String finalState = getFinalState();
                int manhattanDistance = ManhattanDistanceCalculator.calculate(childState, 0, finalState);
                getHeuristicPriorityQueue().add(new HeuristicNode(childState, 0, manhattanDistance));
            } else {
                return true;
            }
        }
        return false;
    }

}


