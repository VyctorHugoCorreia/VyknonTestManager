package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.dto.SuiteDeTesteDTO;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.entity.SuiteDeTesteEntity;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.service.PlanoDeTestesService;
import io.github.vyctorhugocorreia.service.SuiteDeTesteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/suiteDeTeste")
@AllArgsConstructor
public class SuiteDeTesteController {

    private final SuiteDeTesteService service;
    private final SuiteDeTesteRepository repository;

    @PostMapping
    public ResponseEntity<SuiteDeTesteEntity> save(@RequestBody @Valid SuiteDeTesteDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<SuiteDeTesteEntity>> getSuite(
            @RequestParam(required = false) Long idSuite,
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) Long idPlano,
            @RequestParam(required = false) String descSuite
    ) {

        return ResponseEntity.ok(repository.searchSuite(idSuite, idTime, idProduto, idPlano, descSuite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuiteDeTesteEntity> editar(@PathVariable Long id, @RequestBody @Valid SuiteDeTesteDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }
}
