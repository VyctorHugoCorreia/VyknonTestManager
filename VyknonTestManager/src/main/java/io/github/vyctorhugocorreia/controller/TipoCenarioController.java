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

public class TipoCenarioController {

    final TipoCenarioRepository repository;
    final CenarioDeTesteRepository cenarioDeTesteRepository;



    @GetMapping
    public List<ScenarioTypeEntity> getStatus(
            @RequestParam(required = false) TeamEntity idTime
    ) {

        List<ScenarioTypeEntity> scenarioTypeEntityList = repository.findAll();

        for (ScenarioTypeEntity tipoCenario : scenarioTypeEntityList) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByScenarioType(tipoCenario,idTime);
            tipoCenario.setQuantidadeCenarios(quantidadeCenarios);
        }

        return scenarioTypeEntityList;
    }



}
