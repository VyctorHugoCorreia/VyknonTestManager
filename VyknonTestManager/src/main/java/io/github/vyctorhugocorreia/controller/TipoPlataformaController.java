package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TipoPlataformaEntity;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import io.github.vyctorhugocorreia.repository.TipoPlataformaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/tipoPlataforma")
@CrossOrigin
public class TipoPlataformaController {

    final TipoPlataformaRepository repository;

    public TipoPlataformaController(TipoPlataformaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TipoPlataformaEntity> getPlataforma() {

        return repository.findAll();
    }



}
