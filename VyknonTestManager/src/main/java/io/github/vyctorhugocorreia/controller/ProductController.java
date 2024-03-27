package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.ProductDTO;
import io.github.vyctorhugocorreia.entity.ProductEntity;
import io.github.vyctorhugocorreia.repository.ScenarioRepository;
import io.github.vyctorhugocorreia.repository.ProductRepository;
import io.github.vyctorhugocorreia.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService service;
    private final ProductRepository repository;
    private final ScenarioRepository scenarioRepository;



    @PostMapping
    public ResponseEntity<ProductEntity> save(@RequestBody @Valid ProductDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getProduct(
            @RequestParam(required = false) Long idTeam,
            @RequestParam(required = false) Long idProduct,
            @RequestParam(required = false) String descProduct
    ) {

        List<ProductEntity> products = repository.searchProduct(idTeam, idProduct, descProduct);

        for (ProductEntity product : products) {
            int scenarioQuantity = scenarioRepository.countScenariosByProduct(product);
            product.setScenarioQuantity(scenarioQuantity);
        }

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> edit(@PathVariable Long id, @RequestBody @Valid ProductDTO dto) {
        return ResponseEntity.ok(service.edit(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }


}
