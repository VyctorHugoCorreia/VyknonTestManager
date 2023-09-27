package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.dto.ProdutoDTO;

public interface ProdutoService {

    ProdutoEntity salvar(ProdutoDTO dto);

    ProdutoEntity editar(Long id, ProdutoDTO dto);


    String deletar(Long id);


}
