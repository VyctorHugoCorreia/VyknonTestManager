package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.dto.ProdutoDTO;
import io.github.vyctorhugocorreia.dto.TagCenarioDTO;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TagCenarioEntity;
import io.github.vyctorhugocorreia.repository.StatusAutomatizadoRepository;
import io.github.vyctorhugocorreia.repository.TagCenarioRepository;
import io.github.vyctorhugocorreia.service.TagCenarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tagCenario")
@AllArgsConstructor
@CrossOrigin
public class TagCenarioController {

    final TagCenarioRepository repository;
    final TagCenarioService service;

    @PostMapping
    public ResponseEntity<TagCenarioEntity> save(@RequestBody @Valid TagCenarioDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }


    @GetMapping
    public List<TagCenarioEntity> getTag() {

        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagCenarioEntity> editar(@PathVariable Long id, @RequestBody @Valid TagCenarioDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }



}
