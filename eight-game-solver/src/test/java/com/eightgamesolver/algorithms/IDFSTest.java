package com.eightgamesolver.algorithms;

import com.eightgamesolver.exceptions.Exceptions;
import com.eightgamesolver.utils.ProcessInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class IDFSTest {

    @Test
    void idfsConfig1() throws Exceptions.InvalidStates, FileNotFoundException, Exceptions.InvalidPath {
        File initialFile = new File("src/test/resources/validConfig");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        System.setIn(inputFromFile);
        ProcessInput processInput = new ProcessInput();
        int[] initialState = processInput.getInitialState();
        int[] finalState = processInput.getFinalState();
        String solutionPath = new IDFS().getSolutionPath(initialState, finalState, 50);
        Assertions.assertEquals(
            "RULLDRRULLDRRULURDDLURDLUULDRRULLDRRDLULURDLURD",
            solutionPath);
        Assertions.assertEquals(47, solutionPath.length());
    }

    @Test
    void idfsConfig2() throws Exceptions.InvalidStates, FileNotFoundException, Exceptions.InvalidPath {
        File initialFile = new File("src/test/resources/validConfig3");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        System.setIn(inputFromFile);
        ProcessInput processInput = new ProcessInput();
        int[] initialState = processInput.getInitialState();
        int[] finalState = processInput.getFinalState();
        String solutionPath = new IDFS().getSolutionPath(initialState, finalState, 40);
        Assertions.assertEquals(
            "DDRRUL",
            solutionPath);
        Assertions.assertEquals(6, solutionPath.length());
    }
}
