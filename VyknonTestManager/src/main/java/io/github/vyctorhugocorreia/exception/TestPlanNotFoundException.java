package io.github.vyctorhugocorreia.exception;

public class TestPlanNotFoundException extends RuntimeException {

    public TestPlanNotFoundException() {
        super("Plano de testes não encontrada.");
    }

}
