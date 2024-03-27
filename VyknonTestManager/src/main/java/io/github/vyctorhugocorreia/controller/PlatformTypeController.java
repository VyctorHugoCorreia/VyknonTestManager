package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.entity.PlatformTypeEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.PlatformTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/platform-type")
@AllArgsConstructor
@CrossOrigin
public class PlatformTypeController {

    final PlatformTypeRepository repository;
     final ScenarioRepository scenarioRepository;


    @GetMapping
    public List<PlatformTypeEntity> getPlatform(@RequestParam(required = false) TeamEntity idTeam
    ) {

        List<PlatformTypeEntity> platformTypeEntitiesList = repository.findAll();

        for (PlatformTypeEntity platformType : platformTypeEntitiesList) {
            int scenarioQuantity = scenarioRepository.countScenariosByPlataformType(platformType,idTeam);
            platformType.setScenarioQuantity(scenarioQuantity);
        }

        return repository.findAll();
    }



}
