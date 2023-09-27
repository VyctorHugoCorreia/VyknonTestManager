package io.github.VyctorHugoCorreia.service;

import io.github.VyctorHugoCorreia.domain.entity.ProdutoEntity;
import io.github.VyctorHugoCorreia.rest.dto.ProdutoDTO;

public interface ProdutoService {

    ProdutoEntity salvar(ProdutoDTO dto);

    ProdutoEntity editar(Long id, ProdutoDTO dto);


    String deletar(Long id);


}