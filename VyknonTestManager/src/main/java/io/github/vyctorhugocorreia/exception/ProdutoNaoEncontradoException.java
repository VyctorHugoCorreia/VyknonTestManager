package io.github.vyctorhugocorreia.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado.");
    }

}
