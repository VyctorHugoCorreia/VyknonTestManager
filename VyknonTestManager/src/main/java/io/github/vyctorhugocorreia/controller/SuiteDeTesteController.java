package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.TestSuiteDTO;
import io.github.vyctorhugocorreia.entity.TestPlanEntity;
import io.github.vyctorhugocorreia.entity.testSuiteEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
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
public class SuiteDeTesteController {

    private final TestSuiteService service;
    private final SuiteDeTesteRepository repository;

    private final CenarioDeTesteRepository cenarioDeTesteRepository;

    @PostMapping
    public ResponseEntity<testSuiteEntity> save(@RequestBody @Valid TestSuiteDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }
    @GetMapping("/plano/{idPlano}")
    public ResponseEntity<List<testSuiteEntity>> getSuitesByPlanoId(@PathVariable Long idPlano) {
        TestPlanEntity plano = new TestPlanEntity();
        plano.setIdPlano(idPlano);

        List<testSuiteEntity> suites = repository.findBySuitesIdPlano(plano);

        if (suites.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(suites);
    }
    @GetMapping
    public ResponseEntity<List<testSuiteEntity>> getSuite(
            @RequestParam(required = false) Long idSuite,
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idTproduto,
            @RequestParam(required = false) Long idPlano,
            @RequestParam(required = false) String descSuite
    ) {


        List<testSuiteEntity> suiteEntities = repository.searchSuite(idSuite, idTime, idTproduto, idPlano, descSuite);

        for (testSuiteEntity suiteEntity : suiteEntities) {
            int countCenarios = cenarioDeTesteRepository.countCenariosBySuite(suiteEntity);
            suiteEntity.setQuantidadeCenarios(countCenarios);
        }

        return ResponseEntity.ok(suiteEntities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<testSuiteEntity> editar(@PathVariable Long id, @RequestBody @Valid TestSuiteDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }
}
