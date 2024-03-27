package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.ProductDTO;
import io.github.vyctorhugocorreia.entity.ProductEntity;
import io.github.vyctorhugocorreia.repository.CenarioDeTesteRepository;
import io.github.vyctorhugocorreia.repository.ProdutoRepository;
import io.github.vyctorhugocorreia.service.ProductService;
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

    private final ProductService service;
    private final ProdutoRepository repository;
    private final CenarioDeTesteRepository cenarioDeTesteRepository;



    @PostMapping
    public ResponseEntity<ProductEntity> save(@RequestBody @Valid ProductDTO dto) {
        return new ResponseEntity<>(service.salvar(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProduto(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) String descProduto
    ) {

        List<ProductEntity> produtos = repository.searchProduto(idTime, idProduto, descProduto);

        for (ProductEntity produto : produtos) {
            int quantidadeCenarios = cenarioDeTesteRepository.countScenariosByProduto(produto);
            produto.setQuantidadeCenarios(quantidadeCenarios);
        }

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> editar(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletar(id));
    }


}
