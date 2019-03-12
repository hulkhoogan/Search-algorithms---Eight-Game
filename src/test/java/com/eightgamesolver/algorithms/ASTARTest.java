package com.eightgamesolver.algorithms;

import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.ProcessInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class ASTARTest {

    @Test
    void aStarConfig1() throws Exceptions.InvalidStates, FileNotFoundException, Exceptions.InvalidPath {
        File initialFile = new File("src/test/resources/validConfig");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        System.setIn(inputFromFile);
        ProcessInput processInput = new ProcessInput();
        int[] initialState = processInput.getInitialState();
        int[] finalState = processInput.getFinalState();
        String solutionPath = new ASTAR().getSolutionPath(initialState, finalState);
        Assertions.assertEquals("ULDRRULLDRRUULLDDRURULD", solutionPath);
        Assertions.assertEquals(23, solutionPath.length());
    }

    @Test
    void aStarConfig2() throws Exceptions.InvalidStates, FileNotFoundException, Exceptions.InvalidPath {
        File initialFile = new File("src/test/resources/validConfig2");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        System.setIn(inputFromFile);
        ProcessInput processInput = new ProcessInput();
        int[] initialState = processInput.getInitialState();
        int[] finalState = processInput.getFinalState();
        String solutionPath = new ASTAR().getSolutionPath(initialState, finalState);
        Assertions.assertEquals("RDLDRRULLDRUURDDLLURRD", solutionPath);
        Assertions.assertEquals(22, solutionPath.length());
    }
}
