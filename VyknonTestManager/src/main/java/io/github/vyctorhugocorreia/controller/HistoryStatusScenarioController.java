package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.HistoryStatusScenarioDTO;
import io.github.vyctorhugocorreia.entity.HistoryStatusScenarioEntity;
import io.github.vyctorhugocorreia.repository.HistoryStatusScenarioRepository;
import io.github.vyctorhugocorreia.service.HistoryStatusScenarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/historyStatusScenario")
@AllArgsConstructor
@CrossOrigin
public class HistoryStatusScenarioController {

    private final HistoryStatusScenarioService service;
    private final HistoryStatusScenarioRepository repository;

    @PostMapping
    public ResponseEntity<HistoryStatusScenarioEntity> save(@RequestBody @Valid HistoryStatusScenarioDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public List<HistoryStatusScenarioEntity> getHistory(
            @RequestParam(required = false) Long idCenario
    ) {

        return repository.findByIdScenario(idCenario);
    }

}
