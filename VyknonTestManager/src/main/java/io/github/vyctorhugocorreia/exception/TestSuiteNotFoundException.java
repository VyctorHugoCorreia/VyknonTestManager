package io.github.vyctorhugocorreia.exception;

public class TestSuiteNotFoundException extends RuntimeException {

    public TestSuiteNotFoundException() {
        super("Suite de testes não encontrada.");
    }

}
