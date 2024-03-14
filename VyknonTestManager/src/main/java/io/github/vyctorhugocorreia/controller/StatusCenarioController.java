package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusCenarioEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.StatusCenarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
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
    public List<StatusCenarioEntity> getStatus(@RequestParam(required = false) TimeEntity idTime) {

        List<StatusCenarioEntity> status = repository.findAll();

        for (StatusCenarioEntity statusCenario : status) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByScenarioStatus(statusCenario,idTime);
            statusCenario.setQuantidadeCenarios(quantidadeCenarios);
        }

        return status;
    }



}
