package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.StatusCenarioEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.entity.TipoCenarioEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
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
    public List<TipoCenarioEntity> getStatus(
            @RequestParam(required = false) TimeEntity idTime
    ) {

        List<TipoCenarioEntity> tipoCenarioEntityList = repository.findAll();

        for (TipoCenarioEntity tipoCenario : tipoCenarioEntityList) {
            int quantidadeCenarios = cenarioDeTesteRepository.countCenariosByScenarioType(tipoCenario,idTime);
            tipoCenario.setQuantidadeCenarios(quantidadeCenarios);
        }

        return tipoCenarioEntityList;
    }



}
