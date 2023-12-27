package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.CenarioDeTesteDTO;

import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.service.CenarioDeTesteService;
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

    private final CenarioDeTesteService service;
    private final CenarioDeTesteRepository repository;
    private final SuiteDeTesteRepository suiteRepository;
    @PostMapping
    public ResponseEntity<CenarioDeTesteEntity> save(@RequestBody @Valid CenarioDeTesteDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CenarioDeTesteEntity> update(
            @PathVariable Long id,
            @RequestBody @Valid CenarioDeTesteDTO dto
    ) {
        return new ResponseEntity<>(service.editar(id, dto), HttpStatus.OK);
    }
    @GetMapping
    public List<CenarioDeTesteEntity> getTestCase(
            @RequestParam(required = false) Long idCenario,
            @RequestParam(required = false) String tituloCenario
    ) {

        return repository.searchCenario(idCenario, tituloCenario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }

}
