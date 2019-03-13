package com.eightgamesolver.utils;

import com.eightgamesolver.exceptions.Exceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class ProcessInputTest {

    private static final String EXCEPTION_NOT_SOLVABLE = "With the given start an final state is impossible to solve this puzzle.";

    @Test
    void expectInvalidConfigFromInput() throws IOException {
        File initialFile = new File("src/test/resources/InvalidConfig");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        Assertions.assertThrows(Exceptions.class, () -> {
            System.setIn(inputFromFile);
            new ProcessInput();
        }, EXCEPTION_NOT_SOLVABLE);
        inputFromFile.close();
    }

    @Test
    void expectInvalidConfigFromParameters() {
        final int[] initialState = {8, 1, 2, 0, 4, 3, 7, 6, 5};
        final int[] finalState = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        Assertions.assertThrows(Exception.class, () -> new ProcessInput(initialState, finalState), EXCEPTION_NOT_SOLVABLE);
    }

}