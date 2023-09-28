package io.github.vyctorhugocorreia.advice;

import io.github.vyctorhugocorreia.exception.ProdutoNaoEncontradoException;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.exception.TimeNaoEncontradoException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiErrors> handleRegraNegocioException(RegraNegocioException ex) {
        return ResponseEntity.badRequest().body(new ApiErrors(ex.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ApiErrors(errors));
    }

    @ExceptionHandler(TimeNaoEncontradoException.class)
    public ResponseEntity<ApiErrors> handleTimeNotFoundException(TimeNaoEncontradoException ex) {
        return new ResponseEntity<>(new ApiErrors(ex.getMessage()), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ApiErrors> handleProdutoNotFoundException(ProdutoNaoEncontradoException ex) {
        return new ResponseEntity<>(new ApiErrors(ex.getMessage()), HttpStatusCode.valueOf(404));
    }

    private class ApiErrors {

        private List<String> errors;

        public ApiErrors(List<String> errors) {
            this.errors = errors;
        }

        public ApiErrors(String mensagemErro) {
            this.errors = Arrays.asList(mensagemErro);
        }
    }
}