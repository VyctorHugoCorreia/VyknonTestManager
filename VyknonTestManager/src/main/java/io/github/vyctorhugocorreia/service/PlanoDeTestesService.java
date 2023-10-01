package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.PlanoDeTestesDTO;
import io.github.vyctorhugocorreia.entity.PlanoDeTesteEntity;

public interface PlanoDeTestesService {

    PlanoDeTesteEntity salvar(PlanoDeTestesDTO dto);

    PlanoDeTesteEntity editar(Long id, PlanoDeTestesDTO dto);

    String deletar(Long id);


}
