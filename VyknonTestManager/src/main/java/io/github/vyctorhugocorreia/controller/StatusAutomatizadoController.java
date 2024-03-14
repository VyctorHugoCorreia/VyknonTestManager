package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
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
    public List<StatusAutomatizadoEntity> getStatus(@RequestParam(required = false) TimeEntity idTime) {

        List<StatusAutomatizadoEntity> status = repository.findAll();

        for (StatusAutomatizadoEntity statusAutomatizado : status) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByAutomationStatus(statusAutomatizado,idTime);
            statusAutomatizado.setQuantidadeCenarios(quantidadeCenarios);
        }

        return status;
    }



}
