package io.github.vyctorhugocorreia.exception;

public class PlanoDeTestesNaoEncontradaException extends RuntimeException {

    public PlanoDeTestesNaoEncontradaException() {
        super("Plano de testes não encontrada.");
    }

}
