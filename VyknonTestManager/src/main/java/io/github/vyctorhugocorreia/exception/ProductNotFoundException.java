package io.github.vyctorhugocorreia.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Produto não encontrado.");
    }

}
