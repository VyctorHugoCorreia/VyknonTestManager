package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TipoCenarioEntity;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import io.github.vyctorhugocorreia.repository.TipoCenarioRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/tipoCenario")
@CrossOrigin
public class TipoCenarioController {

    final TipoCenarioRepository repository;

    public TipoCenarioController(TipoCenarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TipoCenarioEntity> getStatus() {

        return repository.findAll();
    }



}
