package io.github.VyctorHugoCorreia.rest.controller;

import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.domain.repository.ProdutoRepository;
import io.github.VyctorHugoCorreia.rest.dto.ProdutoDTO;
import io.github.VyctorHugoCorreia.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    final ProdutoService service;
    final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository, ProdutoService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity save(@RequestBody @Valid ProdutoDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<ProdutoEntity> getProduto(
            @RequestParam(required = false) Long idTime,
            @RequestParam(required = false) Long idProduto,
            @RequestParam(required = false) String descProduto
    ) {

        return repository.searchProduto(idTime, idProduto, descProduto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoEntity editar(@PathVariable Long id, @RequestBody @Valid ProdutoDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }


}
