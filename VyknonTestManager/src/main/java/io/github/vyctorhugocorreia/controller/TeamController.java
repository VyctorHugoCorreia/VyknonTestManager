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
public class TeamController {

    final TeamService service;
    final TimeRepository repository;
    final CenarioDeTesteRepository scenarioRepository;

    @GetMapping
    public List<TeamEntity> getTime(
            @RequestParam(required = false) Long idTeam,
            @RequestParam(required = false) String nameTeam
    ) {

        List<TeamEntity> teamEntities = repository.searchTime(idTeam, nameTeam);

        for (TeamEntity teamEntity : teamEntities) {
            int scenarioQuantity = scenarioRepository.countScenariosByTime(teamEntity);
            teamEntity.setScenarioQuantity(scenarioQuantity);
        }

        return teamEntities;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamEntity save(@RequestBody @Valid TeamDTO dto) {
        return service.save(dto);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeamEntity edit(@PathVariable Long id, @RequestBody @Valid TeamDTO dto) {
        return service.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }


}
