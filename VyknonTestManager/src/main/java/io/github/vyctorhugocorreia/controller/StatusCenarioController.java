package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.StatusCenarioEntity;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import io.github.vyctorhugocorreia.repository.StatusCenarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/statusCenario")
@CrossOrigin
public class StatusCenarioController {

    final StatusCenarioRepository repository;

    public StatusCenarioController(StatusCenarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<StatusCenarioEntity> getStatus() {

        return repository.findAll();
    }



}
