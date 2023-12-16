package io.github.vyctorhugocorreia.exception;

public class SuiteDeTesteNaoEncotradoException extends RuntimeException {

    public SuiteDeTesteNaoEncotradoException() {
        super("Suite de testes não encontrada.");
    }

}
