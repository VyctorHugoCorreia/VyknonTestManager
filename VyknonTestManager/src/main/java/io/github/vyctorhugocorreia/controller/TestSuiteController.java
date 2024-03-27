package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.TestSuiteDTO;
import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.entity.testSuiteEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.TestSuiteRepository;
import io.github.vyctorhugocorreia.service.TestSuiteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/suiteDeTeste")
@AllArgsConstructor
@CrossOrigin
public class TestSuiteController {

    private final TestSuiteService service;
    private final TestSuiteRepository repository;

    private final ScenarioRepository scenarioRepository;

    @PostMapping
    public ResponseEntity<testSuiteEntity> save(@RequestBody @Valid TestSuiteDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatusCode.valueOf(201));
    }
    @GetMapping("/plano/{idPlano}")
    public ResponseEntity<List<testSuiteEntity>> getSuitesByPlanoId(@PathVariable Long idTestPlan) {
        TestPlanEntity testPlan = new TestPlanEntity();
        testPlan.setIdTestPlan(idTestPlan);

        List<testSuiteEntity> suites = repository.findBySuitesIdTestPlan(testPlan);

        if (suites.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(suites);
    }
    @GetMapping
    public ResponseEntity<List<testSuiteEntity>> getTestSuite(
            @RequestParam(required = false) Long idTestSuite,
            @RequestParam(required = false) Long idTeam,
            @RequestParam(required = false) Long idProduct,
            @RequestParam(required = false) Long idTestPlan,
            @RequestParam(required = false) String descTestSuite
    ) {


        List<testSuiteEntity> testSuiteEntities = repository.searchSuite(idTestSuite, idTeam, idProduct, idTestPlan, descTestSuite);

        for (testSuiteEntity testSuiteEntity : testSuiteEntities) {
            int scenarioQuantity = scenarioRepository.countCenariosByTestSuite(testSuiteEntity);
            testSuiteEntity.setScenarioQuantity(scenarioQuantity);
        }

        return ResponseEntity.ok(testSuiteEntities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<testSuiteEntity> edit(@PathVariable Long id, @RequestBody @Valid TestSuiteDTO dto) {
        return ResponseEntity.ok(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
