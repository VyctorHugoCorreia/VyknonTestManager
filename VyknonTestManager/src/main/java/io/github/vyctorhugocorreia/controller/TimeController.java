package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.dto.TeamDTO;
import io.github.vyctorhugocorreia.service.TeamService;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/time")
@AllArgsConstructor
@CrossOrigin
public class TimeController {

    final TeamService service;
    final TimeRepository repository;
    final CenarioDeTesteRepository cenarioDeTesteRepository;

    @GetMapping
    public List<TeamEntity> getTime(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) String nomeTime
    ) {

        List<TeamEntity> timeEntities = repository.searchTime(idTime, nomeTime);

        for (TeamEntity timeEntity : timeEntities) {
            int countCenarios = cenarioDeTesteRepository.countScenariosByTime(timeEntity);
            timeEntity.setQuantidadeCenarios(countCenarios);
        }

        return timeEntities;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamEntity save(@RequestBody @Valid TeamDTO dto) {
        return service.salvar(dto);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamEntity editar(@PathVariable Long id, @RequestBody @Valid TeamDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }


}
