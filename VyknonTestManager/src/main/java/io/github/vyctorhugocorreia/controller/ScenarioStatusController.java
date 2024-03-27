package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.ScenarioStatusEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.StatusCenarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/statusCenario")
@AllArgsConstructor
@CrossOrigin
public class ScenarioStatusController {

    final StatusCenarioRepository repository;
    private final CenarioDeTesteRepository scenarioRepository;

    @GetMapping
    public List<ScenarioStatusEntity> getScenarioStatus(@RequestParam(required = false) TeamEntity idTeam) {

        List<ScenarioStatusEntity> scenarioStatusEntities = repository.findAll();

        for (ScenarioStatusEntity scenarioStatus : scenarioStatusEntities) {
            int quantidadeCenarios = scenarioRepository.countCenariosByScenarioStatus(scenarioStatus,idTeam);
            scenarioStatus.setScenarioQuantity(quantidadeCenarios);
        }

        return scenarioStatusEntities;
    }



}
