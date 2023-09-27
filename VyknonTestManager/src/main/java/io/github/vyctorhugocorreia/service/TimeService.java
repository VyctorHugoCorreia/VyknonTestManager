package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.entity.TimeEntity;
import io.github.vyctorhugocorreia.dto.TimeDTO;

public interface TimeService {

    TimeEntity salvar(TimeDTO dto);

    TimeEntity editar(Long id, TimeDTO dto);

    String deletar(Long id);

}
