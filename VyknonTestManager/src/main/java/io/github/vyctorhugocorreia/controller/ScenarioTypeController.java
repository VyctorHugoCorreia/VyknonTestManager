package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.entity.ScenarioTypeEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.TipoCenarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tipoCenario")
@CrossOrigin
@AllArgsConstructor

public class ScenarioTypeController {

    final TipoCenarioRepository repository;
    final CenarioDeTesteRepository scenarioRepository;



    @GetMapping
    public List<ScenarioTypeEntity> getStatus(
            @RequestParam(required = false) TeamEntity idTeam
    ) {

        List<ScenarioTypeEntity> scenarioTypeEntityList = repository.findAll();

        for (ScenarioTypeEntity scenarioType : scenarioTypeEntityList) {
            int scenarioQuantity = scenarioRepository.countCenariosByScenarioType(scenarioType,idTeam);
            scenarioType.setScenarioQuantity(scenarioQuantity);
        }

        return scenarioTypeEntityList;
    }



}
