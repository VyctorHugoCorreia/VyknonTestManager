package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.CenarioDeTesteDTO;
import io.github.vyctorhugocorreia.dto.HistoryStatusScenarioDTO;
import io.github.vyctorhugocorreia.entity.CenarioDeTesteEntity;
import io.github.vyctorhugocorreia.entity.HistoryStatusScenarioEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.HistoryStatusScenarioRepository;
import io.github.vyctorhugocorreia.repository.SuiteDeTesteRepository;
import io.github.vyctorhugocorreia.service.CenarioDeTesteService;
import io.github.vyctorhugocorreia.service.HistoriyStatusScenarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/historyStatusScenario")
@AllArgsConstructor
@CrossOrigin
public class HistoryStatusScenarioController {

    private final HistoriyStatusScenarioService service;
    private final HistoryStatusScenarioRepository repository;

    @PostMapping
    public ResponseEntity<HistoryStatusScenarioEntity> save(@RequestBody @Valid HistoryStatusScenarioDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public List<HistoryStatusScenarioEntity> getHistory(
            @RequestParam(required = false) Long idCenario
    ) {

        return repository.findByCenarioId(idCenario);
    }

}
