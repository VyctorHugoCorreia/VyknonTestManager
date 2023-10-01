package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.FuncionalidadeDTO;
import io.github.vyctorhugocorreia.entity.FuncionalidadeEntity;

public interface FuncionalidadeService {

    FuncionalidadeEntity salvar(FuncionalidadeDTO dto);

    FuncionalidadeEntity editar(Long id, FuncionalidadeDTO dto);


    String deletar(Long id);


}
