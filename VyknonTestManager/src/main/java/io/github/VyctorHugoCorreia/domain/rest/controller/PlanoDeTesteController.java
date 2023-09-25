package io.github.VyctorHugoCorreia.domain.rest.controller;


import io.github.VyctorHugoCorreia.domain.entity.PlanoDeTesteEntity;
import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.rest.dto.PlanoDeTesteDTO;
import io.github.VyctorHugoCorreia.rest.dto.ProdutoDTO;
import io.github.VyctorHugoCorreia.service.PlanoDeTesteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planoDeTeste")
public class PlanoDeTesteController {

    private PlanoDeTesteService service;

    public PlanoDeTesteController(PlanoDeTesteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PlanoDeTesteDTO dto) {
        PlanoDeTesteEntity planoDeTeste = service.salvar(dto);
        return planoDeTeste.getIdPlano();
    }
}
