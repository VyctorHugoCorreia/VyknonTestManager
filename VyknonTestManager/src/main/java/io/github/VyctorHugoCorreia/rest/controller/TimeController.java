package io.github.VyctorHugoCorreia.rest.controller;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.rest.dto.TimeDTO;
import io.github.VyctorHugoCorreia.service.TimeService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/time")
public class TimeController {

    final TimeService service;
    final TimeRepository repository;

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimeEntity editar(@PathVariable Long id, @RequestBody @Valid TimeDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }


}
