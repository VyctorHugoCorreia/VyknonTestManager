package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.ProdutoDTO;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/produto")
@AllArgsConstructor
@CrossOrigin
public class ProdutoController {

    private final ProdutoService service;
    private final ProdutoRepository repository;
    private final CenarioDeTesteRepository cenarioDeTesteRepository;



    @PostMapping
    public ResponseEntity<ProdutoEntity> save(@RequestBody @Valid ProdutoDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> getProduto(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) String descProduto
    ) {

        List<ProdutoEntity> produtos = repository.searchProduto(idTime, idProduto, descProduto);

        for (ProdutoEntity produto : produtos) {
            int quantidadeCenarios = cenarioDeTesteRepository.countScenariosByProduto(produto);
            produto.setQuantidadeCenarios(quantidadeCenarios);
        }

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> editar(@PathVariable Long id, @RequestBody @Valid ProdutoDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }


}
