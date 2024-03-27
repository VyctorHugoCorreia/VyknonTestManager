package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.ScenarioDTO;

import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.service.ScenarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cenarioDeTeste")
@AllArgsConstructor
@CrossOrigin
public class CenarioDeTesteController {

    private final ScenarioService service;
    private final ScenarioRepository repository;

    @PostMapping
    public ResponseEntity<ScenarioEntity> save(@RequestBody @Valid ScenarioDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatusCode.valueOf(201));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ScenarioEntity> edit(
            @PathVariable Long id,
            @RequestBody @Valid ScenarioDTO dto
    ) {
        return new ResponseEntity<>(service.edit(id, dto), HttpStatus.OK);
    }
    @GetMapping
    public List<ScenarioEntity> getTestCase(
            @RequestParam(required = false) Long idScenario,
            @RequestParam(required = false) String titleScenario,
            @RequestParam(required = false) Long idTeam,
               @RequestParam(required = false) Long idTestPlan,
            @RequestParam(required = false) Long idTestSuite,
            @RequestParam(required = false) Long idProduct,
            @RequestParam(required = false) Long idScenarioType,
            @RequestParam(required = false) Long idPlatformType,
            @RequestParam(required = false) Long idScenarioStatus,
            @RequestParam(required = false) Long idAutomationStatus,
            @RequestParam(required = false) String tags
    ) {

        return repository.searchCenario(idScenario, titleScenario,idTeam, idTestPlan, idTestSuite, idProduct,idScenarioType,idPlatformType,idScenarioStatus,idAutomationStatus,tags);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }

}
