package io.github.VyctorHugoCorreia.domain.rest.controller;


import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.rest.dto.ProdutoDTO;
import io.github.VyctorHugoCorreia.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody ProdutoDTO dto) {
        ProdutoEntity produto = service.salvar(dto);
        return produto.getId();
    }
}
