package com.eightgamesolver.algorithms;

import com.eightgamesolver.ValidConfigs;
import com.eightgamesolver.exceptions.Exceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GREEDYTest {

    @Test
    void greedyConfig1() throws Exceptions.InvalidPath {
        ValidConfigs validConfig1 = ValidConfigs.getValidConfig1();
        int[] initialState = validConfig1.getInitialState();
        int[] finalState = validConfig1.getFinalState();
        GREEDY greedy = new GREEDY(initialState, finalState);
        String solutionPath = greedy.getSolutionPath();
        Assertions.assertEquals("LURULDRRDLULDRRULURDLLDRURDLLURRULLDRULDRURDLLURDRULDRULLDRRULDLURRDL", solutionPath);
        Assertions.assertEquals(69, solutionPath.length());
    }

    @Test
    void greedyConfig2() throws Exceptions.InvalidPath {
        ValidConfigs validConfig2 = ValidConfigs.getValidConfig2();
        int[] initialState = validConfig2.getInitialState();
        int[] finalState = validConfig2.getFinalState();
        String solutionPath = new GREEDY(initialState, finalState).getSolutionPath();
        Assertions.assertEquals("DDRULDRRULLDRUULDRURDDLLUURDRULLDDRRULDLURRDLLUURDDLURULDDRR", solutionPath);
        Assertions.assertEquals(60, solutionPath.length());
    }
}
