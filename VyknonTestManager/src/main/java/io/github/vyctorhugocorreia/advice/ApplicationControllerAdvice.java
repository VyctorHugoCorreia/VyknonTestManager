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

    @ExceptionHandler(RuleBusinessException.class)
    @ResponseBody
    public ResponseEntity<List<String>> handleRuleBusinessException(RuleBusinessException ex) {
        return ResponseEntity.badRequest().body(new ApiErrors(ex.getMessage()).errors);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<String>> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(new ApiErrors(errors).errors);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleTeamNotFoundException(TeamNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }


    @ExceptionHandler(TestPlanNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleTestPlanoNotFoundException(TestPlanNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());

    }

     class ApiErrors {

        private final List<String> errors;

        public ApiErrors(List<String> errors) {
            this.errors = errors;
        }

        public ApiErrors(String errorMessage) {
            this.errors = Arrays.asList(errorMessage);
        }

     }
}