package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.CenarioDeTesteDTO;
import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.dto.StepDTO;
import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.entity.TipoCenarioEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.service.CenarioDeTesteService;
import io.github.vyctorhugocorreia.service.PlanoDeTestesService;
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

    @GetMapping
    public List<CenarioDeTesteEntity> getTestCase(
            @RequestParam(required = false) String idCenario,
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
