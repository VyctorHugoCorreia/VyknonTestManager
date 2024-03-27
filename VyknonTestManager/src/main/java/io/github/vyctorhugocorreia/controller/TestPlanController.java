package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.TestPlanDTO;
import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.service.TestPlanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/planoDeTeste")
@AllArgsConstructor
@CrossOrigin
public class TestPlanController {

    private final TestPlanService service;
    private final PlanoDeTestesRepository repository;
    private final SuiteDeTesteRepository testSuiteRepository;

    private final CenarioDeTesteRepository scenarioRepository;

    @PostMapping
    public ResponseEntity<TestPlanEntity> save(@RequestBody @Valid TestPlanDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<TestPlanEntity>> getTestPlan(
            @RequestParam(required = false) Long idTestPlan,
            @RequestParam(required = false) Long idTeam,
            @RequestParam(required = false) Long idProduct,
            @RequestParam(required = false) String descTestPlan
    ) {
        List<TestPlanEntity> testPlanEntities = repository.searchPlano(idTestPlan,idTeam, idProduct, descTestPlan);

        for (TestPlanEntity plano : testPlanEntities) {
            int quantidadeSuites = testSuiteRepository.countSuitesByPlano(plano);
            plano.setTestSuiteQuantity(quantidadeSuites);
        }

        for (TestPlanEntity plano : testPlanEntities) {
            int quantidadeCenarios = scenarioRepository.countScenariosByTestPlan(plano);
            plano.setScenarioQuantity(quantidadeCenarios);
        }

        return ResponseEntity.ok(testPlanEntities);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TestPlanEntity> edit(@PathVariable Long id, @RequestBody @Valid TestPlanDTO dto) {
        return ResponseEntity.ok(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
