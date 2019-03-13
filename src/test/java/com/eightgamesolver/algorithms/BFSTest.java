package com.eightgamesolver.algorithms;

import com.eightgamesolver.ValidConfigs;
import com.eightgamesolver.exceptions.Exceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BFSTest {

    @Test
    void bfsConfig1() throws Exceptions.InvalidPath {
        ValidConfigs validConfig1 = ValidConfigs.getValidConfig1();
        int[] initialState = validConfig1.getInitialState();
        int[] finalState = validConfig1.getFinalState();
        String solutionPath = new BFS(initialState, finalState).getSolutionPath();
        Assertions.assertEquals("ULDRURDLULDRURULLDRRULD", solutionPath);
        Assertions.assertEquals(23, solutionPath.length());
    }

    @Test
    void bfsConfig2() throws Exceptions.InvalidPath {
        ValidConfigs validConfig2 = ValidConfigs.getValidConfig2();
        int[] initialState = validConfig2.getInitialState();
        int[] finalState = validConfig2.getFinalState();
        String solutionPath = new BFS(initialState, finalState).getSolutionPath();
        Assertions.assertEquals("DRRULLDDRUURDLLURRDLDR", solutionPath);
        Assertions.assertEquals(22, solutionPath.length());
    }
}
