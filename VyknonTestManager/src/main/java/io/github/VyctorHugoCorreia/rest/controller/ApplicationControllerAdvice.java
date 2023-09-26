package io.github.VyctorHugoCorreia.rest.controller;

import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import io.github.VyctorHugoCorreia.exception.TimeNaoEncontradoException;
import io.github.VyctorHugoCorreia.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler({
            RegraNegocioException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(errors);
    }

    @ExceptionHandler(TimeNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleTimeNotFoundException( TimeNaoEncontradoException ex ){
        return new ApiErrors(ex.getMessage());
    }


}
