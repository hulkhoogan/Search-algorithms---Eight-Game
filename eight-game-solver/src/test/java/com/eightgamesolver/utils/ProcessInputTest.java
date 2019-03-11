package com.eightgamesolver.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

class ProcessInputTest {

    private static final String EXCEPTION_NOT_SOLVABLE = "With the given start an final state is impossible to solve this puzzle.";

    @Test
    void expect_invalid_config_from_input() throws Exception {
        File initialFile = new File("src/test/resources/InvalidConfig");
        final InputStream inputFromFile = new FileInputStream(initialFile);
        Assertions.assertThrows(Exception.class, new Executable() {
            public void execute() throws Throwable {
                System.setIn(inputFromFile);
                new ProcessInput();
            }
        }, EXCEPTION_NOT_SOLVABLE);
    }

    @Test
    void expect_invalid_config_from_parameters() {
        final int[] initial_state = {8, 1, 2, 0, 4, 3, 7, 6, 5};
        final int[] final_state = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        Assertions.assertThrows(Exception.class, new Executable() {
            public void execute() throws Throwable {
                new ProcessInput(initial_state, final_state);
            }
        }, EXCEPTION_NOT_SOLVABLE);
    }

}