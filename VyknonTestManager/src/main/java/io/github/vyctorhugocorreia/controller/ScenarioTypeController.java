package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.entity.ScenarioTypeEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.ScenarioTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/scenario-type")
@CrossOrigin
@AllArgsConstructor

public class ScenarioTypeController {

    final ScenarioTypeRepository repository;
    final ScenarioRepository scenarioRepository;



    @GetMapping
    public List<ScenarioTypeEntity> getScenarioStatus(
            @RequestParam(required = false) TeamEntity idTeam
    ) {

        List<ScenarioTypeEntity> scenarioTypeEntityList = repository.findAll();

        for (ScenarioTypeEntity scenarioType : scenarioTypeEntityList) {
            int scenarioQuantity = scenarioRepository.countScenariosByScenarioType(scenarioType,idTeam);
            scenarioType.setScenarioQuantity(scenarioQuantity);
        }

        return scenarioTypeEntityList;
    }



}
