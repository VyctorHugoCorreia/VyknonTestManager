package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.entity.PlatformTypeEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.TipoPlataformaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tipoPlataforma")
@AllArgsConstructor
@CrossOrigin
public class PlatformTypeController {

    final TipoPlataformaRepository repository;
     final CenarioDeTesteRepository scenarioRepository;


    @GetMapping
    public List<PlatformTypeEntity> getPlataforma(@RequestParam(required = false) TeamEntity idTeam
    ) {

        List<PlatformTypeEntity> platformTypeEntitiesList = repository.findAll();

        for (PlatformTypeEntity platformType : platformTypeEntitiesList) {
            int scenarioQuantity = scenarioRepository.countCenariosByPlataformType(platformType,idTeam);
            platformType.setScenarioQuantity(scenarioQuantity);
        }

        return repository.findAll();
    }



}
