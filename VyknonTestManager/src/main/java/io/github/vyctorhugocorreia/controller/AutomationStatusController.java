package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.AutomationStatusEntity;
import io.github.vyctorhugocorreia.entity.TeamEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.AutomationStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/automation-status")
@AllArgsConstructor
@CrossOrigin
public class AutomationStatusController {

    final AutomationStatusRepository repository;
    final ScenarioRepository scenarioRepository;


    @GetMapping
    public List<AutomationStatusEntity> getAutomationStatus(@RequestParam(required = false) TeamEntity idTeam) {

        List<AutomationStatusEntity> automationStatusEntities = repository.findAll();

        for (AutomationStatusEntity automationStatus : automationStatusEntities) {
            int scenarioQuantity = scenarioRepository.countScenariosByAutomationStatus(automationStatus,idTeam);
            automationStatus.setScenarioQuantity(scenarioQuantity);
        }

        return automationStatusEntities;
    }



}
