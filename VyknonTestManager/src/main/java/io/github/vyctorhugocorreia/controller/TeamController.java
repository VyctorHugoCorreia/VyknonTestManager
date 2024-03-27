package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.TeamRepository;
import io.github.vyctorhugocorreia.dto.TeamDTO;
import io.github.vyctorhugocorreia.service.TeamService;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/team")
@AllArgsConstructor
@CrossOrigin
public class TeamController {

    final TeamService service;
    final TeamRepository repository;
    final ScenarioRepository scenarioRepository;

    @GetMapping
    public List<TeamEntity> getTeam(
            @RequestParam(required = false) Long idTeam,
            @RequestParam(required = false) String nameTeam
    ) {

        List<TeamEntity> teamEntities = repository.searchTeam(idTeam, nameTeam);

        for (TeamEntity teamEntity : teamEntities) {
            int scenarioQuantity = scenarioRepository.countScenariosByTeam(teamEntity);
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
