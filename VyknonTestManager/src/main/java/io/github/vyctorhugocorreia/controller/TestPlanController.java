package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.TestPlanDTO;
import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.TestPlanRepository;
import io.github.vyctorhugocorreia.repository.TestSuiteRepository;
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
    private final TestPlanRepository repository;
    private final TestSuiteRepository testSuiteRepository;

    private final ScenarioRepository scenarioRepository;

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

        for (TestPlanEntity testPlan : testPlanEntities) {
            int testSuiteQuantity = testSuiteRepository.countSuitesByTestPlan(testPlan);
            testPlan.setTestSuiteQuantity(testSuiteQuantity);
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
