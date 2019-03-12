package com.eightgamesolver.algorithms;

import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.ProcessInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class GREEDYTest {

    @Test
    void greedyConfig1() throws Exceptions.InvalidStates, FileNotFoundException, Exceptions.InvalidPath {
        File initialFile = new File("src/test/resources/validConfig");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        System.setIn(inputFromFile);
        ProcessInput processInput = new ProcessInput();
        int[] initialState = processInput.getInitialState();
        int[] finalState = processInput.getFinalState();
        GREEDY greedy = new GREEDY();
        String solutionPath = greedy.getSolutionPath(initialState, finalState);
        Assertions.assertEquals("LURULDRRDLULDRRULURDLLDRURDLLURRULLDRULDRURDLLURDRULDRULLDRRULDLURRDL", solutionPath);
        Assertions.assertEquals(69, solutionPath.length());
    }

    @Test
    void greedyConfig2() throws Exceptions.InvalidStates, FileNotFoundException, Exceptions.InvalidPath {
        File initialFile = new File("src/test/resources/validConfig2");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        System.setIn(inputFromFile);
        ProcessInput processInput = new ProcessInput();
        int[] initialState = processInput.getInitialState();
        int[] finalState = processInput.getFinalState();
        String solutionPath = new GREEDY().getSolutionPath(initialState, finalState);
        Assertions.assertEquals("DDRULDRRULLDRUULDRURDDLLUURDRULLDDRRULDLURRDLLUURDDLURULDDRR", solutionPath);
        Assertions.assertEquals(60, solutionPath.length());
    }
}
