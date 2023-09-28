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
    public ResponseEntity<List<String>> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ApiErrors(errors).errors);
    }

    @ExceptionHandler(TimeNaoEncontradoException.class)
    @ResponseBody
    public ResponseEntity<String> handleTimeNotFoundException(TimeNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<String> handleProdutoNotFoundException(ProdutoNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());

    }

     class ApiErrors {

        private List<String> errors;

        public ApiErrors(List<String> errors) {
            this.errors = errors;
        }

        public ApiErrors(String mensagemErro) {
            this.errors = Arrays.asList(mensagemErro);
        }

     }
}