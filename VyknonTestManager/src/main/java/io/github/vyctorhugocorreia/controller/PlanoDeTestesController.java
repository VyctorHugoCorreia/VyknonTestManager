package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.repository.PlanoDeTestesRepository;
import io.github.vyctorhugocorreia.service.PlanoDeTestesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/planoDeTeste")
@AllArgsConstructor
public class PlanoDeTestesController {

    private final PlanoDeTestesService service;
    private final PlanoDeTestesRepository repository;

    @PostMapping
    public ResponseEntity<PlanoDeTesteEntity> save(@RequestBody @Valid PlanoDeTestesDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<PlanoDeTesteEntity>> getPlano(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) String descPlano
    ) {

        return ResponseEntity.ok(repository.searchPlano(idTime, idProduto, descPlano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDeTesteEntity> editar(@PathVariable Long id, @RequestBody @Valid PlanoDeTestesDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }


}
