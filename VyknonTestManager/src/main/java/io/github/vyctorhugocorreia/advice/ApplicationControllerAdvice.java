package io.github.vyctorhugocorreia.advice;

import io.github.vyctorhugocorreia.exception.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseBody
    public ResponseEntity<List<String>> handleRegraNegocioException(RegraNegocioException ex) {
        return ResponseEntity.badRequest().body(new ApiErrors(ex.getMessage()).errors);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<String>> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ApiErrors(errors).errors);
    }

    @ExceptionHandler(TimeNaoEncontradoException.class)
    @ResponseBody
    public ResponseEntity<String> handleTimeNotFoundException(TimeNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(FuncionalidadeNaoEncontradaException.class)
    @ResponseBody
    public ResponseEntity<String> handleFuncionalidadeNotFoundException(FuncionalidadeNaoEncontradaException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(PlanoDeTestesNaoEncontradaException.class)
    @ResponseBody
    public ResponseEntity<String> handlePlanoNotFoundException(PlanoDeTestesNaoEncontradaException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseBody
    public ResponseEntity<String> handleProdutoNotFoundException(ProdutoNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());

    }

     class ApiErrors {

        private final List<String> errors;

        public ApiErrors(List<String> errors) {
            this.errors = errors;
        }

        public ApiErrors(String mensagemErro) {
            this.errors = Arrays.asList(mensagemErro);
        }

     }
}