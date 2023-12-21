package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.dto.TimeDTO;
import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import io.github.vyctorhugocorreia.repository.TimeRepository;
import io.github.vyctorhugocorreia.service.TimeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/statusAutomatizado")
@CrossOrigin
public class StatusAutomatizadoController {

    final StatusAutomatizadoRepository repository;

    public StatusAutomatizadoController(StatusAutomatizadoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<StatusAutomatizadoEntity> getStatus() {

        return repository.findAll();
    }



}
