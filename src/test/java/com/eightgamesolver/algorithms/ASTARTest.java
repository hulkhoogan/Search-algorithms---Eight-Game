package com.eightgamesolver.algorithms;

import com.eightgamesolver.ValidConfigs;
import com.eightgamesolver.exceptions.Exceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ASTARTest {

    @Test
    void aStarConfig1() throws Exceptions.InvalidPath {
        ValidConfigs validConfig1 = ValidConfigs.getValidConfig1();
        int[] initialState = validConfig1.getInitialState();
        int[] finalState = validConfig1.getFinalState();
        String solutionPath = new ASTAR().getSolutionPath(initialState, finalState);
        Assertions.assertEquals("ULDRRULLDRRUULLDDRURULD", solutionPath);
        Assertions.assertEquals(23, solutionPath.length());
    }

    @Test
    void aStarConfig2() throws Exceptions.InvalidPath {
        ValidConfigs validConfig2 = ValidConfigs.getValidConfig2();
        int[] initialState = validConfig2.getInitialState();
        int[] finalState = validConfig2.getFinalState();
        String solutionPath = new ASTAR().getSolutionPath(initialState, finalState);
        Assertions.assertEquals("RDLDRRULLDRUURDDLLURRD", solutionPath);
        Assertions.assertEquals(22, solutionPath.length());
    }
}
