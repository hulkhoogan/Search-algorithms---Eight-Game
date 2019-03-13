package com.eightgamesolver.utils;

public class ManhattanDistanceCalculator {

    private int manhattanDistance;

    ManhattanDistanceCalculator(String state, int depth, String goalState) {
        this.manhattanDistance = calculate(state, depth, goalState);
    }

    public static int calculate(String state, int depth, String goalState) {
        int totalDistance = 0;

        for (int x = 0; x < 9; x++) {
            char play = state.charAt(x);
            int xPosition = getXPosition(x);
            int yPosition = getYPosition(x);
            if (play != '0') {
                int finalIndex = goalState.indexOf(play);
                int xPositionOnGoalState = getXPosition(finalIndex);
                int yPositionOnGoalState = getYPosition(finalIndex);
                int xDistance = xPosition - xPositionOnGoalState;
                int yDistance = yPosition - yPositionOnGoalState;
                totalDistance += Math.abs(xDistance) + Math.abs(yDistance);
            }
        }

        return totalDistance + depth;
    }

    private static int getYPosition(int x) {
        int yDistance;
        if (x < 3) {
            yDistance = 0;
        } else if (x < 6) {
            yDistance = 1;
        } else {
            yDistance = 2;
        }
        return yDistance;
    }

    private static int getXPosition(int x) {
        int xDistance;
        if (x < 3) {
            xDistance = x;
        } else if (x < 6) {
            xDistance = x % 3;
        } else {
            xDistance = x % 3;
        }
        return xDistance;
    }

    int getManhattanDistance() {
        return this.manhattanDistance;
    }

}
