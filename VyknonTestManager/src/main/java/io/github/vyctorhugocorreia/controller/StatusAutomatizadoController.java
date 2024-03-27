package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.AutomationStatusEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/statusAutomatizado")
@AllArgsConstructor
@CrossOrigin
public class StatusAutomatizadoController {

    final StatusAutomatizadoRepository repository;
    final CenarioDeTesteRepository cenarioDeTesteRepository;


    @GetMapping
    public List<AutomationStatusEntity> getStatus(@RequestParam(required = false) TeamEntity idTime) {

        List<AutomationStatusEntity> status = repository.findAll();

        for (AutomationStatusEntity statusAutomatizado : status) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByAutomationStatus(statusAutomatizado,idTime);
            statusAutomatizado.setQuantidadeCenarios(quantidadeCenarios);
        }

        return status;
    }



}
