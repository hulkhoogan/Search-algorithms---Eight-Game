package com.eightgamesolver.algorithms;

import com.eightgamesolver.common.Node;
import com.eightgamesolver.utils.NodesGenerator;

class DFS implements NodesGenerator {

    private String solutionPath;
    private String finalState;

    public boolean validateState(String parentState, String childState, String move) {
        if (!visitedMap.containsKey(childState)) {
            visitedMap.put(childState, new Node((visitedMap.get(parentState).getDepth() + 1), (visitedMap.get(parentState).getPath() + move)));
            if (childState.equals(finalState)) {
                setSolutionPath(visitedMap.get(childState).getPath().replaceAll(NULL, ""));
                return true;
            }
            generatedStates.addFirst(childState);
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


