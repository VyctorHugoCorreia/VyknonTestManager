package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.ScenarioStatusEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.ScenarioStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/statusCenario")
@AllArgsConstructor
@CrossOrigin
public class ScenarioStatusController {

    final ScenarioStatusRepository repository;
    private final ScenarioRepository scenarioRepository;

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
