package io.github.VyctorHugoCorreia.domain.rest.controller;

import io.github.VyctorHugoCorreia.domain.entity.TimeEntity;
import io.github.VyctorHugoCorreia.domain.repository.TimeRepository;
import io.github.VyctorHugoCorreia.exception.RegraNegocioException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/time")
public class TimeController {

    private TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping("/{id}")
    public TimeEntity getTimeById(@PathVariable Integer id) {
        return timeRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TimeEntity save(@RequestBody TimeEntity time) {
        return timeRepository.save(time);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTimeById(@PathVariable Integer id) {
        timeRepository.findById(id)
                .map(time -> {
                    timeRepository.delete(time);
                    return time;
                })
                .orElseThrow(() -> new RegraNegocioException("Time não encontrado"));


    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody TimeEntity time) {
        timeRepository.findById(id)
                .map(timeExistente -> {
                    time.setIdTime(timeExistente.getIdTime());
                    timeRepository.save(time);
                    return timeExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time não encontrado"));

    }

    @GetMapping
    public List<TimeEntity> find(TimeEntity filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.STARTING);

        Example example = Example.of(filtro, matcher);
        List<TimeEntity> lista = timeRepository.findAll(example);
        return timeRepository.findAll(example);


    }
}
