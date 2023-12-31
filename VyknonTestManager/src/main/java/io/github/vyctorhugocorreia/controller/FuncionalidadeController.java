package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.FuncionalidadeDTO;
import io.github.vyctorhugocorreia.entity.FuncionalidadeEntity;
import io.github.vyctorhugocorreia.repository.FuncionalidadeRepository;
import io.github.vyctorhugocorreia.service.FuncionalidadeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/funcionalidade")
@AllArgsConstructor
@CrossOrigin
public class FuncionalidadeController {

    private final FuncionalidadeService service;
    private final FuncionalidadeRepository repository;

    @PostMapping
    public ResponseEntity<FuncionalidadeEntity> save(@RequestBody @Valid FuncionalidadeDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<FuncionalidadeEntity>> getFuncionalidade(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) String descFuncionalidade
    ) {

        return ResponseEntity.ok(repository.searchFuncionalidade(idTime, idProduto, descFuncionalidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionalidadeEntity> editar(@PathVariable Long id, @RequestBody @Valid FuncionalidadeDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }


}
