package io.github.VyctorHugoCorreia.rest.controller;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.TimeNaoEncontradoException;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;
import io.github.VyctorHugoCorreia.service.TimeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private TimeService service;
    private TimeRepository repository;

    public TimeController(TimeService service, TimeRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<TimeEntity> getTime(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) String nomeTime
    ) {

        return repository.searchTime(idTime, nomeTime);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeEntity save(@RequestBody @Valid TimeDTO dto) {
        return service.salvar(dto);

    }


}
