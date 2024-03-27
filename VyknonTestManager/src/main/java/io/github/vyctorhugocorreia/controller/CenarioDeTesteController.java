package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.ScenarioDTO;

import io.github.vyctorhugocorreia.entity.*;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
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
    private final CenarioDeTesteRepository repository;

    @PostMapping
    public ResponseEntity<ScenarioEntity> save(@RequestBody @Valid ScenarioDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ScenarioEntity> update(
            @PathVariable Long id,
            @RequestBody @Valid ScenarioDTO dto
    ) {
        return new ResponseEntity<>(service.editar(id, dto), HttpStatus.OK);
    }
    @GetMapping
    public List<ScenarioEntity> getTestCase(
            @RequestParam(required = false) Long idCenario,
            @RequestParam(required = false) String tituloCenario,
            @RequestParam(required = false) Long idTime,
               @RequestParam(required = false) Long idPlano,
            @RequestParam(required = false) Long idSuite,
            @RequestParam(required = false) Long idTproduto,
            @RequestParam(required = false) Long idTpcenario,
            @RequestParam(required = false) Long idPlataforma,
            @RequestParam(required = false) Long idStatus,
            @RequestParam(required = false) Long idAutomatizado,
            @RequestParam(required = false) String tags
    ) {

        return repository.searchCenario(idCenario, tituloCenario,idTime, idPlano, idSuite, idTproduto,idTpcenario,idPlataforma,idStatus,idAutomatizado,tags);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }

}
