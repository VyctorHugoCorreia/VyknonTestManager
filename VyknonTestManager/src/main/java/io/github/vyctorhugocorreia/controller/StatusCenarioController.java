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
public class StatusCenarioController {

    final StatusCenarioRepository repository;
    private final CenarioDeTesteRepository cenarioDeTesteRepository;

    @GetMapping
    public List<ScenarioStatusEntity> getStatus(@RequestParam(required = false) TeamEntity idTime) {

        List<ScenarioStatusEntity> status = repository.findAll();

        for (ScenarioStatusEntity statusCenario : status) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByScenarioStatus(statusCenario,idTime);
            statusCenario.setQuantidadeCenarios(quantidadeCenarios);
        }

        return status;
    }



}
